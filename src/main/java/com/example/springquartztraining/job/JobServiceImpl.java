package com.example.springquartztraining.job;

import com.example.springquartztraining.exception.LanDTException;
import com.example.springquartztraining.trigger.TriggerDTO;
import com.example.springquartztraining.utils.ExeceptionUtils;
import com.example.springquartztraining.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.quartz.JobBuilder.newJob;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
  private final Scheduler scheduler;

  @Override
  public void createJob(JobDetailsDTO dto)
      throws LanDTException, SchedulerException, ClassNotFoundException {
    if (scheduler.checkExists(JobKey.jobKey(dto.getJobName(), dto.getJobGroup())))
      throw new LanDTException(
          ExeceptionUtils.JOB_ALREADY_EXIST,
          String.format(
              ExeceptionUtils.message.get(ExeceptionUtils.JOB_ALREADY_EXIST),
              dto.getJobName(),
              dto.getJobGroup()));
    Class<? extends Job> type = (Class<? extends Job>) Class.forName(dto.getClassType());
    JobDetail jobDetail =
        newJob(type)
            .withIdentity(dto.getJobName(), dto.getJobGroup())
            .withDescription(dto.getDescription())
            .storeDurably()
            .build();
    scheduler.addJob(jobDetail, false);
  }

  @Override
  public List<JobDetailsDTO> getAllJobs() throws SchedulerException {
    List<JobDetailsDTO> dtos = new ArrayList<>();
    for (String jobGroup : scheduler.getJobGroupNames()) {
      for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(jobGroup))) {
        JobDetailsDTO dto = new JobDetailsDTO();
        dto.setJobGroup(jobGroup);
        dto.setJobName(jobKey.getName());
        dto.setDescription(scheduler.getJobDetail(jobKey).getDescription());
        dto.setClassType(scheduler.getJobDetail(jobKey).getJobClass().getName());
        dtos.add(dto);
      }
    }
    return dtos;
  }
}

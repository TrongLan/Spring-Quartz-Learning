package com.example.springquartztraining.trigger;

import com.example.springquartztraining.exception.LanDTException;
import com.example.springquartztraining.utils.ExeceptionUtils;
import com.example.springquartztraining.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class TriggerServiceImpl implements TriggerService {
  private final Scheduler scheduler;
  private final ModelMapper modelMapper;

  @Override
  public void scheduleJob(TriggerDTO triggerDTO) throws SchedulerException, LanDTException {
    if (!scheduler.checkExists(JobKey.jobKey(triggerDTO.getJobName(), triggerDTO.getJobGroup())))
      throw new LanDTException(
          ExeceptionUtils.JOB_NOT_EXIST,
          String.format(
              ExeceptionUtils.message.get(ExeceptionUtils.JOB_NOT_EXIST),
              triggerDTO.getJobName(),
              triggerDTO.getJobGroup()));

    if (scheduler.checkExists(
        TriggerKey.triggerKey(triggerDTO.getTriggerName(), triggerDTO.getTriggerGroup())))
      throw new LanDTException(
          ExeceptionUtils.TRIGGER_ALREADY_EXIST,
          String.format(
              ExeceptionUtils.message.get(ExeceptionUtils.TRIGGER_ALREADY_EXIST),
              triggerDTO.getJobName(),
              triggerDTO.getJobGroup()));

    JobDetail jobDetail =
        scheduler.getJobDetail(JobKey.jobKey(triggerDTO.getJobName(), triggerDTO.getJobGroup()));
    Trigger trigger =
        TriggerBuilder.newTrigger()
            .forJob(jobDetail)
            .withIdentity(triggerDTO.getTriggerName(), triggerDTO.getTriggerGroup())
            .withSchedule(
                CronScheduleBuilder.cronSchedule(
                    Utils.toCronExpression(triggerDTO.getScheduleRequest()).toString()))
            .build();
    scheduler.scheduleJob(trigger);
  }

  @Override
  public void pauseTrigger(String name, String group) throws LanDTException, SchedulerException {
    if (!scheduler.checkExists(TriggerKey.triggerKey(name, group)))
      throw new LanDTException(
          ExeceptionUtils.TRIGGER_NOT_EXIST,
          String.format(
              ExeceptionUtils.message.get(ExeceptionUtils.TRIGGER_NOT_EXIST), name, group));
    scheduler.pauseTrigger(TriggerKey.triggerKey(name, group));
  }

  @Override
  public List<? extends Trigger> getAllTriggersOfJob(String jobName, String jobGroup)
      throws SchedulerException, LanDTException {
    if (!scheduler.checkExists(JobKey.jobKey(jobName, jobGroup)))
      throw new LanDTException(
          ExeceptionUtils.JOB_NOT_EXIST,
          String.format(
              ExeceptionUtils.message.get(ExeceptionUtils.JOB_NOT_EXIST), jobName, jobGroup));
    return scheduler.getTriggersOfJob(JobKey.jobKey(jobName, jobGroup));
  }

  @Override
  public void deleteTrigger(String name, String group) throws SchedulerException, LanDTException {
    if (!scheduler.checkExists(TriggerKey.triggerKey(name, group)))
      throw new LanDTException(
          ExeceptionUtils.TRIGGER_NOT_EXIST,
          String.format(
              ExeceptionUtils.message.get(ExeceptionUtils.TRIGGER_NOT_EXIST), name, group));
    scheduler.unscheduleJob(TriggerKey.triggerKey(name, group));
  }
}

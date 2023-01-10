package com.example.springquartztraining.trigger;

import com.example.springquartztraining.exception.LanDTException;
import com.example.springquartztraining.utils.ExceptionUtils;
import com.example.springquartztraining.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class TriggerServiceImpl implements TriggerService {
  private final Scheduler scheduler;

  @Override
  public void scheduleJob(TriggerDTO triggerDTO) throws SchedulerException, LanDTException {
    if (!scheduler.checkExists(JobKey.jobKey(triggerDTO.getJobName(), triggerDTO.getJobGroup())))
      throw new LanDTException(
          ExceptionUtils.JOB_NOT_EXIST,
          String.format(
              ExceptionUtils.message.get(ExceptionUtils.JOB_NOT_EXIST),
              triggerDTO.getJobName(),
              triggerDTO.getJobGroup()));

    if (scheduler.checkExists(
        TriggerKey.triggerKey(triggerDTO.getTriggerName(), triggerDTO.getTriggerGroup())))
      throw new LanDTException(
          ExceptionUtils.TRIGGER_ALREADY_EXIST,
          String.format(
              ExceptionUtils.message.get(ExceptionUtils.TRIGGER_ALREADY_EXIST),
              triggerDTO.getJobName(),
              triggerDTO.getJobGroup()));

    JobDetail jobDetail =
        scheduler.getJobDetail(JobKey.jobKey(triggerDTO.getJobName(), triggerDTO.getJobGroup()));

    String cronExpression = Utils.toCronExpression(triggerDTO.getScheduleRequest()).toString();
    if (!CronExpression.isValidExpression(cronExpression))
      throw new LanDTException(
          ExceptionUtils.SCHEDULE_REQUEST_INVALID,
          ExceptionUtils.message.get(ExceptionUtils.SCHEDULE_REQUEST_INVALID));
    Trigger trigger =
        TriggerBuilder.newTrigger()
            .forJob(jobDetail)
            .withIdentity(triggerDTO.getTriggerName(), triggerDTO.getTriggerGroup())
            .withDescription(triggerDTO.getDescription())
            .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
            .build();
    scheduler.scheduleJob(trigger);
  }

  @Override
  public void pauseTrigger(String name, String group) throws LanDTException, SchedulerException {
    if (!scheduler.checkExists(TriggerKey.triggerKey(name, group)))
      throw new LanDTException(
          ExceptionUtils.TRIGGER_NOT_EXIST,
          String.format(ExceptionUtils.message.get(ExceptionUtils.TRIGGER_NOT_EXIST), name, group));
    scheduler.pauseTrigger(TriggerKey.triggerKey(name, group));
  }

  @Override
  public void resumeTrigger(String name, String group) throws SchedulerException, LanDTException {
    if (!scheduler.checkExists(TriggerKey.triggerKey(name, group)))
      throw new LanDTException(
          ExceptionUtils.TRIGGER_NOT_EXIST,
          String.format(ExceptionUtils.message.get(ExceptionUtils.TRIGGER_NOT_EXIST), name, group));
    scheduler.resumeTrigger(TriggerKey.triggerKey(name, group));
  }

  @Override
  public List<TriggerDetailsDTO> getAllTriggersOfJob(String jobName, String jobGroup)
      throws SchedulerException, LanDTException {
    if (!scheduler.checkExists(JobKey.jobKey(jobName, jobGroup)))
      throw new LanDTException(
          ExceptionUtils.JOB_NOT_EXIST,
          String.format(
              ExceptionUtils.message.get(ExceptionUtils.JOB_NOT_EXIST), jobName, jobGroup));
    List<? extends Trigger> triggersOfJob =
        scheduler.getTriggersOfJob(JobKey.jobKey(jobName, jobGroup));
    List<TriggerDetailsDTO> dtos = new ArrayList<>();
    boolean fail = false;
    for (Trigger trigger : triggersOfJob) {
      TriggerDetailsDTO triggerDetailsDTO = new TriggerDetailsDTO(trigger);
      try {
        triggerDetailsDTO.setStatus(scheduler.getTriggerState(trigger.getKey()).name());
      } catch (SchedulerException e) {
        fail = true;
        break;
      }
      dtos.add(triggerDetailsDTO);
    }
    if (fail)
      throw new LanDTException(
          ExceptionUtils.MAPPING_DATA_ERROR,
          ExceptionUtils.message.get(ExceptionUtils.MAPPING_DATA_ERROR));
    else return dtos;
  }

  @Override
  public void deleteTrigger(String name, String group) throws SchedulerException, LanDTException {
    if (!scheduler.checkExists(TriggerKey.triggerKey(name, group)))
      throw new LanDTException(
          ExceptionUtils.TRIGGER_NOT_EXIST,
          String.format(ExceptionUtils.message.get(ExceptionUtils.TRIGGER_NOT_EXIST), name, group));
    scheduler.unscheduleJob(TriggerKey.triggerKey(name, group));
  }
}

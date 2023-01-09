package com.example.springquartztraining.trigger;

import com.example.springquartztraining.exception.LanDTException;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import java.util.List;

public interface TriggerService {
  void scheduleJob(TriggerDTO dto) throws SchedulerException, LanDTException;

  void pauseTrigger(String triggerName, String triggerGroup)
      throws SchedulerException, LanDTException;

  List<? extends Trigger> getAllTriggersOfJob(String jobName, String jobGroup)
      throws SchedulerException, LanDTException;

  void deleteTrigger(String triggerName, String triggerGroup)
      throws SchedulerException, LanDTException;
}

package com.example.springquartztraining.trigger;

import com.example.springquartztraining.exception.LanDTException;
import org.quartz.SchedulerException;

import java.util.List;

public interface TriggerService {
  void scheduleJob(TriggerDTO dto) throws SchedulerException, LanDTException;

  void pauseTrigger(String triggerName, String triggerGroup)
      throws SchedulerException, LanDTException;

  void resumeTrigger(String triggerName, String triggerGroup)
      throws SchedulerException, LanDTException;

  List<TriggerDetailsDTO> getAllTriggersOfJob(String jobName, String jobGroup)
      throws SchedulerException, LanDTException;

  void deleteTrigger(String triggerName, String triggerGroup)
      throws SchedulerException, LanDTException;
}

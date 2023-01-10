package com.example.springquartztraining.trigger;

import com.example.springquartztraining.cron.ScheduleRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TriggerDTO {
  private String triggerGroup;
  private String triggerName;
  private String jobName;
  private String jobGroup;
  private String description;
  private ScheduleRequest scheduleRequest;
}

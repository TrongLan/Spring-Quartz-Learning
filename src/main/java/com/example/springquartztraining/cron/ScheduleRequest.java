package com.example.springquartztraining.cron;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleRequest {
  private CronField second;
  private CronField minute;
  private CronField hour;
  private CronField day;
  private CronField month;
  private CronField year;
}

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
  private CronField dayOfMonth;
  private CronField month;
  private CronField dayOfWeek;
  private CronField year;
}

package com.example.springquartztraining.cron;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MyCronExpression {
  private String second;
  private String minute;
  private String hour;
  private String dayOfMonth;
  private String month;
  private String dayOfWeek;
  private String year;

  @Override
  public String toString() {
    return String.format(
        "%s %s %s %s %s %s %s",
        this.second,
        this.minute,
        this.hour,
        this.dayOfMonth,
        this.month,
        this.dayOfWeek,
        this.year);
  }
}

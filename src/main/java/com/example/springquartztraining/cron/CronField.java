package com.example.springquartztraining.cron;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CronField {
  private String type;
  private int baseValue;
  private int fromValue;
  private int toValue;
  private int increaseValue;
  private List<Integer> specificValues;

  private String dayOf;

  public String toCronFormat() {
    String cron = "";
    if (type.equals(CronFieldFormat.NO_SPECIFIC_VALUE)
        || type.equals(CronFieldFormat.ANY_VALUE)
        || type.equals(CronFieldFormat.LAST_VALUE)
        || type.equals(CronFieldFormat.LAST_WEEKDAY)) cron = CronFieldFormat.type_form.get(type);
    if (type.equals(CronFieldFormat.RANGE_VALUE))
      cron = String.format(CronFieldFormat.type_form.get(type), fromValue, toValue);
    if (type.equals(CronFieldFormat.SPECIFIC_VALUES)) {
      if (specificValues.size() == 1) cron = specificValues.get(0).toString();
      else cron = specificValues.stream().map(String::valueOf).collect(Collectors.joining(","));
    }
    if (type.equals(CronFieldFormat.INCREMENTAL_VALUE))
      cron =
          String.format(
              CronFieldFormat.type_form.get(type),
              fromValue,
              increaseValue);
    if (type.equals(CronFieldFormat.DAY_LAST))
      cron = String.format(CronFieldFormat.type_form.get(type), baseValue);
    if (type.equals(CronFieldFormat.BEFORE_LAST))
      cron = String.format(CronFieldFormat.type_form.get(type), baseValue);
    if (type.equals(CronFieldFormat.NEAREST_WEEKDAY))
      cron = String.format(CronFieldFormat.type_form.get(type), baseValue);

    return cron;
  }
}

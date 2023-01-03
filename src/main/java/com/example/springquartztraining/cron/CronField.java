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
  private int fromValue;
  private int toValue;
  private int increaseValue;
  private List<Integer> specificValues;

  public String toCronFormat() {
    String cron = "";
    if (type.equals(CronFieldFormat.NO_SPECIFIC_VALUE) || type.equals(CronFieldFormat.ANY_VALUE))
      cron = CronFieldFormat.type_form.get(type);
    if (type.equals(CronFieldFormat.RANGE_VALUE))
      cron = String.format(CronFieldFormat.type_form.get(type), fromValue, toValue);
    if (type.equals(CronFieldFormat.SPECIFIC_VALUES))
      cron = specificValues.stream().map(String::valueOf).collect(Collectors.joining(","));
    if ((type.equals(CronFieldFormat.SINGLE_VALUE))) cron = specificValues.get(0).toString();
    if (type.equals(CronFieldFormat.INCREMENTAL_VALUE))
      cron =
          String.format(
              CronFieldFormat.type_form.get(CronFieldFormat.INCREMENTAL_VALUE),
              fromValue,
              increaseValue);

    return cron;
  }
}

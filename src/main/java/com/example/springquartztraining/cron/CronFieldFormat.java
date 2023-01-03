package com.example.springquartztraining.cron;

import java.util.HashMap;
import java.util.Map;

public class CronFieldFormat {
  public static final String ANY_VALUE = "ANY_VALUE";
  public static final String NO_SPECIFIC_VALUE = "NO_SPECIFIC_VALUE";
  public static final String INCREMENTAL_VALUE = "INCREMENTAL_VALUE";
  public static final String RANGE_VALUE = "RANGE_VALUE";
  public static final String SPECIFIC_VALUES = "SPECIFIC_VALUES";
  public static final String SINGLE_VALUE = "SINGLE_VALUE";
  public static Map<String, String> type_form;
  static {
    type_form = new HashMap<>();
    type_form.put(CronFieldFormat.ANY_VALUE, "*");
    type_form.put(CronFieldFormat.NO_SPECIFIC_VALUE, "?");
    type_form.put(CronFieldFormat.INCREMENTAL_VALUE, "%s/%s");
    type_form.put(CronFieldFormat.RANGE_VALUE, "%s-%s");
  }
}

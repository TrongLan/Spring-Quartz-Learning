package com.example.springquartztraining.cron;

import java.util.HashMap;
import java.util.Map;

public class CronFieldFormat {
  public static final String ANY_VALUE = "ANY_VALUE";  // mọi giá trị
  public static final String NO_SPECIFIC_VALUE = "NO_SPECIFIC_VALUE"; // ko quan tâm
  public static final String INCREMENTAL_VALUE = "INCREMENTAL_VALUE"; // lặp
  public static final String RANGE_VALUE = "RANGE_VALUE"; // khoảng giá trị
  public static final String SPECIFIC_VALUES = "SPECIFIC_VALUES"; // giá trị cụ thể
  public static final String LAST_VALUE = "LAST_VALUE"; // giá trị cuối
  public static final String BEFORE_LAST = "BEFORE_LAST"; // giá trị cuối trước giá trị cuối của trường
  public static final String DAY_LAST = "DAY_LAST"; // giá trị day of week cuối cùng trong tháng
  public static final String NEAREST_WEEKDAY = "NEAREST_WEEKDAY"; // ngày trong tuần gần nhất
  public static final String LAST_WEEKDAY = "LAST_WEEKDAY"; // ngày trong tuần cuối cùng của tháng
  public static final String WEEK = "WEEK";
  public static final String MONTH = "MONTH";
  public static final Map<String, String> type_form;
  static {
    type_form = new HashMap<>();
    type_form.put(CronFieldFormat.ANY_VALUE, "*");
    type_form.put(CronFieldFormat.NO_SPECIFIC_VALUE, "?");
    type_form.put(CronFieldFormat.INCREMENTAL_VALUE, "%s/%s");
    type_form.put(CronFieldFormat.RANGE_VALUE, "%s-%s");
    type_form.put(CronFieldFormat.LAST_VALUE,"L");
    type_form.put(CronFieldFormat.BEFORE_LAST,"L-%s");
    type_form.put(CronFieldFormat.DAY_LAST,"%sL");
    type_form.put(CronFieldFormat.NEAREST_WEEKDAY,"%sW");
    type_form.put(CronFieldFormat.LAST_WEEKDAY,"LW");
  }
  private CronFieldFormat(){
    throw new IllegalStateException("CronFieldFormat class");
  }

}

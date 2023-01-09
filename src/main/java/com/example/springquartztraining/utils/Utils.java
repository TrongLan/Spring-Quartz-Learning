package com.example.springquartztraining.utils;

import com.example.springquartztraining.cron.CronFieldFormat;
import com.example.springquartztraining.cron.MyCronExpression;
import com.example.springquartztraining.cron.ScheduleRequest;

public class Utils {
  public static MyCronExpression toCronExpression (ScheduleRequest scheduleRequest){
    MyCronExpression myCronExpression = new MyCronExpression();
    myCronExpression.setSecond(scheduleRequest.getSecond().toCronFormat());
    myCronExpression.setMinute(scheduleRequest.getMinute().toCronFormat());
    myCronExpression.setHour(scheduleRequest.getHour().toCronFormat());
    myCronExpression.setMonth(scheduleRequest.getMonth().toCronFormat());
    myCronExpression.setYear(scheduleRequest.getYear().toCronFormat());

    String dom = "";
    String dow = "";
    if (scheduleRequest.getDay().getDayOf().equals(CronFieldFormat.WEEK)) {
      dom = CronFieldFormat.type_form.get(CronFieldFormat.NO_SPECIFIC_VALUE);
      dow = scheduleRequest.getDay().toCronFormat();
    }
    if (scheduleRequest.getDay().getDayOf().equals(CronFieldFormat.MONTH)) {
      dow = CronFieldFormat.type_form.get(CronFieldFormat.NO_SPECIFIC_VALUE);
      dom = scheduleRequest.getDay().toCronFormat();
    }
    myCronExpression.setDayOfMonth(dom);
    myCronExpression.setDayOfWeek(dow);
    return myCronExpression;
  }
}

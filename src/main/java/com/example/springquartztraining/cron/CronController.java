package com.example.springquartztraining.cron;

import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/cron")
public class CronController {

  @GetMapping("/generate")
  public ResponseEntity<Object> cronExpressionGenerate(
      @RequestBody ScheduleRequest scheduleRequest) {
    String cronString = toCronExpression(scheduleRequest).toString();

    // Kiểm tra cron là hợp lệ
    if (CronExpression.isValidExpression(cronString))
      return new ResponseEntity<>(cronString, HttpStatus.OK);
    else return new ResponseEntity<>("Invalid schedule request!", HttpStatus.BAD_REQUEST);
  }

  /**
   *
   * @param scheduleRequest yêu cầu đặt lịch
   * @return đối tượng MyCronExpression
   */
  private MyCronExpression toCronExpression(ScheduleRequest scheduleRequest) {
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
    if (scheduleRequest.getDay().getDayOf().equals(CronFieldFormat.MONTH)){
      dow = CronFieldFormat.type_form.get(CronFieldFormat.NO_SPECIFIC_VALUE);
      dom = scheduleRequest.getDay().toCronFormat();
    }
    myCronExpression.setDayOfMonth(dom);
    myCronExpression.setDayOfWeek(dow);
    return myCronExpression;
  }
}

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
    String cronString =
        String.format(
            "%s %s %s %s %s %s %s",
            scheduleRequest.getSecond().toCronFormat(),
            scheduleRequest.getMinute().toCronFormat(),
            scheduleRequest.getHour().toCronFormat(),
            scheduleRequest.getDayOfMonth().toCronFormat(),
            scheduleRequest.getMonth().toCronFormat(),
            scheduleRequest.getDayOfWeek().toCronFormat(),
            scheduleRequest.getYear().toCronFormat());

    if (CronExpression.isValidExpression(cronString))
      return new ResponseEntity<>(cronString, HttpStatus.OK);
    else return new ResponseEntity<>("Invalid schedule request!", HttpStatus.BAD_REQUEST);
  }
}

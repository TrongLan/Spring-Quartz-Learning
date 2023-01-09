package com.example.springquartztraining.cron;

import com.example.springquartztraining.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/cron")
public class CronController {

  @PostMapping("/generate")
  public ResponseEntity<Object> cronExpressionGenerate(
      @RequestBody ScheduleRequest scheduleRequest) {
    String cronString = Utils.toCronExpression(scheduleRequest).toString();

    // Kiểm tra cron là hợp lệ
    if (CronExpression.isValidExpression(cronString))
      return new ResponseEntity<>(cronString, HttpStatus.OK);
    else return new ResponseEntity<>("Invalid schedule request!", HttpStatus.BAD_REQUEST);
  }
}

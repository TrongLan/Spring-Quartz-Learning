package com.example.springquartztraining.jobcontrol;

import com.example.springquartztraining.account.AccountService;
import com.example.springquartztraining.account.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@RequiredArgsConstructor
@RequestMapping("/job-control")
@RestController
public class JobController {
  private final Scheduler scheduler;
  private final AccountService accountService;

  @GetMapping(value = "/{jobName}")
  public ResponseEntity<Object> scheduleJob(
      @RequestParam(required = false) String scheduled, @PathVariable String jobName) {
    List<String> cmds = Arrays.stream(new String[] {"ON", "OFF"}).toList();
    if (!cmds.contains(scheduled))
      return new ResponseEntity<>("Invalid request!", HttpStatus.BAD_REQUEST);
    try {
      if (!scheduler.isStarted() || scheduler.isShutdown()) scheduler.start();
      JobDataMap jobDataMap = new JobDataMap();
      jobDataMap.put("account-info-list", accountService.getAllAccounts());
      JobDetail jobDetail =
          newJob(AccountServiceImpl.class).withIdentity(jobName).setJobData(jobDataMap).build();
      Trigger trigger =
          newTrigger()
              .forJob(jobName)
              .withSchedule(cronSchedule("*/5 * * ? * *"))
              .build();
//      CronTrigger cronTrigger = newTrigger().forJob(jobDetail).withSchedule(new MyCronExpression("0 0 0 * * *").)
      if (scheduled.equals("ON")) {
        if (!scheduler.checkExists(JobKey.jobKey(jobName)))
          scheduler.scheduleJob(jobDetail, trigger);
      }
      if (scheduled.equals("OFF")) scheduler.pauseJob(JobKey.jobKey(jobName));

    } catch (SchedulerException e) {
      throw new RuntimeException(e);
    }
    return new ResponseEntity<>("Job scheduled!", HttpStatus.OK);
  }
}

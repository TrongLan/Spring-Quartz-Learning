//package com.example.springquartztraining.test;
//
//import com.example.springquartztraining.account.Account;
//import org.quartz.*;
//import org.quartz.impl.StdSchedulerFactory;
//
//import static org.quartz.JobBuilder.newJob;
//import static org.quartz.TriggerBuilder.newTrigger;
//
//public class QuartzTest {
//  public static void main(String[] args) throws SchedulerException {
//    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//    scheduler.start();
//    JobDetail jobDetail = newJob(Account.class).withIdentity("acc_counting").build();
//    SimpleTrigger trigger = newTrigger()
//            .withIdentity("acc_counting_trigger")
//            .startNow()
//            .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
//            .build();
//
//    scheduler.scheduleJob(jobDetail, trigger);
//  }
//}

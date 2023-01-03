package com.example.springquartztraining.account;

import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;

import java.util.List;

public interface AccountService {
  Long count() throws SchedulerException;
  List<AccountInfo> getAllAccounts();
}

package com.example.springquartztraining.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService, Job {
  private final AccountRepository accountRepository;
  private final ModelMapper modelMapper;

  @Override
  public Long count() {
    return accountRepository.count();
  }

  @Override
  public List<AccountInfo> getAllAccounts() {
    return accountRepository.findAll().stream()
        .map(account -> modelMapper.map(account, AccountInfo.class))
        .toList();
  }

  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    //    Trigger trigger = jobExecutionContext.getTrigger();
    //    log.info(
    //        String.format(
    //            "Job name: %s, Execute time: %s",
    //            trigger.getJobKey().getName(), trigger.getPreviousFireTime().toString()));

    //    JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
    //    List<AccountInfo> accountInfos = (List<AccountInfo>)
    // mergedJobDataMap.get("account-info-list");
    //    accountInfos.forEach(accountInfo -> log.info(accountInfo.toString()));
    //    log.info("---------------------------------------------------------");

    log.info(
        "Job executed at {}",
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
  }
}

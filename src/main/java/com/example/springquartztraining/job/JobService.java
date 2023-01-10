package com.example.springquartztraining.job;

import com.example.springquartztraining.exception.LanDTException;
import org.quartz.SchedulerException;

import java.util.List;

public interface JobService {
  void createJob(JobDetailsDTO jobDetailsDTO)
      throws LanDTException, SchedulerException, ClassNotFoundException;

  List<JobDetailsDTO> getAllJobs() throws SchedulerException;

  void deleteJob(String name, String group) throws LanDTException, SchedulerException;

  void pauseJob(String name, String group) throws LanDTException, SchedulerException;

  // viết 2 api này
  // test cả luồng nghiệp vụ
  // tìm hiểu cách set repeat count cho job
}

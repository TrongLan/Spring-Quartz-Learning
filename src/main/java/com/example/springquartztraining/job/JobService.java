package com.example.springquartztraining.job;

import com.example.springquartztraining.exception.LanDTException;
import com.example.springquartztraining.trigger.TriggerDTO;
import org.quartz.SchedulerException;

import java.util.List;

public interface JobService {
  void createJob (JobDetailsDTO jobDetailsDTO) throws LanDTException, SchedulerException, ClassNotFoundException;
  List<JobDetailsDTO> getAllJobs () throws SchedulerException;
}

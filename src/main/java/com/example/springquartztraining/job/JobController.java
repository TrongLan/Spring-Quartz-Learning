package com.example.springquartztraining.job;

import com.example.springquartztraining.exception.LanDTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/job-control")
@RestController
@Slf4j
public class JobController {
  private final JobService jobService;

  @GetMapping(value = "/all-job")
  public ResponseEntity<Object> getAllJobInfo() {
    try {
      return new ResponseEntity<>(jobService.getAllJobs(), HttpStatus.OK);
    } catch (SchedulerException e) {
      log.error(e.getMessage());
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> createJob(@RequestBody JobDetailsDTO dto) {
    try {
      jobService.createJob(dto);
    } catch (LanDTException e) {
      return new ResponseEntity<>(e.errorMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      log.error(e.getMessage());
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping(value = "/pause-job/{name}/{group}")
  public ResponseEntity<Object> pauseJob(@PathVariable String name, @PathVariable String group) {
    try {
      jobService.pauseJob(name, group);
    } catch (LanDTException e) {
      return new ResponseEntity<>(e.errorMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      log.error(e.getMessage());
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping(value = "/delete-job/{name}/{group}")
  public ResponseEntity<Object> deleteJob(@PathVariable String name, @PathVariable String group) {
    try {
      jobService.deleteJob(name, group);
    } catch (LanDTException e) {
      return new ResponseEntity<>(e.errorMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      log.error(e.getMessage());
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

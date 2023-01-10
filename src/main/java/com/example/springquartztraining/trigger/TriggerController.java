package com.example.springquartztraining.trigger;

import com.example.springquartztraining.exception.LanDTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/trigger-control")
public class TriggerController {
  private final TriggerService triggerService;

  @PostMapping(value = "/schedule-job")
  public ResponseEntity<Object> scheduleJob(@RequestBody TriggerDTO triggerDTO) {
    try {
      triggerService.scheduleJob(triggerDTO);
    } catch (LanDTException e) {
      return new ResponseEntity<>(e.errorMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping(value = "/pause-trigger/{name}/{group}")
  public ResponseEntity<Object> pauseTrigger(
      @PathVariable String name, @PathVariable String group) {
    try {
      triggerService.pauseTrigger(name, group);
    } catch (LanDTException e) {

      return new ResponseEntity<>(e.errorMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }
  @GetMapping(value = "/resume-trigger/{name}/{group}")
  public ResponseEntity<Object> resumeTrigger(
      @PathVariable String name, @PathVariable String group) {
    try {
      triggerService.resumeTrigger(name, group);
    } catch (LanDTException e) {

      return new ResponseEntity<>(e.errorMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping(value = "/triggers-of-job/{name}/{group}")
  public ResponseEntity<Object> getAllTriggersOfJob(
      @PathVariable String name, @PathVariable String group) {
    try {
      return new ResponseEntity<>(triggerService.getAllTriggersOfJob(name, group), HttpStatus.OK);
    } catch (LanDTException e) {
      return new ResponseEntity<>(e.errorMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      log.error(e.getMessage());
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping(value = "/{name}/{group}")
  public ResponseEntity<Object> deleteTriggerOfJobs(@PathVariable String name, @PathVariable String group) {
    try{
      triggerService.deleteTrigger(name, group);
    } catch (LanDTException e){
      return new ResponseEntity<>(e.errorMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e){
      log.error(e.getMessage());
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

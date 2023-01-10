package com.example.springquartztraining.trigger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.quartz.Trigger;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TriggerDetailsDTO {
  private String name;
  private String group;
  private String description;
  private LocalDateTime startTime;
  private LocalDateTime previousFireTime;
  private LocalDateTime nextFireTime;
  private LocalDateTime finalFireTime;
  private String status;

  public TriggerDetailsDTO(Trigger trigger) {
    this.name = trigger.getKey().getName();
    this.group = trigger.getKey().getGroup();
    this.description = trigger.getDescription();
    this.previousFireTime = toLocalDateTime(trigger.getPreviousFireTime());
    this.nextFireTime = toLocalDateTime(trigger.getNextFireTime());
    this.startTime = toLocalDateTime(trigger.getStartTime());
    this.finalFireTime = toLocalDateTime(trigger.getFinalFireTime());
  }

  private LocalDateTime toLocalDateTime(Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
  }
}

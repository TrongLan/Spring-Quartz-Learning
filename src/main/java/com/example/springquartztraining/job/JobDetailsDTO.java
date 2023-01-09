package com.example.springquartztraining.job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobDetailsDTO {
  private String jobGroup;
  private String jobName;
  private String description;
  private String classType;
}

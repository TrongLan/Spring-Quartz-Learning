package com.example.springquartztraining.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LanDTException extends Exception {
  private String code;
  private String message;

  public Map<String, String> errorMessage() {
    Map<String, String> err = new HashMap<>();
    err.put("code", this.code);
    err.put("message", this.message);
    return err;
  }
}

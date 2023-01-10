package com.example.springquartztraining.exception;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@Setter
public class LanDTException extends Exception {
  private final String code;
  private final String message;

  public Map<String, String> errorMessage() {
    Map<String, String> err = new HashMap<>();
    err.put("code", this.code);
    err.put("message", this.message);
    return err;
  }
}

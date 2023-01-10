package com.example.springquartztraining.utils;

import java.util.HashMap;
import java.util.Map;

public class ExceptionUtils {
  // Common exception
  public static final String MAPPING_DATA_ERROR = "MAPPING_DATA_ERROR";
  // Job exception
  public static final String JOB_ALREADY_EXIST = "JOB_ALREADY_EXIST";
  public static final String JOB_NAME_REQUIRED = "JOB_NAME_REQUIRED";
  public static final String JOB_NOT_EXIST = "JOB_NOT_EXIST";

  // Trigger exception
  public static final String TRIGGER_ALREADY_EXIST = "TRIGGER_ALREADY_EXIST";
  public static final String TRIGGER_NAME_REQUIRED = "TRIGGER_NAME_REQUIRED";
  public static final String TRIGGER_NOT_EXIST = "TRIGGER_NOT_EXIST";
  public static final String SCHEDULE_REQUEST_INVALID = "SCHEDULE_REQUEST_INVALID";

  public static final Map<String, String> message;

  static {
    message = new HashMap<>();
    // Common error
    message.put(MAPPING_DATA_ERROR, "Lỗi lấy dữ liệu hiển thị");
    // Job
    message.put(JOB_ALREADY_EXIST, "Đã tồn tại Job với định danh [Name: %s, Group: %s].");
    message.put(JOB_NAME_REQUIRED, "Không để trống tên Job.");
    message.put(JOB_NOT_EXIST, "Không tồn tại Job với định danh [Name: %s, Group: %s].");

    // Trigger
    message.put(TRIGGER_ALREADY_EXIST, "Đã tồn tại Trigger với định danh [Name: %s, Group: %s].");
    message.put(TRIGGER_NOT_EXIST, "Không tồn tại Trigger với định danh [Name: %s, Group: %s].");
    message.put(TRIGGER_NAME_REQUIRED, "Không để trống tên Trigger.");
    message.put(
        SCHEDULE_REQUEST_INVALID, "Yêu cầu lập lịch không hợp lệ, xem cron expression: %s.");
  }

  private ExceptionUtils() {
    throw new IllegalStateException("Utility class");
  }
}

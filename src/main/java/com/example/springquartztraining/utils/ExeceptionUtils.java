package com.example.springquartztraining.utils;

import java.util.HashMap;
import java.util.Map;

public class ExeceptionUtils {
  // Job exception
  public static String JOB_ALREADY_EXIST = "JOB_ALREADY_EXIST";
  public static String JOB_NAME_REQUIRED = "JOB_NAME_REQUIRED";
  public static String JOB_NOT_EXIST = "JOB_NOT_EXIST";
//  public static String JOB_ALREADY_EXIST = "JOB_ALREADY_EXIST";

  // Trigger exception
  public static String TRIGGER_ALREADY_EXIST = "TRIGGER_ALREADY_EXIST";
  public static String TRIGGER_NAME_REQUIRED = "TRIGGER_NAME_REQUIRED";
  public static String TRIGGER_NOT_EXIST = "TRIGGER_NOT_EXIST";
//  public static String JOB_ALREADY_EXIST = "JOB_ALREADY_EXIST";

  public static Map<String, String> message;

  static {
    message = new HashMap<>();
    // Job
    message.put(JOB_ALREADY_EXIST, "Đã tồn tại Job với định danh [Name: %s, Group: %s]");
    message.put(JOB_NAME_REQUIRED, "Không để trống tên Job");
    message.put(JOB_NOT_EXIST, "Không tồn tại Job với định danh [Name: %s, Group: %s]");

    // Trigger
    message.put(TRIGGER_ALREADY_EXIST, "Đã tồn tại Trigger với định danh [Name: %s, Group: %s]");
    message.put(TRIGGER_NOT_EXIST, "Không tồn tại Trigger với định danh [Name: %s, Group: %s]");
    message.put(TRIGGER_NAME_REQUIRED, "Không để trống tên Trigger");

  }
}

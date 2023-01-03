package com.example.springquartztraining.account;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AccountInfo implements Serializable {
  private String username;
  private String email;
}

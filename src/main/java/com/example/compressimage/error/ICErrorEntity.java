package com.example.compressimage.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class ICErrorEntity {
  private String userMessage;
  private int errorCode;
}

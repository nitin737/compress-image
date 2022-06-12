package com.tools.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ICErrorEntity {
  private String userMessage;
  private int errorCode;
}

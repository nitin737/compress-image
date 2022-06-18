package com.tools.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Slf4j
@Component
@PropertySource("classpath:messages.properties")
public class ImageEntityBuilder {

  @Value("${IMAGE_FILE_EXTENSION_NOT_SUPPORTED_EXCEPTION}")
  private String fileExtensionNotSupportedException;

  @Value("${IMAGE_FILE_NOT_FOUND_EXCEPTION}")
  private String imageFileNotFoundException;

  @Value("${IMAGE_COMPRESSION_QUALITY_NOT_FOUND_EXCEPTION}")
  private String imageCompressionQualityNotFoundException;

  private HashMap<String, ICErrorEntity> errorCodeMap;

  @PostConstruct
  public void getErrorCodes() {
    errorCodeMap = new HashMap<>();
    errorCodeMap.put(
        ErrorCodes.IMAGE_FILE_EXTENSION_NOT_SUPPORTED,
        ICErrorEntity.builder()
            .errorCode(Integer.valueOf(ErrorCodes.IMAGE_FILE_EXTENSION_NOT_SUPPORTED))
            .userMessage(fileExtensionNotSupportedException)
            .build());
    errorCodeMap.put(
        ErrorCodes.IMAGE_FILE_NOT_FOUND,
        ICErrorEntity.builder()
            .errorCode(Integer.valueOf(ErrorCodes.IMAGE_FILE_NOT_FOUND))
            .userMessage(imageFileNotFoundException)
            .build());
    errorCodeMap.put(
        ErrorCodes.IMAGE_COMPRESSION_QUALITY_NOT_FOUND,
        ICErrorEntity.builder()
            .errorCode(Integer.valueOf(ErrorCodes.IMAGE_COMPRESSION_QUALITY_NOT_FOUND))
            .userMessage(imageCompressionQualityNotFoundException)
            .build());
  }

  public ICErrorEntity getErrorEntity(String errorCode) {
    return errorCodeMap.get(errorCode);
  }
}

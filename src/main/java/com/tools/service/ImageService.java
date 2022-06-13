package com.tools.service;

import com.tools.exception.ICException;
import com.tools.model.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
  public ImageResponse compressImage(MultipartFile multipartFile, float CompressionQuality)
      throws IOException, ICException;
}

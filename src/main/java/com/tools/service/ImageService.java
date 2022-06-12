package com.tools.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
  public String compressImage(MultipartFile multipartFile, int CompressionQuality)
      throws IOException;
}

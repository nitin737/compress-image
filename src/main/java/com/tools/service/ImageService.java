package com.tools.service;

import com.tools.exception.ICException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
  public String compressImage(MultipartFile multipartFile, float CompressionQuality)
          throws IOException, ICException;
}

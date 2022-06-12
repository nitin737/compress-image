package com.tools.service;

import com.tools.algo.ImageCompressionAlgo;
import com.tools.exception.ICException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
  private ImageCompressionAlgo imageCompressionAlgo;

  public String compressImage(MultipartFile multipartFile, float compressionQuality)
          throws IOException, ICException {
    return imageCompressionAlgo.compressAlgoV1(multipartFile, compressionQuality);
  }
}

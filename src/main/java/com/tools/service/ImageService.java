package com.tools.service;

import com.tools.exception.ICException;
import com.tools.model.ImageResponse;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public ImageResponse compressImage(MultipartFile multipartFile, float CompressionQuality)
            throws IOException, ICException;
}

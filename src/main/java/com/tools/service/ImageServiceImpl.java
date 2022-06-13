package com.tools.service;

import com.tools.algo.ImageCompressionAlgo;
import com.tools.exception.ICException;
import com.tools.model.ImageResponse;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    private ImageCompressionAlgo imageCompressionAlgo;

    public ImageResponse compressImage(MultipartFile multipartFile, float compressionQuality)
            throws IOException, ICException {
        return imageCompressionAlgo.compressAlgoV1(multipartFile, compressionQuality);
    }
}

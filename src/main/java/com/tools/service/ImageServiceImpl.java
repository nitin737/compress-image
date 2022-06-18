package com.tools.service;

import com.tools.algo.compress.ImageCompressionAlgo;
import com.tools.algo.format.ImageConverterAlgo;
import com.tools.error.ErrorCodes;
import com.tools.exception.ICException;
import com.tools.imagetype.Image;
import com.tools.model.ImageResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.google.common.io.Files.getFileExtension;

@AllArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    private ImageCompressionAlgo imageCompressionAlgo;
    private ImageConverterAlgo imageConverterAlgo;

    public ImageResponse compressImage(MultipartFile multipartFile, float compressionQuality)
            throws IOException, ICException {
        if (!Image.contains(getFileExtension(multipartFile.getOriginalFilename()))) {
            throw new ICException(ErrorCodes.IMAGE_FILE_EXTENSION_NOT_SUPPORTED);
        }

        return imageCompressionAlgo.compressAlgoV1(multipartFile, compressionQuality);
    }

    @Override
    public ImageResponse convertImageFormat(MultipartFile image, String format)
            throws ICException, IOException {
        return imageConverterAlgo.convertFormat(image, format);
    }
}

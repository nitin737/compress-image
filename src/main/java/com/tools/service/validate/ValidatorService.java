package com.tools.service.validate;

import com.tools.exception.ICException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ValidatorService {
    public void validateRequestParam(MultipartFile multipartFile, float quality)
            throws ICException {
        if (multipartFile == null) {
            throw new ICException("Image file not attached.");
        }
        if (quality < 0 || quality > 1) {
            throw new ICException("Image Compression Quality is not correct");
        }
    }
}

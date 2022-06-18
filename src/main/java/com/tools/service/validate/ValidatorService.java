package com.tools.service.validate;

import com.tools.error.ErrorCodes;
import com.tools.exception.ICException;
import com.tools.imagetype.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import static com.google.common.io.Files.getFileExtension;

@Service
public class ValidatorService {
    public void validateImageAndQualityRequestParam(MultipartFile multipartFile, float quality)
            throws ICException {
        validateMultipartFile(multipartFile);
        if (quality < 0 || quality > 1) {
            throw new ICException(ErrorCodes.IMAGE_COMPRESSION_QUALITY_NOT_FOUND);
        }
    }

    private void validateMultipartFile(MultipartFile multipartFile) throws ICException {
        if (multipartFile == null) {
            throw new ICException(ErrorCodes.IMAGE_FILE_NOT_FOUND);
        }
    }

    public void validateImageAndFormatRequestParam(MultipartFile image, String format)
            throws ICException {
        validateMultipartFile(image);
        if (!Image.contains(format)) {
            throw new ICException(ErrorCodes.IMAGE_CONVERSION_FORMAT_IS_NOT_SUPPORTED);
        }
        if (getFileExtension(image.getOriginalFilename()).equals(format)) {
            throw new ICException(
                    ErrorCodes.OLD_FILE_EXTENSTION_AND_NEW_FILE_EXTENSION_CANNOT_SAME);
        }
    }
}

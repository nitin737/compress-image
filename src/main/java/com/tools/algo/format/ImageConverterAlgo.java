package com.tools.algo.format;

import com.tools.error.ErrorCodes;
import com.tools.exception.ICException;
import com.tools.model.ImageResponse;
import com.tools.utils.ImageFileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class ImageConverterAlgo {

    public ImageResponse convertFormat(MultipartFile multipartFile, String format)
            throws ICException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            BufferedImage input = ImageIO.read(multipartFile.getInputStream());
            ImageIO.write(input, format, baos);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ICException(ErrorCodes.FAILED_TO_CONVERT_IMAGE_FORMAT);
        } finally {
            assert baos != null;

            baos.close();
        }
        return ImageResponse.builder()
                .bytes(baos.toByteArray())
                .fileName(
                        ImageFileUtils.createNewFileName(
                                multipartFile.getOriginalFilename(), format))
                .imageSize(baos.size())
                .build();
    }
}

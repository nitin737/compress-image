package com.tools.algo;

import com.google.common.io.Files;
import com.tools.exception.ICException;
import com.tools.model.ImageResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

@Component
public class ImageCompressionAlgo {

    public ImageResponse compressAlgoV1(MultipartFile imageFile, float cQuality)
            throws ICException, IOException {
        OutputStream os = null;
        ImageWriter writer = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageWriteParam param;
        String compressedImageName = getNewFileName(imageFile);
        try {
            BufferedImage oldImage = ImageIO.read(imageFile.getInputStream());

            writer =
                    ImageIO.getImageWritersByFormatName(
                                    Files.getFileExtension(imageFile.getOriginalFilename()))
                            .next();

            writer.setOutput(ImageIO.createImageOutputStream(baos));

            param = setImageWriteParam(cQuality, writer);
            writer.write(null, new IIOImage(oldImage, null, null), param);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ICException("Image Compression failed", Arrays.toString(e.getStackTrace()));
        } finally {
            assert writer != null;
            assert baos != null;

            writer.dispose();
            baos.close();
        }

        return ImageResponse.builder()
                .bytes(baos.toByteArray())
                .fileName(compressedImageName)
                .imageSize(baos.toByteArray().length)
                .build();
    }

    private String getNewFileName(MultipartFile imageFile) {
        return Files.getNameWithoutExtension(imageFile.getOriginalFilename())
                + "-compressed."
                + Files.getFileExtension(imageFile.getOriginalFilename());
    }

    private ImageWriteParam setImageWriteParam(float compressionQuality, ImageWriter writer)
            throws IOException {
        ImageWriteParam param = writer.getDefaultWriteParam();
        if (param.canWriteCompressed()) {
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(compressionQuality); // Change the quality value you prefer
        }
        return param;
    }
}

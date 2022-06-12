package com.tools.algo;

import com.tools.exception.ICException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class ImageCompressionAlgo {
  public String compressAlgoV1(MultipartFile imageFile, float compressionQuality)
      throws IOException, ICException {
    OutputStream os = null;
    ImageOutputStream ios = null;
    ImageWriter writer = null;
    String compressedImageName =
        extractFileName(imageFile.getOriginalFilename())
            + "-compressed."
            + extractFileExtenstion(imageFile.getOriginalFilename());
    try {
      BufferedImage oldImage = ImageIO.read(imageFile.getInputStream());
      File compressedImage = new File(compressedImageName);
      os = new FileOutputStream(compressedImage);

      writer =
          ImageIO.getImageWritersByFormatName(
                  extractFileExtenstion(imageFile.getOriginalFilename()))
              .next();

      ios = ImageIO.createImageOutputStream(os);
      writer.setOutput(ios);

      compressAndWriteToDisk(compressionQuality, writer, oldImage);
    } catch (IOException e) {
      e.printStackTrace();
      throw new IOException("Image Compression failed");
    } finally {
      os.close();
      ios.close();
      writer.dispose();
    }

    return compressedImageName;
  }

  private void compressAndWriteToDisk(
      float compressionQuality, ImageWriter writer, BufferedImage oldImage) throws IOException {
    ImageWriteParam param = writer.getDefaultWriteParam();
    if (param.canWriteCompressed()) {
      param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
      // Change the quality value you prefer
      param.setCompressionQuality(compressionQuality);
    }
    writer.write(null, new IIOImage(oldImage, null, null), param);
  }

  private String generateCommpressedFileName(String filePrefix, String filePostfix) {
    return filePrefix + "-compressed" + filePostfix;
  }

  private String extractFileExtenstion(String originalFilename) {
    return originalFilename.substring(originalFilename.indexOf(".") + 1);
  }

  private String extractFileName(String originalFilename) {
    return originalFilename.substring(0, originalFilename.indexOf("."));
  }
}

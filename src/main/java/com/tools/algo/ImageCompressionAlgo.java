package com.tools.algo;

import org.springframework.beans.factory.annotation.Autowired;
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
  public String compressAlgoV1(MultipartFile imageFile, int compressionQuality) throws IOException {
    OutputStream os = null;
    ImageOutputStream ios = null;
    ImageWriter writer = null;
    String newFileName = extractFileName(imageFile.getOriginalFilename());
    String compressedImagePath =
        "/tmp/" + newFileName + findFileExtension(imageFile.getOriginalFilename());
    try {
      BufferedImage image = ImageIO.read(imageFile.getInputStream());
      File compressedImageFile = new File(compressedImagePath);
      os = new FileOutputStream(compressedImageFile);

      writer =
          ImageIO.getImageWritersByFormatName(findFileExtension(imageFile.getOriginalFilename()))
              .next();

      ios = ImageIO.createImageOutputStream(os);
      writer.setOutput(ios);

      ImageWriteParam param = writer.getDefaultWriteParam();
      if (param.canWriteCompressed()) {
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(compressionQuality); // Change the quality value you prefer
      }
      writer.write(null, new IIOImage(image, null, null), param);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      os.close();
      ios.close();
      writer.dispose();
    }

    return newFileName;
  }

  private String generateCommpressedFileName(String filePrefix, String filePostfix) {
    return filePrefix + "-compressed" + filePostfix;
  }

  private String findFileExtension(String originalFilename) {
    return originalFilename.substring(originalFilename.indexOf(".") + 1);
  }

  private String extractFileName(String originalFilename) {
    return originalFilename.substring(0, originalFilename.indexOf("."));
  }
}

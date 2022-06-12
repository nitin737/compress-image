package com.tools.controller;

import com.tools.service.ImageService;
import com.tools.service.validate.ValidatorService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/image")
public class ImageController {
  private ImageService imageCompressService;
  private ValidatorService validatorService;

  @PostMapping(
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public ResponseEntity compress(
      @RequestParam("image") MultipartFile image, @RequestParam("quality") float quality)
      throws Exception {
    String compressedImageName = null;
    validatorService.validateRequestParam(image, quality);
    compressedImageName = imageCompressService.compressImage(image, quality);
    return ResponseEntity.ok().body("Compression Started");
  }

  @GetMapping(produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
  public ResponseEntity<Resource> getCompressedFile() throws IOException {
    String fileName = "compressedImage.png";
    File file = new File("photo-compressed.jpg");
    byte[] bytes = new FileInputStream(file).readAllBytes();
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
        .contentType(MediaType.parseMediaType("application/octet-stream"))
        .body(new ByteArrayResource(bytes));
  }

  private void compressAlgoSecond(MultipartFile imageFile) {
    // BufferedImage img = Scalr.resize(ImageIO.read(src), 500);
  }
}

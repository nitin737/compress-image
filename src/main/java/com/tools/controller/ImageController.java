package com.tools.controller;

import com.tools.service.ImageService;
import com.tools.service.validate.ValidatorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/image")
public class ImageController {
  private ImageService imageCompressService;
  private ValidatorService validatorService;

  @PostMapping(
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity compress(
      @RequestParam("image") MultipartFile image, @RequestParam("quality") int quality)
      throws Exception {
    validatorService.validateRequestParam(image, quality);
    imageCompressService.compressImage(image, quality);
    return ResponseEntity.ok().build();
    // return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
    // + image.getName() + "\"").body(image);
  }

  @GetMapping(produces = {MediaType.ALL_VALUE})
  public ResponseEntity getCompressedFile() {
    String fileName = "compressedImage.png";
    HttpHeaders headers = new HttpHeaders();
    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
    headers.add("Pragma", "no-cache");
    headers.add("Expires", "0");
    return ResponseEntity.ok()
        .headers(headers)
        .contentLength(fileName.length())
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body("test");
  }

  private void compressAlgoSecond(MultipartFile imageFile) {
    // BufferedImage img = Scalr.resize(ImageIO.read(src), 500);
  }
}

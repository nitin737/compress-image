package com.tools.controller;

import com.tools.exception.ICException;
import com.tools.model.ImageResponse;
import com.tools.service.ImageService;
import com.tools.service.validate.ValidatorService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/image")
public class ImageController {
    private ImageService imageCompressService;
    private ValidatorService validatorService;

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
        produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<Resource> compress(
            @RequestParam("image") MultipartFile image, @RequestParam("quality") float quality)
            throws ICException, IOException {

        validatorService.validateRequestParam(image, quality);
        ImageResponse imageResponse = imageCompressService.compressImage(image, quality);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + imageResponse.getFileName() + "\"")
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .body(new ByteArrayResource(imageResponse.getBytes()));
    }
}

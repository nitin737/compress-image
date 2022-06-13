package com.tools.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageResponse {
    private byte[] bytes;
    private String fileName;
    private double imageSize;
}

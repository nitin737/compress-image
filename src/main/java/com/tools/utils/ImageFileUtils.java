package com.tools.utils;

import java.util.Arrays;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ImageFileUtils {

    public String extractFileExtn(String fileName) {
        String extn = "";
        if (fileName.contains(".")) {
            extn = Arrays.stream(fileName.split("\\.")).reduce((a, b) -> b).orElse(null);
        }
        return extn;
    }
}

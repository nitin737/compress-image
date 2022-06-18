package com.tools.utils;

import com.tools.imagetype.Image;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@UtilityClass
public class ImageFileUtils {

    public String extractFileExtn(String fileName) {
        String extn = "";
        if (fileName.contains(".")) {
            extn = Arrays.stream(fileName.split("\\.")).reduce((a, b) -> b).orElse(null);
        }
        return extn;
    }

    public String createNewFileName(String inputFileName, String format) {
        for (Image value : Image.values()) {
            inputFileName = inputFileName.replace(value.toString(), format);
        }
        return inputFileName;
    }
}

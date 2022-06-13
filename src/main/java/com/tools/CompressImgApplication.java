package com.tools;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
    info = @Info(title = "Image-Tools API", version = "2.0", description = "Image Manipulation")
)
@SpringBootApplication
public class CompressImgApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompressImgApplication.class, args);
    }
}

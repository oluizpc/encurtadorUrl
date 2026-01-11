package com.example.demo.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;

public record CreateUrlRequest(
    @NotBlank(message = "Original URL must not be blank")
    @URL(message = "Invalid URL")
    String originalUrl,
    boolean generateQrCode,
    boolean customShortCode,
    String shortCode
) {

}

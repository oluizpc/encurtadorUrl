package com.example.demo.dto;

import java.time.OffsetDateTime;

public record UrlResponse(
    Long id,
    String originalUrl,
    String shortCode,
    String shortUrl,
    OffsetDateTime createdAt,
    String qrCodeUrl,
    boolean active
) {

}

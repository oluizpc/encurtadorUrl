package com.example.demo.service;

import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.example.demo.dto.CreateUrlRequest;
import com.example.demo.dto.UrlResponse;
import com.example.demo.model.Url;
import com.example.demo.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;


    @Value("${app.base-url}")
    private String baseUrl;
    public UrlResponse createUrl(CreateUrlRequest request) {

        String shortCode = generateShortCode();

        Url url = new Url(
            null,
            request.originalUrl(),
            shortCode,
            null,
            OffsetDateTime.now(),
            true
        );

        Url savedUrl = urlRepository.save(url);

        String shortUrl = baseUrl + "/r/" + savedUrl.getShortCode();

        return new UrlResponse(
            savedUrl.getId(),
            savedUrl.getOriginalUrl(),
            savedUrl.getShortCode(),
            shortUrl,
            savedUrl.getCreatedAt(),
            savedUrl.getQrCodeUrl(),
            savedUrl.isActive()
        );
    }
    private String generateShortCode() {
        return UUID.randomUUID()
                .toString()
                .substring(0, 8);
    }

    public String getOriginalUrl(String shortCode) {
    Url url = urlRepository.findByShortCode(shortCode)
            .orElseThrow(() -> new RuntimeException("URL not found"));

    if (!url.isActive()) {
        throw new RuntimeException("URL inactive");
    }

    return url.getOriginalUrl();
}
}

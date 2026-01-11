package com.example.demo.controller.api.v1;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CreateUrlRequest;
import com.example.demo.dto.UrlResponse;
import com.example.demo.service.UrlService;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/v1/urls")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping
    public ResponseEntity<UrlResponse> createUrl(
            @Valid @RequestBody CreateUrlRequest request
    ) {
        UrlResponse urlResponse = urlService.createUrl(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(urlResponse);
    }
}

package com.desafio.urlshorter.dtos;

import jakarta.validation.constraints.NotBlank;

public record UrlRecordDto(@NotBlank String link) {
}

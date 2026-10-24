package com.cinevault.dto;

import com.cinevault.entity.WatchStatus;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateWatchlistItemRequest(
        @NotNull(message = "tmdbId is required")
        Long tmdbId,

        @NotBlank(message = "title must not be blank")
        @Size(max = 255, message = "title must be at most 255 characters")
        String title,

        @NotNull(message = "status is required")
        WatchStatus status,

        @Min(value  = 1, message = "rating must be at least 1")
        @Max(value  = 10, message = "rating must be at most 10")
        Integer rating
) {}
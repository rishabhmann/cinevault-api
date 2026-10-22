package com.cinevault.dto;

import com.cinevault.entity.WatchStatus;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateWatchlistItemRequest(
        @NotNull
        Long tmdbId,

        @NotBlank
        @Size(max = 255)
        String title,

        @NotNull
        WatchStatus status,

        @Min(1)
        @Max(10)
        Integer rating
) {}
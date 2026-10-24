package com.cinevault.dto;

import com.cinevault.entity.WatchStatus;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateWatchlistItemRequest(
        @NotNull(message = "status is required")
        WatchStatus status,

        @Min(value = 1, message = "rating must be at least 1")
        @Max(value = 10, message = "rating must be at most 10")
        Integer rating
) {}
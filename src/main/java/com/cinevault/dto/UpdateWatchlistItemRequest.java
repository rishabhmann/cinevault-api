package com.cinevault.dto;

import com.cinevault.entity.WatchStatus;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateWatchlistItemRequest(
        @NotNull
        WatchStatus status,

        @Min(1)
        @Max(10)
        Integer rating
) {}
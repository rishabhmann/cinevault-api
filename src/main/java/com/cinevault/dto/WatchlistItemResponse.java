package com.cinevault.dto;

import java.time.Instant;

import com.cinevault.entity.WatchStatus;
import com.cinevault.entity.WatchlistItem;

public record WatchlistItemResponse(
        Long id,
        Long tmdbId,
        String title,
        WatchStatus status,
        Integer rating,
        Instant createdAt,
        Instant updatedAt
) {
    public static WatchlistItemResponse from(WatchlistItem item) {
        return new WatchlistItemResponse(
                item.getId(),
                item.getTmdbId(),
                item.getTitle(),
                item.getStatus(),
                item.getRating(),
                item.getCreatedAt(),
                item.getUpdatedAt()
        );
    }
}
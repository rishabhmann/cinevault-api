package com.cinevault.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinevault.dto.CreateWatchlistItemRequest;
import com.cinevault.dto.UpdateWatchlistItemRequest;
import com.cinevault.dto.WatchlistItemResponse;
import com.cinevault.entity.WatchStatus;
import com.cinevault.entity.WatchlistItem;
import com.cinevault.exception.ResourceNotFoundException;
import com.cinevault.repository.WatchlistItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WatchlistService {

    private final WatchlistItemRepository repository;

    @Transactional
    public WatchlistItemResponse create(CreateWatchlistItemRequest request) {
        WatchlistItem item = new WatchlistItem();
        item.setTmdbId(request.tmdbId());
        item.setTitle(request.title());
        item.setStatus(request.status());
        item.setRating(request.rating());
        return WatchlistItemResponse.from(repository.save(item));
    }

    public List<WatchlistItemResponse> findAll(WatchStatus status) {
        List<WatchlistItem> items = (status == null)
                ? repository.findAll()
                : repository.findByStatus(status);
        return items.stream().map(WatchlistItemResponse::from).toList();
    }

    public WatchlistItemResponse findById(Long id) {
        return WatchlistItemResponse.from(getOrThrow(id));
    }

    @Transactional
    public WatchlistItemResponse update(Long id, UpdateWatchlistItemRequest request) {
        WatchlistItem item = getOrThrow(id);
        item.setStatus(request.status());
        item.setRating(request.rating());
        return WatchlistItemResponse.from(item);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(getOrThrow(id));
    }

    private WatchlistItem getOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Watchlist item " + id + " not found"));
    }
}
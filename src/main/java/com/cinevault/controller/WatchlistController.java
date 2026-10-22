package com.cinevault.controller;

import com.cinevault.dto.CreateWatchlistItemRequest;
import com.cinevault.dto.UpdateWatchlistItemRequest;
import com.cinevault.dto.WatchlistItemResponse;
import com.cinevault.entity.WatchStatus;
import com.cinevault.service.WatchlistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/watchlist")
@RequiredArgsConstructor
public class WatchlistController {

    private final WatchlistService watchListService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WatchlistItemResponse create(@Valid @RequestBody CreateWatchlistItemRequest request){
        return watchListService.create(request);
    }

    @GetMapping
    public List<WatchlistItemResponse> findAll(@RequestParam(required = false) WatchStatus status){
        return watchListService.findAll(status);
    }

    @GetMapping("/{id}")
    public WatchlistItemResponse findById(@PathVariable Long id){
        return watchListService.findById(id);
    }

    @PutMapping("/{id}")
    public WatchlistItemResponse update(@PathVariable Long id, @Valid @RequestBody UpdateWatchlistItemRequest request){
        return watchListService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        watchListService.delete(id);
    }
}

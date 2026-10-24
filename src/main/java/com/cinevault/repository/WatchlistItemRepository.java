package com.cinevault.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinevault.entity.WatchStatus;
import com.cinevault.entity.WatchlistItem;

/*Jpa Repository is an interface by JPA, gives ready-made DB Operations.
It accepts <Entity, type of Primary Key i.e. Long for ID> */
public interface WatchlistItemRepository extends JpaRepository<WatchlistItem, Long> {

    List<WatchlistItem> findByStatus(WatchStatus status);
    boolean existsByUserIdAndTmdbId(Long userId, Long tmdbId);
}
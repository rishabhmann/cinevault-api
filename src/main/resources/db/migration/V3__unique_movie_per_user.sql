-- Remove duplicates created during testing (keep the oldest of each)
DELETE FROM watchlist_items a
    USING watchlist_items b
WHERE a.user_id = b.user_id
  AND a.tmdb_id = b.tmdb_id
  AND a.id > b.id;

ALTER TABLE watchlist_items
    ADD CONSTRAINT uq_watchlist_user_movie UNIQUE (user_id, tmdb_id);
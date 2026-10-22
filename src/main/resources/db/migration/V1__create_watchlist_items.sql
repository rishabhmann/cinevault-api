CREATE TABLE watchlist_items (
                                 id          BIGSERIAL PRIMARY KEY,
                                 tmdb_id     BIGINT       NOT NULL,
                                 title       VARCHAR(255) NOT NULL,
                                 status      VARCHAR(20)  NOT NULL,
                                 rating      INTEGER,
                                 created_at  TIMESTAMPTZ  NOT NULL,
                                 updated_at  TIMESTAMPTZ  NOT NULL
);
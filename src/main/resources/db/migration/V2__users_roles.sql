CREATE TABLE roles (
                       id   BIGSERIAL PRIMARY KEY,
                       name VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE users (
                       id            BIGSERIAL PRIMARY KEY,
                       email         VARCHAR(255) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       display_name  VARCHAR(100) NOT NULL,
                       enabled       BOOLEAN      NOT NULL DEFAULT TRUE,
                       created_at    TIMESTAMPTZ  NOT NULL,
                       updated_at    TIMESTAMPTZ  NOT NULL
);

CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
                            role_id BIGINT NOT NULL REFERENCES roles (id),
                            PRIMARY KEY (user_id, role_id)
);

INSERT INTO roles (name) VALUES ('USER'), ('MODERATOR'), ('ADMIN');

-- TEMPORARY demo user so the API keeps working until we build login (Lesson 6).
-- The hash is fake, so nobody can log in as this user.
INSERT INTO users (email, password_hash, display_name, created_at, updated_at)
VALUES ('demo@cinevault.local', 'NOT_A_REAL_HASH', 'Demo User', now(), now());

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u, roles r
WHERE u.email = 'demo@cinevault.local' AND r.name = 'USER';

-- Link watchlist items to users.
-- Add the column as nullable, fill existing rows, then make it NOT NULL.
-- This is the safe pattern for adding a required column to a table that already has data.
ALTER TABLE watchlist_items ADD COLUMN user_id BIGINT;

UPDATE watchlist_items
SET user_id = (SELECT id FROM users WHERE email = 'demo@cinevault.local');

ALTER TABLE watchlist_items ALTER COLUMN user_id SET NOT NULL;

ALTER TABLE watchlist_items
    ADD CONSTRAINT fk_watchlist_user
        FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

CREATE INDEX idx_watchlist_user ON watchlist_items (user_id);
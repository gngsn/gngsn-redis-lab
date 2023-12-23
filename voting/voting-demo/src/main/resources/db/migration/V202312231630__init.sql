CREATE TABLE IF NOT EXISTS vote
(
    id         BIGSERIAL PRIMARY KEY,
    question   VARCHAR(50) NOT NULL,
    due_at     TIMESTAMP   NOT NULL,
    updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE IF NOT EXISTS vote_option
(
    id      BIGSERIAL PRIMARY KEY,
    vote_id BIGINT NOT NULL,
    option  INT    NOT NULL,
    score   BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS "user"
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_vote
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT NOT NULL,
    vote_id    BIGINT NOT NULL,
    option     INT    NOT NULL,
    updated_at TIMESTAMP DEFAULT now()
);


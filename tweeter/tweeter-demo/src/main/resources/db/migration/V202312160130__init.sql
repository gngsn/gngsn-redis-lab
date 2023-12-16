CREATE TABLE IF NOT EXISTS tweet(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    message VARCHAR( 255 ) NOT NULL,
    created_at TIMESTAMP DEFAULT now()
);

CREATE TABLE IF NOT EXISTS tweet_like(
    tweet_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT now(),
    PRIMARY KEY ( tweet_id, user_id )
);

CREATE TABLE IF NOT EXISTS tweet_like_count(
    tweet_id BIGINT PRIMARY KEY,
    `count` BIGINT NOT NULL
);


CREATE TABLE sessions
(
    -- Fields
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id    VARCHAR(64) NOT NULL,
    place_id   BIGINT      NOT NULL,
    started_at DATETIME    NOT NULL,
    ended_at   DATETIME,

    -- Constraints
    FOREIGN KEY sessions_place_id_fk (place_id) REFERENCES places (id)
);
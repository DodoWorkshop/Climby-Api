CREATE TABLE session_entries
(
    -- Fields
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    session_id          BIGINT   NOT NULL,
    difficulty_level_id BIGINT   NOT NULL,
    created_at          DATETIME NOT NULL,

    -- Constraints
    FOREIGN KEY session_entries_session_id_fk (session_id) REFERENCES sessions (id),
    FOREIGN KEY session_entries_difficulty_level_id_fk (difficulty_level_id) REFERENCES difficulty_levels (id)
);
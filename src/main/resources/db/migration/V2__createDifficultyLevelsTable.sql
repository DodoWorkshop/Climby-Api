CREATE TABLE difficulty_levels
(
    -- Fields
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    place_id BIGINT NOT NULL,
    color    BIGINT NOT NULL,
    score    INT    NOT NULL,
    `order`  INT    NOT NULL,

    -- Constraints
    FOREIGN KEY difficulty_levels_places_id_fk (place_id) REFERENCES places (id),
    UNIQUE difficulty_levels_place_id_color_unique (place_id, color)
);

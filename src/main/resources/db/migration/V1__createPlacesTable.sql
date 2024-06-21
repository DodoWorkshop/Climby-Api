CREATE TABLE places
(
    -- Fields
    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(64),
    name    VARCHAR(255) NOT NULL,

    -- Constraints
    UNIQUE places_user_id_name_unique (user_id, name)
);
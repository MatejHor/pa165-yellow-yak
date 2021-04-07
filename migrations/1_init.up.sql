CREATE TABLE player (
    id INT PRIMARY KEY,
    username VARCHAR(80) UNIQUE NOT NULL,
    email VARCHAR(80) UNIQUE NOT NULL,
    created_at TIME NOT NULL
);

CREATE TABLE game (
    id INT PRIMARY KEY,
    name VARCHAR(80) UNIQUE NOT NULL,
    created_at TIME NOT NULL
)

CREATE TABLE team (
    id INT PRIMARY KEY,
    game_id INT NOT NULL REFERENCES game (id),
    name VARCHAR(80) UNIQUE NOT NULL,
    created_at TIME NOT NULL
);

CREATE TABLE team_player (
    player_id INT NOT NULL REFERENCES player (id),
    team_id INT NOT NULL REFERENCES team (id)
);

CREATE TABLE competition (
    id INT PRIMARY KEY,
    game_id INT NOT NULL REFERENCES game (id),
    name VARCHAR(80) UNIQUE NOT NULL,
    prices VARCHAR(255),
    start_at TIME NOT NULL,
    finish_at TIME,
    created_at TIME NOT NULL
);

CREATE TABLE competitor (
    id INT PRIMARY KEY,
    competition_id INT NOT NULL REFERENCES competition (id),
    team_id INT NOT NULL REFERENCES team (id),
    created_at TIME NOT NULL
)

CREATE TABLE score (
    id INT PRIMARY KEY,
    competitor_id INT NOT NULL REFERENCES competitor (id),
    index INT NOT NULL,
    created_at TIME NOT NULL
)

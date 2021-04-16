CREATE TABLE player (
    id INT PRIMARY KEY,
    username VARCHAR(80) UNIQUE NOT NULL,
    email VARCHAR(80) UNIQUE NOT NULL,
    createdAt TIME NOT NULL
);

CREATE TABLE game (
    id INT PRIMARY KEY,
    name VARCHAR(80) UNIQUE NOT NULL,
    createdAt TIME NOT NULL
);

CREATE TABLE team (
    id INT PRIMARY KEY,
    name VARCHAR(80) UNIQUE NOT NULL,
    createdAt TIME NOT NULL
);

CREATE TABLE member (
    user_id INT NOT NULL REFERENCES player (id),
    team_id INT NOT NULL REFERENCES team (id)
);

CREATE TABLE competition (
    id INT PRIMARY KEY,
    game_id INT NOT NULL REFERENCES game (id),
    name VARCHAR(80) UNIQUE NOT NULL,
    prices VARCHAR(255),
    startedAt TIME NOT NULL,
    finishedAt TIME,
    createdAt TIME NOT NULL
);

CREATE TABLE score (
    id INT PRIMARY KEY,
    user_id INT NOT NULL REFERENCES player (id),
    competition_id INT NOT NULL REFERENCES competition (id),
    placement INT,
    result VARCHAR(255),
    stats VARCHAR(255),
    createdAt TIME NOT NULL
);

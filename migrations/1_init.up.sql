CREATE TABLE member (
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

CREATE TABLE team_member (
    member_id INT NOT NULL REFERENCES member (id),
    team_id INT NOT NULL REFERENCES team (id)
);

CREATE TABLE event (
    id INT PRIMARY KEY,
    game_id INT NOT NULL REFERENCES game (id),
    name VARCHAR(80) UNIQUE NOT NULL,
    start_at TIME NOT NULL,
    created_at TIME NOT NULL
);

CREATE TABLE competitor (
    id INT PRIMARY KEY,
    event_id INT NOT NULL REFERENCES event (id),
    team_id INT NOT NULL REFERENCES team (id),
    created_at TIME NOT NULL
)

CREATE TABLE rank (
    id INT PRIMARY KEY,
    event_id INT NOT NULL REFERENCES event (id),
    team_id INT NOT NULL REFERENCES team (id),
    index INT NOT NULL,
    created_at TIME NOT NULL
)

import React from "react";
import { Alert, Form } from "react-bootstrap";

import type {Game} from "../../../services/game";
import { useAllGames} from "../../../services/game";
import { deleteScore, useScoreByGameIdPlayerId } from "../../../services/score";
import {useAllPlayers} from "../../../services/player";
import type Player from "../../../records/Player";
import ScoreList from "./ScoreList";


const SearchScoresByGamesAndPlayers = () => {
    const [gameId, setGameId] = React.useState(0);
    const [playerId, setPlayerId] = React.useState(0);
    const { data: scoresByGamePlayer, error: scoresByGamePlayerError, mutate: scoresByGamePlayerMutate } = useScoreByGameIdPlayerId(gameId, playerId);
    const { data: games, error: gamesError } = useAllGames();
    const { data: players, error: playersError } = useAllPlayers();

    const handleDelete = React.useCallback(
        (id: number) => {
            deleteScore(id)
                .then(() => {
                    return scoresByGamePlayerMutate();
                })
                .catch(() => {});
        },
        [scoresByGamePlayerMutate],
    );
    return (
        <div className="my-3">
            <h2 className="my-2">Search scores by games and players</h2>
            {games && (
                <Form.Group controlId="username">
                    <Form.Label>Games</Form.Label>
                    <Form.Control
                        as="select"
                        onChange={(ev) => setGameId(Number(ev.target.value))}
                        value={gameId}
                    >
                        {games.map((game: Game) => (
                            <option key={game.id} value={game.id}>{game.name}</option>
                        ))}
                    </Form.Control>
                </Form.Group>
            )}
            {players && (
                <Form.Group controlId="username">
                    <Form.Label>Games</Form.Label>
                    <Form.Control
                        as="select"
                        onChange={(ev) => setPlayerId(Number(ev.target.value))}
                        value={playerId}
                    >
                        {players.map((player: Player) => (
                            <option key={player.id} value={player.id}>{player.username}, {player.email}</option>
                        ))}
                    </Form.Control>
                </Form.Group>
            )}

            {gamesError != null && <Alert title="Games loading failed" variant="danger">{gamesError.message}</Alert>}
            {playersError != null && <Alert title="Players loading failed" variant="danger">{playersError.message}</Alert>}
            {scoresByGamePlayerError != null && <Alert title="Scores loading failed"  variant="danger">{scoresByGamePlayerError.message}</Alert>}

            {scoresByGamePlayer && <ScoreList scores={scoresByGamePlayer} handleDelete={handleDelete} />}
        </div>
    );
};

export default SearchScoresByGamesAndPlayers;

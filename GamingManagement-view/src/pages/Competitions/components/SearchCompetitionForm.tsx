import React from "react";
import { Alert, Form } from "react-bootstrap";

import type { Game } from "../../../services/game";
import { useAllGames } from "../../../services/game";
import { deleteCompetition, useCompetitionsByGameId } from "../../../services/competition";
import CompetitionList from "./CompetitionList";


const SearchCompetitionForm = () => {
    const [value, setValue] = React.useState(0);
    const { data: competitions, error: competitionsError, mutate: competitionsMutate } = useCompetitionsByGameId(value);
    const { data: games, error: gamesError } = useAllGames();

    const handleDelete = React.useCallback(
        (id: number) => {
            deleteCompetition(id)
                .then(() => {
                    return competitionsMutate();
                })
                .catch(() => {});
        },
        [competitionsMutate],
    );
    return (
        <div className="my-3">
            <h2 className="my-2">Search competitions by games</h2>
            {games && (
                <Form.Group controlId="username">
                    <Form.Label>Game</Form.Label>
                    <Form.Control
                        as="select"
                        onChange={(ev) => setValue(Number(ev.target.value))}
                        value={value}
                    >
                        {games.map((game: Game) => (
                            <option key={game.id} value={game.id}>{game.name}</option>
                        ))}
                    </Form.Control>
                </Form.Group>
            )}

            {competitionsError != null && <Alert title="Competition loading failed" variant="danger">{competitionsError.message}</Alert>}
            {gamesError != null && <Alert title="Games loading failed"  variant="danger">{gamesError.message}</Alert>}

            {competitions && <CompetitionList competitions={competitions} handleDelete={handleDelete} />}
        </div>
    );
};

export default SearchCompetitionForm;

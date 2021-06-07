import React from "react";
import { Alert, Form } from "react-bootstrap";

import { deleteScore, useScoreByCompetitionId } from "../../../services/score";
import type { Competition } from "../../../services/competition";
import { useAllCompetitions } from "../../../services/competition";
import ScoreList from "./ScoreList";


const SearchScoresByCompetitionForm = () => {
    const [competitionId, setCompetitionId] = React.useState(0);
    const { data: competitions, error: competitionsError } = useAllCompetitions();
    const { data: scoresByCompetition, error: scoresByCompetitionError, mutate: scoresByCompetitionMutate } = useScoreByCompetitionId(competitionId);

    const handleDelete = React.useCallback(
        (id: number) => {
            deleteScore(id)
                .then(() => {
                    return scoresByCompetitionMutate()
                })
                .catch(() => {});
        },
        [scoresByCompetitionMutate],
    );
    return (
        <div className="my-3">
            <h2 className="my-2">Search scores by competition</h2>
            {competitions && (
                <Form.Group controlId="username">
                    <Form.Label>Competition</Form.Label>
                    <Form.Control
                        as="select"
                        onChange={(ev) => setCompetitionId(Number(ev.target.value))}
                        value={competitionId}
                    >
                        {competitions.map((competition: Competition) => (
                            <option key={competition.id} value={competition.id}>{competition.name}</option>
                        ))}
                    </Form.Control>
                </Form.Group>
            )}

            {competitionsError != null && <Alert title="Competitions loading failed" variant="danger">{competitionsError.message}</Alert>}
            {scoresByCompetitionError != null && <Alert title="Scores loading failed"  variant="danger">{scoresByCompetitionError.message}</Alert>}

            {scoresByCompetition && <ScoreList scores={scoresByCompetition} handleDelete={handleDelete} />}
        </div>
    );
};

export default SearchScoresByCompetitionForm;

import React from "react";
import { Alert, Form } from "react-bootstrap";

import GameList from "./GameList";
import {deleteGame, useGamesByName} from "../../../services/game";

const SearchGameForm = () => {
    const ref = React.useRef<HTMLInputElement>(null);
    const value = ref.current?.value ?? "";
    const { data, error, revalidate } = useGamesByName(value);

    const handleDelete = React.useCallback(
        (id: number) => {
            deleteGame(id)
                .then(() => {
                    return revalidate();
                })
                .catch(() => {});
        },
        [revalidate],
    );
    return (
        <div className="my-3">
            <h2 className="my-2">Search game</h2>
            <Form.Group controlId="username">
                <Form.Label>Name</Form.Label>
                <Form.Control ref={ref} name="name" type="name" placeholder="e.g. Foxes vs Hawks" />
            </Form.Group>

            {error != null && <Alert variant="danger">{error.message}</Alert>}

            {data && <GameList games={data} handleDelete={handleDelete} />}
        </div>
    );
};

export default SearchGameForm;
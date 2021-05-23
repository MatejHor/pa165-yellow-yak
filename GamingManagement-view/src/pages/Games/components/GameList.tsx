import * as React from "react";
import {Button, Col, Row} from "react-bootstrap";

import type {Game} from "../../../services/game";

type Props = {
    games: Game[];
    handleDelete: (id: number) => void;
};

const GameList = ({ games, handleDelete }: Props) => {
    return (
        <>
            {games.map(game => (
                <Row key={game.id} className="col-auto border-dark">
                    <Col>
                        <Row>
                            <p><b>Name: </b> {game.name}</p>
                        </Row>
                    </Col>
                    <Col className="col-auto">
                         <Row>
                             <Button variant="outline-danger" onClick={() => handleDelete(game.id)}>
                                 Delete
                             </Button>
                         </Row>
                    </Col>
                </Row>
            ))}
        </>
    );
};
export default GameList;

import * as React from "react";
import { Button, Col, Row } from "react-bootstrap";

import type {Score} from "../../../services/score";

type Props = {
    scores: Score[];
    handleDelete: (id: number) => void;
};

const ScoreList = ({ scores, handleDelete }: Props) => {
    return (
        <>
            {scores.map((score) => (
                <Row key={score.id} className="col-auto border-dark">
                    <Col>
                        <Row>
                            <p>
                                <b>Result: </b> {score.result}
                            </p>
                        </Row>
                    </Col>
                    <Col className="col-auto">
                        <Row>
                            <Button variant="outline-danger" onClick={() => handleDelete(score.id)}>
                                Delete
                            </Button>
                        </Row>
                    </Col>
                </Row>
            ))}
        </>
    );
};
export default ScoreList;

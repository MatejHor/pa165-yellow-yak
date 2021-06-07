import * as React from "react";
import { Button, Col, Row } from "react-bootstrap";

import type { Competition } from "../../../services/competition";


type Props = {
    competitions: Competition[];
    handleDelete: (id: number) => void;
};

const CompetitionList = ({ competitions, handleDelete }: Props) => {
    return (
        <>
            {competitions.map((competition) => (
                <Row key={competition.id} className="col-auto border-dark">
                    <Col>
                        <Row>
                            <p>
                                <b>Name: </b> {competition.name}
                            </p>
                        </Row>
                    </Col>
                    <Col className="col-auto">
                        <Row>
                            <Button variant="outline-danger" onClick={() => handleDelete(competition.id)}>
                                Delete
                            </Button>
                        </Row>
                    </Col>
                </Row>
            ))}
        </>
    );
};
export default CompetitionList;

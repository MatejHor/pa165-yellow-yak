import * as React from "react";

import PlayerType from "../../records/Player";
import { Col, Row } from "react-bootstrap";

type Props = {
  data: PlayerType;
  renderActions?: (id: number) => React.ReactNode;
};

const Player = ({ data, renderActions }: Props) => {
  return (
    <Row className="my-2 border-dark">
      <Col>
        <Row>
          <b>Username: </b>
          {data.username}
        </Row>
        <Row>
          <b>Email: </b>
          {data.email}
        </Row>
      </Col>

      {renderActions != null && <Col>{renderActions(data.id)}</Col>}
    </Row>
  );
};

export default Player;

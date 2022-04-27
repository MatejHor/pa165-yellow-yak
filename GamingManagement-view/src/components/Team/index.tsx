import * as React from "react";
import { Col, Row } from "react-bootstrap";

import type TeamType from "../../records/Team";

type Props = {
  data: TeamType;
  renderActions?: (id: number) => React.ReactNode;
};
const Team = ({ data, renderActions }: Props) => {
  return (
    <Row className="my-2 border-dark">
      <Col>
        <Row>
          <b>Name: </b>
          {data.name}
        </Row>
      </Col>
      {renderActions != null && <Col>{renderActions(data.id)}</Col>}
    </Row>
  );
};
export default Team;

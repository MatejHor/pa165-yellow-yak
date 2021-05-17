import { Col } from "react-bootstrap";

import CreateForm from "./components/CreateForm";
import UsernameAutocomplete from "./components/UsernameAutocomplete";

const Players = () => {
  return (
    <Col md="auto">
      <h1 className="my-3">Players</h1>

      <CreateForm />

      <UsernameAutocomplete />
    </Col>
  );
};

export default Players;

import { Col } from "react-bootstrap";

import CreateForm from "./components/CreateForm";
import UsernameAutocomplete from "./components/UsernameAutocomplete";
import { useIsAdmin } from "../../services/authContext";

const Players = () => {
  const isAdmin = useIsAdmin();

  return (
    <Col md="auto">
      <h1 className="my-3">Players</h1>

      {isAdmin && <CreateForm />}

      <UsernameAutocomplete />
    </Col>
  );
};

export default Players;

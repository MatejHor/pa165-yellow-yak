import { Col } from "react-bootstrap";

import CreateForm from "./components/CreateForm";
import NameAutocomplete from "./components/NameAutocomplete";
import { useIsAdmin } from "../../services/authContext";

const Teams = () => {
  const isAdmin = useIsAdmin();

  return (
    <Col md="auto">
      <h1 className="my-3">Teams</h1>
      {isAdmin && <CreateForm />}
      <NameAutocomplete />
    </Col>
  );
};
export default Teams;

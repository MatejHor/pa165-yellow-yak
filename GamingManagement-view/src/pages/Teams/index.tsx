import { Col } from "react-bootstrap";

import CreateForm from "./components/CreateForm";
import NameAutocomplete from "./components/NameAutocomplete";

const Teams = () => {
    return (
        <Col md="auto">
            <h1 className="my-3">Teams</h1>
            <CreateForm />
            <NameAutocomplete />
        </Col>
    );
};
export default Teams;
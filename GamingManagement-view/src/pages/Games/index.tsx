import { Col, Tab, Tabs } from "react-bootstrap";

import CreateGameForm from "./components/CreateGameForm";
import SearchGameForm from "./components/SearchGameForm";
import { useIsAdmin } from "../../services/authContext";

const Games = () => {
  const isAdmin = useIsAdmin();

  return (
    <Col md="auto">
      <h1 className="my-3">Games</h1>
      <Tabs defaultActiveKey="profile" id="uncontrolled-tab-example">
        <Tab eventKey="home" title="Search games">
          <SearchGameForm />
        </Tab>
        {isAdmin && (
          <Tab eventKey="profile" title="Create new game">
            <CreateGameForm />
          </Tab>
        )}
      </Tabs>
    </Col>
  );
};

export default Games;

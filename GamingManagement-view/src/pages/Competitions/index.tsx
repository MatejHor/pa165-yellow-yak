import {Col, Tab, Tabs} from "react-bootstrap";

import SearchCompetitionForm from "./components/SearchCompetitionForm";
import CreateCompetitionForm from "./components/CreateCompetitionForm";
import {useIsAdmin} from "../../services/authContext";


const Competitions = () => {
    const isAdmin = useIsAdmin();

    return (
        <Col md="auto">
            <h1 className="my-3">Competitions</h1>
            <Tabs defaultActiveKey="profile" id="uncontrolled-tab-example">
                <Tab eventKey="home" title="Search competitions">
                    <SearchCompetitionForm />
                </Tab>
                {isAdmin && (
                    <Tab eventKey="profile" title="Create new competition">
                        <CreateCompetitionForm />
                    </Tab>
                )}
            </Tabs>
        </Col>
    );
};

export default Competitions;

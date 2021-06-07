import {Col, Tab, Tabs} from "react-bootstrap";

import SearchCompetitionForm from "./components/SearchCompetitionForm";


const Competitions = () => {
    return (
        <Col md="auto">
            <h1 className="my-3">Competitions</h1>
            <Tabs defaultActiveKey="profile" id="uncontrolled-tab-example">
                <Tab eventKey="home" title="Search competitions">
                    <SearchCompetitionForm />
                </Tab>
                <Tab eventKey="profile" title="Create new competition">
                    Create new competition...
                </Tab>
            </Tabs>
        </Col>
    );
};

export default Competitions;

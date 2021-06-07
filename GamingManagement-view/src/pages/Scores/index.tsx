import {Col, Tab, Tabs} from "react-bootstrap";

import SearchScoresByCompetitionForm from "./components/SearchScoresByCompetition";
import SearchScoresByGamesAndPlayers from "./components/SearchScoresByGamesAndPlayers";


const Scores = () => {
    return (
        <Col md="auto">
            <h1 className="my-3">Scores</h1>
            <Tabs defaultActiveKey="profile" id="uncontrolled-tab-example">
                <Tab eventKey="home" title="Search scores by competition">
                    <SearchScoresByCompetitionForm />
                </Tab>
                <Tab eventKey="home" title="Search scores by games and players">
                    <SearchScoresByGamesAndPlayers />
                </Tab>
            </Tabs>
        </Col>
    );
};

export default Scores;

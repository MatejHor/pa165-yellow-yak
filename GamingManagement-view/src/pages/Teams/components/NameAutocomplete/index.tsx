import * as React from "react";
import { Alert, Button, Form } from "react-bootstrap";

import { deleteTeam, useTeamsByName } from "../../../../services/team";
import Team from "../../../../components/Team";

const NameAutocomplete = () => {
    const ref = React.useRef<HTMLInputElement>(null);
    const value = ref.current?.value ?? "";
    const { data, error, revalidate } = useTeamsByName(value);
    const handleDelete = React.useCallback(
        (id: number) => {
            deleteTeam(id)
                .then(() => {
                    return revalidate();
                })
                .catch(() => {});
        },
        [revalidate],
    );
    return (
        <div className="my-3">
            <h2 className="my-2">Serach by username</h2>
            <Form.Group controlId="username">
                <Form.Label>Name</Form.Label>
                <Form.Control ref={ref} name="username" type="username" placeholder="Enter username" />
            </Form.Group>
            {error != null && <Alert variant="danger">{error.message}</Alert>}
            {data?.map((team) => (
                <Team
                    key={team.id}
                    data={team}
                    renderActions={(id) => (
                        <Button type="primary" onClick={() => handleDelete(id)}>
                            Delete
                        </Button>
                    )}
                />
            ))}
        </div>
    );
};
export default NameAutocomplete;
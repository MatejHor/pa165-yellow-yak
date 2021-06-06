import * as React from "react";
import { Alert, Button, Form } from "react-bootstrap";

import { deletePlayer, usePlayersByUsername } from "../../../../services/player";
import Player from "../../../../components/Player";
import { useIsAdmin } from "../../../../services/authContext";

const UsernameAutocomplete = () => {
  const isAdmin = useIsAdmin();
  const [value, setValue] = React.useState("");
  const { data, error, mutate } = usePlayersByUsername(value);

  const handleDelete = React.useCallback(
    (id: number) => {
      deletePlayer(id)
        .then(() => {
          return mutate();
        })
        .catch(() => {});
    },
    [mutate],
  );

  return (
    <div className="my-3">
      <h2 className="my-2">Serach by username</h2>

      <Form.Group controlId="username">
        <Form.Label>Username</Form.Label>
        <Form.Control
          value={value}
          onChange={(ev) => setValue(ev.target.value)}
          name="username"
          type="username"
          placeholder="Enter username"
        />
      </Form.Group>

      {error != null && <Alert variant="danger">{error.message}</Alert>}

      {data?.map((player) => (
        <Player
          key={player.id}
          data={player}
          renderActions={(id) =>
            isAdmin && (
              <Button type="primary" onClick={() => handleDelete(id)}>
                Delete
              </Button>
            )
          }
        />
      ))}
    </div>
  );
};

export default UsernameAutocomplete;

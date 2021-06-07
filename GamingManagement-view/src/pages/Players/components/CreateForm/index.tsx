import type { FormikHelpers } from "formik";
import { Formik } from "formik";
import { Alert, Button, Form, InputGroup } from "react-bootstrap";
import React from "react";
import * as Yup from "yup";

import type { CreatePlayerInput } from "../../../../services/player";
import { createPlayer } from "../../../../services/player";
import useState, { isSuccess } from "../../../../services/useState";
import { useTeamsByName } from "../../../../services/team";

const CreateForm = () => {
  const [state, setState] = useState();
  const success = isSuccess(state);
  const [teamName, setTeamName] = React.useState("");
  const { data: teams, error: teamsError } = useTeamsByName(teamName);

  const validationSchema = React.useMemo(
    () =>
      Yup.object().shape({
        username: Yup.string().required("Required"),
        email: Yup.string().email("Email is invalid").required("Required"),
        teamId: Yup.number().min(1, "Required").required("Required"),
      }),
    [],
  );

  const handleSubmit = React.useCallback(
    (values: CreatePlayerInput, form: FormikHelpers<CreatePlayerInput>) => {
      setState(null);
      form.setSubmitting(true);

      createPlayer(values)
        .then((res) => {
          console.log("Player created!", res); // eslint-disable-line

          setState({ error: null });
        })
        .catch((error) => {
          setState({ error });
        })
        .finally(() => {
          form.setSubmitting(false);
        });
    },
    [],
  );

  return (
    <Formik<CreatePlayerInput>
      initialValues={{
        username: "",
        email: "",
        teamId: 0,
      }}
      validationSchema={validationSchema}
      onSubmit={handleSubmit}
    >
      {(form) => (
        <Form noValidate onSubmit={form.handleSubmit} className="my-3">
          <h2 className="my-3">Create player</h2>

          <Form.Group controlId="username">
            <Form.Label>Username</Form.Label>
            <InputGroup hasValidation>
              <Form.Control
                name="username"
                type="username"
                value={form.values.username}
                isInvalid={form.touched.username === true && form.errors.username != null}
                onChange={form.handleChange}
                onBlur={form.handleBlur}
                placeholder="Username"
              />
              <Form.Control.Feedback type="invalid">{form.errors.username}</Form.Control.Feedback>
            </InputGroup>
          </Form.Group>

          <Form.Group controlId="email">
            <Form.Label>Email address</Form.Label>
            <InputGroup hasValidation>
              <Form.Control
                name="email"
                type="email"
                value={form.values.email}
                isInvalid={form.touched.email === true && form.errors.email != null}
                onChange={form.handleChange}
                onBlur={form.handleBlur}
                placeholder="Enter email"
              />
              <Form.Control.Feedback type="invalid">{form.errors.email}</Form.Control.Feedback>
            </InputGroup>
          </Form.Group>

          <Form.Group controlId="teamId">
            <Form.Label>Team name</Form.Label>
            <Form.Control
              value={teamName}
              onChange={(ev) => setTeamName(ev.target.value)}
              name="name"
              placeholder="Enter team name"
            />
          </Form.Group>

          <Form.Group controlId="teamId">
            <Form.Label>Team</Form.Label>
            <InputGroup hasValidation>
              <Form.Control
                as="select"
                name="teamId"
                value={form.values.teamId}
                isInvalid={form.touched.teamId === true && form.errors.teamId != null}
                onChange={form.handleChange}
                onBlur={form.handleBlur}
                placeholder="Select team"
              >
                <option value={0} disabled>Select team</option>
                {teams?.map(team => (
                  <option key={team.id} value={team.id}>{team.name}</option>
                ))}
              </Form.Control>
              <Form.Control.Feedback type="invalid">{form.errors.teamId}</Form.Control.Feedback>
            </InputGroup>
          </Form.Group>

          {teamsError != null && <Alert variant="danger">{teamsError.message}</Alert>}

          {state?.error != null && <Alert variant="danger">{state.error.message}</Alert>}

          {success && (
            <>
              <Alert variant="success">Player created!</Alert>

              <Button variant="secondary" onClick={() => form.resetForm()}>
                Add another
              </Button>
            </>
          )}

          <Button variant="primary" type="submit" disabled={!form.isValid || form.isSubmitting}>
            Create player
          </Button>
        </Form>
      )}
    </Formik>
  );
};

export default CreateForm;

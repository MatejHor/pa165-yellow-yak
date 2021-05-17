import { Formik, FormikHelpers } from "formik";
import { Alert, Button, Form, InputGroup } from "react-bootstrap";
import React from "react";
import * as Yup from "yup";

import { createPlayer, CreatePlayerInput } from "../../../../services/player";
import useState, { isSuccess } from "../../../../services/useState";

const CreateForm = () => {
  const [state, setState] = useState();
  const success = isSuccess(state);

  const validationSchema = React.useMemo(
    () =>
      Yup.object().shape({
        username: Yup.string().required("Required"),
        email: Yup.string().email("Email is invalid").required("Required"),
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

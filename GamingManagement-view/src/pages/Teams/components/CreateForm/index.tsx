import type { FormikHelpers } from "formik";
import { Formik } from "formik";
import { Alert, Button, Form, InputGroup } from "react-bootstrap";
import React from "react";
import * as Yup from "yup";

import type { CreateTeamInput } from "../../../../services/team";
import { createTeam } from "../../../../services/team";
import useState, { isSuccess } from "../../../../services/useState";

const CreateForm = () => {
  const [state, setState] = useState();
  const success = isSuccess(state);
  const validationSchema = React.useMemo(
    () =>
      Yup.object().shape({
        name: Yup.string().required("Required"),
      }),
    [],
  );
  const handleSubmit = React.useCallback(
    (values: CreateTeamInput, form: FormikHelpers<CreateTeamInput>) => {
      setState(null);
      form.setSubmitting(true);
      createTeam(values)
        .then((res) => {
          console.log("Team created!", res); // eslint-disable-line
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
    <Formik<CreateTeamInput>
      initialValues={{
        name: "",
      }}
      validationSchema={validationSchema}
      onSubmit={handleSubmit}
    >
      {(form) => (
        <Form noValidate onSubmit={form.handleSubmit} className="my-3">
          <h2 className="my-3">Create team</h2>
          <Form.Group controlId="name">
            <Form.Label>Name</Form.Label>
            <InputGroup hasValidation>
              <Form.Control
                name="name"
                type="name"
                value={form.values.name}
                isInvalid={form.touched.name === true && form.errors.name != null}
                onChange={form.handleChange}
                onBlur={form.handleBlur}
                placeholder="Name"
              />
              <Form.Control.Feedback type="invalid">{form.errors.name}</Form.Control.Feedback>
            </InputGroup>
          </Form.Group>
          {state?.error != null && <Alert variant="danger">{state.error.message}</Alert>}
          {success && (
            <>
              <Alert variant="success">Team created!</Alert>
              <Button variant="secondary" onClick={() => form.resetForm()}>
                Add another
              </Button>
            </>
          )}
          <Button variant="primary" type="submit" disabled={!form.isValid || form.isSubmitting}>
            Create team
          </Button>
        </Form>
      )}
    </Formik>
  );
};
export default CreateForm;

import React from "react";
import * as Yup from "yup";
import { Redirect, Route, Link, Switch } from "wouter";
import { Alert, Button, Col, Container, Form, InputGroup, Nav, Navbar, Row } from "react-bootstrap";
import type { FormikHelpers } from "formik";
import { Formik } from "formik";

import Players from "./Players";
import Teams from "./Teams";
import { save } from "../services/storage";
import { login } from "../services/auth";
import useState, { isSuccess } from "../services/useState";

enum Routes {
  INDEX = "/",
  PLAYERS = "/players",
  TEAMS = "/teams",
}

type FormValues = {
  email: string;
  password: string;
};

const Index = () => {
  const [isAdmin, setIsAdmin] = React.useState(false);
  const [state, setState] = useState();
  const success = isSuccess(state);

  const validationSchema = React.useMemo(
    () =>
      Yup.object().shape({
        email: Yup.string().email("Invalid email").required("Required"),
        password: Yup.string(),
      }),
    [],
  );

  const handleSubmit = React.useCallback((values: FormValues, form: FormikHelpers<FormValues>) => {
    form.setSubmitting(true);
    login(values)
      .then(({ token }) => {
        save(token);
        setState({ error: null });
      })
      .catch((error) => {
        setState({ error });
      })
      .finally(() => {
        form.setSubmitting(false);
      });
  }, []);

  if (!success) {
    return (
      <Container>
        <Row className="justify-content-md-center">
          <Col md="auto">
            <h1 className="my-5">Gaming management</h1>

            <Formik<FormValues>
              initialValues={{
                email: "",
                password: "",
              }}
              validationSchema={validationSchema}
              onSubmit={handleSubmit}
            >
              {(form) => (
                <Form noValidate onSubmit={form.handleSubmit}>
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
                      <Form.Control.Feedback type="invalid">
                        {form.errors.email}
                      </Form.Control.Feedback>
                    </InputGroup>
                  </Form.Group>

                  {isAdmin && (
                    <Form.Group controlId="password">
                      <Form.Label>Password</Form.Label>
                      <InputGroup hasValidation>
                        <Form.Control
                          name="password"
                          type="password"
                          value={form.values.password}
                          isInvalid={form.touched.password === true && form.errors.password != null}
                          onChange={form.handleChange}
                          onBlur={form.handleBlur}
                          placeholder="Password"
                        />
                        <Form.Control.Feedback type="invalid">
                          {form.errors.password}
                        </Form.Control.Feedback>
                      </InputGroup>
                    </Form.Group>
                  )}

                  {state?.error != null && <Alert variant="danger">{state.error.message}</Alert>}

                  <Button
                    variant="primary"
                    type="submit"
                    disabled={!form.isValid || form.isSubmitting}
                  >
                    Sign in
                  </Button>

                  <Button
                    className="mx-2"
                    variant="outline-secondary"
                    onClick={() => setIsAdmin((state) => !state)}
                  >
                    Login as {isAdmin ? "player" : "admin"}
                  </Button>
                </Form>
              )}
            </Formik>
          </Col>
        </Row>
      </Container>
    );
  }

  return (
    <>
      <Navbar variant="light" bg="light" expand="lg">
        <Link href={Routes.INDEX}>
          <Navbar.Brand>Gaming management</Navbar.Brand>
        </Link>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="mr-auto">
            <Link href={Routes.PLAYERS}>
              <Nav.Link>Players</Nav.Link>
            </Link>
            <Link href={Routes.TEAMS}>
              <Nav.Link>Teams</Nav.Link>
            </Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>

      <Container className="my-3">
        <Row className="justify-content-md-center">
          <Switch>
            <Route path={Routes.PLAYERS}>{() => <Players />}</Route>
            <Route path={Routes.TEAMS}>{() => <Teams />}</Route>

            <Redirect to={Routes.INDEX} />
          </Switch>
        </Row>
      </Container>
    </>
  );
};

export default Index;

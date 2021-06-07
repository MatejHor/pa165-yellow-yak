import React from "react";
import * as Yup from "yup";
import { Redirect, Route, Link, Switch } from "wouter";
import { Alert, Button, Col, Container, Form, InputGroup, Nav, Navbar, Row } from "react-bootstrap";
import type { FormikHelpers } from "formik";
import { Formik } from "formik";

import Players from "./Players";
import Teams from "./Teams";
import { save, remove } from "../services/storage";
import { login } from "../services/auth";
import useState, { isSuccess } from "../services/useState";
import Games from "./Games";
import { Role, useAuth } from "../services/authContext";

enum Routes {
  INDEX = "/",
  TEAMS = "/teams",
  GAMES = "/games",
}

type FormValues = {
  email: string;
  password: string;
};

const Index = () => {
  const { role, onSetRole } = useAuth();
  const [isAdmin, setIsAdmin] = React.useState(false);
  const [state, setState] = useState();
  const success = isSuccess(state);

  const validationSchema = React.useMemo(
    () =>
      isAdmin
        ? Yup.object().shape({
            email: Yup.string().email("Invalid email").required("Required"),
            password: Yup.string().required("Required"),
          })
        : Yup.object().shape({
            email: Yup.string().email("Invalid email").required("Required"),
          }),
    [isAdmin],
  );

  const handleSubmit = React.useCallback(
    (values: FormValues, form: FormikHelpers<FormValues>) => {
      form.setSubmitting(true);
      login(values)
        .then(({ token }) => {
          save(token);

          // Yolo
          if (token.toLowerCase().includes("admin")) {
            onSetRole(Role.Admin);
          } else {
            onSetRole(Role.Player);
          }

          setState({ error: null });
        })
        .catch((error) => {
          setState({ error });
        })
        .finally(() => {
          form.setSubmitting(false);
        });
    },
    [onSetRole],
  );

  const handleLogout = React.useCallback(() => {
    remove();
    onSetRole(null);
    setState(null);
  }, [onSetRole]);

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
                        placeholder={isAdmin ? "admin@gaming.com" : "Enter email"}
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
                          placeholder="admin"
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
            <Link href={Routes.INDEX}>
              <Nav.Link>Players</Nav.Link>
            </Link>
            <Link href={Routes.TEAMS}>
              <Nav.Link>Teams</Nav.Link>
            </Link>
            <Link href={Routes.GAMES}>
              <Nav.Link>Games</Nav.Link>
            </Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>

      <Container className="my-3">
        <Row className="justify-content-md-center">
          <Switch>
            <Route path={Routes.INDEX}>{() => <Players />}</Route>
            <Route path={Routes.TEAMS}>{() => <Teams />}</Route>
            <Route path={Routes.GAMES}>{() => <Games />}</Route>

            <Redirect to={Routes.INDEX} />
          </Switch>
        </Row>
      </Container>

      <Container className="my-5">
        <Row className="justify-content-md-center align-items-center">
          <Button onClick={handleLogout}>Logout {role === Role.Admin ? "Admin" : "Player"}</Button>
        </Row>
      </Container>
    </>
  );
};

export default Index;

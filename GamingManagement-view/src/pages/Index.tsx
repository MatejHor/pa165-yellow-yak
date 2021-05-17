import React from "react";
import * as Yup from "yup";
import { Redirect, Route, Link, Switch } from "wouter";
import { Button, Col, Container, Form, InputGroup, Nav, Navbar, Row } from "react-bootstrap";
import { Formik } from "formik";

import Player from "./Player";
import { load, save } from "../services/storage";

enum Routes {
  INDEX = "/",
  PLAYER = "/player",
}

type FormValues = {
  email: string;
  password: string;
};

const Index = () => {
  const [authed, setAuthed] = React.useState(load() !== null);

  const validationSchema = React.useMemo(
    () =>
      Yup.object().shape({
        email: Yup.string().email("Invalid email").required("Required"),
        password: Yup.string().required("Required"),
      }),
    [],
  );

  const handleSubmit = React.useCallback((values: FormValues) => {
    save(btoa(`${values.email}:${values.password}`));
    setAuthed(true);
  }, []);

  if (!authed) {
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

                  <Button
                    variant="primary"
                    type="submit"
                    disabled={!form.isValid || form.isSubmitting}
                  >
                    Sign in
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
        <Navbar.Brand href="#home">Gaming management</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="mr-auto">
            <Link href={Routes.PLAYER}>
              <Nav.Link>Player</Nav.Link>
            </Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>

      <Container className="my-3">
        <Row className="justify-content-md-center">
          <Switch>
            <Route path={Routes.PLAYER}>{() => <Player />}</Route>

            <Redirect to={Routes.INDEX} />
          </Switch>
        </Row>
      </Container>
    </>
  );
};

export default Index;

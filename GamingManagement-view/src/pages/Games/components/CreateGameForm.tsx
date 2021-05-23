import type { FormikHelpers} from "formik";
import {Formik} from "formik";
import {Alert, Button, Form, InputGroup} from "react-bootstrap";
import React from "react";
import * as Yup from "yup";

import type { CreateGameInput } from "../../../services/game";
import useState, {isSuccess} from "../../../services/useState";
import {createGame} from "../../../services/game";

const CreateGameForm = () => {
    const [state, setState] = useState();
    const success = isSuccess(state);

    const validationSchema = React.useMemo(
        () =>
            Yup.object().shape({
                name: Yup.string().required("Required").min(5).matches(/.+\svs\s.+/, "Please enter game name in following format: Team1 vs Team2"),
            }),
        [],
    );
    const onSubmitHandler = React.useCallback(
        (values: CreateGameInput, form: FormikHelpers<CreateGameInput>) => {
            setState(null);
            form.setSubmitting(true);
            createGame(values)
                .then((res) => {
                    console.log("Game created!", res); // eslint-disable-line
                    setState({ error: null });
                })
                .catch((error) => {
                    setState({ error });
                })
                .finally(() => {
                    form.setSubmitting(false);
                });
            console.log("Team created!"); // eslint-disable-line
            form.setSubmitting(false);
        },
        [],
    );

    return (<Formik<CreateGameInput>
            initialValues={{
                name: ""
            }}
            validationSchema={validationSchema}
            onSubmit={onSubmitHandler}
        >
            {(form) => (
                <Form noValidate onSubmit={form.handleSubmit} className="my-3">
                    <h2 className="my-3">Create Game</h2>

                    <Form.Group controlId="name">
                        <Form.Label>Name of the game</Form.Label>
                        <InputGroup hasValidation>
                            <Form.Control
                                name="name"
                                type="name"
                                value={form.values.name}
                                isInvalid={form.touched.name === true && form.errors.name != null}
                                onChange={form.handleChange}
                                onBlur={form.handleBlur}
                                placeholder="e.g. Foxes vs Piranhas"
                            />
                            <Form.Control.Feedback type="invalid">{form.errors.name}</Form.Control.Feedback>
                        </InputGroup>
                    </Form.Group>

                    {state?.error != null && <Alert variant="danger">{state.error.message}</Alert>}

                    {success && (
                        <Alert variant="success">
                            <Alert.Heading>Game created!</Alert.Heading>
                            <Button
                                variant="secondary"
                                onClick={() => {
                                    form.resetForm();
                                    setState({ error: null });
                                }}
                            >
                                Create another
                            </Button>
                        </Alert>
                    )}

                    <Button variant="primary" type="submit" disabled={!form.isValid || form.isSubmitting}>
                        Create Game
                    </Button>
                </Form>
            )}
        </Formik>
    );
};

export default CreateGameForm;

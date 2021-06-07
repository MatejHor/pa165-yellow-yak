import type { FormikHelpers } from "formik";
import { Formik } from "formik";
import { Alert, Button, Form, InputGroup } from "react-bootstrap";
import React from "react";
import * as Yup from "yup";

import type { Game } from "../../../services/game";
import useState, { isSuccess } from "../../../services/useState";
import { useAllGames } from "../../../services/game";
import { createCompetition } from "../../../services/competition";

type FormInput = {
    name: string;
    gameId: number;
}

const CreateCompetitionForm = () => {
    const [state, setState] = useState();
    const { data: games, error: gamesError } = useAllGames();
    const success = isSuccess(state);

    const validationSchema = React.useMemo(
        () =>
            Yup.object().shape({
                name: Yup.string().required("Required").min(5),
            }),
        [],
    );
    const onSubmitHandler = React.useCallback(
        (values: FormInput, form: FormikHelpers<FormInput>) => {
            setState(null);
            const game = games?.find(game => game.id === values.gameId)
            form.setSubmitting(true);
            createCompetition({name: values.name, game})
                .then(() => {
                    setState({ error: null });
                })
                .catch((error) => {
                    setState({ error });
                })
                .finally(() => {
                    form.setSubmitting(false);
                });
        },
        [games],
    );

    return (
        <Formik<FormInput>
            initialValues={{
                name: "",
                gameId: 0
            }}
            validationSchema={validationSchema}
            onSubmit={onSubmitHandler}
        >
            {(form) => (
                <Form noValidate onSubmit={form.handleSubmit} className="my-3">
                    <h2 className="my-3">Create Competition</h2>

                    <Form.Group controlId="name">
                        <Form.Label>Name of the competition</Form.Label>
                        <InputGroup hasValidation>
                            <Form.Control
                                name="name"
                                type="name"
                                value={form.values.name}
                                isInvalid={form.touched.name === true && form.errors.name != null}
                                onChange={form.handleChange}
                                onBlur={form.handleBlur}
                                placeholder="Legends League"
                            />

                            <Form.Control.Feedback type="invalid">{form.errors.name}</Form.Control.Feedback>
                        </InputGroup>
                        <Form.Label>Game</Form.Label>
                        <InputGroup hasValidation>
                            <Form.Control
                                as="select"
                                onChange={form.handleChange}
                                value={form.values.gameId}
                                placeholder="e.g. Foxes vs Piranhas"
                                onBlur={form.handleBlur}
                                isInvalid={form.touched.name === true && !games?.find(game => game.id === form.values.gameId)}
                            >
                                {games?.map((game: Game) => (
                                    <option key={game.id} value={game.id}>{game.name}</option>
                                ))}
                            </Form.Control>

                            <Form.Control.Feedback type="invalid">{form.errors.name}</Form.Control.Feedback>
                        </InputGroup>
                    </Form.Group>

                    {state?.error != null && <Alert variant="danger">{state.error.message}</Alert>}
                    {gamesError != null && <Alert variant="danger">{gamesError.message}</Alert>}

                    {success && (
                        <Alert variant="success">
                            <Alert.Heading>Competition created!</Alert.Heading>
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
                        Create Competition
                    </Button>
                </Form>
            )}
        </Formik>
    );
};

export default CreateCompetitionForm;

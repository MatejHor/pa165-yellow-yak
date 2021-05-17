import * as React from "react";

type State<E extends Error> = null | { error: E | null };

const useState = <E extends Error = Error>() => {
  return React.useState<State<E>>(null);
};

export default useState;

export const isSuccess = <E extends Error>(state: State<E>) => {
  return state !== null && state.error === null;
};

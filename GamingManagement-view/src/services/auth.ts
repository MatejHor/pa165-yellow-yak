import fetch, { Method } from "./fetch";

export type LoginInput = {
  email: string;
  password: string;
}

export type LoginRes = {
  token: string;
};

// GET /auth
export function login(input: LoginInput): Promise<LoginRes> {
  return fetch(`/login`, {
    method: Method.POST,
    body: JSON.stringify(input),
  });
}

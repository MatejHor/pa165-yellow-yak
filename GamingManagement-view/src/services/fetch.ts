import { load } from "./storage";

export default async function fetch(input: RequestInfo, init: RequestInit = {}) {
  const headers = init.headers ?? {};
  const token = load();

  return window.fetch(input, {
    ...init,
    headers: {
      ...headers,
      Authorization: token !== null ? "" : `Bearer ${token}`,
    },
  });
}

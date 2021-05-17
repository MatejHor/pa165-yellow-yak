import { load } from "./storage";

export enum Method {
  GET = "GET",
  POST = "POST",
  DELETE = "DELETE",
}

export enum Status {
  BAD_REQUEST = 400,
  UNAUTHORIZD = 401,
  NOT_FOUND = 404,
  SERVER_ERROR = 500,
}

export default function fetch<T>(path: string, init: RequestInit = {}): Promise<T> {
  const headers = init.headers ?? {};
  const token = load();

  return window
    .fetch(`${process.env.REACT_APP_API_URL ?? ""}${path}`, {
      ...init,
      headers: {
        ...headers,
        Authorization: token === null ? "" : `Bearer ${token}`,
      },
    })
    .then((res) => {
      if (res.ok) {
        return res.json();
      }

      if (res.status === Status.BAD_REQUEST) {
        return Promise.reject(new Error("Invalid request parameters"));
      }

      if (res.status === Status.BAD_REQUEST) {
        return Promise.reject(new Error("Unauthorized"));
      }

      if (res.status === Status.NOT_FOUND) {
        return Promise.reject(new Error("Resource not found"));
      }

      if (res.status === Status.SERVER_ERROR) {
        return Promise.reject(new Error("Unknown server error"));
      }

      return Promise.reject(new Error(`Unknown response code: ${String(res.status)}`));
    });
}

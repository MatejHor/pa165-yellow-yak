import useSWR from "swr";

import fetch, { Method } from "./fetch";
import type Team from "../records/Team";
// === QUERY ===
// GET /teams/:id
export function useTeam(id: string) {
  return useSWR<Team>(`/teams/${id}`, fetch);
}
// GET /teams?name=
export function useTeamsByName(name: string) {
  return useSWR<Team[]>(name.length >= 1 ? `/teams/name/${name}` : null, fetch);
}
// === MUTATIONS ===
export type CreateTeamInput = {
  name: string;
};
// POST /teams
export function createTeam(input: CreateTeamInput): Promise<Team> {
  return fetch(`/teams/create`, {
    method: Method.POST,
    body: JSON.stringify(input),
  });
}
// DELETE /teams/:id
export function deleteTeam(id: number): Promise<null> {
  return fetch(`/teams/${id}`, {
    method: Method.DELETE,
  }).then(() => null);
}

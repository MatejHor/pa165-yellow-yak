import fetch, { Method } from "./fetch";

import Player from "../records/Player";
import useSWR from "swr";

// === QUERY ===

// GET /players/:id
export function usePlayer(id: string) {
  return useSWR<Player>(`/players/${id}`, fetch);
}

export type CreatePlayerInput = {
  username: string;
  email: string;
};

// GET /players?username=
export function usePlayersByUsername(username: string) {
  return useSWR<Player[]>(username.length < 1 ? null : `/players?username=${username}`, fetch);
}

// GET /players?team=
export function usePlayersByTeam(teamId: string | null) {
  return useSWR<Player[]>(teamId ?? `/players?team=${teamId}`, fetch);
}

// === MUTATIONS ===

// POST /players
export function createPlayer(input: CreatePlayerInput): Promise<Player> {
  return fetch(`/players/`, {
    method: Method.POST,
    body: JSON.stringify(input),
  });
}

// DELETE /players/:id
export function deletePlayer(id: number): Promise<null> {
  return fetch(`/players/${id}`, {
    method: Method.DELETE,
  }).then(() => null);
}

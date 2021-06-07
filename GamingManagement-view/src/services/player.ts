import useSWR from "swr";

import fetch, { Method } from "./fetch";
import type Player from "../records/Player";

// === QUERY ===

// GET /players/:id
export function usePlayer(id: string) {
  return useSWR<Player>(`/players/${id}`, fetch);
}

// GET /players?username=
export function usePlayersByUsername(username: string) {
  return useSWR<Player[]>(username.length < 1 ? null : `/players/username/${username}`, fetch);
}

// GET /players?team=
export function usePlayersByTeam(teamId: string | null) {
  return useSWR<Player[]>(teamId === null ? null : `/players/team/${teamId}`, fetch);
}

// === MUTATIONS ===

export type CreatePlayerInput = {
  username: string;
  email: string;
  teamId: number;
};

// POST /players
export function createPlayer({ username, email, teamId }: CreatePlayerInput): Promise<Player> {
  return fetch(`/players/create`, {
    method: Method.POST,
    body: JSON.stringify({
      username,
      email,
      team: {
        id: teamId,
      },
    }),
  });
}

// DELETE /players/:id
export function deletePlayer(id: number): Promise<null> {
  return fetch(`/players/${id}`, {
    method: Method.DELETE,
  }).then(() => null);
}

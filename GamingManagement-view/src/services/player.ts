import fetch, { Method } from "./fetch";

type Player = {
  id: number;
  username: string;
  email: string;
};

// GET /players/:id
export async function getPlayer(id: string): Promise<Player> {
  return fetch(`/players/${id}`);
}

type CreatePlayerInput = {
  username: string;
  email: string;
};

// POST /players
export async function createPlayer(input: CreatePlayerInput): Promise<Player> {
  return fetch(`/players/`, {
    method: Method.POST,
    body: JSON.stringify(input),
  });
}

// DELETE /players/:id
export async function deletePlayer(id: string): Promise<null> {
  return fetch(`/players/${id}`, {
    method: Method.DELETE,
  }).then(() => null);
}

// GET /players?username=
export async function listPlayersByUsername(username: string): Promise<Player[]> {
  return fetch(`/players?username=${username}`);
}

// GET /players?team=
export async function listPlayersByTeam(team: string): Promise<Player[]> {
  return fetch(`/players?team=${team}`);
}

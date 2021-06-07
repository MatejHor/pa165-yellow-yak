import useSWR from "swr";

import fetch, { Method } from "./fetch";

export type Game = {
  id: number;
  name: string;
  createdAt: string;
};

type PostCreateGameResponse = Game;

type GetGamesByNameResponse = Game[];
type GetAllGames = Game[];

export type CreateGameInput = {
  name: string;
};

// POST /games
export function createGame(input: CreateGameInput): Promise<PostCreateGameResponse> {
  return fetch<PostCreateGameResponse>(`/games/create`, {
    method: Method.POST,
    body: JSON.stringify(input),
  });
}

// GET /games?name=
export function useGamesByName(name: string) {
  return useSWR<GetGamesByNameResponse>(name.length > 0 ? `/games/name/${name}` : null, fetch);
}

// GET /games
export function useAllGames() {
  return useSWR<GetAllGames>(`/games`, fetch);
}

// DELETE /games/:id
export function deleteGame(id: number): Promise<null> {
  return fetch(`/games/${id}`, {
    method: Method.DELETE,
  }).then(() => null);
}

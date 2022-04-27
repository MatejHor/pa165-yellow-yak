import useSWR from "swr";

import fetch, { Method } from "./fetch";
import type { Game } from "./game";

export type Competition = {
    id: number;
    name: string;
    prices: string;
    game: Game;
    createdAt: string;
    startedAt: string;
    finishedAt: string;
};

type PostCreateCompetitionResponse = Competition;

type GetCompetitionsByGameId = Competition[];
type GetAllCompetitions = Competition[];

export type CreateCompetitionInput = {
    name: string;
    game?: {
        id: number,
        name: string
    };
};

// POST /competitions/create
export function createCompetition(input: CreateCompetitionInput): Promise<PostCreateCompetitionResponse> {
    return fetch<PostCreateCompetitionResponse>(`/competitions/create`, {
        method: Method.POST,
        body: JSON.stringify(input),
    });
}

// GET /competitions
export function useAllCompetitions() {
    return useSWR<GetAllCompetitions>(`/competitions`, fetch);
}

// GET /competitions/game/:gameId
export function useCompetitionsByGameId(gameId: number) {
    return useSWR<GetCompetitionsByGameId>(gameId > 0 ? `/competitions/game/${gameId}` : null, fetch);
}


// DELETE /competitions/:id
export function deleteCompetition(id: number): Promise<null> {
    return fetch(`/competitions/${id}`, {
        method: Method.DELETE,
    }).then(() => null);
}

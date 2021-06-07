import useSWR from "swr";

import fetch, { Method } from "./fetch";
import type Player from "../records/Player";
import type { Competition } from "./competition";


export type Score = {
    player: Player;
    competition: Competition;
    id: number;
    placement: number;
    createdAt: string;
    result: string;
    stats: string;
};

type PostCreateScoreResponse = Score;

type GetScoreByCompetitionId = Score[];
type GetScoreByGameIdPlayerId = Score[];

export type CreateScoreInput = {
    competition: {
        id: number;
    }
    player: {
        id: number,
    };
};

export type SetScoreResultInput = {
    id: number,
    result: string
}

// POST /scores/create
export function createScore(input: CreateScoreInput): Promise<PostCreateScoreResponse> {
    return fetch<PostCreateScoreResponse>(`/scores/create`, {
        method: Method.POST,
        body: JSON.stringify(input),
    });
}

// POST /scores/result
export function setResult(input: SetScoreResultInput): Promise<PostCreateScoreResponse> {
    return fetch<PostCreateScoreResponse>(`/scores/result`, {
        method: Method.POST,
        body: JSON.stringify(input),
    });
}

// GET /scores/findBy/CompetitionId/:competitionId
export function useScoreByCompetitionId(competitionId: number) {
    return useSWR<GetScoreByCompetitionId>(competitionId > 0 ? `/scores/findBy/CompetitionId/${competitionId}` : null, fetch);
}

// GET /scores/findBy/GameId/:gameId/PlayerId/:playerId
export function useScoreByGameIdPlayerId(gameId: number, playerId: number) {
    return useSWR<GetScoreByGameIdPlayerId>(gameId > 0 && playerId > 0 ? `/scores/findBy/GameId/${gameId}/PlayerId/${playerId}` : null, fetch);
}


// DELETE /scores/:id
export function deleteScore(id: number): Promise<null> {
    return fetch(`/scores/${id}`, {
        method: Method.DELETE,
    }).then(() => null);
}

# Yellow Yak

## Authors, github name:
1. Matej Horniak, MatejHor
2. Lukas Mikula, Lukinooo 
3. Matej Knazik, D1LL1G4F
4. Boris Petrenko, oreqizer

## Assigment
### Online gaming management system
The goal of the system is to manage teams of users participating to eSports competitions. The system should allow to
track the different team members with their records and statistics depending on different online games. It should also
allow to manage the teams and their results in the competitions against other teams. Competitions with other teams can
be set in some location together with prizes and score results from all the participants. History of all the results can
be seen by the users with the best teams according to different games and statistics.

##  Class diagram

![Alt text](wiki/ClassDiagram.png?raw=true "Class diagram")

## UseCase diagram

![Alt text](wiki/UseCaseDiagram.PNG "UseCase diagram")

## API

Competition:
* `GET /competitions/:id` -> `CompetitionFacade.findById`
* `POST /competitions` -> `CompetitionFacade.create`
* `DELETE /competitions/:id` -> `CompetitionFacade.remove`
* `GET /competitions?game=` -> `CompetitionFacade.listByGame`

Game:
* `GET /games/:id` -> `GameFacade.findById`
* `POST /games` -> `GameFacade.create`
* `DELETE /games/:id` -> `GameFacade.remove`
* `GET /games?name=` -> `GameFacade.listByName`
* `GET /games` -> `GameFacade.listAll`

Player:
* `GET /players/:id` -> `PlayerFacade.findById`
* `POST /players` -> `PlayerFacade.create`
* `DELETE /players/:id` -> `PlayerFacade.remove`
* `GET /players?username=` -> `PlayerFacade.listByUsername`
* `GET /players?team=` -> `PlayerFacade.listByTeam`

Score:
* `GET /score/:id` -> `ScoreFacade.findById`
* `POST /score` -> `ScoreFacade.create`
* `POST /score/result` -> `ScoreFacade.setResult` business 1
* `DELETE /score/:id` -> `ScoreFacade.remove`
* `GET /score?game=&player=` -> `ScoreFacade.listByPlayerGame`
* `GET /score?competition=` -> `ScoreFacade.listByCompetition` business 2
  
Team:
* `GET /teams/:id` -> `TeamFacade.findById`
* `POST /teams` -> `TeamFacade.create`
* `DELETE /teams/:id` -> `TeamFacade.remove`
* `POST /teams/:id/players` -> `TeamFacade.addPlayer`
* `DELETE /teams/:id/players/:id` -> `TeamFacade.removePlayer`
* `GET /teams?player=` -> `TeamFacade.listByPlayer`
* `GET /teams?name=` -> `TeamFacade.listByName`
* `GET /teams?competition=` -> `TeamFacade.listByCompetition`

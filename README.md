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

## TODO

```java
// TODO @Matej Knazik and @Boris Petrenko,
//  Work divide by yourself.
//  One can write half of the implementation and half of the test,
//  and second, write another half.
//  Or One can write all implementation and the second write tests.

// Je potrebne spravit Create, Remove a Find implementacie vo Facade
// a vsetky potrebne veci pre nich v servisoch,
// taktiez spravit testy, pre service a facade,
// pokial budete pridavat metodu do DAO, treba spravit test aj pre nu v DAO
// v servisoch by ste mali uz osetrovat aj DAO metody,
// pouzivajte Dozer pre mapovanie DTO objektov a Entity z Persistance
```

- [x] DTOs
- [x] Facade interfaces
- [ ] Services by facade interfaces
  - [ ] Competition, Player @oreqizer
  - [ ] Game, Team @mknazik
- [ ] DAOs by services (as needed)
- [ ] DAO tests (as needed)
- [ ] Service tests
  - [ ] Game, Team @oreqizer
  - [ ] Competition, Player @mknazik
- [ ] Facade implementation
  - [ ] Competition, Player @oreqizer
  - [ ] Game, Team @mknazik
- [ ] Facade tests
  - [ ] Game, Team @oreqizer
  - [ ] Competition, Player @mknazik

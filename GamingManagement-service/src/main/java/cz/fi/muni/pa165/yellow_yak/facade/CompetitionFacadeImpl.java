package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.CompetitionDTO;

import java.time.LocalDateTime;
import java.util.List;

public class CompetitionFacadeImpl implements CompetitionFacade{
    @Override
    // TODO D1LL1G4F
    public CompetitionDTO create(Long gameId, String name) {
        return null;
    }

    @Override
    // TODO oreqizer
    public void remove(Long id) {

    }

    @Override
    // TODO D1LL1G4F
    public CompetitionDTO findById(Long id) {
        return null;
    }

    @Override
    // TODO D1LL1G4F
    public List<CompetitionDTO> findByGame(Long gameId, LocalDateTime since) {
        return null;
    }
}

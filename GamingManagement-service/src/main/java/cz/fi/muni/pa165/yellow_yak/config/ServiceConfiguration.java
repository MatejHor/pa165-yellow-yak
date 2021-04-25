package cz.fi.muni.pa165.yellow_yak.config;


import cz.fi.muni.pa165.yellow_yak.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.yellow_yak.dto.CompetitionDTO;
import cz.fi.muni.pa165.yellow_yak.dto.GameDTO;
import cz.fi.muni.pa165.yellow_yak.dto.PlayerDTO;
import cz.fi.muni.pa165.yellow_yak.dto.ScoreDTO;
import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.entity.Player;
import cz.fi.muni.pa165.yellow_yak.entity.Score;
import cz.fi.muni.pa165.yellow_yak.facade.ScoreFacadeImpl;
import cz.fi.muni.pa165.yellow_yak.service.CompetitionService;
import cz.fi.muni.pa165.yellow_yak.service.GameService;
import cz.fi.muni.pa165.yellow_yak.service.PlayerService;
import cz.fi.muni.pa165.yellow_yak.service.ScoreService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses={
        CompetitionService.class, GameService.class,
        PlayerService.class, ScoreService.class,
        ScoreFacadeImpl.class
})
/**
 * @author matho
 */
public class ServiceConfiguration {


    @Bean
    public Mapper dozer(){
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    /**
     * Custom config for Dozer if needed
     * @author nguyen
     *
     */
    public class DozerCustomConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Score.class, ScoreDTO.class);
            mapping(Competition.class, CompetitionDTO.class);
            mapping(Game.class, GameDTO.class);
            mapping(Player.class, PlayerDTO.class);
        }
    }

}



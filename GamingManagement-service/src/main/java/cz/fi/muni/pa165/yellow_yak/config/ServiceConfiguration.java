package cz.fi.muni.pa165.yellow_yak.config;


import cz.fi.muni.pa165.yellow_yak.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.yellow_yak.dto.*;
import cz.fi.muni.pa165.yellow_yak.entity.*;
import cz.fi.muni.pa165.yellow_yak.facade.*;
import cz.fi.muni.pa165.yellow_yak.service.*;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Matej Horniak
 */
@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses={
        CompetitionService.class,
        GameService.class,
        PlayerService.class,
        ScoreService.class,
        TeamService.class,
        CompetitionFacade.class,
        GameFacade.class,
        PlayerFacade.class,
        ScoreFacade.class,
        TeamFacade.class
})
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
            mapping(Team.class, TeamDTO.class);
        }
    }

}



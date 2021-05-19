package cz.fi.muni.pa165.yellow_yak.controllers;

import cz.fi.muni.pa165.yellow_yak.assemblers.CompetitionResourceAssembler;
import cz.fi.muni.pa165.yellow_yak.dto.CompetitionDTO;
import cz.fi.muni.pa165.yellow_yak.facade.CompetitionFacade;
import cz.fi.muni.pa165.yellow_yak.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.yellow_yak.exceptions.ResourceNotModifiedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * REST HATEOAS Controller for Competitions
 * This class shows Spring support for full HATEOAS REST services
 * resources to be returned as ResponseEntities
 *
 */
@RestController
@RequestMapping("/Competitions_hateoas")
public class CompetitionControllerHateoas {

    final static Logger logger = LoggerFactory.getLogger(CompetitionControllerHateoas.class);

    @Autowired
    private CompetitionFacade competitionFacade;

    @Autowired
    private CompetitionResourceAssembler competitionResourceAssembler;

    /**
     *
     * Get list of Competitions
     * 
     * @return HttpEntity<CollectionModel<EntityModel<CompetitionDTO>>>
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<CollectionModel<EntityModel<CompetitionDTO>>> getCompetitions() {
        
        logger.debug("rest getCompetitions({}) hateoas");

        Collection<CompetitionDTO> CompetitionsDTO = competitionFacade.findByGame(1L);
        Collection<EntityModel<CompetitionDTO>> CompetitionResourceCollection = new ArrayList<>();

        for (CompetitionDTO p : CompetitionsDTO) {
            CompetitionResourceCollection.add(competitionResourceAssembler.toModel(p));
        }

        CollectionModel<EntityModel<CompetitionDTO>> CompetitionsResources = new CollectionModel<>(CompetitionResourceCollection);
        CompetitionsResources.add(linkTo(CompetitionControllerHateoas.class).withSelfRel());

        return new ResponseEntity<>(CompetitionsResources, HttpStatus.OK);

    }
    
    /**
     *
     * Get list of Competitions - this method also supports HTTP caching
     * See http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html#mvc-caching
     * 
     * See also http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/context/request/WebRequest.html#checkNotModified-java.lang.String-
     * 
     * The conditional request can be sent with
     * curl -i -X GET http://localhost:8080/eshop-rest/Competitions_hateoas/cached  --header 'If-None-Match: "077e8fe377ab6dfa8b765b166972ba2d6"'
     * 
     * @return HttpEntity<CollectionModel<EntityModel<CompetitionDTO>>>
     */
    @RequestMapping(value = "/cached", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<CollectionModel<EntityModel<CompetitionDTO>>> getCompetitionsCached(WebRequest webRequest) {
        
        logger.debug("rest getCompetitions({}) hateoas cached version");
       
        final Collection<CompetitionDTO> CompetitionsDTO = competitionFacade.findByGame(1L);
        final Collection<EntityModel<CompetitionDTO>> CompetitionResourceCollection = new ArrayList<>();

        for (CompetitionDTO p : CompetitionsDTO) {
            CompetitionResourceCollection.add(competitionResourceAssembler.toModel(p));
        }

        CollectionModel<EntityModel<CompetitionDTO>> CompetitionsResources = new CollectionModel<>(CompetitionResourceCollection);
        CompetitionsResources.add(linkTo(CompetitionControllerHateoas.class).withSelfRel());

        final StringBuffer eTag = new StringBuffer("\"");
        eTag.append(Integer.toString(CompetitionsResources.hashCode()));
        eTag.append('\"');
        
        if (webRequest.checkNotModified(eTag.toString())){
            throw new ResourceNotModifiedException();
        }
                
        return ResponseEntity.ok().eTag(eTag.toString()).body(CompetitionsResources);
    }

    /**
     *
     * Get one Competition according to id
     * 
     * @param id identifier for Competition
     * @return HttpEntity<EntityModel<CompetitionDTO>>
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<CompetitionDTO>> getCompetition(@PathVariable("id") long id) throws Exception {
        
        logger.debug("rest getCompetition({}) hateoas", id);

        try {
        CompetitionDTO CompetitionDTO = competitionFacade.findById(id);
            EntityModel<CompetitionDTO> resource = competitionResourceAssembler.toModel(CompetitionDTO);
            return new ResponseEntity<>(resource, HttpStatus.OK);
        } catch (Exception ex){
            throw new ResourceNotFoundException();
        }
    }
    
        /**
     * Delete one Competition by id curl -i -X DELETE
     * http://localhost:8080/eshop-rest/Competitions/1
     *
     * @param id identifier for Competition
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteCompetition(@PathVariable("id") long id) throws Exception {
        logger.debug("rest deleteCompetition({}) hateoas", id);
        try {
            competitionFacade.remove(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

}

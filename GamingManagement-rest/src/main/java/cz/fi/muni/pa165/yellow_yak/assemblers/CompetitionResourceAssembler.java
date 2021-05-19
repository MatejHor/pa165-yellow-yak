package cz.fi.muni.pa165.yellow_yak.assemblers;

import cz.fi.muni.pa165.yellow_yak.controllers.CompetitionControllerHateoas;
import cz.fi.muni.pa165.yellow_yak.dto.CompetitionDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * This class shows a resource assembler for a HATEOAS REST Service we are
 * mapping the DTO to a resource that can provide links to the different parts
 * of the API See
 * http://docs.spring.io/spring-hateoas/docs/current/reference/html/
 *
 */
@Component
public class CompetitionResourceAssembler implements RepresentationModelAssembler<CompetitionDTO, EntityModel<CompetitionDTO>> {

    @Override
    public @NotNull EntityModel<CompetitionDTO> toModel(CompetitionDTO CompetitionDTO) {
        EntityModel<CompetitionDTO> CompetitionResource = new EntityModel<>(CompetitionDTO);

        try {
//            CompetitionResource.add(linkTo(CompetitionControllerHateoas.class).slash(CompetitionDTO.()).withSelfRel());
//            CompetitionResource.add(linkTo(CompetitionControllerHateoas.class).slash(CompetitionDTO.getId()).withRel("DELETE"));

        } catch (Exception ex) {
            Logger.getLogger(CompetitionResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resource from CompetitionsControllerHateoas", ex);
        }

        return CompetitionResource;
    }
}

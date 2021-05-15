package cz.fi.muni.pa165.yellow_yak.controller;

import cz.fi.muni.pa165.yellow_yak.dto.PlayerDTO;
import cz.fi.muni.pa165.yellow_yak.facade.PlayerFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Controller
@RequestMapping("/players")
public class PlayerController {

    final static Logger log = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerFacade playerFacade;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findId(@PathVariable long id, Model model) {
        model.addAttribute("players", playerFacade.findById(id));
        return "players/id";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String findUsername(@PathVariable String username, Model model) {
        model.addAttribute("players", playerFacade.findByUsername(username));
        return "players/username";
    }

    @RequestMapping(value = "/{teamId}", method = RequestMethod.GET)
    public String findTeam(@PathVariable long teamId, Model model) {
        model.addAttribute("players", playerFacade.findByTeam(teamId));
        return "players/teamId";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        PlayerDTO player = playerFacade.findById(id);
        log.debug("delete({})", id);
        try {
            playerFacade.remove(id);
            redirectAttributes.addFlashAttribute("alert_success", "Player \"" + player.getUsername() + "\" was deleted.");
        } catch (Exception ex) {
            log.error("Player "+id+" cannot be deleted (it is included in an order)");
            log.error(NestedExceptionUtils.getMostSpecificCause(ex).getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", "Player \"" + player.getUsername() + "\" cannot be deleted.");
        }
        return "redirect:" + uriBuilder.path("/players/").toUriString();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("player") PlayerDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        log.debug("create(playerCreate={})", formBean);
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            return "player/new";
        }
        //create player
        Long id = playerFacade.create(formBean.getUsername(), formBean.getEmail()).getId();
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Player " + id + " was created");
        return "redirect:" + uriBuilder.path("/players/view/{id}").buildAndExpand(id).encode().toUriString();
    }
}

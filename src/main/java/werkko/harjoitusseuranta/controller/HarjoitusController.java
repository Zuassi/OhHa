/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import werkko.harjoitusseuranta.domain.Harjoitus;
import werkko.harjoitusseuranta.helper.SallitutTyypit;
import werkko.harjoitusseuranta.service.HarjoittelijaService;
import werkko.harjoitusseuranta.service.HarjoitusService;

/**
 *
 * @author Lalli
 */
@Controller
public class HarjoitusController {

    @Autowired
    private HarjoitusService harjoitusService;
    @Autowired
    private HarjoittelijaService harjoittelijaService;
    
 


    @RequestMapping(value = "harjoittelija/harjoitus", method = RequestMethod.POST)
    public String lisaaHarjoitus(HttpSession session, Model model,
            @Valid @ModelAttribute Harjoitus harjoitus, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        Long harjoittelijaId = (Long) session.getAttribute("harjoittelijaId");

        if (bindingResult.hasErrors()) {
            model.addAttribute("sallitutTyypit", SallitutTyypit.sallitutTyypit);
            return "lisaa-harjoitus";
        } else {
            harjoitusService.create(harjoitus, harjoittelijaId);
            redirectAttributes.addFlashAttribute("message", "treeni lisätty");
            return "redirect:/harjoittelija/";
        }
    }

    @RequestMapping(value = "harjoittelija/lisaa-harjoitus", method = RequestMethod.GET)
    public String getHarjoitusLomake(@ModelAttribute("harjoitus") Harjoitus harjoitus, Model model, HttpSession session) {

        model.addAttribute("sallitutTyypit", SallitutTyypit.sallitutTyypit);
        return "lisaa-harjoitus";

    }

    @RequestMapping(value = "harjoittelija/poista-harjoitus/{id}", method = RequestMethod.GET)
    public String poistaHarjoitus(@PathVariable("id") Long id, HttpSession session) {
        Harjoitus harjoitus = harjoitusService.read(id);
        if (harjoitus.getHarjoittelijaId() == session.getAttribute("harjoittelijaId")) {
            harjoitusService.delete(id);
        }
        return "redirect:/harjoittelija/selaa";

    }

    @RequestMapping(value = "harjoittelija/harjoitus/{id}", method = RequestMethod.GET)
    public String naytaHarjoitus(@PathVariable("id") Long id, HttpSession session, Model model) {
        Harjoitus harjoitus = harjoitusService.read(id);
        if (harjoitus.getHarjoittelijaId() == session.getAttribute("harjoittelijaId")) {
            model.addAttribute("harjoitus", harjoitus);
            return "harjoitus";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "harjoittelija/harjoitus/muokkaa/{id}", method = RequestMethod.GET)
    public String muokkaaHarjoitusta(@PathVariable("id") Long id, HttpSession session, Model model) {
        Harjoitus harjoitus = harjoitusService.read(id);
        if (harjoitus.getHarjoittelijaId() == session.getAttribute("harjoittelijaId")) {
            model.addAttribute("harjoitus", harjoitus);
            model.addAttribute("sallitutTyypit",SallitutTyypit.sallitutTyypit);
            
            return "muokkaa";
        } else {
            return "index";
        }

    }
}

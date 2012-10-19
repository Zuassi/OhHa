/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import java.util.List;
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
import werkko.harjoitusseuranta.domain.Harjoittelija;
import werkko.harjoitusseuranta.domain.Harjoitus;
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
        Long id = (Long) session.getAttribute("harjoittelijaId");
        model.addAttribute(harjoittelijaService.read(id)); // harjoitus.jsp tarvitsee harjoittelija attribuutin
        if (bindingResult.hasErrors()) {
            model.addAttribute("id", id);
            return "lisaa-harjoitus";
        } else {
            Harjoittelija harjoittelija = harjoittelijaService.read(id);
            harjoitus.setHarjoittelija(harjoittelija);
            harjoitusService.create(harjoitus);
            List<Harjoitus> harjoitukset = harjoittelija.getHarjoitukset();
            harjoitukset.add(harjoitus);
            harjoittelijaService.save(harjoittelija);

            redirectAttributes.addFlashAttribute("message", "treeni lis‰tty"); // viesti ei n‰y miss‰‰n atm
            return "redirect:/harjoittelija/";
        }
    }

    @RequestMapping(value = "harjoittelija/lisaa-harjoitus", method = RequestMethod.GET)
    public String lisaaHarjoitus(@ModelAttribute("harjoitus") Harjoitus harjoitus, Model model, HttpSession session) {
        
        model.addAttribute("id", session.getAttribute("harjoittelijaId"));
        return "lisaa-harjoitus";

    }
}

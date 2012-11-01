/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import javax.servlet.http.HttpServletRequest;
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
            @Valid @ModelAttribute Harjoitus harjoitus, BindingResult bindingResult, RedirectAttributes redirectAttributes,
            HttpServletRequest request) {

        Long harjoittelijaId = (Long) session.getAttribute("harjoittelijaId");
        model.addAttribute("sallitutTyypit", SallitutTyypit.sallitutTyypit);
       

        if (bindingResult.hasErrors()) {

      
            model.addAttribute("harjoitus", harjoitus);

            return "lisaa-harjoitus";
        } else {

            harjoitusService.create(harjoitus, harjoittelijaId);
            redirectAttributes.addAttribute("lisatty","Harjoitus lisätty!");
            
            return "redirect:/home";
        }
    }

    @RequestMapping(value = "harjoittelija/poista-harjoitus/{id}", method = RequestMethod.GET)
    public String poistaHarjoitus(@PathVariable("id") Long id, HttpSession session, HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        Harjoitus harjoitus = harjoitusService.read(id);
        if (harjoitus.getHarjoittelijaId() == session.getAttribute("harjoittelijaId")) {
            harjoitusService.delete(id);
        }
        redirectAttributes.addAttribute("page", 1);
        return "redirect:" + request.getHeader("Referer");

    }

    @RequestMapping(value = "harjoittelija/harjoitus/muokkaa/{id}", method = RequestMethod.GET)
    public String muokkaaHarjoitusta(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,
            HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("id", id);
        redirectAttributes.addFlashAttribute("harjoitus", harjoitusService.read(id));
        redirectAttributes.addFlashAttribute("page", 5);
        return "redirect: " + request.getHeader("Referer");
    }
}

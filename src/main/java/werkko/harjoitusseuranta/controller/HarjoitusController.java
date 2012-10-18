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

    @RequestMapping(value = "harjoittelija/{harjoittelijaId}/harjoitus", method = RequestMethod.POST)
    public String lisaaHarjoitus(HttpSession session, @PathVariable("harjoittelijaId") Long id, Model model,
            @Valid @ModelAttribute Harjoitus harjoitus, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!session.getAttribute("harjoittelijaId").equals(id)) {
            model.addAttribute("message", "Hups. Jokin meni pieleen");
            return "index";
        }
        model.addAttribute(harjoittelijaService.read(id)); // harjoitus.jsp tarvitsee harjoittelija attribuutin
        if (bindingResult.hasErrors()) {
          
            return "harjoittelija";
        } else {
            harjoitus.setHarjoittelijanID(id);
            harjoitusService.create(harjoitus);

            redirectAttributes.addFlashAttribute("message", "treeni lis‰tty"); // viesti ei n‰y miss‰‰n atm
            return "redirect:/harjoittelija/"+id;
        }

    }
}

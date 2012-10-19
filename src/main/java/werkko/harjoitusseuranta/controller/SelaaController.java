/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import java.util.Collections;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import werkko.harjoitusseuranta.domain.Harjoittelija;
import werkko.harjoitusseuranta.domain.comparators.PaivamaaraComparator;
import werkko.harjoitusseuranta.helper.HarjoittelijaSorttaaja;
import werkko.harjoitusseuranta.service.HarjoittelijaService;
import werkko.harjoitusseuranta.service.HarjoitusService;

/**
 *
 * @author Lalli
 */
@Controller
public class SelaaController {

    @Autowired
    private HarjoittelijaService harjoittelijaService;
    
    @Autowired
    private HarjoittelijaSorttaaja harjoittelijaSorttaaja;

    @RequestMapping(value = "harjoittelija/selaa", method = RequestMethod.GET)
    public String selaa(@ModelAttribute(value = "jarjestys") String jarjestys, Model model, HttpSession session) {

        Long harjoittelijaId = (Long) session.getAttribute("harjoittelijaId");  // springsecurityä niin näistä eroon
        if (harjoittelijaId == null) {
            return "redirect:/logout";
        }

        Harjoittelija harjoittelija = harjoittelijaService.read((Long) session.getAttribute("harjoittelijaId"));
        harjoittelija = harjoittelijaSorttaaja.jarjesta(jarjestys, harjoittelija,session);
        model.addAttribute("harjoittelija", harjoittelija);
        return "selaa";
    }

    @RequestMapping(value = "harjoittelija/selaa/teho", method = RequestMethod.GET)
    public String jarjestaTehonMukaan(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("jarjestys", "teho");
        return "redirect:/harjoittelija/selaa";

    }

    @RequestMapping(value = "harjoittelija/selaa/pvm", method = RequestMethod.GET)
    public String jarjestaTehonPvm(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("jarjestys", "pvm");
        return "redirect:/harjoittelija/selaa";

    }
}

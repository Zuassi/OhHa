/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import werkko.harjoitusseuranta.domain.Harjoitus;
import werkko.harjoitusseuranta.helper.HarjoitusSorttaaja;
import werkko.harjoitusseuranta.service.HarjoitusService;

/**
 *
 * @author Lalli
 */
@Controller
public class SelaaController {

    @Autowired
    private HarjoitusService harjoitusService;
    @Autowired
    private HarjoitusSorttaaja harjoitusSorttaaja;

    @RequestMapping(value = "harjoittelija/selaa", method = RequestMethod.GET)
    public String selaa(@ModelAttribute(value = "jarjestys") String jarjestys, Model model, HttpSession session) {
        if (session.getAttribute("harjoittelijaId") == null) {
            return "index";
        }
        Long harjoittelijaId = (Long) session.getAttribute("harjoittelijaId");
        List<Harjoitus> harjoitukset = harjoitusService.findByHarjoittelijaId(harjoittelijaId);
        harjoitukset = harjoitusSorttaaja.jarjesta(jarjestys, harjoitukset, session);
        model.addAttribute("harjoitukset", harjoitukset);
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

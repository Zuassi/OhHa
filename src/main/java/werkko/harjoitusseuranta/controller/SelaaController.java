/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import werkko.harjoitusseuranta.domain.Harjoitus;
import werkko.harjoitusseuranta.service.HarjoitusService;

/**
 *
 * @author Lalli
 */
@Controller
public class SelaaController {

    @Autowired
    private HarjoitusService harjoitusService;

    @RequestMapping(value = "harjoittelija/selaa", method = RequestMethod.GET)
    public String selaa(@RequestParam(value = "sivuNumero", required = false) Integer sivunumero,
            @RequestParam(value = "jarjestys", required = false) String jarjestys,
            Model model, HttpSession session) {
        if (session.getAttribute("harjoittelijaId") == null) {
            return "index";
        }
        if (sivunumero == null) {
            sivunumero = 1;
        }

        model.addAttribute("jarjestys", jarjestys);
        Page<Harjoitus> harjoitukset = harjoitusService.listHarjoitukset(sivunumero, 25, jarjestys, session);

        boolean sivutus = (harjoitukset.getTotalPages() != 0) ? true : false;
        model.addAttribute("sivutus", sivutus);
        model.addAttribute("sivuNumero", sivunumero);
        model.addAttribute("sivumaara", harjoitukset.getTotalPages());
        model.addAttribute("harjoitukset", harjoitukset.getContent());
        return "selaa";
    }
}

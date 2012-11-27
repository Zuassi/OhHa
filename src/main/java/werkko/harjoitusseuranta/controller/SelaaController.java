/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * Metodi palauttaa selaa sivun halutussa j‰rjestyksess‰.
     * @param sivunumero haluttu sivunumero
     * @param jarjestys j‰rjestys jossa harjoitukset halutaan n‰ytt‰‰
     * @param model talletetaan tarvittavat tiedot
     * @param session tarkastetaan et‰ k‰ytt‰j‰ on kirjautunut
     * @return selaa sivu
     */
    @RequestMapping(value = "harjoittelija/selaa", method = RequestMethod.GET)
    public String selaa(@RequestParam(value = "sivuNumero", required = false) Integer sivunumero,
            @RequestParam(value = "jarjestys", required = false) String jarjestys,
            Model model, HttpSession session) {

        if (session.getAttribute("harjoittelijaId") == null) {
      
            return "redirect:/";
        }
        
        model.addAttribute("jarjestys", jarjestys);
        Page<Harjoitus> harjoitukset = harjoitusService.listHarjoitukset(sivunumero, 6, jarjestys, (Long)session.getAttribute("harjoittelijaId"));
        model.addAttribute("harjoitukset",harjoitukset);
        boolean sivutus = (harjoitukset.getTotalPages() != 0) ? true : false;
        model.addAttribute("sivutus", sivutus);
        model.addAttribute("sivuNumero", sivunumero);
        model.addAttribute("sivumaara", harjoitukset.getTotalPages());
        model.addAttribute("harjoitukset", harjoitukset.getContent());

        return "kokonaiset_sivut/selaa";
    }
}

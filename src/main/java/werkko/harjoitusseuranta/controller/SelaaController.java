/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
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
            RedirectAttributes redirectAttributes, HttpSession session,
            HttpServletRequest request) {

        if (session.getAttribute("harjoittelijaId") == null) {
            redirectAttributes.addAttribute("login_message","Istunto vanhentunut");
            return "redirect:/";
        }
        System.out.println("Miten me tänne jouduttiin?");
        if (sivunumero == null) {
            sivunumero = 1;
        }

        redirectAttributes.addFlashAttribute("jarjestys", jarjestys);
        Page<Harjoitus> harjoitukset = harjoitusService.listHarjoitukset(sivunumero, 6, jarjestys, session);

        boolean sivutus = (harjoitukset.getTotalPages() != 0) ? true : false;
        redirectAttributes.addFlashAttribute("sivutus", sivutus);
        redirectAttributes.addFlashAttribute("sivuNumero", sivunumero);
        redirectAttributes.addFlashAttribute("sivumaara", harjoitukset.getTotalPages());
        redirectAttributes.addFlashAttribute("harjoitukset", harjoitukset.getContent());
        redirectAttributes.addFlashAttribute("page",1);
        return "redirect:"+request.getHeader("Referer");
    }
}

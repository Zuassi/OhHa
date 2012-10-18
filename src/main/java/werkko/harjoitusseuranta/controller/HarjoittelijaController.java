/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import werkko.harjoitusseuranta.domain.Harjoittelija;
import werkko.harjoitusseuranta.domain.Harjoitus;
import werkko.harjoitusseuranta.service.HarjoittelijaService;

/**
 *
 * @author Lalli
 */
@Controller
public class HarjoittelijaController {

    @Autowired
    private HarjoittelijaService harjoittelijaService;
    private Md5PasswordEncoder md5 = new Md5PasswordEncoder();

    @RequestMapping(value = "rekisterointi", method = RequestMethod.GET)
    public String rekisterointiLomake(@ModelAttribute("harjoittelija") Harjoittelija harjoittelija) {

        return "rekisterointi";
    }

    @RequestMapping(value = "rekisterointi", method = RequestMethod.POST)
    public String rekisterointi(@Valid @ModelAttribute Harjoittelija harjoittelija,
            BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {

            return "rekisterointi";
        }
        if (harjoittelijaService.findByNimi(harjoittelija.getNimi()) != null) {
            model.addAttribute("message", "Nimi on jo käytössä");
            return "rekisterointi";
        }

        String md5salasana = md5.encodePassword(harjoittelija.getSalasana(), null);
        harjoittelija.setSalasana(md5salasana);
        harjoittelija.setNimi(StringEscapeUtils.escapeHtml4(harjoittelija.getNimi()));
        harjoittelijaService.create(harjoittelija);
        model.addAttribute("harjoittelija", harjoittelija);
        session.setAttribute("harjoittelijaId", harjoittelija.getId());

        return "redirect:harjoittelija/" + harjoittelija.getId();

    }

    @RequestMapping(value = "kirjaudu", method = RequestMethod.POST)
    public String kirjaudu(@RequestParam("nimi") String nimi, @RequestParam("salasana") String salasana, HttpSession session,
            RedirectAttributes redirectAttributes, Model model) {


        if (harjoittelijaService.findByNimi(nimi) != null) {
            String md5salasana = md5.encodePassword(salasana, null);
            Harjoittelija harjoittelija = harjoittelijaService.findByNimi(nimi);
            if (harjoittelija.getSalasana().equals(md5salasana)) {
                session.setAttribute("harjoittelijaId", harjoittelija.getId());
                redirectAttributes.addAttribute("harjoittelijaId", harjoittelija.getId());
                return "redirect:harjoittelija/{harjoittelijaId}";
            }
        }
        model.addAttribute("message", "Väärä salasana tai käyttäjänimi");
        return "index";
    }

    @RequestMapping(value = "harjoittelija/{harjoittelijaId}", method = RequestMethod.GET)
    public String getHarjoittelija(Model model, HttpSession session, @PathVariable("harjoittelijaId") Long id,
            @ModelAttribute("harjoitus") Harjoitus harjoitus) {
        if (session.getAttribute("harjoittelijaId") != null
                && session.getAttribute("harjoittelijaId").equals(id)) {
            model.addAttribute("harjoittelija", harjoittelijaService.read((Long) session.getAttribute("harjoittelijaId")));
            return "harjoittelija";
        }
        model.addAttribute("message", "Hups. Jotain meni pieleen.");
        return "index";
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import werkko.harjoitusseuranta.domain.Harjoittelija;
import werkko.harjoitusseuranta.service.HarjoittelijaService;

/**
 *
 * @author Lalli
 */
@Controller
public class EtusivuController {

    @Autowired
    private HarjoittelijaService harjoittelijaService;
    private Md5PasswordEncoder md5 = new Md5PasswordEncoder();

    @RequestMapping(value = "/")
    public String etusivu(HttpSession session) {
        //tarkastetaan että sessionissa oleva id on olemassa tietokannassa 
        //jos database on satuttu uusimaan, ongelma lähinnä softaa koodatessa kun database tyhjenee vähänväliä
        if (session.getAttribute("harjoittelijaId") != null && harjoittelijaService.read((Long) session.getAttribute("harjoittelijaId")) == null) {
            return "redirect:logout";
        }

        // harjoittelija id ei ole null = ollaan jo sisällä joten ohjataan omalle sivulle
        if (session.getAttribute("harjoittelijaId") != null) {
            return "redirect:harjoittelija/lisaa-harjoitus";
        }

        return "index";
    }

    //tuhotaan sessioni
    @RequestMapping(value = "logout")
    public String logout(Model model, HttpSession session) {

        session.invalidate();
        model.addAttribute("message", "Kirjauduit ulos onnistuneesti");
        return "index";
    }

    @RequestMapping(value = "kirjaudu", method = RequestMethod.POST)
    public String kirjaudu(@RequestParam("nimi") String nimi, @RequestParam("salasana") String salasana, HttpSession session,
            RedirectAttributes redirectAttributes, Model model) {

        //jos servicestä löytyy kyseinen nimi tarkastetaan että md5 kryptatut
        //passut on samat ja sen jälkeen asetetaan sessioniin id
        if (harjoittelijaService.findByNimi(nimi) != null) {
            String md5salasana = md5.encodePassword(salasana, null);
            Harjoittelija harjoittelija = harjoittelijaService.findByNimi(nimi);
            if (harjoittelija.getSalasana().equals(md5salasana)) {
                session.setAttribute("harjoittelijaId", harjoittelija.getId());
                redirectAttributes.addAttribute("harjoittelijaId", harjoittelija.getId());
                return "redirect:harjoittelija/lisaa-harjoitus";
            }
        }
        model.addAttribute("message", "Väärä salasana tai käyttäjänimi");
        return "index";
    }
}

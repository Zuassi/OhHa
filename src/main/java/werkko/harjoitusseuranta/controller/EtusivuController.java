/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import java.util.HashMap;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import werkko.harjoitusseuranta.controller.form.AikavaliForm;
import werkko.harjoitusseuranta.domain.Harjoittelija;
import werkko.harjoitusseuranta.service.HarjoittelijaService;
import werkko.harjoitusseuranta.service.TilastoService;

/**
 *
 * @author Lalli
 */
@Controller
public class EtusivuController {

    @Autowired
    private TilastoService tilastoService;
    @Autowired
    private HarjoittelijaService harjoittelijaService;
    private Md5PasswordEncoder md5 = new Md5PasswordEncoder();

    @RequestMapping(value = "/")
    public String etusivu(HttpSession session, @ModelAttribute("harjoittelija") Harjoittelija harjoittelija,
            Model model, @ModelAttribute("AikavaliForm") AikavaliForm aikavaliForm) {

        //tarkastetaan ett� sessionissa oleva id on olemassa tietokannassa 
        //jos database on satuttu uusimaan, ongelma l�hinn� softaa koodatessa kun database tyhjenee v�h�nv�li�
        if (session.getAttribute("harjoittelijaId") != null && harjoittelijaService.read((Long) session.getAttribute("harjoittelijaId")) == null) {
            return "redirect:logout";
        }

        // harjoittelija id ei ole null = ollaan jo sis�ll� joten ohjataan omalle sivulle
        if (session.getAttribute("harjoittelijaId") != null) {
            return "redirect:home";
        }       
        return "index";

    }

    //tuhotaan sessioni
    @RequestMapping(value = "logout")
    public String logout(RedirectAttributes redirectAttributes, HttpSession session) {

        session.invalidate();
        redirectAttributes.addFlashAttribute("login_message", "Kirjauduit ulos onnistuneesti");
        return "redirect:/";
    }

    @RequestMapping(value = "kirjaudu", method = RequestMethod.POST)
    public String kirjaudu(@RequestParam("nimi") String nimi, @RequestParam("salasana") String salasana, HttpSession session,
            RedirectAttributes redirectAttributes) {

        //jos servicest� l�ytyy kyseinen nimi tarkastetaan ett� md5 kryptatut
        //passut on samat ja sen j�lkeen asetetaan sessioniin id
        if (harjoittelijaService.findByNimi(nimi) != null) {
            String md5salasana = md5.encodePassword(salasana, null);
            Harjoittelija harjoittelija = harjoittelijaService.findByNimi(nimi);
            if (harjoittelija.getSalasana().equals(md5salasana)) {
                session.setAttribute("harjoittelijaId", harjoittelija.getId());
                
                return "redirect:/home";
            }
        }
        redirectAttributes.addFlashAttribute("login_message", "V��r� salasana tai k�ytt�j�nimi");
        return "redirect:/";
    }
}

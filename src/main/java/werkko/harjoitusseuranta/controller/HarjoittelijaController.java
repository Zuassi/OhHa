/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import werkko.harjoitusseuranta.domain.Harjoittelija;
import werkko.harjoitusseuranta.domain.Seurantaavain;
import werkko.harjoitusseuranta.service.HarjoittelijaService;
import werkko.harjoitusseuranta.service.SeurantaavainService;

/**
 *
 * @author Lalli
 */
@Controller
public class HarjoittelijaController {

    @Autowired
    private HarjoittelijaService harjoittelijaService;
    private Md5PasswordEncoder md5 = new Md5PasswordEncoder();
    @Autowired
    private SeurantaavainService avainService;

    @PostConstruct
    private void init() {
//        Harjoittelija harjoittelija = new Harjoittelija();
//        harjoittelija.setNimi("asdasd");
//        harjoittelija.setSalasana("asdasd");
//        harjoittelijaService.create(harjoittelija);
    }

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
        harjoittelijaService.create(harjoittelija);
        session.setAttribute("harjoittelijaId", harjoittelija.getId());
        return "redirect:harjoittelija";

    }

    @RequestMapping(value = "harjoittelija", method = RequestMethod.GET)
    public String getHarjoittelija(Model model, HttpSession session) {
        if (session.getAttribute("harjoittelijaId") == null) {
            return "index";
        }
        model.addAttribute("harjoittelija", harjoittelijaService.read((Long) session.getAttribute("harjoittelijaId")));
        return "harjoittelija";
    }

    @RequestMapping(value = "harjoittelija/asetukset", method = RequestMethod.GET)
    public String getAsetukset(String message, Model model, HttpSession session) {
        if (session.getAttribute("harjoittelijaId") == null) {
            return "index";
        }
        model.addAttribute("avaimet", avainService.findByHarjoittelijaId((Long) session.getAttribute("harjoittelijaId")));
        Harjoittelija harjoittelija = harjoittelijaService.read((Long) session.getAttribute("harjoittelijaId"));
        return "asetukset";
    }

    @RequestMapping(value = "harjoittelija/asetukset/salasana", method = RequestMethod.POST)
    public String vaihdaSalasana(HttpSession session,
            @RequestParam("vanha_salasana") String vanhaSalasana,
            @RequestParam("uusi_salasana") String uusiSalasana,
            @RequestParam("uusi_salasana2") String uusiSalasana2,
            RedirectAttributes redirectAttributes) {
        if (session.getAttribute("harjoittelijaId") == null) {
            return "index";
        }
        String message = harjoittelijaService.vaihdaSalasana(session, vanhaSalasana, uusiSalasana, uusiSalasana2);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/harjoittelija/asetukset";
    }

    @RequestMapping(value = "harjoittelija/asetukset/luo_avain", method = RequestMethod.POST)
    public String luoAvain(HttpSession session, @RequestParam("nimi") String nimi, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("harjoittelijaId") == null) {
            return "index";
        }
        if (nimi.length()<1) {
            redirectAttributes.addFlashAttribute("avain_message","Anna avaimen omistajan nimi");
            return "redirect:/harjoittelija/asetukset";
        }
        Seurantaavain avain = new Seurantaavain();
        avain.setAvaimenOmistaja(nimi);
        avain.setAvain(UUID.randomUUID().toString());
        avain.setHarjoittelijaId((Long) session.getAttribute("harjoittelijaId"));
        avainService.create(avain);
        return "redirect:/harjoittelija/asetukset";
    }

    @RequestMapping(value = "harjoittelija/asetukset/poista_avain", method = RequestMethod.POST)
    public String poistaAvain(HttpSession session, @RequestParam("avainId") Long id) {
        avainService.delete(id);
        return "redirect:/harjoittelija/asetukset";
    }
}

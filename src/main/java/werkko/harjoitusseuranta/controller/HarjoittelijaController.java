/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import werkko.harjoitusseuranta.domain.Harjoitus;
import werkko.harjoitusseuranta.domain.Seurantaavain;
import werkko.harjoitusseuranta.helper.SallitutTyypit;
import werkko.harjoitusseuranta.service.HarjoittelijaService;
import werkko.harjoitusseuranta.service.HarjoitusService;
import werkko.harjoitusseuranta.service.SeurantaavainService;
import werkko.harjoitusseuranta.service.TilastoService;

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
    @Autowired
    private TilastoService tilastoService;
    @Autowired
    private HarjoitusService harjoitusService;

    @PostConstruct
    private void init() {
        Harjoittelija harjoittelija = new Harjoittelija();
        harjoittelija.setNimi("asdasd");
        harjoittelija.setSalasana("asdasd");
        harjoittelijaService.create(harjoittelija);
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home(@ModelAttribute Harjoitus harjoitus, Model model) {
        model.addAttribute("sallitutTyypit", SallitutTyypit.sallitutTyypit);


        return "home";
    }

    @RequestMapping(value = "rekisterointi", method = RequestMethod.GET)
    public String getRekisterointi(@ModelAttribute Harjoittelija harjoittelija) {
        return "kokonaiset_sivut/rekisterointi";
    }

    @RequestMapping(value = "rekisterointi", method = RequestMethod.POST)
    public String rekisterointi(@Valid @ModelAttribute Harjoittelija harjoittelija,
            BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "index_rekisteroinnilla";
        }
        if (harjoittelijaService.findByNimi(harjoittelija.getNimi()) != null) {
            model.addAttribute("register_message", "Nimi on jo käytössä");

            return "index_rekisteroinnilla";
        }
        harjoittelijaService.create(harjoittelija);
        session.setAttribute("harjoittelijaId", harjoittelija.getId());
        return "redirect:home";

    }

    @RequestMapping(value = "harjoittelija/asetukset", method = RequestMethod.GET)
    public String getAsetukset(Model model, HttpSession session) {
        model.addAttribute("avaimet", avainService.findByHarjoittelijaId((Long) session.getAttribute("harjoittelijaId")));
        return "kokonaiset_sivut/asetukset";
    }

    @RequestMapping(value = "harjoittelija/asetukset/salasana", method = RequestMethod.POST)
    public String vaihdaSalasana(HttpSession session,
            @RequestParam("vanha_salasana") String vanhaSalasana,
            @RequestParam("uusi_salasana") String uusiSalasana,
            @RequestParam("uusi_salasana2") String uusiSalasana2,
            RedirectAttributes redirectAttributes, Model model) {
        if (session.getAttribute("harjoittelijaId") == null) {
            redirectAttributes.addAttribute("login_message", "Istunto vanhentunut");
            return "redirect:/";
        }
        String message = harjoittelijaService.vaihdaSalasana(session, vanhaSalasana, uusiSalasana, uusiSalasana2);
        model.addAttribute("message", message);

        return "asetukset_salasana";
    }

    @RequestMapping(value = "harjoittelija/asetukset/luo_avain", method = RequestMethod.POST)
    public String luoAvain(HttpSession session, @RequestParam("nimi") String nimi, Model model,
            HttpServletRequest request) {
        if (session.getAttribute("harjoittelijaId") == null) {
            model.addAttribute("login_message", "Istunto vanhentunut");
            return "redirect:/";
        }
        model.addAttribute("avaimet", avainService.findByHarjoittelijaId((Long) session.getAttribute("harjoittelijaId")));
        if (nimi.length() < 1) {
            model.addAttribute("avain_message", "Anna avaimen omistajan nimi");
            return "asetukset";
        }
        Seurantaavain avain = new Seurantaavain();
        avain.setAvaimenOmistaja(nimi);
        avain.setAvain(UUID.randomUUID().toString());
        avain.setHarjoittelijaId((Long) session.getAttribute("harjoittelijaId"));
        avainService.create(avain);
        model.addAttribute("avaimet", avainService.findByHarjoittelijaId((Long) session.getAttribute("harjoittelijaId")));
        return "asetukset";
    }

    @RequestMapping(value = "harjoittelija/asetukset/poista_avain", method = RequestMethod.POST)
    public String poistaAvain(Model model, HttpSession session, @RequestParam("avainId") Long id,
            HttpServletRequest request) {
        if (avainService.read(id) != null) {
            avainService.delete(id);
        }
        model.addAttribute("avaimet", avainService.findByHarjoittelijaId((Long) session.getAttribute("harjoittelijaId")));
        return "asetukset";
    }

    public void sivutus(Model model, Integer sivunumero, String jarjestys, HttpSession session) {
        if (sivunumero == null) {
            sivunumero = 1;
        } else {
            model.addAttribute("page", 1);
        }

        model.addAttribute("jarjestys", jarjestys);
        Page<Harjoitus> harjoitukset = harjoitusService.listHarjoitukset(sivunumero, 6, jarjestys, session);

        boolean sivutus = (harjoitukset.getTotalPages() != 0) ? true : false;
        model.addAttribute("sivutus", sivutus);
        model.addAttribute("sivuNumero", sivunumero);
        model.addAttribute("sivumaara", harjoitukset.getTotalPages());
        model.addAttribute("harjoitukset", harjoitukset.getContent());
    }
}

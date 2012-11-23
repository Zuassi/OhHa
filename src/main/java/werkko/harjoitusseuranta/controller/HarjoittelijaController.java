/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
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

    /**
     * Luo tunnuksen nimell� asdasd ja salasanalla asdasd testaamista varten
     *
     */
    @PostConstruct
    private void init() {
        Harjoittelija harjoittelija = new Harjoittelija();
        harjoittelija.setNimi("asdasd");
        harjoittelija.setSalasana("asdasd");
        if (harjoittelijaService.findByNimi("asdasd") == null) {
            harjoittelijaService.create(harjoittelija);
        }
    }

    /**
     * Palauttaa homen ensimm�isen sivun eli "lis�� harjoitus" sivun ja
     * tallettaa modeliin SallitutTyypit listan joka sis�lt�� erilaiset
     * harjoitustyypit
     *
     * @param harjoitus Harjoitusformin validointiin ja luomiseen k�ytett�v�
     * olio
     * @param model
     * @return lis�� harjoitussivu sis�lt� ja home
     */
    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home(@ModelAttribute Harjoitus harjoitus, Model model) {
        model.addAttribute("sallitutTyypit", SallitutTyypit.sallitutTyypit);


        return "home";
    }

    /**
     * Palauttaa rekiste�rintisivun
     *
     * @param harjoittelija harjoittelija Rekist�iryess� validointia ja formin
     * luomista auttava olio
     * @return rekister�itymissivu
     */
    @RequestMapping(value = "rekisterointi", method = RequestMethod.GET)
    public String getRekisterointi(@ModelAttribute Harjoittelija harjoittelija) {
        return "kokonaiset_sivut/rekisterointi";
    }

    /**
     * Metodi ottaa vastaan rekister�itymisi�, tarkastaa ett� ne on kunnossa ja
     * palauttaa takasin rekister�intisivulle, tai asettaa sessioniin uuden
     * harjoittelijaId:n ja p��st�� home sivulle
     *
     * @param harjoittelija harjoittelija olio jonka k�ytt�j� on l�hett�nyt,
     * tarkistaa onko kyseess� oikeanlainen olioi.
     * @param bindingResult tarkastetaan sis�lt��k� virheit�
     *
     * @param session talletetaan uusi harjoittelijaId
     * @return palautetaan joko rekister�intisivu tai home
     */
    @RequestMapping(value = "rekisterointi", method = RequestMethod.POST)
    public String rekisterointi(@Valid @ModelAttribute Harjoittelija harjoittelija,
            BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "kokonaiset_sivut/rekisterointi";
        }
        if (harjoittelijaService.findByNimi(harjoittelija.getNimi()) != null) {
            model.addAttribute("register_message","K�ytt�j�nimi on jo k�yt�ss�");
            return "kokonaiset_sivut/rekisterointi";
        }
        harjoittelijaService.create(harjoittelija);
        session.setAttribute("harjoittelijaId", harjoittelija.getId());
        return "redirect:home";

    }

    /**
     * Metodi palauttaa asetukset sivu mik�li k�ytt�j� on kirjautunut ja
     * tallettaa modeliin k�ytt�j�n luomat seuranta-avaimet
     *
     * @param model modeliin talletetaan seuranta-avaimet
     * @param session sessionista tarkastetaan kirjautuminen
     * @return palauttaa asetukset sivun jos k�ytt�j� on kirjautunut
     */
    @RequestMapping(value = "harjoittelija/asetukset", method = RequestMethod.GET)
    public String getAsetukset(Model model, HttpSession session) {
        if (session.getAttribute("harjoittelijaId") == null) {
            return "redirect:/";
        }
        model.addAttribute("avaimet", avainService.findByHarjoittelijaId((Long) session.getAttribute("harjoittelijaId")));
        return "kokonaiset_sivut/asetukset";
    }

    /**
     * Metodi tarkastaa ett� k�ytt�j� on kirjautunut sis��n, sitten salasanaan
     * liittyv�t tiedot l�hetet��n servicelle. Service palauttaa onnistuiko
     * salasanan vaihto vai ei, jonka j�lkeen palautetaan asetukset sivun
     * salasana divi.
     *
     * @param session sessionista tarkastetaan onko k�yttj� kirjautunut
     * @param vanhaSalasana k�ytt�j�n kirjoittama entinen salasana
     * @param uusiSalasana k�ytt�j�n kirjoittama uusi salasana
     * @param uusiSalasana2 k�ytt�j�n kirjoittama uusi salasana uudestaan
     * @param redirectAttributes tallennetaan
     * @param model talletaan harjoittelijaservicen palauttama message
     * @return palauttaa asetukset_salasana sivunpalan
     */
    @RequestMapping(value = "harjoittelija/asetukset/salasana", method = RequestMethod.POST)
    public String vaihdaSalasana(HttpSession session,
            @RequestParam("vanha_salasana") String vanhaSalasana,
            @RequestParam("uusi_salasana") String uusiSalasana,
            @RequestParam("uusi_salasana2") String uusiSalasana2,
            RedirectAttributes redirectAttributes, Model model) {
        if (session.getAttribute("harjoittelijaId") == null) {
            return "redirect:/";
        }
        String message = harjoittelijaService.vaihdaSalasana(session, vanhaSalasana, uusiSalasana, uusiSalasana2);
        model.addAttribute("message", message);

        return "asetukset_salasana";
    }

    /**
     * Metodin avulla luodaan uusi seuranta-avain halutulla nimell�
     *
     * @param session tarkastetaan onko k�ytt�j� kirjautunut
     * @param nimi k�ytt�j�n antama avaimen nimi
     * @param model talletetaan uudet avaimet
     * @return palauttaa asetukset sivun
     */
    @RequestMapping(value = "harjoittelija/asetukset/luo_avain", method = RequestMethod.POST)
    public String luoAvain(HttpSession session, @RequestParam("nimi") String nimi, Model model) {
        if (session.getAttribute("harjoittelijaId") == null) {
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

    /**
     * Metodi poistaa halutun avaimen mik�li se kuuluu k�ytt�j�lle ja sen j�lkeen
     * tallettaa modeliin j�ljelle j��neet avaimet ja palauttaa 
     *
     * @param model 
     * @param session
     * @param id poistettavan avaimen id
     * @return palauttaa asetukset sivun sis�ll�n
     */
    @RequestMapping(value = "harjoittelija/asetukset/poista_avain", method = RequestMethod.POST)
    public String poistaAvain(Model model, HttpSession session, @RequestParam("avainId") Long id) {
        if (kayttajaOmistaaAvaimen(session, id)) {
            avainService.delete(id);
        }
        model.addAttribute("avaimet", avainService.findByHarjoittelijaId((Long) session.getAttribute("harjoittelijaId")));
        return "asetukset";
    }

 /**
  * Metodi tarkastaa omistaako k�ytt�j� avaimen
  * @param session k�ytt�j�n sessioni josta tarkastetaan harjoittelijaId
  * @param id halutun seuranta-avaimen id
  * @return kuuluuko avain k�ytt�j�lle
  */
    
    private boolean kayttajaOmistaaAvaimen(HttpSession session, Long id) {
        Harjoittelija harjoittelija = harjoittelijaService.read((Long) session.getAttribute("harjoittelijaId"));
        Seurantaavain avain = avainService.read(id);
        Long avaimenOmistaja = avain.getHarjoittelijaId();
        return avaimenOmistaja.equals(harjoittelija.getId());
    }
}

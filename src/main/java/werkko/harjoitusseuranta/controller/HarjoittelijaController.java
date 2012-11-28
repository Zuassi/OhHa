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
import werkko.harjoitusseuranta.domain.Harjoitus;
import werkko.harjoitusseuranta.domain.Seurantaavain;
import werkko.harjoitusseuranta.helper.SallitutTyypit;
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



    /**
     * Luo tunnuksen nimellä asdasd ja salasanalla asdasd testaamista varten
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
     * Palauttaa homen ensimmäisen sivun eli "lisää harjoitus" sivun ja
     * tallettaa modeliin SallitutTyypit listan joka sisältää erilaiset
     * harjoitustyypit
     *
     * @param harjoitus Harjoitusformin validointiin ja luomiseen käytettävä
     * olio
     * @param model
     * @return lisää harjoitussivu sisältö ja home
     */
    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home(@ModelAttribute Harjoitus harjoitus, Model model) {
        model.addAttribute("sallitutTyypit", SallitutTyypit.sallitutTyypit);


        return "home";
    }

    /**
     * Palauttaa rekisteörintisivun
     *
     * @param harjoittelija harjoittelija Rekistöiryessä validointia ja formin
     * luomista auttava olio
     * @return rekisteröitymissivu
     */
    @RequestMapping(value = "rekisterointi", method = RequestMethod.GET)
    public String getRekisterointi(@ModelAttribute Harjoittelija harjoittelija) {
        return "kokonaiset_sivut/rekisterointi";
    }

    /**
     * Metodi ottaa vastaan rekisteröitymisiä, tarkastaa että ne on kunnossa ja
     * palauttaa takasin rekisteröintisivulle, tai asettaa sessioniin uuden
     * harjoittelijaId:n ja päästää home sivulle
     *
     * @param harjoittelija harjoittelija olio jonka käyttäjä on lähettänyt,
     * tarkistaa onko kyseessä oikeanlainen olioi.
     * @param bindingResult tarkastetaan sisältääkö virheitä
     *
     * @param session talletetaan uusi harjoittelijaId
     * @return palautetaan joko rekisteröintisivu tai home
     */
    @RequestMapping(value = "rekisterointi", method = RequestMethod.POST)
    public String rekisterointi(@Valid @ModelAttribute Harjoittelija harjoittelija,
            BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "kokonaiset_sivut/rekisterointi";
        }
        if (harjoittelijaService.findByNimi(harjoittelija.getNimi()) != null) {
            model.addAttribute("register_message","Käyttäjänimi on jo käytössä");
            return "kokonaiset_sivut/rekisterointi";
        }
        harjoittelijaService.create(harjoittelija);
        session.setAttribute("harjoittelijaId", harjoittelija.getId());
        return "redirect:home";

    }

    /**
     * Metodi palauttaa asetukset sivu mikäli käyttäjä on kirjautunut ja
     * tallettaa modeliin käyttäjän luomat seuranta-avaimet
     *
     * @param model modeliin talletetaan seuranta-avaimet
     * @param session sessionista tarkastetaan kirjautuminen
     * @return palauttaa asetukset sivun jos käyttäjä on kirjautunut
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
     * Metodi tarkastaa että käyttäjä on kirjautunut sisään, sitten salasanaan
     * liittyvät tiedot lähetetään servicelle. Service palauttaa onnistuiko
     * salasanan vaihto vai ei, jonka jälkeen palautetaan asetukset sivun
     * salasana divi.
     *
     * @param session sessionista tarkastetaan onko käyttjä kirjautunut
     * @param vanhaSalasana käyttäjän kirjoittama entinen salasana
     * @param uusiSalasana käyttäjän kirjoittama uusi salasana
     * @param uusiSalasana2 käyttäjän kirjoittama uusi salasana uudestaan
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
        String message = harjoittelijaService.vaihdaSalasana((Long)session.getAttribute("harjoittelijaId"), vanhaSalasana, uusiSalasana, uusiSalasana2);
        model.addAttribute("message", message);

        return "asetukset_salasana";
    }

    /**
     * Metodin avulla luodaan uusi seuranta-avain halutulla nimellä
     *
     * @param session tarkastetaan onko käyttäjä kirjautunut
     * @param nimi käyttäjän antama avaimen nimi
     * @param model talletetaan uudet avaimet
     * @return palauttaa asetukset sivun
     */
    @RequestMapping(value = "harjoittelija/asetukset/luo_avain", method = RequestMethod.POST)
    public String luoAvain(HttpSession session, @RequestParam("nimi") String nimi, Model model) {
        if (session.getAttribute("harjoittelijaId") == null) {
            return "redirect:/";
        }
       
        if (nimi.length() < 1) {
            model.addAttribute("avain_message", "Anna avaimen omistajan nimi");
            return "asetukset";
        }
        Seurantaavain avain = new Seurantaavain();
        avain.setAvaimenOmistaja(nimi);
        avain.setAvain(UUID.randomUUID().toString());
        avain.setHarjoittelijaId((Long) session.getAttribute("harjoittelijaId"));
        avainService.create(avain);
        return "redirect:/harjoittelija/avaimet";
    }
    
    /**Palauttaa halutun käyttäjän avaimet
     *
     * @param model
     * @param session
     * @return halutun käyttäjän avaimet sisältä sivu
     */
    @RequestMapping(value="harjoittelija/avaimet")
    public String getAvaimet(Model model,HttpSession session){
         model.addAttribute("avaimet", avainService.findByHarjoittelijaId((Long) session.getAttribute("harjoittelijaId")));
         return "asetukset";
    }

    /**
     * Metodi poistaa halutun avaimen mikäli se kuuluu käyttäjälle ja sen jälkeen
     * tallettaa modeliin jäljelle jääneet avaimet ja palauttaa 
     *
     * @param model 
     * @param session
     * @param id poistettavan avaimen id
     * @return palauttaa asetukset sivun sisällön
     */
    @RequestMapping(value = "harjoittelija/asetukset/poista_avain", method = RequestMethod.POST)
    public String poistaAvain(Model model, HttpSession session, @RequestParam("avainId") Long id) {
        if (kayttajaOmistaaAvaimen(session, id)) {
            avainService.delete(id);
        }
        return "redirect:/harjoittelija/avaimet";
    }

 /**
  * Metodi tarkastaa omistaako käyttäjä avaimen
  * @param session käyttäjän sessioni josta tarkastetaan harjoittelijaId
  * @param id halutun seuranta-avaimen id
  * @return kuuluuko avain käyttäjälle
  */
    
    private boolean kayttajaOmistaaAvaimen(HttpSession session, Long id) {
       
        Seurantaavain avain = avainService.read(id);
        Long avaimenOmistaja = avain.getHarjoittelijaId();
        return avaimenOmistaja.equals((Long)session.getAttribute("harjoittelijaId"));
    }
}

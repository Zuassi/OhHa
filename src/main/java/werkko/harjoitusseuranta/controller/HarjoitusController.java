
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import werkko.harjoitusseuranta.domain.Harjoitus;
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
public class HarjoitusController {

    @Autowired
    private HarjoitusService harjoitusService;
    @Autowired
    private HarjoittelijaService harjoittelijaService;
    @Autowired
    private SeurantaavainService avainService;
    @Autowired
    private TilastoService tilastoService;

    /**
     * Palauttaa lisää harjoitus sivun
     *
     * @param harjoitus Harjoitusformin luomista ja tarkastamista auttava olio
     * @param model talletetaan sallituttyypit
     * @return lisaa-harjoitus sivun osa
     */
    @RequestMapping(value = "harjoittelija/harjoitus", method = RequestMethod.GET)
    public String getLisaaHarjoitus(@ModelAttribute Harjoitus harjoitus, Model model) {
        model.addAttribute("sallitutTyypit", SallitutTyypit.sallitutTyypit);
        return "kokonaiset_sivut/lisaa-harjoitus";
    }

    /**
     * Metodi ottaa vastaan harjoitusten lisäämiset ja muokkaukset. Jos 
     * lähetetyllä harjoituksella on jo id, on kyseessä muokkaus. Muuten 
     * luodaan uusi harjoitus. 
     * @param session tarkastetaan harjoittelijaId
     * @param model 
     * @param harjoitus sisältää lähetetyn harjoitusolion
     * @param bindingResult tarkastetaan sisältääkö lähetetty harjoitus virheitä
     * @return 
     */
    @RequestMapping(value = "harjoittelija/harjoitus", method = RequestMethod.POST)
    public String lisaaHarjoitus(HttpSession session, Model model,
            @Valid @ModelAttribute Harjoitus harjoitus, BindingResult bindingResult) {
        Long harjoittelijaId = (Long) session.getAttribute("harjoittelijaId");
        model.addAttribute("sallitutTyypit", SallitutTyypit.sallitutTyypit);
        if (bindingResult.hasErrors()) {
            model.addAttribute("harjoitus", harjoitus);
      
            //kyseessä muokkaus
            if (harjoitus.getId() != null) {
                return "redirect:/harjoittelija/selaa";
            }
            return "kokonaiset_sivut/lisaa-harjoitus";
        } else {
            harjoitusService.create(harjoitus, harjoittelijaId);
            //kyseessä muokkaus
            if (harjoitus.getId() != null) {
                return "redirect:/harjoittelija/selaa";
            }
            return "kokonaiset_sivut/lisaa-harjoitus";
        }
    }

    /**
     * Metodi poistaa halutun harjoituksen
     * @param id Poistettavan harjoituksen id
     * @param session tarkastetaan kuuluuko harjoitus harjoittelijalle
     * @return palauttaa selaa sivun
     */
    @RequestMapping(value = "harjoittelija/poista-harjoitus/{id}", method = RequestMethod.GET)
    public String poistaHarjoitus(@PathVariable("id") Long id, HttpSession session) {
        Harjoitus harjoitus = harjoitusService.read(id);
        if (harjoitus.getHarjoittelijaId() == session.getAttribute("harjoittelijaId")) {
            harjoitusService.delete(id);
        }

        return "redirect:/harjoittelija/selaa";

    }

    /**
     * Näyttää halutun harjoituksen sisällön
     * @param model Tallettaa modeliin sisällön
     * @param id etsittävän harjoituksen id
     * @param session sessionin avulla tarkastetaan että harjoitus kuuluu harjoittelijalle
     * @return haluttu harjoitus
     */
    @RequestMapping(value = "harjoittelija/harjoitus/{id}", method = RequestMethod.GET)
    public String naytaHarjoitus(Model model, @PathVariable("id") Long id, HttpSession session) {
        Harjoitus harjoitus = harjoitusService.read(id);
        if (harjoitus.getHarjoittelijaId() == (Long) session.getAttribute("harjoittelijaId")) {
            model.addAttribute("harjoitus", harjoitus.getSisalto());
        }

        return "harjoitus";
    }

    /**
     * Metodin avulla palautetaan muokkausnäkymä
     * @param id harjoituksen id
     * @param model
     * @return muokkaa sivu
     */
    @RequestMapping(value = "harjoittelija/harjoitus/muokkaa/{id}", method = RequestMethod.GET)
    public String muokkaaHarjoitusta(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("harjoitus", harjoitusService.read(id));
        model.addAttribute("sallitutTyypit", SallitutTyypit.sallitutTyypit);
        return "kokonaiset_sivut/muokkaa";
    }
}

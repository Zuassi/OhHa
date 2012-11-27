/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import werkko.harjoitusseuranta.controller.form.AikavaliForm;
import werkko.harjoitusseuranta.service.HarjoittelijaService;
import werkko.harjoitusseuranta.service.SeurantaavainService;
import werkko.harjoitusseuranta.service.TilastoService;

/**
 *
 * @author Lalli
 */
@Controller
public class TilastoController {

    @Autowired
    private TilastoService tilastoService;
    @Autowired
    private HarjoittelijaService harjoittelijaService;
    @Autowired
    private SeurantaavainService seurantaService;

    /**
     * Palauttaa k‰ytt‰j‰n harjoittelutilastot sivun
     *
     * @param aikavali k‰ytt‰j‰n itseasettama aikav‰li jota halutaan tarkkailla
     * @param session sis‰lt‰‰ harjoittelijaId:n
     * @param model
     * @return tilastosivu
     */
    @RequestMapping(value = "harjoittelija/tilasto", method = RequestMethod.GET)
    public String getTilasto(@ModelAttribute("aikavali") AikavaliForm aikavali, HttpSession session, Model model) {
        Date alkamisaika = (Date) session.getAttribute("alkamisaika");
        Date loppumisaika = (Date) session.getAttribute("loppumisaika");
        model.addAttribute("tilasto", tilastoService.keraaTilastot(alkamisaika, loppumisaika,
                harjoittelijaService.read((Long) session.getAttribute("harjoittelijaId"))));
        return "kokonaiset_sivut/tilasto";
    }

    /**
     * Metodi asettaa ja hakee harjoitustilastot halutulta aikav‰lilt‰
     *
     * @param form aikav‰lin formin validointiin ja luomiseen k‰yttett‰v‰ olio
     * @param bindingResult sis‰lt‰‰ mahdolliset virheet aikav‰lin asetukseen
     * liittyen
     * @param session sis‰lt‰‰ harjoittelijaIdn
     * @param model
     * @return tilastosivu
     */
    @RequestMapping(value = "harjoittelija/tilasto", method = RequestMethod.POST)
    public String setOmaAikavali(@Valid @ModelAttribute("aikavali") AikavaliForm form, BindingResult bindingResult,
            HttpSession session, Model model) {
        model.addAttribute("seurantaAsetettu", true);
        session.setAttribute("alkamisaika", form.getAlkamisaika());
        session.setAttribute("loppumisaika", form.getLoppumisaika());
        if (session.getAttribute("harjoittelijaId") != null) {
            model.addAttribute("tilasto", tilastoService.keraaTilastot(form.getAlkamisaika(), form.getLoppumisaika(),
                    harjoittelijaService.read((Long) session.getAttribute("harjoittelijaId"))));
        } else {
            String avain = (String) session.getAttribute("avain");

            model.addAttribute("tilasto", tilastoService.findTilastoByHarjoittelijaSeurantaAvain(avain, form.getAlkamisaika(), form.getLoppumisaika()));
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("seuranta_error", "Anna p‰iv‰m‰‰r‰ oikeassa muodossa");
            return "tilasto";
        }


        return "tilasto";
    }

    /**
     * Tarkastaa lˆytyykˆ seuranta-avainta ja sitten l‰hett‰‰ siihen liittyv‰t
     * tiedot
     *
     * @param AikavaliForm aikav‰lin formin validointiin ja luomiseen
     * k‰yttett‰v‰ olio
     * @param session sis‰lt‰‰ harjoittelijaIdn
     * @param model
     * @param seurantaAvain k‰ytt‰j‰n l‰hett‰m‰ seurattava harjoittelija id
     * @return seuranta
     */
    @RequestMapping(value = "seuranta", method = RequestMethod.POST)
    public String etsiSeurattava(@ModelAttribute("aikavali") AikavaliForm AikavaliForm,
            HttpSession session, Model model,
            @RequestParam("avain") String seurantaAvain) {
        if (seurantaService.findByAvain(seurantaAvain) != null) {
            session.setAttribute("avain", seurantaAvain);
            model.addAttribute("seurantaAsetettu", true);
            String avain = (String) session.getAttribute("avain");
            Date alkamisaika = (Date) session.getAttribute("alkamisaika");
            Date loppumisaika = (Date) session.getAttribute("loppumisaika");
            model.addAttribute("tilasto", tilastoService.findTilastoByHarjoittelijaSeurantaAvain(avain, alkamisaika,loppumisaika));

        } else {
            model.addAttribute("seuranta_message", "Tuntematon seurantakoodi");
        }

        return "seuranta";
    }

    @RequestMapping(value = "vierasseuranta", method = RequestMethod.GET)
    public String vierasSeuranta(@ModelAttribute("aikavali") AikavaliForm AikavaliForm) {

        return "seuranta";
    }
}

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
import org.springframework.web.bind.annotation.PathVariable;
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
public class TilastoController {

    @Autowired
    private TilastoService tilastoService;
    @Autowired
    private HarjoittelijaService harjoittelijaService;

    @RequestMapping(value = "harjoittelija/tilasto", method = RequestMethod.GET)
    public String getTilastot(@ModelAttribute("AikavaliForm") AikavaliForm AikavaliForm, Model model, HttpSession session) {
        Date alkamisaika;
        Date loppumisaika;
        if (session.getAttribute("harjoittelijaId") != null) {
            Harjoittelija harjoittelija = harjoittelijaService.read((Long) session.getAttribute("harjoittelijaId"));
            model.addAttribute("tilasto", tilastoService.keraaTilastot(session, harjoittelija));
            return "tilasto";
        } else {
            return "index";
        }

    }

    @RequestMapping(value = "harjoittelija/tilasto", method = RequestMethod.POST)
    public String setOmaAikavali(@Valid @ModelAttribute("AikavaliForm") AikavaliForm form, BindingResult bindingResult,
            HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "tilasto";
        }
        session.setAttribute("alkamisaika", form.getAlkamisaika());
        session.setAttribute("loppumisaika", form.getLoppumisaika());

        return "redirect:/harjoittelija/tilasto";
    }

    @RequestMapping(value = "seuranta", method = RequestMethod.GET)
    public String seuranta(@ModelAttribute("AikavaliForm") AikavaliForm AikavaliForm) {
        return "seuranta";
    }

    @RequestMapping(value = "seuranta", method = RequestMethod.POST)
    public String etsiSeurattava(@ModelAttribute("AikavaliForm") AikavaliForm AikavaliForm, HttpSession session, Model model, @RequestParam("avain") String seurantaAvain) {
        Harjoittelija harjoittelija = harjoittelijaService.findBySeurantaAvain(seurantaAvain);
        if (harjoittelija != null) {
            model.addAttribute("tilasto", tilastoService.keraaTilastot(session, harjoittelija));
            model.addAttribute("seurantaAsetettu", true);
        }else{
            model.addAttribute("message","Tuntematon seuranta-avain");
        }
        return "seuranta";
    }
}

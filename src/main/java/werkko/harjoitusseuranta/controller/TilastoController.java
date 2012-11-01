/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import werkko.harjoitusseuranta.controller.form.AikavaliForm;
import werkko.harjoitusseuranta.domain.Harjoittelija;
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

 

    @RequestMapping(value = "harjoittelija/tilasto", method = RequestMethod.POST)
    public String setOmaAikavali(@Valid @ModelAttribute("AikavaliForm") AikavaliForm form, BindingResult bindingResult,
            HttpSession session, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("seurantaAsetettu", true);
        redirectAttributes.addFlashAttribute("page", 2);
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("seuranta_error", "Anna p‰iv‰m‰‰r‰ oikeassa muodossa");

            return "redirect:" + request.getHeader("Referer");
        }
        session.setAttribute("alkamisaika", form.getAlkamisaika());
        session.setAttribute("loppumisaika", form.getLoppumisaika());

        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "seuranta", method = RequestMethod.POST)
    public String etsiSeurattava(@ModelAttribute("AikavaliForm") AikavaliForm AikavaliForm,
            HttpSession session, RedirectAttributes redirectAttributes,
            @RequestParam("avain") String seurantaAvain) {
        if (seurantaService.findByAvain(seurantaAvain) != null) {
            session.setAttribute("avain", seurantaAvain);

        } else {
            redirectAttributes.addFlashAttribute("seuranta_message", "Tuntematon seurantakoodi");
        }
        redirectAttributes.addFlashAttribute("page", 2);
        return "redirect:/";
    }
}

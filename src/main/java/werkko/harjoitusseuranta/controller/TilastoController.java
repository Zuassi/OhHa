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
    
    
    @RequestMapping(value = "harjoittelija/tilasto", method = RequestMethod.GET)
    public String getTilasto(@ModelAttribute("aikavali") AikavaliForm aikavali, HttpSession session, Model model) {
      
        model.addAttribute("tilasto", tilastoService.keraaTilastot(session,
                harjoittelijaService.read((Long) session.getAttribute("harjoittelijaId"))));
        return "kokonaiset_sivut/tilasto";
    }

    @RequestMapping(value = "harjoittelija/tilasto", method = RequestMethod.POST)
    public String setOmaAikavali(@Valid @ModelAttribute("aikavali") AikavaliForm form, BindingResult bindingResult,
            HttpSession session, HttpServletRequest request, Model model) {
        model.addAttribute("seurantaAsetettu", true);
           model.addAttribute("tilasto", tilastoService.keraaTilastot(session,
                harjoittelijaService.read((Long) session.getAttribute("harjoittelijaId"))));
  
        if (bindingResult.hasErrors()) {
            model.addAttribute("seuranta_error", "Anna p‰iv‰m‰‰r‰ oikeassa muodossa");
            return "kokonaiset_sivut/tilasto";
        }
        session.setAttribute("alkamisaika", form.getAlkamisaika());
        session.setAttribute("loppumisaika", form.getLoppumisaika());
       
        return "kokonaiset_sivut/tilasto";
    }

    @RequestMapping(value = "seuranta", method = RequestMethod.POST)
    public String etsiSeurattava(@ModelAttribute("aikavali") AikavaliForm AikavaliForm,
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

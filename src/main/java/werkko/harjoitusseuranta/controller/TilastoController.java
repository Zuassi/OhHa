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
import werkko.harjoitusseuranta.service.TilastoService;

/**
 *
 * @author Lalli
 */
@Controller
public class TilastoController {

    @Autowired
    private TilastoService tilastoService;

    @RequestMapping(value = "harjoittelija/tilasto", method = RequestMethod.GET)
    public String getTilastot(@ModelAttribute("AikavaliForm") AikavaliForm AikavaliForm, Model model, HttpSession session) {
        Date alkamisaika;
        Date loppumisaika;
        if (session.getAttribute("harjoittelijaId") != null) {
        
            model.addAttribute("tilasto", tilastoService.keraaTilastot(session));
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
}

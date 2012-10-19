/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

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
import werkko.harjoitusseuranta.domain.Harjoittelija;
import werkko.harjoitusseuranta.service.HarjoittelijaService;

/**
 *
 * @author Lalli
 */
@Controller
public class HarjoittelijaController {

    @Autowired
    private HarjoittelijaService harjoittelijaService;
    private Md5PasswordEncoder md5 = new Md5PasswordEncoder();
    
    @PostConstruct
    private void init(){
        Harjoittelija harjoittelija = new Harjoittelija();
        harjoittelija.setNimi("asdasd");
        harjoittelija.setSalasana("asdasd");
        harjoittelijaService.create(harjoittelija);
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
        if(session.getAttribute("harjoittelijaId")==null){
            return "index";
        }
        model.addAttribute("harjoittelija", harjoittelijaService.read((Long) session.getAttribute("harjoittelijaId")));
        return "harjoittelija";


    }
}

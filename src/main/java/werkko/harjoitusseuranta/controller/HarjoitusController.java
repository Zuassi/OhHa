/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import werkko.harjoitusseuranta.domain.Harjoitus;
import werkko.harjoitusseuranta.service.HarjoitusService;

/**
 *
 * @author Lalli
 */
@Controller
public class HarjoitusController {
    @Autowired
    private HarjoitusService service;
    
    @RequestMapping(value="harjoittelija/{harjoittelijaId}/harjoitus", method = RequestMethod.POST)
    public String lisaaHarjoitus(HttpSession session, @PathVariable("harjoittelijanId")Long id,Model model,
            @Valid @ModelAttribute Harjoitus harjoitus){
        if(!session.getAttribute("harjoittelijanId").equals(id)){
            model.addAttribute("message","Hups. Jokin meni pieleen");
            return "index";
        }else{
            harjoitus.setHarjoittelijanID(id);
            service.create(harjoitus);
        }
        return null;
    }
    
}

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
    
    @RequestMapping(value="harjoittelija/harjoitus", method = RequestMethod.GET)
    public String getLisaaHarjoitus(@ModelAttribute Harjoitus harjoitus, Model model){
        model.addAttribute("sallitutTyypit",SallitutTyypit.sallitutTyypit);
        return "kokonaiset_sivut/lisaa-harjoitus";
    }

    @RequestMapping(value = "harjoittelija/harjoitus", method = RequestMethod.POST)
    public String lisaaHarjoitus(HttpSession session, Model model,
            @Valid @ModelAttribute Harjoitus harjoitus, BindingResult bindingResult, RedirectAttributes redirectAttributes,
            HttpServletRequest request) {

        Long harjoittelijaId = (Long) session.getAttribute("harjoittelijaId");
        model.addAttribute("sallitutTyypit", SallitutTyypit.sallitutTyypit);


        if (bindingResult.hasErrors()) {


            model.addAttribute("harjoitus", harjoitus);
            model.addAttribute("avaimet", avainService.findByHarjoittelijaId((Long) session.getAttribute("harjoittelijaId")));
            model.addAttribute("tilasto", tilastoService.keraaTilastot(session,
                    harjoittelijaService.read((Long) session.getAttribute("harjoittelijaId"))));
            model.addAttribute("sallitutTyypit", SallitutTyypit.sallitutTyypit);
            //kyseessä muokkaus
                if(harjoitus.getId()!=null){
                return "redirect:/harjoittelija/selaa";
            }
            return "kokonaiset_sivut/lisaa-harjoitus";
        } else {
         
          

            harjoitusService.create(harjoitus, harjoittelijaId);
               //kyseessä muokkaus
              if(harjoitus.getId()!=null){
                return "redirect:/harjoittelija/selaa";
            }
           return "kokonaiset_sivut/lisaa-harjoitus";
        }
    }

    @RequestMapping(value = "harjoittelija/poista-harjoitus/{id}", method = RequestMethod.GET)
    public String poistaHarjoitus(@PathVariable("id") Long id, HttpSession session, HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
        Harjoitus harjoitus = harjoitusService.read(id);
        if (harjoitus.getHarjoittelijaId() == session.getAttribute("harjoittelijaId")) {
            harjoitusService.delete(id);
        }
       
        return "redirect:/harjoittelija/selaa";

    }
    
    @RequestMapping(value="harjoittelija/harjoitus/{id}", method = RequestMethod.GET)
    public String naytaHarjoitus(Model model, @PathVariable("id")Long id, HttpSession session){
        Harjoitus harjoitus = harjoitusService.findById(id);
        if(harjoitus.getHarjoittelijaId() ==(Long)session.getAttribute("harjoittelijaId")){
           model.addAttribute("harjoitus",harjoitus.getSisalto());
        }
         
        return "harjoitus";
    }

    @RequestMapping(value = "harjoittelija/harjoitus/muokkaa/{id}", method = RequestMethod.GET)
    public String muokkaaHarjoitusta(@PathVariable("id") Long id, Model model,
            HttpServletRequest request) {
        model.addAttribute("id", id);
        model.addAttribute("harjoitus", harjoitusService.read(id));
        model.addAttribute("page", 5);
        model.addAttribute("sallitutTyypit",SallitutTyypit.sallitutTyypit);
        return "kokonaiset_sivut/muokkaa";
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import werkko.harjoitusseuranta.service.HarjoittelijaService;

/**
 *
 * @author Lalli
 */
@Controller
public class EtusivuController {
    @Autowired 
    private HarjoittelijaService harjoittelijaService;
    
     @RequestMapping(value = "/")
     public String etusivu(HttpSession session){
         //tarkastetaan että sessionissa oleva id on olemassa tietokannassa 
         //jos database on satuttu uusimaan
         if(session.getAttribute("harjoittelijaId") != null && harjoittelijaService.read((Long)session.getAttribute("harjoittelijaId"))==null){
             return "redirect:logout";
         }
         
         if(session.getAttribute("harjoittelijaId")!=null){
             return "redirect:harjoittelija/"+session.getAttribute("harjoittelijaId");
         }
         return "index";
     }
     
     @RequestMapping(value ="logout")
     public String logout(Model model, HttpSession session){
         
         session.invalidate();
         model.addAttribute("message","Kirjauduit ulos onnistuneesti");
         return "index";
     }
    
}

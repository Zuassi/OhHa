/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Lalli
 */
@Controller
public class EtusivuController {
    
     @RequestMapping(value = "/")
     public String etusivu(){
         return "index";
     }
     
     @RequestMapping(value ="logout")
     public String logout(Model model, HttpSession session){
         
         session.invalidate();
         model.addAttribute("message","Kirjauduit ulos onnistuneesti");
         return "index";
     }
    
}

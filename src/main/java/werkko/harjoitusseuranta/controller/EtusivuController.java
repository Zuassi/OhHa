/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller;

import org.springframework.stereotype.Controller;
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
    
}

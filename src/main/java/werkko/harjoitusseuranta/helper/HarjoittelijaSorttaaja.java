/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.helper;

import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import werkko.harjoitusseuranta.domain.Harjoittelija;
import werkko.harjoitusseuranta.domain.Harjoitus;
import werkko.harjoitusseuranta.domain.comparators.PaivamaaraComparator;
import werkko.harjoitusseuranta.domain.comparators.TehoComparator;

/**
 *
 * @author Lalli
 */
@Component
public class HarjoittelijaSorttaaja {

    public static Harjoittelija jarjesta(String jarjestys, Harjoittelija harjoittelija, HttpSession session) {

        List<Harjoitus> harjoitukset = harjoittelija.getHarjoitukset();

        if (jarjestys == null || jarjestys.equals("pvm")) {

            Collections.sort(harjoitukset, new PaivamaaraComparator());
        
        } else if (jarjestys.equals("teho")) {
            Collections.sort(harjoitukset, new TehoComparator());
         

        }
      
        if (session.getAttribute("viimeisin") != null && session.getAttribute("viimeisin").equals(jarjestys)) {
            Collections.reverse(harjoitukset);
            session.setAttribute("viimeisin",null);

        }else{
            session.setAttribute("viimeisin",jarjestys);
        }
          harjoittelija.setHarjoitukset(harjoitukset);
        return harjoittelija;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.service;

import java.util.List;
import javax.servlet.http.HttpSession;
import werkko.harjoitusseuranta.domain.Harjoittelija;
import werkko.harjoitusseuranta.domain.Harjoitus;

/**
 *
 * @author Lalli
 */
public interface HarjoittelijaService {
    Harjoittelija create(Harjoittelija harjoittelija);
    Harjoittelija read(Long id);
    List<Harjoittelija> list();
    void delete(Long id);
    Harjoittelija findByNimi(String nimi);

    public void save(Harjoittelija harjoittelija);

    public String vaihdaSalasana(HttpSession session,String vanhaSalasana, String uusiSalasana, String uusiSalasana2);
    public Harjoittelija findBySeurantaAvain(String seurantaAvain);





    
    
}

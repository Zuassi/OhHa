/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.service;

import java.util.List;
import werkko.harjoitusseuranta.domain.Harjoittelija;

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

    public String vaihdaSalasana(Long harjoittelijaId,String vanhaSalasana, String uusiSalasana, String uusiSalasana2);






    
    
}

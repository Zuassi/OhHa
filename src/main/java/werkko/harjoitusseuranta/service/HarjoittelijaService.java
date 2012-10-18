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
    
}

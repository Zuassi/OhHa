/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import werkko.harjoitusseuranta.domain.Harjoittelija;

/**
 *
 * @author Lalli
 */
public interface HarjoittelijaRepository extends JpaRepository<Harjoittelija,Long> {
   Harjoittelija findByNimi(String nimi);

    
}

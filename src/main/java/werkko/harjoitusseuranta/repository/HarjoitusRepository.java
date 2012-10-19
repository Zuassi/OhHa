/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import werkko.harjoitusseuranta.domain.Harjoitus;

/**
 *
 * @author Lalli
 */
public interface HarjoitusRepository extends JpaRepository<Harjoitus,Long>{

    public List<Harjoitus> findByHarjoittelijaId(Long id);
    
}

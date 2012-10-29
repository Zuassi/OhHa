/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import werkko.harjoitusseuranta.domain.Seurantaavain;

/**
 *
 * @author Lalli
 */
public interface SeurantaavainRepository extends JpaRepository<Seurantaavain, Long> {

    public List<Seurantaavain> findAllById(Long id);

    public Seurantaavain findByAvain(Seurantaavain avain);

    public Seurantaavain findByAvain(String avain);

    public List<Seurantaavain> findByHarjoittelijaId(Long id);
}

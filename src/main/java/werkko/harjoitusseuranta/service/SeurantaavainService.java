/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.service;

import java.util.List;
import werkko.harjoitusseuranta.domain.Seurantaavain;

/**
 *
 * @author Lalli
 */
public interface SeurantaavainService {

    public Seurantaavain create(Seurantaavain avain);

    public Seurantaavain read(Long id);

    public void delete(Long id);

    public Seurantaavain findByAvain(String avain);


    public List<Seurantaavain> findByHarjoittelijaId(Long id);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import werkko.harjoitusseuranta.domain.Seurantaavain;
import werkko.harjoitusseuranta.repository.SeurantaavainRepository;

/**
 *
 * @author Lalli
 */
@Service
public class JpaSeurantaavainService implements SeurantaavainService {

    @Autowired
    private SeurantaavainRepository repo;

    public Seurantaavain create(Seurantaavain avain) {
        return repo.save(avain);

    }

    public Seurantaavain read(Long id) {
        return repo.findOne(id);
    }

    public void delete(Long id) {
        repo.delete(id);

    }

    public Seurantaavain findByAvain(String avain) {
        return repo.findByAvain(avain);
    }

     public List<Seurantaavain> findByHarjoittelijaId(Long id) {
       
        return repo.findByHarjoittelijaId(id);
    }
}
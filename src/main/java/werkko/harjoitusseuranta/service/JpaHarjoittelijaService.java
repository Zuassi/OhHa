/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import werkko.harjoitusseuranta.domain.Harjoittelija;
import werkko.harjoitusseuranta.repository.HarjoittelijaRepository;

/**
 *
 * @author Lalli
 */
@Service
public class JpaHarjoittelijaService implements HarjoittelijaService {
    @Autowired
    private HarjoittelijaRepository harjoittelijaRepository;

    public Harjoittelija create(Harjoittelija harjoittelija) {
        return harjoittelijaRepository.save(harjoittelija);
    }

    public Harjoittelija read(Long id) {
       return harjoittelijaRepository.findOne(id);
    }

    public List<Harjoittelija> list() {
      return harjoittelijaRepository.findAll();
    }

    public void delete(Long id) {
      harjoittelijaRepository.delete(id);
    }
    
}

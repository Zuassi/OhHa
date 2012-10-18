/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import werkko.harjoitusseuranta.domain.Harjoitus;
import werkko.harjoitusseuranta.repository.HarjoitusRepository;

/**
 *
 * @author Lalli
 */
@Service
public class JpaHarjoitusService implements HarjoitusService {

    @Autowired
    private HarjoitusRepository harjoitusRepository;

    public Harjoitus create(Harjoitus harjoitus) {
        return harjoitusRepository.save(harjoitus);
    }

    public Harjoitus read(Long id) {
        return harjoitusRepository.findOne(id);
    }

    public List<Harjoitus> list() {
        return harjoitusRepository.findAll();
    }

    public void delete(Long id) {
        harjoitusRepository.delete(id);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.service;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Harjoitus create(Harjoitus harjoitus, Long harjoittelijaId) {
        harjoitus.setHarjoittelijaId(harjoittelijaId);
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

    public List<Harjoitus> findByHarjoittelijaId(Long id) {

        return harjoitusRepository.findByHarjoittelijaId(id);
    }

    public Page<Harjoitus> listHarjoitukset(Integer sivuNumero, Integer sivuKoko, String jarjestys, HttpSession session) {


       
            jarjestys = "alkamisaika";
       

        if(sivuNumero==null){
            sivuNumero=1;
        }
        

        Pageable request = request = new PageRequest(sivuNumero - 1, sivuKoko, Sort.Direction.DESC, jarjestys);
        session.setAttribute("viimeisinSort", null);


        return harjoitusRepository.findByHarjoittelijaId((Long) session.getAttribute("harjoittelijaId"), request);
    }

    public Harjoitus findById(Long id) {
        return harjoitusRepository.findById(id);
    }
}

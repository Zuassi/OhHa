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

    /**
     * Luo harjoitusolion ja asettaa sille harjoittelijan id:n
     *
     * @param harjoitus harjoitusolio
     * @param harjoittelijaId harjoitusolion omistajan id
     * @return tallettaa harjoituksen
     */
    public Harjoitus create(Harjoitus harjoitus, Long harjoittelijaId) {
        harjoitus.setHarjoittelijaId(harjoittelijaId);
        return harjoitusRepository.save(harjoitus);
    }

    /**
     * Palauttaa halutun harjoituksen id perusteella
     *
     * @param id halutun harjoituksen id
     * @return harjoitus
     */
    public Harjoitus read(Long id) {
        return harjoitusRepository.findOne(id);
    }

    /**
     * Palauttaa listan kaikista harjoituksista
     *
     * @return lista kaikista harjoituksista
     */
    public List<Harjoitus> list() {
        return harjoitusRepository.findAll();
    }

    /**
     * Poistaa harjoituksen id perusteella
     *
     * @param id poistettavan harjoituksen id
     */
    public void delete(Long id) {
        harjoitusRepository.delete(id);

    }

    /**
     * Palauttaa listan halutun harjoittelijan harjoituksista
     *
     * @param id halutun harjoittelijan id
     * @return lista harjoittelijan harjoituksista
     */
    public List<Harjoitus> findByHarjoittelijaId(Long id) {

        return harjoitusRepository.findByHarjoittelijaId(id);
    }

    /**
     * Palauttaa halutun sivun harjoitukset j‰rjestyksen ja sivunumeron perusteella
     * @param sivuNumero Sivunumero jonka k‰ytt‰j‰ haluaa
     * @param sivuKoko viestej‰ per sivu
     * @param jarjestys haluttu j‰rjestys
     * @param session sis‰lt‰‰ harjoittelijaIdn
     * @return haluttu sivu
     */
    public Page<Harjoitus> listHarjoitukset(Integer sivuNumero, Integer sivuKoko, String jarjestys, HttpSession session) {

        if (jarjestys == null || jarjestys.isEmpty()) {
            jarjestys = "alkamisaika";
        }

        if (sivuNumero == null) {
            sivuNumero = 1;
        }


        Pageable request = request = new PageRequest(sivuNumero - 1, sivuKoko, Sort.Direction.DESC, jarjestys);
        session.setAttribute("viimeisinSort", null);


        return harjoitusRepository.findByHarjoittelijaId((Long) session.getAttribute("harjoittelijaId"), request);
    }


}



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.service;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import werkko.harjoitusseuranta.domain.Harjoitus;

/**
 *
 * @author Lalli
 */
public interface HarjoitusService {

    Harjoitus create(Harjoitus harjoitus, Long harjoittelijaId);

    Harjoitus read(Long id);

    List<Harjoitus> list();

    void delete(Long id);

    public List<Harjoitus> findByHarjoittelijaId(Long id);

    Page<Harjoitus> listHarjoitukset(Integer sivuNumero, Integer sivuKoko, String jarjestys, HttpSession session);
}

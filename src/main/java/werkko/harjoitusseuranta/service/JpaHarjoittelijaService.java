/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.service;

import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import werkko.harjoitusseuranta.domain.Harjoittelija;
import werkko.harjoitusseuranta.domain.Harjoitus;
import werkko.harjoitusseuranta.repository.HarjoittelijaRepository;

/**
 *
 * @author Lalli
 */
@Service
public class JpaHarjoittelijaService implements HarjoittelijaService {
    @Autowired
    private HarjoittelijaRepository harjoittelijaRepository;
    private Md5PasswordEncoder md5 = new Md5PasswordEncoder();

    public Harjoittelija create(Harjoittelija harjoittelija) {
        String md5salasana = md5.encodePassword(harjoittelija.getSalasana(), null);
        harjoittelija.setSalasana(md5salasana);
        harjoittelija.setNimi(StringEscapeUtils.escapeHtml4(harjoittelija.getNimi()));
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

    public Harjoittelija findByNimi(String nimi) {
        return harjoittelijaRepository.findByNimi(nimi);
    }

    public void save(Harjoittelija harjoittelija) {
        harjoittelijaRepository.save(harjoittelija);
    }




 
}
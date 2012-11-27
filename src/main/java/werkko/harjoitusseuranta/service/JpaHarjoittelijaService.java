/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
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
    private Md5PasswordEncoder md5 = new Md5PasswordEncoder();

    /**
     * Luo halutun harjoittelijan tietokantaan
     * @param harjoittelija k‰ytt‰j‰n l‰hett‰m‰ harjoittelijaolio
     * @return
     */
    public Harjoittelija create(Harjoittelija harjoittelija) {
        String md5salasana = md5.encodePassword(harjoittelija.getSalasana(), null);
        harjoittelija.setSalasana(md5salasana);
        harjoittelija.setNimi(harjoittelija.getNimi());
        return harjoittelijaRepository.save(harjoittelija);
    }

    
    /**
     * Palauttaa harjoittelija olion id perusteella
     * @param id halutun harjoittelijaolion id
     * @return harjoittelijaolio
     */
    public Harjoittelija read(Long id) {
        return harjoittelijaRepository.findOne(id);
    }

    /**
     * Palauttaa kaikki harjoittelijaoliot
     * @return lista harjoittelijaoloista
     */
    public List<Harjoittelija> list() {
        return harjoittelijaRepository.findAll();
    }

    /**
     * Poistaa harjoittelijan kyseisell‰ ID:ll‰
     * @param id poistettavan harjoittelijan id
     */
    public void delete(Long id) {
        harjoittelijaRepository.delete(id);
    }

    /**
     * Etsii harjoittelijan nimen perusteella
     * @param nimi halutun harjoittelijan nimi
     * @return harjoittelija
     */
    public Harjoittelija findByNimi(String nimi) {
        return harjoittelijaRepository.findByNimi(nimi);
    }

    /**
     * Tallettaa harjoittelijaan tehydt muutokset
     * @param harjoittelija l‰hetetty harjoittelija
     */
    public void save(Harjoittelija harjoittelija) {
        harjoittelijaRepository.save(harjoittelija);
    }

    /**
     * Metodi vaihtaa k‰ytt‰j‰n salasanan mik‰li se menee validoinnista l‰pi.
     * Salasanan tulee olla yli 5 merkki‰ pitk‰, lyhempi kuin 100 merkki‰ ja 
     * uusien salasanojen tulee t‰sm‰t‰.
     * @param session sis‰lt‰‰ harjoittelijaIdn
     * @param vanhaSalasana k‰ytt‰j‰n l‰ehtt‰m‰ vanha salasana
     * @param uusiSalasana k‰ytt‰j‰n l‰hett‰m‰ uusi salasana
     * @param uusiSalasana2 k‰ytt‰j‰n l‰hett‰m‰ uusi salansa uudestaan
     * @return viesti liittyen onnistumiseen/ep‰onnistumiseen
     */
    public String vaihdaSalasana(Long harjoittelijaId, String vanhaSalasana, String uusiSalasana, String uusiSalasana2) {
        String kryptattuVanhaSalasana = md5.encodePassword(vanhaSalasana, null);
        Harjoittelija harjoittelija = this.read(harjoittelijaId);
        if (!harjoittelija.getSalasana().equals(kryptattuVanhaSalasana)) {
            return "V‰‰r‰ vanha salasana";
        } else if (!uusiSalasana.equals(uusiSalasana2)) {
            return "Uudet salasanat eiv‰t t‰sm‰‰";
        } else if (uusiSalasana.length() < 5) {
            return "Salasana on liian lyhyt";
        } else if (uusiSalasana.length() > 100) {
            return "Salasana on liian pitk‰";
        } else {
            String kryptattuUusiSalasana = md5.encodePassword(uusiSalasana, null);
            harjoittelija.setSalasana(kryptattuUusiSalasana);
            harjoittelijaRepository.save(harjoittelija);
            return "Salasana vaihdettu";
        }
    }
}

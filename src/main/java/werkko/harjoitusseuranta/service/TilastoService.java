/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import werkko.harjoitusseuranta.domain.Harjoittelija;
import werkko.harjoitusseuranta.domain.Harjoitus;
import werkko.harjoitusseuranta.domain.Seurantaavain;
import werkko.harjoitusseuranta.helper.SallitutTyypit;
import werkko.harjoitusseuranta.repository.HarjoittelijaRepository;
import werkko.harjoitusseuranta.repository.HarjoitusRepository;

/**
 *
 * @author Lalli
 */
@Service
public class TilastoService {

    @Autowired
    private HarjoitusRepository repo;
    @Autowired
    private HarjoittelijaRepository harjoittelijaRepo;
    @Autowired
    private SeurantaavainService avainRepo;

    
    /** Etsii tilastot harjoittelijan tilastot tietokannasta seuranta-avaimen perusteella
     *
     * @param session sis‰lt‰‰ halutun avaimen
     * @return tilastot
     */
    public HashMap<String, Integer> findTilastoByHarjoittelijaSeurantaAvain(HttpSession session) {
        String avain = (String) session.getAttribute("avain");
        Seurantaavain seurantaavain = avainRepo.findByAvain(avain);
        Harjoittelija harjoittelija = harjoittelijaRepo.findOne(seurantaavain.getHarjoittelijaId());

        return keraaTilastot(session, harjoittelija);

    }

    /** Analysoi harjoitukset ja tekee niist‰ hashmapin
     *
     * @param session sis‰lt‰‰ avaimen
     * @param harjoittelija sis‰lt‰‰ halutun harjoittelijan
     * @return harjoitukset mapattuna 
     */
    public HashMap<String, Integer> keraaTilastot(HttpSession session, Harjoittelija harjoittelija) {


        List<Harjoitus> harjoitukset = repo.findByHarjoittelijaId(harjoittelija.getId());
        HashMap<String, Integer> harjoituksetMapattuna = new HashMap<String, Integer>();
        alustaMappi(harjoituksetMapattuna);
        lajitteleHarjoitukset(harjoituksetMapattuna, harjoitukset, session);
        return harjoituksetMapattuna;
    }

    
    /**
     * Lajittelee harjoitukset hashmappiin p‰iviin, viikkoihin, kuukausiin, vuosiin ja haluttuun aikav‰liin
     * @param harjoituksetMapattuna Tyhj‰ hashmappi joka tulee j‰rjest‰‰
     * @param harjoitukset kaikki harjoitukset listassa
     * @param session sessioni joka sis‰lt‰‰ halutun oman alkamisajan
     * @return harjoitukset j‰rjestettyn‰
     */
    private HashMap<String, Integer> lajitteleHarjoitukset(HashMap<String, Integer> harjoituksetMapattuna,
            List<Harjoitus> harjoitukset, HttpSession session) {

        boolean oma = (session.getAttribute("alkamisaika") != null);
        DateTime tanaan = new DateTime();
        tanaan.withMillis(new Date().getTime());

        DateTime harjoitusAika = new DateTime();
        for (Harjoitus harjoitus : harjoitukset) {
            boolean kova = (harjoitus.getTeho() > 3);
            harjoitusAika = harjoitusAika.withMillis(harjoitus.getAlkamisaika().getTime());  // muutetaan harjoitusaika datesta DateTimeksi helpottaaksemme vertailua
            if (harjoitusAika.getDayOfYear() == tanaan.getDayOfYear()) {
                harjoituksetMapattuna.put("paiva", harjoituksetMapattuna.get("paiva") + 1);
                harjoituksetMapattuna.put("paiva" + harjoitus.getTyyppi(), harjoituksetMapattuna.get("paiva" + harjoitus.getTyyppi()) + 1);
                if (kova) {
                    harjoituksetMapattuna.put("paivaKovat", harjoituksetMapattuna.get("paivaKovat") + 1);
                }

            }
            if (harjoitusAika.getWeekOfWeekyear() == tanaan.getWeekOfWeekyear()) {
                harjoituksetMapattuna.put("viikko", harjoituksetMapattuna.get("viikko") + 1);
                harjoituksetMapattuna.put("viikko" + harjoitus.getTyyppi(), harjoituksetMapattuna.get("viikko" + harjoitus.getTyyppi()) + 1);
                if (kova) {
                    harjoituksetMapattuna.put("viikkoKovat", harjoituksetMapattuna.get("viikkoKovat") + 1);
                }
            }
            if (harjoitusAika.getMonthOfYear() == tanaan.getMonthOfYear()) {
                harjoituksetMapattuna.put("kuukausi", harjoituksetMapattuna.get("kuukausi") + 1);
                harjoituksetMapattuna.put("kuukausi" + harjoitus.getTyyppi(), harjoituksetMapattuna.get("kuukausi" + harjoitus.getTyyppi()) + 1);
                if (kova) {
                    harjoituksetMapattuna.put("kuukausiKovat", harjoituksetMapattuna.get("kuukausiKovat") + 1);
                }

            }
            if (harjoitusAika.getYear() == tanaan.getYear()) {
                harjoituksetMapattuna.put("vuosi", harjoituksetMapattuna.get("vuosi") + 1);
                harjoituksetMapattuna.put("vuosi" + harjoitus.getTyyppi(), harjoituksetMapattuna.get("vuosi" + harjoitus.getTyyppi()) + 1);
                if (kova) {
                    harjoituksetMapattuna.put("vuosiKovat", harjoituksetMapattuna.get("vuosiKovat") + 1);
                }
            }
            if (oma) {
                if (harjoitus.getAlkamisaika().after((Date) session.getAttribute("alkamisaika"))
                        && harjoitus.getAlkamisaika().before((Date) session.getAttribute("loppumisaika"))) {
                    harjoituksetMapattuna.put("oma", harjoituksetMapattuna.get("oma") + 1);
                    harjoituksetMapattuna.put("oma" + harjoitus.getTyyppi(), harjoituksetMapattuna.get("oma" + harjoitus.getTyyppi()) + 1);
                    if (kova) {
                        harjoituksetMapattuna.put("omaKovat", harjoituksetMapattuna.get("omaKovat") + 1);
                    }
                }
            }


        }
        return harjoituksetMapattuna;
    }

    /**
     * Alustaa mappiin tarvittavat aikav‰lit
     * @param harjoituksetMapattuna
     * @return alustettu mappi
     */
    private HashMap<String, Integer> alustaMappi(HashMap<String, Integer> harjoituksetMapattuna) {
        harjoituksetMapattuna.put("paiva", 0);
        harjoituksetMapattuna.put("viikko", 0);
        harjoituksetMapattuna.put("kuukausi", 0);
        harjoituksetMapattuna.put("vuosi", 0);
        harjoituksetMapattuna.put("oma", 0);

        ArrayList<String> tyypit = new ArrayList(Arrays.asList(SallitutTyypit.sallitutTyypit));
        tyypit.add("Kovat");
        for (String tyyppi : tyypit) {
            harjoituksetMapattuna.put("paiva" + tyyppi, 0);
            harjoituksetMapattuna.put("viikko" + tyyppi, 0);
            harjoituksetMapattuna.put("kuukausi" + tyyppi, 0);
            harjoituksetMapattuna.put("vuosi" + tyyppi, 0);
            harjoituksetMapattuna.put("oma" + tyyppi, 0);
        }
        return harjoituksetMapattuna;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import werkko.harjoitusseuranta.domain.Harjoitus;
import werkko.harjoitusseuranta.domain.Tilasto;
import werkko.harjoitusseuranta.repository.HarjoitusRepository;

/**
 *
 * @author Lalli
 */
@Service
public class TilastoService {

    @Autowired
    private HarjoitusRepository repo;

    public Tilasto getTilasto(Long id) {
        Tilasto tilasto = new Tilasto();
        List<Harjoitus> harjoitukset = repo.findByHarjoittelijaId(id);
        HashMap<String, List<Harjoitus>> harjoituksetMapattuna = haeHarjoitukset(harjoitukset);

        return tilasto;

    }

    private HashMap<String, List<Harjoitus>> haeHarjoitukset(List<Harjoitus> harjoitukset) {
        HashMap<String, List<Harjoitus>> harjoituksetMapattuna = new HashMap<String, List<Harjoitus>>();

        harjoituksetMapattuna.put("paiva", new ArrayList<Harjoitus>());
        harjoituksetMapattuna.put("viikko", new ArrayList<Harjoitus>());
        harjoituksetMapattuna.put("kuukausi", new ArrayList<Harjoitus>());
        harjoituksetMapattuna.put("vuosi", new ArrayList<Harjoitus>());

        DateTime tanaan = new DateTime();
        tanaan.withMillis(new Date().getTime());

        DateTime harjoitusAika = new DateTime();
        for (Harjoitus harjoitus : harjoitukset) {
            harjoitusAika = harjoitusAika.withMillis(harjoitus.getAlkamisaika().getTime());  // muutetaan harjoitusaika datesta DateTimeksi helpottaaksemme vertailua
            if (harjoitusAika.getDayOfYear() == tanaan.getDayOfYear()) {
                harjoituksetMapattuna.get("paiva").add(harjoitus);

            }
            if (harjoitusAika.getWeekOfWeekyear() == tanaan.getWeekOfWeekyear()) {
                harjoituksetMapattuna.get("viikko").add(harjoitus);
            }
            if (harjoitusAika.getMonthOfYear() == tanaan.getMonthOfYear()) {
                harjoituksetMapattuna.get("kuukausi").add(harjoitus);

            }
            if (harjoitusAika.getYear() == tanaan.getYear()) {
                harjoituksetMapattuna.get("vuosi").add(harjoitus);
            }

        }
        return harjoituksetMapattuna;
    }
}

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
    private HarjoittelijaService harjoittelijaService;
    @Autowired
    private SeurantaavainService avainService;

    /**
     * Etsii tilastot harjoittelijan tilastot tietokannasta seuranta-avaimen
     *
     * @param avain Harjoittelijan seuranta-avain
     * @param alkamisaika Halutun seurantav‰lin alkamisaika
     * @param loppumisaika Halutun seurantav‰lin p‰‰ttymisaika
     * @return Sortatut tilastot
     */
    public HashMap<String, Integer> findTilastoByHarjoittelijaSeurantaAvain(String avain, Date alkamisaika, Date loppumisaika) {

        Seurantaavain seurantaavain = avainService.findByAvain(avain);
        Harjoittelija harjoittelija = harjoittelijaService.read(seurantaavain.getHarjoittelijaId());

        return keraaTilastot(alkamisaika, loppumisaika, harjoittelija);

    }

    /**
     * Analysoi harjoitukset ja tekee niist‰ hashmapin
     *
     * @param alkamisaika Halutun seurantav‰lin alkamisaika
     * @param loppumisaika Halutun seurantav‰lin p‰‰ttymisaika
     * @param harjoittelija Haluttu harjoittelija
     * @return
     */
    public HashMap<String, Integer> keraaTilastot(Date alkamisaika, Date loppumisaika, Harjoittelija harjoittelija) {


        List<Harjoitus> harjoitukset = repo.findByHarjoittelijaId(harjoittelija.getId());
        HashMap<String, Integer> harjoituksetMapattuna = new HashMap<String, Integer>();
        alustaMappi(harjoituksetMapattuna);
        lajitteleHarjoitukset(harjoituksetMapattuna, harjoitukset, alkamisaika, loppumisaika);
        return harjoituksetMapattuna;
    }

    /**
     * Lajittelee harjoitukset hashmappiin p‰iviin, viikkoihin, kuukausiin,
     * vuosiin ja haluttuun aikav‰liin
     *
     * @param harjoituksetMapattuna Tyhj‰ hashmappi joka tulee j‰rjest‰‰
     * @param harjoitukset kaikki harjoitukset listassa
     *
     * @return harjoitukset j‰rjestettyn‰
     */
    private HashMap<String, Integer> lajitteleHarjoitukset(HashMap<String, Integer> harjoituksetMapattuna,
            List<Harjoitus> harjoitukset, Date alkamisaika, Date loppumisaika) {

        boolean oma = (alkamisaika != null && loppumisaika != null);
        DateTime tanaan = new DateTime();
        tanaan.withMillis(new Date().getTime());

        DateTime harjoitusAika = new DateTime();
        for (Harjoitus harjoitus : harjoitukset) {
            boolean kova = (harjoitus.getTeho() > 3);
            harjoitusAika = harjoitusAika.withMillis(harjoitus.getAlkamisaika().getTime());
            if (harjoitusAika.getDayOfYear() == tanaan.getDayOfYear()) {
                lisaaHarjoitusMappiin("paiva", harjoitus, harjoituksetMapattuna, kova);
            }
            if (harjoitusAika.getWeekOfWeekyear() == tanaan.getWeekOfWeekyear()) {
                lisaaHarjoitusMappiin("viikko", harjoitus, harjoituksetMapattuna, kova);
            }
            if (harjoitusAika.getMonthOfYear() == tanaan.getMonthOfYear()) {
                lisaaHarjoitusMappiin("kuukausi", harjoitus, harjoituksetMapattuna, kova);

            }
            if (harjoitusAika.getYear() == tanaan.getYear()) {
                lisaaHarjoitusMappiin("vuosi", harjoitus, harjoituksetMapattuna, kova);
            }
            if (oma) {
                if (harjoitus.getAlkamisaika().after(alkamisaika)
                        && harjoitus.getAlkamisaika().before(loppumisaika)) {
                    lisaaHarjoitusMappiin("oma", harjoitus, harjoituksetMapattuna, kova);
                }
            }
        }
        return harjoituksetMapattuna;
    }

    /**
     * Alustaa mappiin tarvittavat aikav‰lit
     *
     * @param harjoituksetMapattuna
     * @return alustettu mappi
     */
    private HashMap<String, Integer> alustaMappi(HashMap<String, Integer> harjoituksetMapattuna) {
        String aikavalit[] = {"paiva", "viikko", "kuukausi", "vuosi", "oma"};
        for (String aikavali : aikavalit) {
            harjoituksetMapattuna.put(aikavali, 0);
        }

        ArrayList<String> tyypit = new ArrayList(Arrays.asList(SallitutTyypit.sallitutTyypit));
        tyypit.add("Kovat");
        for (String tyyppi : tyypit) {
            for (String aikavali : aikavalit) {
                harjoituksetMapattuna.put(aikavali + tyyppi, 0);
            }
        }
        return harjoituksetMapattuna;
    }

    /**
     * Lis‰‰ annetun harjoituksen hashmappiin
     *
     * @param aika paiva/viikko/kuukausi/vuosi
     * @param harjoitus haluttu harjoitus
     * @param harjoituksetMapattuna mappi johon lis‰t‰‰n
     * @param kova oliko kova harjoitus
     */
    private void lisaaHarjoitusMappiin(String aika, Harjoitus harjoitus, HashMap<String, Integer> harjoituksetMapattuna, boolean kova) {
        harjoituksetMapattuna.put(aika, harjoituksetMapattuna.get(aika) + 1);
        harjoituksetMapattuna.put(aika + harjoitus.getTyyppi(), harjoituksetMapattuna.get(aika + harjoitus.getTyyppi()) + 1);
        if (kova) {
            harjoituksetMapattuna.put(aika + "Kovat", harjoituksetMapattuna.get(aika + "Kovat") + 1);
        }
    }
}

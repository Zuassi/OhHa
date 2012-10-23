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
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import werkko.harjoitusseuranta.domain.Harjoitus;
import werkko.harjoitusseuranta.domain.Tilasto;
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

    public Tilasto keraaTilastot(Long harjoittelijaId) {
        Tilasto tilasto = new Tilasto();
        List<Harjoitus> harjoitukset = repo.findByHarjoittelijaId(harjoittelijaId);
        HashMap<String, Integer> harjoituksetMapattuna = new HashMap<String, Integer>();
        alustaMappi(harjoituksetMapattuna);
        lajitteleHarjoitukset(harjoituksetMapattuna, harjoitukset);
        lisaaKokonaisTreenimaarat(tilasto, harjoituksetMapattuna);
        lisaaTyyppikohtaisetTreenimaarat(tilasto, harjoituksetMapattuna);



        return tilasto;
    }

    private HashMap<String, Integer> lajitteleHarjoitukset(HashMap<String, Integer> harjoituksetMapattuna, List<Harjoitus> harjoitukset) {


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
            if (harjoitus.getAlkamisaika().after(new Date()) && harjoitus.getAlkamisaika().before(new Date())) {
                harjoituksetMapattuna.put("oma", harjoituksetMapattuna.get("oma") + 1);
            }


        }
        return harjoituksetMapattuna;
    }

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

    private void lisaaKokonaisTreenimaarat(Tilasto tilasto, HashMap<String, Integer> harjoituksetMapattuna) {
        tilasto.setTreenitYhteensaPaivassa(harjoituksetMapattuna.get("paiva"));
        tilasto.setTreenitYhteensaViikossa(harjoituksetMapattuna.get("viikko"));
        tilasto.setTreenitYhteensaKuukaudessa(harjoituksetMapattuna.get("kuukausi"));
        tilasto.setTreenitYhteensaVuodessa(harjoituksetMapattuna.get("vuosi"));
        tilasto.setTreenitOmallaAikavalilla(harjoituksetMapattuna.get("oma"));

    }

    private void lisaaTyyppikohtaisetTreenimaarat(Tilasto tilasto, HashMap<String, Integer> harjoituksetMapattuna) {
        tilasto.setLajitPaivassa(harjoituksetMapattuna.get("paivaLajiharjoitus"));
        tilasto.setLajitViikossa(harjoituksetMapattuna.get("viikkoLajiharjoitus"));
        tilasto.setLajitKuukaudessa(harjoituksetMapattuna.get("kuukausiLajiharjoitus"));
        tilasto.setLajitVuodessa(harjoituksetMapattuna.get("vuosiLajiharjoitus"));
        tilasto.setLajitOmallaAikavalilla(harjoituksetMapattuna.get("omaLajiharjoitus"));

        tilasto.setPuntitPaivassa(harjoituksetMapattuna.get("paivaPuntti"));
        tilasto.setPuntitViikossa(harjoituksetMapattuna.get("viikkoPuntti"));
        tilasto.setPuntitKuukaudessa(harjoituksetMapattuna.get("kuukausiPuntti"));
        tilasto.setPuntitVuodessa(harjoituksetMapattuna.get("vuosiPuntti"));
        tilasto.setPuntitOmallaAikavalilla(harjoituksetMapattuna.get("omaPuntti"));

        tilasto.setPalauttavatPaivassa(harjoituksetMapattuna.get("paivaPalauttava"));
        tilasto.setPalauttavatViikossa(harjoituksetMapattuna.get("viikkoPalauttava"));
        tilasto.setPalauttavatKuukaudessa(harjoituksetMapattuna.get("kuukausiPalauttava"));
        tilasto.setPalauttavatVuodessa(harjoituksetMapattuna.get("vuosiPalauttava"));
        tilasto.setPalauttavatOmallaAikavalilla(harjoituksetMapattuna.get("omaPalauttava"));

        tilasto.setMuutPaivassa(harjoituksetMapattuna.get("paivaMuu"));
        tilasto.setMuutViikossa(harjoituksetMapattuna.get("viikkoMuu"));
        tilasto.setMuutKuukaudessa(harjoituksetMapattuna.get("kuukausiMuu"));
        tilasto.setMuutVuodessa(harjoituksetMapattuna.get("vuosiMuu"));
        tilasto.setMuutOmallaAikavalilla(harjoituksetMapattuna.get("omaMuu"));

        tilasto.setKilpailujaKuukaudessa(harjoituksetMapattuna.get("kuukausiKilpailu"));
        tilasto.setKilpailujaKuukaudessa(harjoituksetMapattuna.get("vuosiKilpailu"));
        tilasto.setKilpailujaKuukaudessa(harjoituksetMapattuna.get("omaKilpailu"));
        tilasto.setKovatTreenitPaivassa(harjoituksetMapattuna.get("paivaKovat"));
        tilasto.setKovatTreenitViikossa(harjoituksetMapattuna.get("viikkoKovat"));
        tilasto.setKovatTreenitKuukaudessa(harjoituksetMapattuna.get("kuukausiKovat"));
        tilasto.setKovatTreenitVuodessa(harjoituksetMapattuna.get("vuosiKovat"));
        tilasto.setKovatTreenitVuodessa(harjoituksetMapattuna.get("omaKovat"));
    }
}

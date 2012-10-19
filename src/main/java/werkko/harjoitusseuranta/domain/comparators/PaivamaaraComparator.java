/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.domain.comparators;

import java.util.Comparator;
import werkko.harjoitusseuranta.domain.Harjoitus;

/**
 *
 * @author Lalli
 */
public class PaivamaaraComparator implements Comparator<Harjoitus> {

   // laitetaan "v‰‰rinp‰in" jotta saadaan uusin ensimm‰iseksi
    public int compare(Harjoitus o1, Harjoitus o2) {
        if (o1.getAlkamisaika().compareTo(o2.getAlkamisaika()) == 1) {
            return -1;
        } else if (o1.getAlkamisaika().compareTo(o2.getAlkamisaika()) == -1) {
            return 1;
        } else {
            return 0;
        }

    }
}

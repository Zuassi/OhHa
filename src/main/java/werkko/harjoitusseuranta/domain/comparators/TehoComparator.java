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
public class TehoComparator implements Comparator<Harjoitus> {

    public int compare(Harjoitus o1, Harjoitus o2) {
        if(o1.getTeho() < o2.getTeho()){
            return -1;
        }else if(o1.getTeho() == o2.getTeho()){
            return 0;
        }else{
            return 1;
        }
    }
    
}

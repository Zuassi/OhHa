/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Lalli
 */
@Entity
@Table(name = "Seurantaavain")
public class Seurantaavain implements Serializable {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(name = "avain")
    private String avain;
    @Column(name = "harjoittelijaId")
    private Long harjoittelijaId;
    @Column(name="omistaja")
    private String avaimenOmistaja;
    
    @Override
    public String toString() {
        return avaimenOmistaja+" - "+avain;
    }

    public String getAvaimenOmistaja() {
        return avaimenOmistaja;
    }

    public void setAvaimenOmistaja(String avaimenOmistaja) {
        this.avaimenOmistaja = avaimenOmistaja;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvain() {
        return avain;
    }

    public void setAvain(String avain) {
        this.avain = avain;
    }

    public Long getHarjoittelijaId() {
        return harjoittelijaId;
    }

    public void setHarjoittelijaId(Long harjoittelijaId) {
        this.harjoittelijaId = harjoittelijaId;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Lalli
 */
@Entity
@Table(name = "harjoitus")
public class Harjoitus implements Serializable {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank
    @DateTimeFormat(pattern = "d.M.y H.m")
    @Column(name = "alkamisaika")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date alkamisaika;
    
    @Column(name = "kesto")
    @NotBlank
    private int kesto;
    
    @Column(name = "teho")
    @NotBlank
    @Length(min = 1, max = 5)
    private int teho;
    
    @NotBlank
    @Column(name = "paikka")
    private String paikka;
    
    @Column(name = "tyyppi")
    @NotBlank
    private String tyyppi;
    
    @Column(name = "sisalto")
    @NotBlank
    private String sisalto;
    
    @NotBlank
    @Column(name = "harjoittelijaId")
    private Long harjoittelijanID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAlkamisaika() {
        return alkamisaika;
    }

    public void setAlkamisaika(Date alkamisaika) {
        this.alkamisaika = alkamisaika;
    }

    public int getKesto() {
        return kesto;
    }

    public void setKesto(int kesto) {
        this.kesto = kesto;
    }

    public int getTeho() {
        return teho;
    }

    public void setTeho(int teho) {
        this.teho = teho;
    }

    public String getPaikka() {
        return paikka;
    }

    public void setPaikka(String paikka) {
        this.paikka = paikka;
    }

    public String getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    public String getSisalto() {
        return sisalto;
    }

    public void setSisalto(String sisalto) {
        this.sisalto = sisalto;
    }

    public Long getHarjoittelijanID() {
        return harjoittelijanID;
    }

    public void setHarjoittelijanID(Long harjoittelijanID) {
        this.harjoittelijanID = harjoittelijanID;
    }
}

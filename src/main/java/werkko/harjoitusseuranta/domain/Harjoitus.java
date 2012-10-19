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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Lalli
 */
@Entity
@Table(name = "harjoitus")
public class Harjoitus  implements Serializable {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @DateTimeFormat(pattern = "d.M.y H.m")
    @Column(name = "alkamisaika")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date alkamisaika;
    @Column(name = "kesto")
   
   @NotNull
    private Integer kesto;
    @Column(name = "teho")
    @NotNull
    @Max(5)
    @Min(1)
    private Integer teho;
    @NotBlank
    @Column(name = "paikka")
    private String paikka;
    @Column(name = "tyyppi")
    @NotBlank
    private String tyyppi;
    @Column(name = "sisalto")
    @NotBlank
    private String sisalto;
    @ManyToOne
    private Harjoittelija harjoittelija;

    public Long getId() {
        return id;
    }

    public Integer getKesto() {
        return kesto;
    }

    public Harjoittelija getHarjoittelija() {
        return harjoittelija;
    }

    public void setHarjoittelija(Harjoittelija harjoittelija) {
        this.harjoittelija = harjoittelija;
    
    }

    public void setKesto(Integer kesto) {
        this.kesto = kesto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAlkamisaika() {
        return alkamisaika;
    }

    public Integer getTeho() {
        return teho;
    }

    public void setTeho(Integer teho) {
        this.teho = teho;
    }

    public void setAlkamisaika(Date alkamisaika) {
        this.alkamisaika = alkamisaika;
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



    public int compareTo(Harjoitus o) {
          return this.getAlkamisaika().compareTo(o.getAlkamisaika());
    }




}

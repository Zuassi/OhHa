/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import werkko.harjoitusseuranta.domain.comparators.PaivamaaraComparator;

/**
 *
 * @author Lalli
 */
@Entity
@Table(name = "Harjoittelija")
public class Harjoittelija implements Serializable {

    @Column(name = "harjoittelijaId")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(name = "nimi")
    @NotBlank
    @NotNull
    private String nimi;
    @Column(name = "salasana")
    @NotBlank
    @Length(min = 5)
    private String salasana; //md5



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getSalasana() {
        return salasana;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }
}
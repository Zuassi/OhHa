/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package werkko.harjoitusseuranta.controller.form;

import java.util.Date;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Lalli
 */
public class AikavaliForm {

    @NotNull
    @DateTimeFormat(pattern = "d.M.y")
    private Date alkamisaika;
    @NotNull
    @DateTimeFormat(pattern = "d.M.y")
    private Date loppumisaika;

    public Date getAlkamisaika() {
        return alkamisaika;
    }

    public void setAlkamisaika(Date alkamisaika) {
        this.alkamisaika = alkamisaika;
    }

    public Date getLoppumisaika() {
        return loppumisaika;
    }

    public void setLoppumisaika(Date loppumisaika) {
        this.loppumisaika = loppumisaika;
    }
    
    
}


import java.util.UUID;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class PerusTest {

    private WebDriver driver;
    private String baseUrl;
    private String kayttajanimi ="nakunkka23";
    private String salasana = "salasana";



    @Before
    public void setUp() throws Exception {
        driver = new HtmlUnitDriver();
        baseUrl = "http://localhost:8080/OhHa";

    }

    @Test
    public void SivuToimii() {
        driver.get(baseUrl);
        Assert.assertEquals(driver.getTitle(), "Harjoitusseuranta");
    }

    @Test
    public void RekisteroityminenToimii() {

        driver.get(baseUrl);
        rekisteroidy(kayttajanimi, salasana);
        System.out.println(driver.getPageSource());
        Assert.assertTrue(driver.getPageSource().contains("Harjoitusseuranta - Kirjautunut"));
    }

    @Test
    public void RekisteroityminenKaytossaOlevallaNimellaEpaonnistuu() {
        driver.manage().deleteAllCookies();
        driver.get(baseUrl);
        rekisteroidy(kayttajanimi, salasana);
        Assert.assertTrue(driver.getPageSource().contains("Nimi on jo käytössä"));
    }

    public void rekisteroidy(String nimi, String salasana) {
        driver.findElement(By.linkText("Rekisteröidy")).click();
        driver.findElement(By.className("rekisteroidy_nimi")).sendKeys(nimi);
        driver.findElement(By.className("rekisteroidy_salasana")).sendKeys(salasana);
        driver.findElement(By.className("rekisteroidy_nappula")).submit();
    }

    @Test
    public void kirjautuminenToimii() {
        driver.manage().deleteAllCookies();
        driver.get(baseUrl);
        kirjaudu(kayttajanimi, salasana);
        Assert.assertTrue(driver.getPageSource().contains("Harjoitusseuranta - Kirjautunut"));
    }

    @Test
    public void kirjautuminenVaarallaSalasanallaEiToimi() {
        driver.manage().deleteAllCookies();

        driver.get(baseUrl);
        kirjaudu(kayttajanimi, "lisaa kokista");
        Assert.assertTrue(driver.getPageSource().contains("Väärä salasana tai käyttäjänimi"));
    }

    public void kirjaudu(String nimi, String salasana) {
        driver.findElement(By.className("kirjaudu_nimi")).sendKeys(nimi);
        driver.findElement(By.className("kirjaudu_salasana")).sendKeys(salasana);
        driver.findElement(By.className("kirjaudu_nappula")).submit();
    }
}

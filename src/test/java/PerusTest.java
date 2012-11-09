
import junit.framework.Assert;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class PerusTest {

    private WebDriver driver;
    private String baseUrl;
    private String kayttajanimi = "beebopedoli";
    private String salasana = "salasana";

    @Before
    public void setUp() throws Exception {

        driver = new HtmlUnitDriver();
        baseUrl = "http://localhost:8080/OhHa";

    }

    @Test
    public void sivuToimii() {
        driver.get(baseUrl);
        Assert.assertEquals(driver.getTitle(), "Harjoitusseuranta");
    }

    @Test
    public void rekisteroityminenToimii() {
        driver.manage().deleteAllCookies();
        driver.get(baseUrl);
        rekisteroidy(kayttajanimi, salasana);
        System.out.println(driver.getPageSource());
        Assert.assertTrue(driver.getPageSource().contains("Harjoitusseuranta - Kirjautunut"));
    }

    @Test
    public void rekisteroityminenKaytossaOlevallaNimellaEpaonnistuu() {
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

        kirjaudu(kayttajanimi, salasana);
        Assert.assertTrue(driver.getPageSource().contains("Harjoitusseuranta - Kirjautunut"));
    }

    @Test
    public void kirjautuminenVaarallaSalasanallaEiToimi() {
        driver.manage().deleteAllCookies();
        kirjaudu(kayttajanimi, "lisaa kokista");
        Assert.assertTrue(driver.getPageSource().contains("Väärä salasana tai käyttäjänimi"));
    }

    public void kirjaudu(String nimi, String salasana) {
        driver.get(baseUrl);
        driver.findElement(By.className("kirjaudu_nimi")).sendKeys(nimi);
        driver.findElement(By.className("kirjaudu_salasana")).sendKeys(salasana);
        driver.findElement(By.className("kirjaudu_nappula")).submit();
    }

    @Test
    public void kirjauduJaLisaaHarjoitusToimii() {
        driver.manage().deleteAllCookies();
        kirjaudu(kayttajanimi, salasana);

        driver.findElement(By.className("lisaa_alkamisaika")).sendKeys("10.10." + new DateTime().getYear() + " 10.00");
        driver.findElement(By.className("lisaa_kesto")).sendKeys("4");
        driver.findElement(By.className("lisaa_teho")).sendKeys("4");
        driver.findElement(By.className("lisaa_paikka")).sendKeys("baari");
        driver.findElement(By.className("lisaa_sisalto")).sendKeys("rankkaa tanssimista ja kaljan kittausta");
        driver.findElement(By.className("lisaa_harjoitus")).submit();

        Assert.assertTrue(driver.getPageSource().replaceAll("\\s", "").contains("<b>Treenejäyhteensätänävuonna:</b>1<br/>"));
    }

    @Test
    public void asetaAikavaliToimiiKunHarjoitusOnLisatty() {

        kirjaudu(kayttajanimi, salasana);
        driver.findElement(By.className("aseta_alkamisaika")).sendKeys("9.10." + new DateTime().getYear());
        driver.findElement(By.className("aseta_loppumisaika")).sendKeys("11.10." + new DateTime().getYear());
        driver.findElement(By.className("aseta_submit")).submit();
        System.out.println(driver.getPageSource());
        Assert.assertTrue(driver.getPageSource().replaceAll("\\s", "").contains("<b>Treenejäasetetullaaikavälillä:</b>1<br/>"));
    }
}

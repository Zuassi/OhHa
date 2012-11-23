
import java.util.UUID;
import junit.framework.Assert;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        rekisteroidy(UUID.randomUUID().toString(), salasana);

        Assert.assertTrue(driver.getPageSource().contains("Harjoitusseuranta - Kirjautunut"));
    }

    @Test
    public void rekisteroityminenLiianLyhyellaSalasanallaEiToimi() {
        driver.manage().deleteAllCookies();
        rekisteroidy(UUID.randomUUID().toString(), "abb");
        Assert.assertTrue(!driver.getPageSource().contains("Harjoitusseuranta - Kirjautunut"));
    }

    @Test
    public void rekisteroityminenSamallaNimellaEiToimi() {

        String nimi = UUID.randomUUID().toString();
        for (int i = 0; i < 2; i++) {
            driver.manage().deleteAllCookies();
            driver.get(baseUrl);
            rekisteroidy(nimi, salasana);
        }
        Assert.assertTrue(driver.getPageSource().contains("Käyttäjänimi on jo käytössä"));
    }

    public void rekisteroidy(String nimi, String salasana) {
        driver.navigate().to(baseUrl + "/" + "rekisterointi");

        driver.findElement(By.className("rekisteroidy_nimi")).sendKeys(nimi);
        driver.findElement(By.className("rekisteroidy_salasana")).sendKeys(salasana);
        driver.findElement(By.className("rekisteroidy_nappula")).submit();
    }

    @Test
    public void kirjautuminenToimii() {
        driver.manage().deleteAllCookies();

        kirjaudu("asdasd", "asdasd");
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
        kirjaudu("asdasd", "asdasd");

        driver.findElement(By.className("lisaa_alkamisaika")).sendKeys("10.10." + new DateTime().getYear() + " 10.00");
        driver.findElement(By.className("lisaa_kesto")).sendKeys("4");
        driver.findElement(By.className("lisaa_teho")).sendKeys("4");
        driver.findElement(By.className("lisaa_paikka")).sendKeys("baari");
        driver.findElement(By.className("lisaa_sisalto")).sendKeys("rankkaa tanssimista ja kaljan kittausta");
        driver.findElement(By.className("lisaa_harjoitus")).submit();
        driver.navigate().to(baseUrl + "/harjoittelija/tilasto");

        Assert.assertTrue(!driver.getPageSource().replaceAll("\\s", "").contains("<b>Treenejäyhteensätänävuonna:</b>0<br/>"));
    }

    @Test
    public void asetaAikavaliToimiiKunHarjoitusOnLisatty() {
        kirjaudu("asdasd", "asdasd");

        driver.navigate().to(baseUrl + "/harjoittelija/tilasto");

        driver.findElement(By.name("alkamisaika")).sendKeys("9.10." + new DateTime().getYear());
        driver.findElement(By.id("loppumisaika")).sendKeys("11.10." + new DateTime().getYear());
        driver.findElement(By.className("aseta_submit")).submit();
        driver.navigate().to(baseUrl + "/harjoittelija/tilasto");
        Assert.assertTrue(!driver.getPageSource().replaceAll("\\s", "").contains("<b>Treenejäasetetullaaikavälillä:</b>0<br/>"));
    }

    @Test
    public void kirjauduLuoAvainKirjauduUlosKokeileSeurantaa() {
        kirjaudu("asdasd", "asdasd");
        driver.navigate().to(baseUrl + "/harjoittelija/asetukset");
        driver.findElement(By.name("nimi")).sendKeys("testi");
        driver.findElement(By.className("luo_avain")).submit();
        String key = driver.findElement(By.tagName("option")).getText();
        String avain = key.split(" ")[2];
        driver.manage().deleteAllCookies();
        driver.navigate().to(baseUrl + "/vierasseuranta");
        driver.findElement(By.name("avain")).sendKeys(avain);
        driver.findElement(By.className("seuranta_nappula")).submit();
        Assert.assertTrue(driver.getPageSource().replaceAll("\\s", "").contains("<b>Treenejäasetetullaaikavälillä:"));

    }

    @Test
    public void kirjauduLuoAvainPoistaAvainSeurantaEiToimi() {
        String rekisteroitymisNimi = UUID.randomUUID().toString();
        rekisteroidy(rekisteroitymisNimi, "abbaa");

        driver.navigate().to(baseUrl + "/harjoittelija/asetukset");
        String nimi = UUID.randomUUID().toString();
        driver.findElement(By.name("nimi")).sendKeys(nimi);
        driver.findElement(By.className("luo_avain")).submit();
        WebElement avaimet = driver.findElement(By.tagName("option"));
        avaimet.click();
        String key = avaimet.getText();
        String avain = key.split(" ")[2];
        driver.findElement(By.className("input_nappula")).click();
        driver.manage().deleteAllCookies();
        driver.navigate().to(baseUrl + "/vierasseuranta");
        driver.findElement(By.name("avain")).sendKeys(avain);
        driver.findElement(By.className("seuranta_nappula")).submit();

        Assert.assertTrue(driver.getPageSource().replaceAll("\\s", "").contains("Tuntematonseurantakoodi"));

    }

    @Test
    public void seurantaEiToimiOlemattomallaAvaimella() {
        driver.navigate().to(baseUrl + "/vierasseuranta");
        driver.findElement(By.name("avain")).sendKeys("bedobear");
        driver.findElement(By.className("seuranta_nappula")).submit();
        Assert.assertTrue(driver.getPageSource().replaceAll("\\s", "").contains("Tuntematonseurantakoodi"));
    }

    public void vaihdaSalasana(String vanha, String uusi, String uusi2) {
        driver.navigate().to(baseUrl + "/harjoittelija/asetukset");
        driver.findElement(By.name("vanha_salasana")).sendKeys(vanha);
        driver.findElement(By.name("uusi_salasana")).sendKeys(uusi);
        driver.findElement(By.name("uusi_salasana2")).sendKeys(uusi);
        driver.findElement(By.className("asetukset_salasana")).submit();
    }

    @Test
    public void vaihdaSalasana() {
        String nimi = UUID.randomUUID().toString();
        rekisteroidy(nimi, "asdasd");

        vaihdaSalasana("asdasd", "dsadsa", "dsadsa");
        driver.manage().deleteAllCookies();
        kirjaudu(nimi, "dsadsa");

        Assert.assertTrue(driver.getPageSource().contains("Harjoitusseuranta - Kirjautunut"));
    }

    @Test
    public void salasananVaihtoEriSalasanoillaEpaonnistuu() {
        String nimi = UUID.randomUUID().toString();
        rekisteroidy(nimi, "asdasd");

        vaihdaSalasana("asdasd", "dsads", "dsadsa");
        driver.manage().deleteAllCookies();
        kirjaudu(nimi, "dsadsa");

        Assert.assertTrue(!driver.getPageSource().contains("Harjoitusseuranta - Kirjautunut"));
    }

    @Test
    public void salasananVaihtoLiianLyhyellaSalasanallaEpaonnistuu() {
        String nimi = UUID.randomUUID().toString();
        rekisteroidy(nimi, "asdasd");

        vaihdaSalasana("asdasd", "dsa", "dsa");
        driver.manage().deleteAllCookies();
        kirjaudu(nimi, "dsa");

        Assert.assertTrue(!driver.getPageSource().contains("Harjoitusseuranta - Kirjautunut"));
    }
}

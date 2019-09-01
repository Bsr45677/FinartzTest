import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.*;


import java.net.URL;
import java.util.List;
import java.io.File;


import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageTest {

    protected static String ChromePath = "C:\\driver\\chromedriver.exe";
    //chrome driverlarına ulasmayı saglayan servis
    protected static ChromeDriverService service;
    //Tarayıcıdaki elementlei bulmamızı saglayan tarayıcı kontrol eden nesne
    protected static WebDriver driver;
    //Sürücünüyü bekletecek özellik
    protected static WebDriverWait wait;



    public static void main(String[] args) throws InterruptedException {
        Setup();
        OpenHomePage();
        assertEquals(driver.getCurrentUrl(),"https://www.finartz.com/");
        onClickSolution();
        assertEquals(driver.getCurrentUrl(),"https://www.finartz.com/solutions.html");
        baslıkYazdırma();
        onClickBlog();
        assertEquals(driver.getCurrentUrl(),"https://www.finartz.com/solutions.html");
        String parentWinHandle = driver.getWindowHandle();
        driver.switchTo().window(parentWinHandle);
        Search();
        assertEquals(driver.getCurrentUrl(),"https://blog.finartz.com/search?q=Bitcoin%20Solutions");
         Stop();

    }

    //OPEN HOMEPAGE
    public static void OpenHomePage() throws InterruptedException {
        driver.get("https://www.finartz.com/");
    }



    public static void onClickSolution() throws InterruptedException {
        sleep(5000);
        driver.findElement(By.partialLinkText("Solutions")).click();
        sleep(5000);
        driver.navigate();
    }

    public static void baslıkYazdırma() throws InterruptedException {
        //new WebDriverWait(driver, 100).until(ExpectedConditions.urlToBe("https://www.finartz.com/solutions.html"));
       /* List<WebElement> elements = driver.findElements(By.className("title section-title has-text-centered dark-text"));
        for (WebElement element : elements) {
            System.out.println(element.getText());
        }*/
        String h1 = driver.findElement(By.xpath("/html/body/section[1]/div/div[1]/h2")).getText();
        String h2 = driver.findElement(By.xpath("/html/body/section[2]/div/div[1]/h2")).getText();
        String h3 = driver.findElement(By.xpath("/html/body/section[3]/div/div[1]/h2")).getText();
        System.out.println("Finartz Innovative Solutions : "  + h1 + " , "  + h2 +  " , " + h3);
    }


    public static void onClickBlog() throws InterruptedException {
        driver.findElement(By.partialLinkText("Blog")).click();
        sleep(5000);
        driver.navigate();
    }

    //SETUP
    public static void Setup() {
        // Chrome_driver.exe dizininden servisi oluştur ve başlat
        service = new ChromeDriverService.Builder().
                usingDriverExecutable(new File(ChromePath)).
                usingAnyFreePort().
                build();
        try {
            service.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.setProperty("webdriver.chrome.driver", ChromePath);
        // Driver nesnesini service bilgilerini kullanarak oluştur
        driver = new
                RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
        // Bütün tarayıcıyı açarken 15ms bekletir
        wait = new WebDriverWait(driver, 15);
        // Penceri Büyüt
        driver.manage().window().maximize();
    }

    //STOP
    public static void Stop() {
        // Tarayıcıdan çıkış yapar
        driver.quit();
        // Servisi Durdurur
        service.stop();
    }



    public static void Search() throws InterruptedException {
        driver.get("https://blog.finartz.com/");
        WebElement search = driver.findElement(By.xpath("//*[@title=\"Search Finartz\"]"));
        search.click();
        search.sendKeys("Bitcoin Solutions");
        search.click();
        search.sendKeys(Keys.ENTER);
        sleep(5000);
        driver.navigate();
    }

}









package setup;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import sun.security.krb5.internal.crypto.Des;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverSetup {
//    public static WebDriver driver;
    public static RemoteWebDriver driver;

    /*public void startDriver(){
        System.setProperty("webdriver.chrome.driver", "E:\\Drivers\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }*/

    public void startDriver(){
        System.setProperty("webdriver.chrome.driver", "E:\\Drivers\\chromedriver.exe");
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);
            capabilities.merge(options);
            driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().window().maximize();
    }

    public void killDriver(){
        driver.quit();
    }

    public static WebDriver getDriverInstance(){
        return driver;
    }

}

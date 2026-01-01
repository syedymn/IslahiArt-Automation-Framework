package IslahiArt.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

    // 1. Change static WebDriver to ThreadLocal
    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
    public static Properties prop;

    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") 
                    + "/src/main/java/IslahiArt/config/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialization() {
        String browserName = prop.getProperty("browser");
        
        // 2. We use a local variable first
        WebDriver driver = null;

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        // 3. ASSIGN the driver to the current Thread
        tdriver.set(driver);

        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(prop.getProperty("implicitWait"))));
        getDriver().get(prop.getProperty("url"));
    }

    // 4. Create a "Getter" method to use the driver anywhere
    public static synchronized WebDriver getDriver() {
        return tdriver.get();
    }
    
    public void tearDown() {
        // Close the driver for THIS thread only
        if(getDriver() != null) {
            getDriver().quit();
            tdriver.remove(); // Clean up memory
        }
    }
}
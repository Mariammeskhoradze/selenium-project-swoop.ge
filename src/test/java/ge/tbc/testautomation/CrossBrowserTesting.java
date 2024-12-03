package ge.tbc.testautomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserTesting {
    public WebDriver driver;

    @Parameters({"browserType", "isSomething"})
    @Test
    public void testBrowser(@Optional("chrome") String browserType, @Optional("false") String isSomething) {

        System.out.println("Browser: " + browserType);
        System.out.println("IsSomething: " + isSomething);


        switch (browserType.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }

        driver.quit();
    }

}

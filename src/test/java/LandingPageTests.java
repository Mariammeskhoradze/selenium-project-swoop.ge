import Base.BaseTest;
import ge.tbcitacademy.data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LandingPageTests extends BaseTest {

    @Test
    public void activeCategoryTest() {
        driver.get(Constants.SWOOP_URL);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement categoriesDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='flex  justify-start items-start hidden laptop:flex']")));
        categoriesDropdown.click();


        WebElement sportCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='სპორტი']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(sportCategory).perform();



        WebElement kartingCategory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/category/2058/sporti/kartingi/']/child::h4")));
        kartingCategory.click();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // URL-ის ვალიდაცია
        wait.until(ExpectedConditions.urlContains(Constants.KARTINGI_URL_CONTAINS));

        String expectedUrl = Constants.KARTINGI_URL;
        String currentUrl = driver.getCurrentUrl();


        if (currentUrl.startsWith(Constants.SWOOP_WITHOUT_WWW) || currentUrl.startsWith(Constants.SWOOP_WITH_WWW)) {
            currentUrl = currentUrl.replace(Constants.HTTTPS_WWW, Constants.HTTPS);
            expectedUrl = expectedUrl.replace(Constants.HTTTPS_WWW, Constants.HTTPS);
        }

        Assert.assertEquals(currentUrl, expectedUrl, Constants.KARTING_URL_ASSERT_MESSAGE + currentUrl);
        System.out.println(Constants.KARTING_SOUT + currentUrl);



        WebElement categoryChain = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[contains(@class, 'whitespace-nowrap py-2')]")));
        String categoryChainText = categoryChain.getText();
        Assert.assertTrue(categoryChainText.contains(Constants.CATEGORYCHAINTEXT_VALUE), Constants.CATEGORYCHAINTEXT_ASSERT_MESSAGE + categoryChainText);

        System.out.println(Constants.CATEGORYCHAINTEXT_SOUT + categoryChainText);
    }


    @Test
    public void logoTest() {
        driver.get(Constants.SWOOP_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.linkText("დასვენება")).click();

        WebElement swoopLogo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='cursor-pointer flex items-center py-2 pr-3 min-w-fit hover:underline underline-offset-6']")));
        swoopLogo.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        wait.until(ExpectedConditions.urlContains(Constants.SWOOP_URL));
        String currentUrl = driver.getCurrentUrl();



        if (currentUrl.endsWith("/")) {
            currentUrl = currentUrl.substring(0, currentUrl.length() - 1);
        }

        String landingPageUrl = Constants.SWOOP_URL;

        Assert.assertTrue(currentUrl.equals(landingPageUrl), Constants.LANDINGPAGEURL_ASSERT_MESSAGE + currentUrl);


    }

}


// //img[@src='/icons/swoop_logo.svg']/parent::a
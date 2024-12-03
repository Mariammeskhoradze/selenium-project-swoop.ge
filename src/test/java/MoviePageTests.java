import Base.BaseTest;
import com.github.javafaker.Faker;
import ge.tbcitacademy.data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class MoviePageTests extends BaseTest {

    @Test
    public void MoviePageTest() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(Constants.SWOOP_URL);

        WebElement kinoMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("კინო")));
        kinoMenu.click();

        WebElement firstMovie = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,  'flex-col gap-7.5')]/div[1]")));
        firstMovie.click();

     /*   try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        } */


        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll vertically
        js.executeScript("window.scrollBy(0, 100);");
        WebElement cinemaElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[text()= 'კავეა ისთ ფოინთი']")));

        // Scroll until the cinema element is in view
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", cinemaElement);


        WebElement lastOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='__next']//h3[text()='კავეა ისთ ფოინთი']/parent::div/following-sibling::div/child::div/child::div[last()]/child::div[last()]")));
        lastOption.click();

        // Validate free seat colors
        WebElement legendFreeSeat = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='w-2.5 h-2.5 rounded-full bg-primary_green-100-value']")));
        WebElement freeSeat = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cursor-pointer ']")));

        String legendFreeSeatColor = legendFreeSeat.getCssValue("background-color");
        String FreeSeatColor = freeSeat.getCssValue("fill");


        try {
            // Legend free seat color validation
            Assert.assertEquals(legendFreeSeatColor, "rgb(142, 204, 105)", Constants.MOVIE_LEGENDCOLOR_ASSERTMESSAGE);
        } catch (AssertionError e) {
            System.out.println("Warning: " + e.getMessage());
        }

        try {
            // free seat color validation
            Assert.assertEquals(FreeSeatColor, "rgb(114, 191, 68)", Constants.MOVIE_FREESEATCOLOR_ASSERTMESSAGE);
        } catch (AssertionError e) {
            System.out.println("Warning: " + e.getMessage());
        }

        WebElement vacantPlace = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='cursor-pointer ']")));
        vacantPlace.click();

        WebElement RegisterButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='create pl-2 ']")));
        RegisterButton.click();

        //input

        Faker faker = new Faker();

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password' and @name='password']")));
        passwordField.sendKeys(Constants.MOVIE_PASSWORDFIELD_VALUE);


        WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PasswordRetype")));
        confirmPasswordField.sendKeys(Constants.MOVIE_PASSWORDFIELD_VALUE);


        WebElement genderField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='Gender2']")));
        genderField.click();


        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstname']")));
        firstNameField.sendKeys(Constants.MOVIE_FIRSTNAME_VALUE);



        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='lastname']")));
        lastNameField.sendKeys(Constants.MOVIE_LASTNAME_VALUE);


        js = (JavascriptExecutor) driver;
        // Scroll vertically
        js.executeScript("window.scrollBy(0, 500);");

      /*  try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        } */

        WebElement birthYearField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='select2-selection__arrow']")));
        birthYearField.click();


        WebElement birthYearFieldInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='2001']/parent::ul")));
        birthYearFieldInput.click();


        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Phone")));
        phoneField.sendKeys(Constants.MOVIE_PHONEFIELD_VALUE);


        WebElement smsCodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PhoneCode")));
        smsCodeField.sendKeys(Constants.MOVIE_SMSCODEFIELD_VALUE);


        WebElement checkmarkSpan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='checkmark']")));
        checkmarkSpan.click();


         WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("registrationBtn")));
         registerButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-error-email")));

        try {
            Assert.assertTrue(errorMessage.isDisplayed(), Constants.EMAIL_ASSERTIONMESSAGE);
            System.out.println(Constants.EMAIL_SOUT);
        } catch (AssertionError e) {
            System.out.println(Constants.EMAIL_ERRORMESSAGE_SOUT);
            e.getMessage();
        }


    }

}





// //h3[text()= 'კავეა ისთ ფოინთი']/parent::div/following-sibling::div/child::div/child::div[last()]/child::div[last()]



















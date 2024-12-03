import Base.BaseTest;
import ge.tbcitacademy.data.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HolidayPageTests extends BaseTest {


    @Test
    public void descendingOrderTest() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(Constants.SWOOP_URL);
        driver.findElement(By.linkText("დასვენება")).click();

        List<Double> allPrices = new ArrayList<>();
        double highestPrice = 0;

        int currentPage = 1;

        while (true) {
            try {
                String pageUrl = String.format(Constants.DASVENEBA_URL, currentPage);
                driver.navigate().to(pageUrl);

                List<WebElement> priceElements = wait.until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath(
                                "//h4[@class='text-primary_black-100-value text-2md leading-5 font-tbcx-bold']")));

                if (priceElements.isEmpty()) {
                    break;
                }

                for (WebElement priceElement : priceElements) {
                    try {
                        String priceText = priceElement.getText();
                        double parsedPrice = Double.parseDouble(priceText.replaceAll("[^\\d.]", ""));
                        allPrices.add(parsedPrice);
                        highestPrice = Math.max(highestPrice, parsedPrice);
                    } catch (NumberFormatException e) {
                        System.out.println(Constants.SOUT_PARSING + priceElement.getText());
                    }
                }

                currentPage++;
            } catch (TimeoutException e) {
                System.out.println(Constants.EXCEPTION_MESSAGE_SOUT);
                break;
            }
        }

        System.out.println(Constants.MOST_EXPENSIVE_PRICE_SOUT + highestPrice + Constants.LARI);


        List<Double> sortedPricesDescending = allPrices.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());


        sortedPricesDescending.forEach(System.out::println);

        Assert.assertEquals(highestPrice, sortedPricesDescending.get(0));


    }



    @Test
    public void ascendingOrderTest() {


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(Constants.SWOOP_URL);
        driver.findElement(By.linkText("დასვენება")).click();

        List<Double> allPrices = new ArrayList<>();

        int currentPage = 1;
        double lowestPrice = Double.MAX_VALUE;

        while (true) {
            try {
                String pageUrl = String.format(Constants.DASVENEBA_URL, currentPage);
                driver.navigate().to(pageUrl);

                List<WebElement> priceElements = wait.until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath(
                                "//h4[@class='text-primary_black-100-value text-2md leading-5 font-tbcx-bold']")));

                if (priceElements.isEmpty()) {
                    break;
                }

                for (WebElement priceElement : priceElements) {
                    try {
                        String priceText = priceElement.getText();
                        double parsedPrice = Double.parseDouble(priceText.replaceAll("[^\\d.]", ""));
                        allPrices.add(parsedPrice);
                        lowestPrice = Math.min(lowestPrice, parsedPrice);
                    } catch (NumberFormatException e) {
                        System.out.println(Constants.SOUT_PARSING + priceElement.getText());
                    }
                }

                currentPage++;
            } catch (TimeoutException e) {
                System.out.println("No more pages or timeout occurred.");
                break;
            }
        }

        System.out.println(Constants.LEAST_EXPENSIVE_PRICE_SOUT + lowestPrice + Constants.LARI);


        List<Double> sortedPricesAscending = allPrices.stream()
                .sorted()
                .collect(Collectors.toList());


        sortedPricesAscending.forEach(System.out::println);



        Assert.assertEquals(lowestPrice, sortedPricesAscending.get(0), Constants.LOWEST_PRICE_ASSERTION_MESSAGE);
    }


    @Test
    public void filterTest() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(Constants.SWOOP_URL);

        driver.findElement(By.linkText("დასვენება")).click();

        WebElement mountainResortsCategory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[h5[text()='მთის კურორტები']]")));
        mountainResortsCategory.click();

        for (int i = 0; i < 3; i++) {
            try {
                WebElement fullPaymentRadio = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='radio-გადახდის ტიპი-1']")));
                fullPaymentRadio.click();
            } catch (StaleElementReferenceException e) {
                System.out.println(e.getMessage());
            }

        }

        WebElement fullPaymentTag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'laptop:py-2.5')]")));
        Assert.assertTrue(fullPaymentTag.isDisplayed(), Constants.SRULIGADAXDA_TAG_ASSERTION_MESSAGE);

        List<Double> allPrices = new ArrayList<>();
        int currentPage = 1;
        double lowestPrice = Double.MAX_VALUE;


        while (true) {
            try {
                String pageUrl = String.format(Constants.DASVENEBA_URL, currentPage);
                driver.navigate().to(pageUrl);

                List<WebElement> priceElements = wait.until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath(
                                "//h4[@class='text-primary_black-100-value text-2md leading-5 font-tbcx-bold']")));

                if (priceElements.isEmpty()) {
                    break;
                }

                for (WebElement priceElement : priceElements) {
                    try {
                        String priceText = priceElement.getText();
                        double parsedPrice = Double.parseDouble(priceText.replaceAll("[^\\d.]", ""));
                        allPrices.add(parsedPrice);
                        lowestPrice = Math.min(lowestPrice, parsedPrice);
                    } catch (NumberFormatException e) {
                        System.out.println(Constants.SOUT_PARSING + priceElement.getText());
                    }
                }

                currentPage++;
            } catch (TimeoutException e) {
                System.out.println(e.getMessage());
                break;
            }
        }


        System.out.println(Constants.LEAST_EXPENSIVE_PRICE_SOUT + lowestPrice + Constants.LARI);


        List<Double> sortedPricesAscending = allPrices.stream()
                .sorted()
                .collect(Collectors.toList());


        sortedPricesAscending.forEach(System.out::println);
        Assert.assertEquals(lowestPrice, sortedPricesAscending.get(0), Constants.LOWEST_PRICE_ASSERTION_MESSAGE);


    }


    @Test
    public void priceRangeTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get(Constants.SWOOP_URL);


        driver.findElement(By.linkText("დასვენება")).click();
        int minPrice = 100; // Specify minimum price
        int maxPrice = 400; // Specify maximum price

// Locate and set minimum price
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='დან']/parent::div/child::input")))
                .sendKeys(String.valueOf(minPrice));


// Locate and set maximum price
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='მდე']/parent::div/child::input")))
                .sendKeys(String.valueOf(maxPrice));

// Click on the apply button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='flex-1']")))
                .click();


        List<Double> allPrices = new ArrayList<>();
        int currentPage = 1;

        while (true) {
            try {

                    String pageUrl = Constants.PRICE_FROM_TO_URL + Constants.PRICE_FROM_TO_URL_PAGE + currentPage;
                    driver.navigate().to(pageUrl);


                List<WebElement> prices = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//h4[@class='text-primary_black-100-value text-2md leading-5 font-tbcx-bold']")));

                if (prices.isEmpty()) {
                    System.out.println(Constants.SOUT_NO_PRICES + currentPage);
                    break;
                }


                for (WebElement price : prices) {
                    try {
                        String priceText = price.getText().replaceAll("[^\\d.]", "");
                        double parsedPrice = Double.parseDouble(priceText);


                        Assert.assertTrue(parsedPrice >= 100 && parsedPrice <= 400, "Price " + parsedPrice + " is out of range");

                        allPrices.add(parsedPrice);
                        System.out.println(parsedPrice);
                    } catch (NumberFormatException e) {
                        System.out.println(Constants.SOUT_PARSING + price.getText());
                    }
                }


                currentPage++;
            } catch (TimeoutException e) {
                System.out.println(e.getMessage());
                break;
            }
        }

        boolean allPricesInRange = allPrices.stream().allMatch(price -> price >= 100 && price <= 400);
        Assert.assertTrue(allPricesInRange, Constants.RANGE_ASSERTION_MESSAGE);
    }

}







import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.Keys.ENTER;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AvicTest {

    private WebDriver driver;

    @BeforeTest
    public void profileSetUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://avic.ua/");
    }

    @Test(priority = 1)
    public void checkThatUrlContainsSearchWord() {
        driver.findElement(xpath("//input [@class= 'search-query']")).sendKeys("OnePlus Nord 12/256GB Gray Onyx", Keys.ENTER);
        assertTrue(driver.getCurrentUrl().contains("query=OnePlus+Nord+12%2F256GB+Gray+Onyx"));
    }



    @Test(priority = 2)
    public void checkReview(){
        driver.findElement(xpath("//span[@class='sidebar-item']")).click();
        driver.findElement(xpath("//span[text()='Телевизоры и аксессуары']")).click();
        driver.findElement(xpath("//div [@class  = 'row js_height-block category-brand-parent'] //a[text() = 'Телевизоры']")).click();
        new WebDriverWait(driver, 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        driver.findElement(xpath("//div [@class  = 'prod-cart__img'] /img[@class = ' lazyloaded'] [@title = 'Телевизор LG 49NANO806NA']")).click();
        new WebDriverWait(driver, 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        driver.findElement(xpath("//span [@class = 'tab-item'] [text() = 'Отзывы                                    ' ]")).click();
        new WebDriverWait(driver, 20).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        driver.findElement(xpath("//div[@class = 'tab-box active visible'] //img[@alt=\"5\"]")).click();
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        assertEquals(driver.findElement(xpath("//div[@class = 'tab-box active visible'] //input[@name=\"score\"]")).getAttribute("value"),"5");
    }

    @Test(priority = 3)
    public void checkAddToCartTwoPlayStation() {
        driver.findElement(xpath("//input [@class= 'search-query']")).sendKeys("Sony PlayStation 5", Keys.ENTER);

        driver.findElement(xpath("//div[@class = 'two-column-wrapper '] //img [@title = 'Игровая приставка Sony PlayStation 5' ]")).click();
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        driver.findElement(xpath("//a[text() = 'Купить']")).click();
        new WebDriverWait(driver, 100).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        driver.findElement(xpath("//span[@class = 'js_plus btn-count btn-count--plus ']")).click();

        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        String actualProductsCountInCart =
                driver.findElement(xpath("//div[contains(@class,'header-bottom__cart')]//div[contains(@class,'cart_count')]")).getText();
        assertEquals(actualProductsCountInCart, "2");

    }

    @Test(priority = 4)
    public void checkAddToCart() {

        driver.findElement(xpath("//span[@class='sidebar-item']")).click();
        driver.findElement(xpath("//span [text()='Гаджеты']")).click();
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        assertTrue(driver.getCurrentUrl().contains("avic.ua/gadzhetyi1"));

    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class GigatronRegistrationForm {
    WebDriver driver;
    WebDriverWait wait;

    //Environment
    String urlGigatronSite = "https://gigatron.rs/";

    //Locators
    String registracijaButton = "//*[@id=\"content\"]/div[1]/div[1]/div[1]/div/div/div/div[1]/ul/li[2]/div/a[2]/span"; // by.xpath
    String emailField = "email"; // by.id
    String passwordField = "password"; // by.name
    String registrujmeButton = "//*[@id=\"loginSubmit\"]"; // by.xpath
    String checkBox = "aggrement"; // by.id
    String cookiesButton = "button.btn.primary"; // by.cssSelector
    String errorMessageForPassword = "span.field-info.label-info.poor"; // by.cssSelector
    String strongPasswordMessage = "span.field-info.label-info.strong"; // by.cssSelector
    String registrationThumsUpPicture = "div.login-box"; // by.cssSelector

    //Test Data
    String emailTestData = "milos123@example.com";
    String passwordTestData = "MILOS123@";

    @Before
    public void openRegistrationForm() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get(urlGigatronSite);
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(registracijaButton)));
        driver.findElement(By.xpath(registracijaButton)).click();
        System.out.println("Open gigatron site and click on 'registracija' button");
    }

    //Test01 Verify that the user cannot be registered if leaves all required fields empty.
    @Test
    public void Test01 () {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cookiesButton)));
        driver.findElement(By.cssSelector(cookiesButton)).click();
        driver.findElement(By.xpath(registrujmeButton)).click();
        System.out.println("Click on 'registruj me' button");
        Assert.assertTrue(driver.findElement(By.cssSelector(errorMessageForPassword)).isDisplayed());
        System.out.println("Verify that the error message are present");
    }

    //Test02 Verify that the user cannot be registered if leaves the email field empty and enters valid data in other required field.
    @Test
    public void Test02 () {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name(passwordField)));
        driver.findElement(By.name(passwordField)).sendKeys(passwordTestData);
        System.out.println("Enter data in password field");
        driver.findElement(By.cssSelector(cookiesButton)).click();
        System.out.println("Click on cookies button");
        driver.findElement(By.xpath(registrujmeButton)).click();
        System.out.println("Click on 'registruj me' button");
        Assert.assertTrue(driver.findElement(By.cssSelector(strongPasswordMessage)).isDisplayed());
        System.out.println("Verify that the strong password message are present");
    }

    //Test03 Verify that the user cannot be registered if leaves the password field empty and enters valid data in other required field.
    @Test
    public void Test03 () {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(emailField)));
        driver.findElement(By.id(emailField)).sendKeys(emailTestData);
        System.out.println("Enter data in email field");
        driver.findElement(By.cssSelector(cookiesButton)).click();
        System.out.println("Click on cookies button");
        driver.findElement(By.xpath(registrujmeButton)).click();
        System.out.println("Click on 'registruj me' button");
        Assert.assertTrue(driver.findElement(By.cssSelector(errorMessageForPassword)).isDisplayed());
        System.out.println("Verify that the error message for password are present");
    }

    //Test04 Verify that the user can be register if enter proper data in all required fields.
    @Test
    public void Test04 () {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(emailField)));
        driver.findElement(By.id(emailField)).sendKeys(emailTestData);
        System.out.println("Enter data in email field ");
        driver.findElement(By.name(passwordField)).sendKeys(passwordTestData);
        System.out.println("Enter valid data in password field");
        driver.findElement(By.id(checkBox)).click();
        System.out.println("Click on check box field");
        driver.findElement(By.cssSelector(cookiesButton)).click();
        System.out.println("Click on cookies button");
        driver.findElement(By.xpath(registrujmeButton)).click();
        System.out.println("Click on 'registruj me' button");

        System.out.println("User must be registered on gigatron registration form");
        Assert.assertTrue(driver.findElement(By.cssSelector(registrationThumsUpPicture)).isDisplayed());
    }
    @After
    public void TestQuit () {
        driver.quit();
    }
}
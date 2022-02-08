import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QaPracticeTelenorWebshop {

    WebDriver driver;
    WebDriverWait wait;

    //The goal of this testing is to check whether the user can log in to the 'telenor webshop' with the invalid email address

    //Set up driver and open site for testing
    @Before
    public void setUp(){

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.telenor.rs/");

    }

    @Test
    public void telenorWebshopLogIn(){

        WebElement ulogujSeButton = driver.findElement(By.cssSelector("span[class='list-item font-weight-bold second-level mr-2 ']"));
        ulogujSeButton.click();
        System.out.println("Click on 'uloguj se' button");

        try {Thread.sleep(1000); } catch ( Exception e ){ }

        WebElement webshopButton = driver.findElement(By.cssSelector("span[class='list-item third-level no-child undefined']"));
        webshopButton.click();
        System.out.println("Click on 'webshop' button");

        try {Thread.sleep(1000); } catch ( Exception e ){ }

        WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        emailField.sendKeys("PERAPERIC@123");
        System.out.println("Enter invalid email address");

        try {Thread.sleep(1000); } catch ( Exception e ){ }

        WebElement prijaviSeButton = driver.findElement(By.cssSelector("div>button[class='btn btn-primary login-btn']"));
        prijaviSeButton.click();
        System.out.println("Click on 'prijavi se' button");

        try {Thread.sleep(1000); } catch ( Exception e ){ }


        WebElement errorMessage = driver.findElement(By.cssSelector("div[class='nalog-error col-lg-12 col-sm-12 col-md-12 col-xs-12 notification-container']"));
        String actualColorOfErrorMessage = errorMessage.getCssValue("color");
        String expecetedColorOfErroMessage = "rgba(112, 11, 11, 1)";

        Assert.assertEquals(actualColorOfErrorMessage, expecetedColorOfErroMessage);

        //The User cannot register if  enters an incorrect email address







    }


}

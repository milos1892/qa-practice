import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QaPracticeVariousExercises {
    WebDriver driver;
    WebElement element;
    WebDriverWait wait;

    @Before
    public void setUpDriver (){
        System.setProperty("webdriver.chrome.driver", "C:/SELENIUM/Chrome 3/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);

    }

    @Test
    public void dropDown () {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        element = driver.findElement(By.id("dropdown"));
        element.click();
        WebElement option1 = element.findElement(By.cssSelector("option[value='1']"));
        WebElement option2 = element.findElement(By.cssSelector("option[value='2']"));
        option1.click();
        Assert.assertTrue(option1.isSelected());
        Assert.assertFalse(option2.isSelected());
        System.out.println("I selected option 1");
        driver.quit();

    }
    @Test
    public void hovers() {
        driver.get("https://the-internet.herokuapp.com/hovers");
        element = driver.findElement(By.className("figure"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        driver.quit();
    }
    @Test
    public void rightClick () {
        driver.navigate().to("https://the-internet.herokuapp.com/context_menu");
        element = driver.findElement(By.id("hot-spot"));
        Actions actions = new Actions((driver));
        actions.contextClick(element).perform();
        driver.switchTo().alert().accept();
        driver.quit();
    }
    @Test
    public void xpathExercise() {
        driver.navigate().to("https://www.telenor.rs/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id=\"3\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("accept-button")));
        driver.findElement(By.id("accept-button")).click();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[5]/div[1]/a")).click();
        driver.quit();
    }


}



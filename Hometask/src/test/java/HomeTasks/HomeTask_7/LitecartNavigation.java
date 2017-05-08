package HomeTasks.HomeTask_7;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class LitecartNavigation {
  private WebDriver driver;
  private WebDriverWait wait;

  public boolean isElementPresent(By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (InvalidSelectorException ex) {
      throw ex;
    } catch (NoSuchElementException ex) {
      return false;
    }

  }

  @Before
  public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
    driver.get("http://localhost/litecart/admin");
    driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    login("admin", "admin");
  }

  private void login(String username, String password) {
    driver.findElement(By.name("username")).sendKeys(username);
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.xpath("*//button[@name='login']")).click();
    driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
  }


  @Test
  public void menuCheckTest() {
    int mainMenuItemsQuantity = driver.findElements(By.cssSelector("span.name")).size();
    for (int i = 1; i <= mainMenuItemsQuantity; i++) {
      clickMainMenuItem(i);
      int subMenuItemsQuantity = driver.findElements(By.cssSelector("ul.docs [id^=doc-]")).size();
      for (int l = 1; l <= subMenuItemsQuantity; l++) {
        clickSubMenuItem(l);
        Assert.assertEquals(true, isElementPresent(By.cssSelector("h1")));
      }
    }
  }

  private void clickMainMenuItem(int i) {
    String cssMainMenuSelector = "ul#box-apps-menu" + " " + "li:nth-child" + "(" + i + ")";
    driver.findElement(By.cssSelector(cssMainMenuSelector)).click();
  }

  private void clickSubMenuItem(int l) {
    String cssSubMenuSelector = "ul.docs" + " " + "li:nth-child" + "(" + l + ")";
    driver.findElement(By.cssSelector(cssSubMenuSelector)).click();

  }

@After
  //logout and stop
  public void stop() {
    driver.findElement(By.xpath("//*[@id=\"shortcuts\"]/a[5]/i")).click();
    driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    driver.quit();
  }

}


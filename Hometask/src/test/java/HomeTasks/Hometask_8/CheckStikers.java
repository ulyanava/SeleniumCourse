package HomeTasks.Hometask_8;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CheckStikers {
  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
  }


  @Test
  public void CheckStikersTest() {
    driver.get("http://localhost/litecart/en/");
    driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

    clickTab("campaign-products");
    int allDucksQuantity = getAllDucksQuantity("box-campaigns");
    for (int i = 1; i <= allDucksQuantity; i++) {
      int allStikersQuantity = getAllStikersForItem("box-campaigns", i);
      Assert.assertTrue(allStikersQuantity == 1);
    }
    clickTab("popular-products");
    allDucksQuantity = getAllDucksQuantity("box-most-popular");
    for (int i = 1; i <= allDucksQuantity; i++) {
      int allStikersQuantity = getAllStikersForItem("box-most-popular", i);
      Assert.assertTrue(allStikersQuantity == 1);
    }
    clickTab("latest-products");
    allDucksQuantity = getAllDucksQuantity("box-latest-products");
    for (int i = 1; i <= allDucksQuantity; i++) {
      int allStikersQuantity = getAllStikersForItem("box-latest-products", i);
      Assert.assertTrue(allStikersQuantity == 1);
    }

  }

  private void clickTab(String tabName) {
    WebElement page = driver.findElement(By.cssSelector("#content "));
    String cssSelector = "a[href *=" + tabName + "]";
    page.findElement(By.cssSelector(cssSelector)).click();
  }

  //calculate quantity of all ducks under defined Tab
  private int getAllDucksQuantity(String tabAllDucksId) {
    String cssLocatorAllDucks = "div#" + tabAllDucksId + " " + "a.link[data-toggle=lightbox]";
    int allDucksQuantity = driver.findElements(By.cssSelector(cssLocatorAllDucks)).size();
    return allDucksQuantity;
  }
//calculate quantity of all attributes of 'stickers' class for one duck
  public int getAllStikersForItem(String tabAllDucksId, int i) {
    String xPathSelector = "//div[@id='" + tabAllDucksId + "']/div/" + "div[" + i + "]";
    WebElement oneDuck = driver.findElement(By.xpath(xPathSelector));
    int allStikersQuantity = oneDuck.findElements(By.xpath(".//div[contains(@class,'sticker')]")).size();
    return allStikersQuantity;
  }

  @After
  //logout and stop
  public void stop() {
    driver.quit();
  }

}
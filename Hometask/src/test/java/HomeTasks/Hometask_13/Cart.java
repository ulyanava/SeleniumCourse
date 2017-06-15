package HomeTasks.Hometask_13;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

@SuppressWarnings("ALL")
public class Cart {
  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
    driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
  }

  @Test
  public void addDeleteCartItemsTest() {
    driver.get("http://localhost/litecart/en/");
    addGoodsToCart();
    driver.get("http://localhost/litecart/en/checkout");
    deleteAllGoods();
  }

  @After
  public void stop() {
    driver.quit();
  }

  public void addGoodsToCart() {

    for (int i = 1; i <= 3; i++) {

      driver.findElement(By.cssSelector("div#box-most-popular li.product:nth-child(1)")).click();
      if (checkSizePopup()) {
        Select select = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
        select.selectByValue("Small");
      }
      driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
      waitCartUpdate();
      driver.navigate().back();
    }
  }

  public void waitCartUpdate() {
    WebElement selectorGoodsQuantity = driver.findElement(By.cssSelector("div#cart span.quantity"));
    int cartSize = Integer.parseInt((selectorGoodsQuantity).getText());
    wait.until(textToBePresentInElement(selectorGoodsQuantity, String.valueOf(cartSize + 1)));
  }

  public boolean checkSizePopup() {
    return driver.findElements(By.cssSelector("select[name='options[Size]']")).size() > 0;
  }


  public void deleteAllGoods() {
    while (driver.findElements(By.cssSelector("table.dataTable td.item")).size() > 1) {
        WebElement deleteButton = wait.until(presenceOfElementLocated(
                By.cssSelector("form[name='cart_form'] button[name='remove_cart_item']")));
        driver.findElement(By.cssSelector("button[name='remove_cart_item']")).click();
        wait.until(ExpectedConditions.stalenessOf(deleteButton));
    }
    driver.findElement(By.cssSelector("button[name='remove_cart_item']")).click();
  }
}

package HomeTasks.Hometask_13;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

@SuppressWarnings("ALL")
public class Cart{
  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
    driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
  }
  @Test
  public void addDucksToCart(){
    for (int i = 1; i <= 3; i++) {
      driver.get("http://localhost/litecart/en/");
      driver.findElement(By.cssSelector("div#box-most-popular li.product:nth-child(1)")).click();
     if(checkSizePopup()){
       Select select = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
       select.selectByValue("Small");
      }
      driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
    waitCartUpdate();
     // driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
      //driver.get("http://localhost/litecart/en/");
      driver.navigate().back();
    }
  }

  /*@After
  public void stop() {

    driver.quit();
  }*/
  public void waitCartUpdate() {
    WebElement selectorGoodsQuantity = driver.findElement(By.cssSelector("div#cart span.quantity"));
    int cartSize = Integer.parseInt((selectorGoodsQuantity).getText());
    wait.until(textToBePresentInElement(selectorGoodsQuantity, String.valueOf(cartSize + 1)));
  }
  public boolean checkSizePopup() {
    return driver.findElements(By.cssSelector("select[name='options[Size]']")).size()>0;
 /*   try {
    driver.findElement(By.cssSelector("select[name='options[Size]']"));
    return true;
    } catch (NoSuchElementException ex) {
return false;
    }*/


  }
/*1) открыть главную страницу+
2) открыть первый товар из списка+
2) добавить его в корзину (при этом может случайно добавиться товар, который там уже есть, ничего страшного)+
3) подождать, пока счётчик товаров в корзине обновится+
4) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара+
5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
6) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится табли*/
}

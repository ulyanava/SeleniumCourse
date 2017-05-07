package HomeTasks.Hometask_8;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class CheckStikers {
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
  /*  public static boolean testik (List<WebElement>  allDucks){
    int i = 0;
    boolean haha=true;
        while (haha == true) {
     return haha= true
        }
    }
    }
*/


  @Before
  public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
  }


  @Test
  public void CheckStikersTest() {
    driver.get("http://localhost/litecart/en/");
    driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    /*click tab1
    WebElement form = driver.findElement(By.cssSelector("#content"));
    WebElement tab1 = form.findElement(By.cssSelector("ul a[href *=campaign-products]"));*/

    //click tab2
    WebElement form = driver.findElement(By.cssSelector("#content"));
    form.findElement(By.cssSelector("ul a[href *=popular-products]")).click();
    //take all ducks
    WebElement table = driver.findElement(By.cssSelector("#box-most-popular"));
    List<WebElement> allDucks = table.findElements(By.cssSelector(".col-xs-halfs col-sm-thirds col-md-fourths col-lg-fifths"));


    /*check stikers
    div.image-wrapper div.stiker class $=^stiker
  //  List<WebElement> ducks = table.findElements(By.cssSelector("#popular-products"));



   //String allDucks = driver.findElements(By.cssSelector("div#box-most-popular"));

    //click tab3
    form.findElement(By.cssSelector("ul a[href *=latest-products]")).click();
  }*/


/*  @After
  //logout and stop
  public void stop() {
    driver.findElement(By.xpath("//*[@id=\"shortcuts\"]/a[5]/i")).click();
    driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    driver.quit();
  }
*/


  }
}

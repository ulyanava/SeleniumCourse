package HomeTasks.Hometask_10;

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


public class CheckGoodСampaigns {
  private WebDriver driver;
  private WebDriverWait wait;
  String tabSelector = "[href *=campaign-products]";


  public void openGoodDetails(){
    driver.findElement(By.cssSelector("#box-campaigns > div > div:nth-child(1) a.link[data-toggle=lightbox]")).click();
  }

  public String[] getRGB (String colorValue)
  {
    System.out.println("Color: "+colorValue);
    colorValue =colorValue.substring((colorValue.indexOf("(")+1), colorValue.lastIndexOf(")"));
    String [] rgbArray= colorValue.split(",");

    return rgbArray;
      }
      public boolean rgbEquals (String[] rgbArray){
        String r =" "+ rgbArray[0];
        String g = rgbArray[1];
        String b = rgbArray[2];

        if (r.equals(b)&& b.equals(g)) {
          return true;
        }
        else {
          return false;
        }
      }
  public boolean rgbRed (String[] rgbArray){
    String g = rgbArray[1];
    String b = rgbArray[2];

    if (g.equals(" 0")&& b.equals(" 0")) {
      return true;
    }
    else {
      return false;
    }
  }
  @Before
  public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
    driver.get("http://localhost/litecart/en/");
    driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    driver.findElement(By.cssSelector(tabSelector)).click();
  }


  @Test
  //compare good name on Main page and on good details
  //!! WORKS!!!
  public void NameCompare() {
    String nameMainPage = driver.findElement(By.cssSelector("#box-campaigns > div > div:nth-child(1) div.name")).getText();
    openGoodDetails();
    String nameGoodDetails = driver.findElement(By.cssSelector("h1")).getText();
    Assert.assertEquals(nameMainPage,nameGoodDetails );
  }
  @Test
  //compare regular good price on Main page and on good details
  //!! WORKS!!!
  public void RegularPriceCompare() {
    String regularPriceMainPage = driver.findElement(By.cssSelector("#box-campaigns > div > div:nth-child(1) s.regular-price")).getText();
    openGoodDetails();
    String regularPriceGoodDetails = driver.findElement(By.cssSelector("div.row div.price-wrapper del.regular-price")).getText();
    Assert.assertEquals(regularPriceMainPage,regularPriceGoodDetails );
  }

  @Test
  //compare sale good price on Main page and on good details
  //!! WORKS!!!
  public void SalePriceCompare() {
    String regularPriceMainPage = driver.findElement(By.cssSelector("#box-campaigns > div > div:nth-child(1) strong.campaign-price")).getText();
    openGoodDetails();
    String regularPriceGoodDetails = driver.findElement(By.cssSelector("div.row div.price-wrapper strong.campaign-price")).getText();
    Assert.assertEquals(regularPriceMainPage,regularPriceGoodDetails );
  }
  @Test
  //check regular price( Main page):text crossed
  //!! WORKS!!!
  public void regularPriceStyleMainPage() {
    String cssValue = driver.findElement(By.cssSelector("#box-campaigns > div > div:nth-child(1) s.regular-price")).getCssValue("text-decoration-line");
    System.out.print(cssValue);
    Assert.assertEquals("line-through", cssValue);
  }

  @Test
  //check regular price( good details):text crossed
  //!! WORKS!!!
  public void regularPriceStyleDetailsPage() {
    openGoodDetails();
    String cssValue =driver.findElement(By.cssSelector("div.row div.price-wrapper del.regular-price")).getCssValue("text-decoration-line");
    System.out.print(cssValue);
    Assert.assertEquals("line-through", cssValue);
  }
  @Test
  //check regular price( Main page): gray color
  //!! WORKS!!!
  public void regularPriceColorMainPage() {
    String colorPrice = driver.findElement(By.cssSelector("#box-campaigns > div > div:nth-child(1) s.regular-price")).getCssValue("color");
    Assert.assertTrue(rgbEquals(getRGB(colorPrice)));
  }
  @Test
  //check regular price( Details page): gray color
  //!! WORKS!!!
  public void regularPriceColorDetailsPage() {
    openGoodDetails();
    String colorPrice =driver.findElement(By.cssSelector("div.row div.price-wrapper del.regular-price")).getCssValue("color");
    Assert.assertTrue(rgbEquals(getRGB(colorPrice)));
  }

  @Test
  //check sale color on Main Page= red (G and B = 0)
  //!! WORKS!!!
  public void salePriceColorMainPage() {
    String colorPrice = driver.findElement(By.cssSelector("#box-campaigns > div > div:nth-child(1) strong.campaign-price")).getCssValue("color");
    Assert.assertTrue(rgbRed(getRGB(colorPrice)));
  }
  @Test
  //check sale color on Details page= red (G and B = 0)
  //!! WORKS!!!
  public void salePriceColorDetailsPage() {
    openGoodDetails();
    String colorPrice = driver.findElement(By.cssSelector("div.row div.price-wrapper strong.campaign-price")).getCssValue("color");
    Assert.assertTrue(rgbRed(getRGB(colorPrice)));
  }


    @After
  public void stop() {

    driver.quit();
  }
  }

package HomeTasks.Hometask_10;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class GoodСampaignsFirefox {
  String tabSelector = "[href *=campaign-products]";
  String regularPriceMainPageSelector = "#box-campaigns > div > div:nth-child(1) s.regular-price";
  String regularPriceDetailsPageSelector = "div.row div.price-wrapper del.regular-price";
  String salePriceMainPageSelector = "#box-campaigns > div > div:nth-child(1) strong.campaign-price";
  String salePriceDetailsPageSelector = "div.row div.price-wrapper strong.campaign-price";

  private WebDriver driver;
  private WebDriverWait wait;

  public void openGoodDetails() {
    driver.findElement(By.cssSelector("#box-campaigns > div > div:nth-child(1) a.link[data-toggle=lightbox]")).click();
  }

  public String[] getRGB(String colorValue) {
    System.out.println("Color: " + colorValue);
    colorValue = colorValue.substring((colorValue.indexOf("(") + 1), colorValue.lastIndexOf(")"));
    String[] rgbArray = colorValue.split(",");
    return rgbArray;
  }

  public boolean rgbEquals(String[] rgbArray) {
    String r = " " + rgbArray[0];
    String g = rgbArray[1];
    String b = rgbArray[2];
    if (r.equals(b) && b.equals(g)) {
      return true;
    } else {
      return false;
    }
  }

  public boolean rgbRed(String[] rgbArray) {
    String g = rgbArray[1];
    String b = rgbArray[2];
    if (g.equals(" 0") && b.equals(" 0")) {
      return true;
    } else {
      return false;
    }
  }

  public boolean checkBoldTag(String price) {
    if (price.equals("strong") || price.equals("b")) {
      return true;
    } else {
      return false;
    }
  }

  public boolean checkCroossTag(String price) {
    if (price.equals("strike") || price.equals("s") || price.equals("del")) {
      return true;
    } else {
      return false;
    }
  }

  @Before
  public void start() {
    driver = new FirefoxDriver();
    wait = new WebDriverWait(driver, 10);
    driver.get("http://localhost/litecart/en/");
    driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    driver.findElement(By.cssSelector(tabSelector)).click();
  }


  @Test
  //!!compare good name on Main page and on good details
  public void nameCompare() {
    String nameMainPage = driver.findElement(By.cssSelector("#box-campaigns > div > div:nth-child(1) div.name")).getText();
    openGoodDetails();
    String nameGoodDetails = driver.findElement(By.cssSelector("h1")).getText();
    Assert.assertEquals(nameMainPage, nameGoodDetails);
  }

  @Test
  //!!!compare regular good price on Main page and on good details
  public void regularPriceCompare() {
    String regularPriceMainPage = driver.findElement(By.cssSelector(regularPriceMainPageSelector)).getText();
    openGoodDetails();
    String regularPriceGoodDetails = driver.findElement(By.cssSelector(regularPriceDetailsPageSelector)).getText();
    Assert.assertEquals(regularPriceMainPage, regularPriceGoodDetails);
  }

  @Test
  //!!!compare sale good price on Main page and on good details
  public void salePriceCompare() {
    String regularPriceMainPage = driver.findElement(By.cssSelector(salePriceMainPageSelector)).getText();
    openGoodDetails();
    String regularPriceGoodDetails = driver.findElement(By.cssSelector(salePriceDetailsPageSelector)).getText();
    Assert.assertEquals(regularPriceMainPage, regularPriceGoodDetails);
  }

  @Test
  //!!!check regular price( Main page):text crossed
  public void regularPriceStyleMainPage() {
    String tagName = driver.findElement(By.cssSelector(regularPriceMainPageSelector)).getTagName();
    Assert.assertTrue(checkCroossTag(tagName));
  }

  @Test
  //!!check regular price( good details):text crossed
  public void regularPriceStyleDetailsPage() {
    openGoodDetails();
    String tagName = driver.findElement(By.cssSelector(regularPriceDetailsPageSelector)).getTagName();
    Assert.assertTrue(checkCroossTag(tagName));
  }

  @Test
  //!!!check regular price( Main page): gray color
  public void regularPriceColorMainPage() {
    String colorPrice = driver.findElement(By.cssSelector(regularPriceMainPageSelector)).getCssValue("color");
    Assert.assertTrue(rgbEquals(getRGB(colorPrice)));
  }

  @Test
  //!!!check regular price( Details page): gray color
  public void regularPriceColorDetailsPage() {
    openGoodDetails();
    String colorPrice = driver.findElement(By.cssSelector(regularPriceDetailsPageSelector)).getCssValue("color");
    Assert.assertTrue(rgbEquals(getRGB(colorPrice)));
  }

  @Test
  //!!!check sale color on Main Page= red (G and B = 0)
  public void salePriceColorMainPage() {
    String colorPrice = driver.findElement(By.cssSelector(salePriceMainPageSelector)).getCssValue("color");
    Assert.assertTrue(rgbRed(getRGB(colorPrice)));
  }

  @Test
  //!!check sale color on Details page= red (G and B = 0)
  public void salePriceColorDetailsPage() {
    openGoodDetails();
    String colorPrice = driver.findElement(By.cssSelector(salePriceDetailsPageSelector)).getCssValue("color");
    Assert.assertTrue(rgbRed(getRGB(colorPrice)));
  }

  @Test
  //check sale price is bold on Main Page
  public void salePriceBoldMainPage() {
    String price = driver.findElement(By.cssSelector(salePriceMainPageSelector)).getTagName();
    Assert.assertTrue(checkBoldTag(price));
  }

  @Test
  //check sale price is bold on Details Page
  public void salePriceBoldDetailsPage() {
    openGoodDetails();
    String price = driver.findElement(By.cssSelector(salePriceDetailsPageSelector)).getTagName();
    Assert.assertTrue(checkBoldTag(price));
  }

  @Test
  //111compare regular and sale size on Main Page
  public void pricesSizeCompareMainPage() {
    //get regular price size
    Dimension regularPrice = driver.findElement(By.cssSelector(regularPriceMainPageSelector)).getSize();
    int heightRegular = regularPrice.getHeight();
    int widthRegular = regularPrice.getWidth();
    System.out.println("Regular price: " + "height=" + heightRegular + " width=" + widthRegular);
// get sale price size
    Dimension salePrice = driver.findElement(By.cssSelector(salePriceMainPageSelector)).getSize();
    int heightSale = salePrice.getHeight();
    int widthSale = salePrice.getWidth();
    System.out.println("Sale price: " + "height=" + heightSale + " width=" + widthSale);
    Assert.assertTrue(heightRegular < heightSale);
  }

  @Test
  //!!compare regular and sale size on Detail Page
  public void pricesSizeCompareDetailsPage() {
    openGoodDetails();
    //get regular price size
    Dimension regularPrice = driver.findElement(By.cssSelector(regularPriceDetailsPageSelector)).getSize();
    int heightRegular = regularPrice.getHeight();
    int widthRegular = regularPrice.getWidth();
    System.out.println("Regular price: " + "height=" + heightRegular + " width=" + widthRegular);
// get sale price size
    Dimension salePrice = driver.findElement(By.cssSelector(salePriceDetailsPageSelector)).getSize();
    int heightSale = salePrice.getHeight();
    int widthSale = salePrice.getWidth();
    System.out.println("Sale price: " + "height=" + heightSale + " width=" + widthSale);
    Assert.assertTrue(heightRegular < heightSale);
  }

  @After
  public void stop() {

    driver.quit();
  }
}

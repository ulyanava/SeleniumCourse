package HomeTasks.Hometask_12;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class AdminAddGood {
  private WebDriver driver;
  private WebDriverWait wait;

  public void login(String username, String password) {
    driver.findElement(By.name("username")).sendKeys(username);
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.xpath("*//button[@name='login']")).click();
    driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
  }

  @Before
  public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
    driver.get("http://localhost/litecart/admin");
    driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    login("admin", "admin");
  }

  @Test
  public void addGood() {
    clickCatalog();
    clickAddNewProduct();
    //Not done
    //fillGeneral();
   // openInformation();
    //Fix Description!!
    //fillInformation();
    openPrices();
    fillPrices();
  }

  private void clickCatalog() {
    driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(2)")).click();
  }

  private void clickAddNewProduct() {
    driver.findElement(By.cssSelector("ul.list-inline a[href*='edit_product']")).click();
  }

  private void fillGeneral() {
    //*code*
    driver.findElement(By.cssSelector(".tab-content input[name='code']")).sendKeys("123456");
    //*name*
    driver.findElement(By.cssSelector(".tab-content input[name='name[en]']")).sendKeys("name");

   //??Categories checkbox

    //Default Category selectBox
    Select select= new Select(driver.findElement(By.cssSelector("#tab-general select[name='default_category_id']")));
    select.selectByIndex(0);


    /*//???Date Valid From
    ((JavascriptExecutor)driver).executeScript ("document.getElementById('fromDate').removeAttribute('readonly',0);"); // Enables the from date box

    WebElement fromDateBox= driver.findElement(By.id("fromDate"));
    fromDateBox.clear();
    fromDateBox.sendKeys("8-Dec-2014"); //Enter date in required format*/

//??Date Valid To
//*SKU input*
    driver.findElement(By.cssSelector("#tab-general input[name='sku']")).sendKeys("SKU");
    //*GTIN input*
    driver.findElement(By.cssSelector("#tab-general input[name='gtin']")).sendKeys("GTIN");
    //*TARIC input*
    driver.findElement(By.cssSelector("#tab-general input[name='taric']")).sendKeys("TARIC");
//???Quantity rows
    //???Weight
    //???Width x Height x Length

    //*Delivery Status select*
    select= new Select(driver.findElement(By.cssSelector("#tab-general select[name='delivery_status_id']")));
    select.selectByIndex(0);

    //*Sold Out Status select*
    select= new Select(driver.findElement(By.cssSelector("#tab-general select[name='sold_out_status_id']")));
    select.selectByIndex(0);
  }

  private void openInformation() {
    driver.findElement(By.cssSelector(".nav a[href='#tab-information']")).click();
  }
  private void openPrices() {
    driver.findElement(By.cssSelector(".nav a[href='#tab-prices']")).click();
  }

  private void fillInformation() {
    //*Manufacturer*
    Select select = new Select(driver.findElement(By.cssSelector("#tab-information select[name='manufacturer_id']")));
    select.selectByIndex(0);
//*Supplier*
    select = new Select(driver.findElement(By.cssSelector("#tab-information select[name='supplier_id']")));
    select.selectByIndex(0);
    //*Keys*
    driver.findElement(By.cssSelector("#tab-information input[name='keywords']")).sendKeys("keys");
    //*short description*
    driver.findElement(By.cssSelector("#tab-information input[name='short_description[en]']")).sendKeys("short_description");

//!!!!Локатор неверный, атрибут искать Description
    driver.findElement(By.cssSelector("#tab-information textarea[name='description[en]']")).sendKeys("Description");
    //*Attributes*
    driver.findElement(By.cssSelector("#tab-information textarea[name='attributes[en]']")).sendKeys("Attributes");
    //*Head Title*
    driver.findElement(By.cssSelector("#tab-information input[name='head_title[en]']")).sendKeys("head title");
    //*Meta Description*
    driver.findElement(By.cssSelector("#tab-information input[name='meta_description[en]']")).sendKeys("meta_description");
  }

  private void fillPrices() {
    //Purchase Price
    driver.findElement(By.cssSelector("#prices select[name='purchase_price_currency_code']")).click();
    Select select = new Select(driver.findElement(By.cssSelector("#prices select[name='purchase_price_currency_code']")));
    select.selectByIndex(2);

    //Tax Class
    select = new Select(driver.findElement(By.cssSelector("#prices select[name='tax_class_id']")));
    select.selectByIndex(0);

    //Price Incl. Tax (?)
    driver.findElement(By.cssSelector(".tab-content input[name='name[en]']")).sendKeys("name");
    driver.findElement(By.cssSelector(".tab-content input[name='name[en]']")).sendKeys("name");


    //Campaigns
    driver.findElement(By.cssSelector(".tab-content input[name='name[en]']")).sendKeys("name");
    driver.findElement(By.cssSelector(".tab-content input[name='name[en]']")).sendKeys("name");
    driver.findElement(By.cssSelector(".tab-content input[name='name[en]']")).sendKeys("name");
    driver.findElement(By.cssSelector(".tab-content input[name='name[en]']")).sendKeys("name");
    driver.findElement(By.cssSelector(".tab-content input[name='name[en]']")).sendKeys("name");
    driver.findElement(By.cssSelector(".tab-content input[name='name[en]']")).sendKeys("name");
  }

/*
@After
  //logout and stop
  public void stop() {
    driver.findElement(By.xpath("//*[@id=\"shortcuts\"]/a[5]/i")).click();
    driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    driver.quit();
  }
  */
}

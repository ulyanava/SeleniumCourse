package HomeTasks.Hometask_12;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
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
    String name = "name_" + System.currentTimeMillis();
    fillGeneral(name);
    openInformation();
    fillInformation();
    openPrices();
    fillPrices();
    save();
    clickCatalogCatalog();
    String pathGood = String.format("//a[text()='%s']", name);
    System.out.println(pathGood);
    Assert.assertTrue(name, driver.findElement(By.xpath(pathGood)).isDisplayed());


  }

  //Navigation
  private void clickCatalog() {
    driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(2)")).click();
  }

  private void openInformation() {
    driver.findElement(By.cssSelector("div.tabs a[href='#tab-information']")).click();
  }

  private void openPrices() {
    driver.findElement(By.cssSelector("div.tabs a[href='#tab-prices']")).click();
  }

  private void clickAddNewProduct() {
    driver.findElement(By.cssSelector("a.button[href*='edit_product']")).click();
  }

  private void clickCatalogCatalog() {
    driver.findElement(By.cssSelector("a[href='http://localhost/litecart/admin/?app=catalog&doc=catalog']")).click();
  }

//General tab

  private void fillGeneral(String name) {
    //Input fields
    String code = "code_" + System.currentTimeMillis();
    driver.findElement(By.cssSelector("input[name='code']")).sendKeys(code);
    driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys(name);


    //Button Status
    driver.findElement(By.xpath("//div[@id='tab-general']//label[contains(text(),'Enabled')]")).click();

    //Select drop downs
    //*Delivery Status select*
    Select select = new Select(driver.findElement(By.cssSelector("select[name='delivery_status_id']")));
    select.selectByVisibleText("3-5 days");
    //*Sold Out Status select*
    select = new Select(driver.findElement(By.cssSelector("select[name='sold_out_status_id']")));
    select.selectByVisibleText("Sold out");

    //*Categories checkbox--> Select value*

    if (driver.findElement(By.cssSelector("input[data-name='Rubber Ducks']")).isSelected()) ;
    else
      driver.findElement(By.cssSelector("input[data-name='Rubber Ducks']")).click();

    //*Product groups checkbox --> Select all*

    int checkboxesQuantity = driver.findElements(By.cssSelector("input[name='product_groups[]']")).size();
    for (int i = 1; i <= checkboxesQuantity; i++) {

      if (driver.findElement(By.cssSelector("input[name='product_groups[]'][value='1-" + i + "']")).isSelected()) ;
      else
        driver.findElement(By.cssSelector("input[name='product_groups[]'][value='1-" + i + "']")).click();
    }

//Date Fields
    driver.findElement(By.cssSelector("input[name='date_valid_from']")).sendKeys("01-01-2017");
    driver.findElement(By.cssSelector("input[name='date_valid_to']")).sendKeys("10-06-2017");


    //Input + select fields (clear mask+ type + select)
//*Quantity rows*
    driver.findElement(By.cssSelector("input[name='quantity']")).clear();
    driver.findElement(By.cssSelector("input[name='quantity']")).sendKeys("2");
    select = new Select(driver.findElement(By.cssSelector("select[name='quantity_unit_id']")));
    select.selectByVisibleText("pcs");


//Upload File
    String path = new File("src/test/java/HomeTasks/Hometask_12/good.jpg").getAbsolutePath();
    driver.findElement(By.cssSelector("input[name='new_images[]']")).sendKeys(path);
  }

  private void fillInformation() {
    //*Manufacturer*
    Select select = new Select(driver.findElement(By.cssSelector("select[name='manufacturer_id']")));
    select.selectByVisibleText("ACME Corp.");
//*Supplier*
    select = new Select(driver.findElement(By.cssSelector("select[name='supplier_id']")));
    select.selectByIndex(0);
    //*Keys*
    driver.findElement(By.cssSelector("input[name='keywords']")).sendKeys("keys");
    //*short description*
    driver.findElement(By.cssSelector("input[name='short_description[en]']")).sendKeys("short_description");

//Description
    driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("Description");

    //*Head Title*
    driver.findElement(By.cssSelector("input[name='head_title[en]']")).sendKeys("head title");
    //*Meta Description*
    driver.findElement(By.cssSelector("input[name='meta_description[en]']")).sendKeys("meta_description");
  }

  private void fillPrices() {
    //*Purchase Price
    driver.findElement(By.cssSelector("input[name='purchase_price']")).clear();
    driver.findElement(By.cssSelector("input[name='purchase_price']")).sendKeys("200");
    Select select = new Select(driver.findElement(By.cssSelector("select[name='purchase_price_currency_code']")));
    select.selectByValue("EUR");

    //*Tax Class
    select = new Select(driver.findElement(By.cssSelector("table#table-prices select[name='tax_class_id']")));
    select.selectByIndex(0);

    //*Price Incl. Tax*
    driver.findElement(By.cssSelector("input[name='gross_prices[USD]']")).clear();
    driver.findElement(By.cssSelector("input[name='gross_prices[USD]']")).sendKeys("901");
    driver.findElement(By.cssSelector("input[name='gross_prices[EUR]']")).clear();
    driver.findElement(By.cssSelector("input[name='gross_prices[EUR]']")).sendKeys("902");

  }

  private void save() {
    driver.findElement(By.cssSelector("button[name='save']")).click();
  }


  @After
  //logout and stop
  public void stop() {
    driver.findElement(By.cssSelector("div.header a[title='Logout']")).click();
    driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    driver.quit();
  }

}

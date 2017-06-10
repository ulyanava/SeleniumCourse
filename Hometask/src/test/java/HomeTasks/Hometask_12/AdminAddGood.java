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
    driver.findElement(By.cssSelector(".nav a[href='#tab-information']")).click();
  }
  private void openPrices() {
    driver.findElement(By.cssSelector(".nav a[href='#tab-prices']")).click();
  }
  private void clickAddNewProduct() {
    driver.findElement(By.cssSelector("ul.list-inline a[href*='edit_product']")).click();
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
    String skuName = "skuName_" + System.currentTimeMillis();
    String gtinName = "gtinName_" + System.currentTimeMillis();
    String taricName = "taricName_" + System.currentTimeMillis();
    driver.findElement(By.cssSelector("input[name='sku']")).sendKeys(skuName);
    driver.findElement(By.cssSelector("input[name='gtin']")).sendKeys(gtinName);
    driver.findElement(By.cssSelector("input[name='taric']")).sendKeys(taricName);

    //Button Status
    driver.findElement(By.xpath("//div[@id='tab-general']//label[contains(text(),'Enabled')]")).click();

    //Select drop downs
    //Default Category selectBox
    Select select = new Select(driver.findElement(By.cssSelector("select[name='default_category_id']")));
    select.selectByIndex(0);
    //*Delivery Status select*
    select = new Select(driver.findElement(By.cssSelector("select[name='delivery_status_id']")));
    select.selectByIndex(0);
    //*Sold Out Status select*
    select = new Select(driver.findElement(By.cssSelector("select[name='sold_out_status_id']")));
    select.selectByIndex(0);

//Check whether checkbox is selected and click if not
    //*Categories checkbox*

    if (driver.findElement(By.cssSelector("div.checkbox:nth-child(1) input")).isSelected()) ;
    else
      driver.findElement(By.cssSelector("div.checkbox:nth-child(1) input")).click();


//Date Fields
    driver.findElement(By.cssSelector("input[name='date_valid_from']")).sendKeys("01-01-2017");
    driver.findElement(By.cssSelector("input[name='date_valid_to']")).sendKeys("10-06-2017");


    //Input + select fields (clear mask+ type + select)
//*Quantity rows*
    driver.findElement(By.cssSelector("input[name='quantity']")).clear();
    driver.findElement(By.cssSelector("input[name='quantity']")).sendKeys("2");
    select = new Select(driver.findElement(By.cssSelector("select[name='quantity_unit_id']")));
    select.selectByIndex(0);
    //Weight*
    driver.findElement(By.cssSelector("input[name='weight']")).clear();
    driver.findElement(By.cssSelector("input[name='weight']")).sendKeys("1000");
    select = new Select(driver.findElement(By.cssSelector("select[name='weight_class']")));
    select.selectByValue("oz");
    //*Width x Height x Length*
    driver.findElement(By.cssSelector("input[name='dim_x']")).clear();
    driver.findElement(By.cssSelector("input[name='dim_x']")).sendKeys("101");
    driver.findElement(By.cssSelector("input[name='dim_y']")).clear();
    driver.findElement(By.cssSelector("input[name='dim_y']")).sendKeys("102");
    driver.findElement(By.cssSelector("input[name='dim_z']")).clear();
    driver.findElement(By.cssSelector("input[name='dim_z']")).sendKeys("103");
    select = new Select(driver.findElement(By.cssSelector("select[name='dim_class']")));
    select.selectByValue("in");

//Upload File
    String path = new File("src/test/java/HomeTasks/Hometask_12/good.jpg").getAbsolutePath();
    driver.findElement(By.cssSelector("input[name='new_images[]']")).sendKeys(path);

  }

  private void fillInformation() {
    //*Manufacturer*
    Select select = new Select(driver.findElement(By.cssSelector("select[name='manufacturer_id']")));
    select.selectByIndex(0);
//*Supplier*
    select = new Select(driver.findElement(By.cssSelector("select[name='supplier_id']")));
    select.selectByIndex(0);
    //*Keys*
    driver.findElement(By.cssSelector("input[name='keywords']")).sendKeys("keys");
    //*short description*
    driver.findElement(By.cssSelector("input[name='short_description[en]']")).sendKeys("short_description");

//Description
    driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("Description");

    //*Attributes*
    driver.findElement(By.cssSelector("textarea[name='attributes[en]']")).sendKeys("Attributes");
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
    select.selectByIndex(2);

    //*Tax Class
    select = new Select(driver.findElement(By.cssSelector("#prices select[name='tax_class_id']")));
    select.selectByIndex(0);

    //*Price Incl. Tax*
    driver.findElement(By.cssSelector("input[name='gross_prices[USD]']")).clear();
    driver.findElement(By.cssSelector("input[name='gross_prices[USD]']")).sendKeys("901");
    driver.findElement(By.cssSelector("input[name='gross_prices[EUR]']")).sendKeys("902");

  }

  private void save() {
    driver.findElement(By.cssSelector("button[name='save']")).click();
  }


@After
  //logout and stop
  public void stop() {
    driver.findElement(By.xpath("//*[@id=\"shortcuts\"]/a[5]/i")).click();
    driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    driver.quit();
  }

}

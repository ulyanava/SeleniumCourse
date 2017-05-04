package HomeTasks.HomeTask_7;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by tatianka on 01.05.2017.
 */
public class LitecartNavigation {
  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
  }


  @Test
  public void menuCheckTest() {
    driver.get("http://localhost/litecart/admin");
    driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    login("admin", "admin");
    menuClicker();
  }

  private void login(String username, String password) {
    driver.findElement(By.name("username")).sendKeys(username);
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.xpath("*//button[@name='login']")).click();
    driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
  }

  private void menuClicker() {
    //Appearance
    driver.findElement(By.cssSelector(".list-vertical li:first-child")).click();
    driver.findElement(By.cssSelector("#doc-template span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-logotype span[class=name]")).click();

    //Catalog
    driver.findElement(By.cssSelector(".list-vertical a[href *=catalog]")).click();
    driver.findElement(By.cssSelector("#doc-catalog span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-product_groups span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-option_groups span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-manufacturers span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-suppliers span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-delivery_statuses span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-sold_out_statuses span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-quantity_units span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-csv span[class=name]")).click();

    //Countries
    driver.findElement(By.cssSelector(".list-vertical a[href *=countries]")).click();

    //Currencies
    driver.findElement(By.cssSelector(".list-vertical a[href *=currencies]")).click();

    //Customers
    driver.findElement(By.cssSelector(".list-vertical a[href *=customers]")).click();
    driver.findElement(By.cssSelector("#doc-customers span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-csv span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-newsletter span[class=name]")).click();

    //Geo Zones
    driver.findElement(By.cssSelector(".list-vertical a[href *=geo_zones]")).click();

    //Languages
    driver.findElement(By.cssSelector(".list-vertical a[href *=languages]")).click();
    driver.findElement(By.cssSelector("#doc-languages span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-storage_encoding span[class=name]")).click();

    //Modules
    driver.findElement(By.cssSelector(".list-vertical a[href *=modules]")).click();
    driver.findElement(By.cssSelector("#doc-customer span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-shipping span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-payment span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-order span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-order_total span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-jobs span[class=name]")).click();

    //Orders
    driver.findElement(By.cssSelector(".list-vertical a[href *=orders]")).click();
    driver.findElement(By.cssSelector("#doc-orders span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-order_statuses span[class=name]")).click();

    //Pages
    driver.findElement(By.cssSelector(".list-vertical a[href *=pages]")).click();
    driver.findElement(By.cssSelector("#doc-pages span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-csv span[class=name]")).click();

    //Reports
    driver.findElement(By.cssSelector(".list-vertical a[href *=reports]")).click();
    driver.findElement(By.cssSelector("#doc-monthly_sales span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-most_sold_products span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-most_shopping_customers span[class=name]")).click();

    //Settings
    driver.findElement(By.cssSelector(".list-vertical a[href *=settings]")).click();
    driver.findElement(By.cssSelector("#doc-store_info span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-defaults span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-general span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-listings span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-images span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-checkout span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-advanced span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-security span[class=name]")).click();

    //Slides*
    driver.findElement(By.cssSelector(".list-vertical a[href *=slides]")).click();

    //Tax*
    driver.findElement(By.cssSelector(".list-vertical a[href *=tax]")).click();
    driver.findElement(By.cssSelector("#doc-tax_rates span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-tax_classes span[class=name]")).click();

    //Translations*
    driver.findElement(By.cssSelector(".list-vertical a[href *=translations]")).click();
    driver.findElement(By.cssSelector("#doc-search span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-scan span[class=name]")).click();
    driver.findElement(By.cssSelector("#doc-csv span[class=name]")).click();

    //Users*
    driver.findElement(By.cssSelector(".list-vertical a[href *=users]")).click();

    //Vqmods*
    driver.findElement(By.cssSelector(".list-vertical a[href *=vqmods]")).click();

  }

  @After
  //logout and stop
  public void stop() {
    driver.findElement(By.xpath("//*[@id=\"shortcuts\"]/a[5]/i")).click();
    driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    driver.quit();
  }

}


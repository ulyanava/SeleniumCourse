package HomeTasks.Hometask_9;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.TimeUnit;

public class AdminSorting {
  private WebDriver driver;
  private WebDriverWait wait;

  public boolean checkAlphabetSorting(List<WebElement> webElementList) {
    for (int i = 1; i < webElementList.size(); i++) {
      int compareTo = webElementList.get(i - 1).getText().compareTo(webElementList.get(i).getText());
      if (compareTo > 0) {
        return false;
      }
    }
    return true;
  }

  public ArrayList<Integer> findNotNullZones() {
    List<WebElement> listZones = driver.findElements(By.cssSelector("form[name=countries_form] td:nth-child(6)"));
    int i;
        ArrayList<Integer> numberOfContry = new ArrayList<>();
    for (i=1; i<listZones.size(); i++){
      String zoneValue = listZones.get(i).getText();
     if (zoneValue.equals("0")) {
     }else{
             numberOfContry.add(i+1);}
   }

    return numberOfContry;
  }



  @Before
  public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
  }

  private void login(String username, String password) {
    driver.findElement(By.name("username")).sendKeys(username);
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.xpath("*//button[@name='login']")).click();
    driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
  }

  @Test
  public void checkSortingTest1() {
    driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    login("admin", "admin");
    //check Countries sorting
  //  List<WebElement> listCountries = driver.findElements(By.cssSelector("form[name=countries_form] td:nth-child(5)"));
  //  Assert.assertTrue(checkAlphabetSorting(listCountries));

    System.out.println("Id of country with Zone:"+" " +findNotNullZones());

    for(int i=0; i<findNotNullZones().size(); i++) {
      String countryCssLocator = "form[name=countries_form] tr:nth-child(" + findNotNullZones().get(i)+") a";
              driver.findElement(By.cssSelector(countryCssLocator)).click();
              List<WebElement> listZones = driver.findElements(By.cssSelector("form > table td:nth-child(2)"));
            Assert.assertTrue(checkAlphabetSorting(listZones));
      driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    }
  }



/*  @After
  //logout and stop
  public void stop() {
    driver.findElement(By.xpath("//*[@id=\"shortcuts\"]/a[5]/i")).click();
    driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    driver.quit();
  }
  */

}
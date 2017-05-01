package HomeTasks.HomeTask_1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * HomeTask1: Start Browser, Open Google.com and Close Browser
 */
public class OpenBrowser {
  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
  }

  @Test
  public void openBrowser() {
    driver.navigate().to("http://www.google.com");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @After
  public void stop() {
    driver.quit();
  }
}

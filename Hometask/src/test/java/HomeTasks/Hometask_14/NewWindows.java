package HomeTasks.Hometask_14;

        import org.junit.After;
        import org.junit.Assert;
        import org.junit.Before;
        import org.junit.Test;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.support.ui.ExpectedCondition;
        import org.openqa.selenium.support.ui.WebDriverWait;

        import java.util.List;
        import java.util.Set;
        import java.util.concurrent.TimeUnit;

public class NewWindows {
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
  public void checkNewWindowsTest() {
    driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    driver.get("http://localhost/litecart/admin/?app=countries&doc=edit_country");

    for (int i = 0; i < getAllLinks(); i++) {
      List<WebElement> elements = driver.findElements(By.cssSelector("i.fa-external-link"));
      String defaultWindow = driver.getWindowHandle();
      Set<String> oldWindows = driver.getWindowHandles();
        elements.get(i).click();
      String newWindow = wait.until(anyWindowOtherThan(oldWindows));
      driver.switchTo().window(newWindow);
      driver.close();
      driver.switchTo().window(defaultWindow);
      Set<String> windowsAfter = driver.getWindowHandles();
      Assert.assertTrue(oldWindows.equals(windowsAfter) ? true : false);


    }
  }
  @After
  //logout and stop
  public void stop() {
    driver.findElement(By.cssSelector("div.header a[title='Logout']")).click();
    driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    driver.quit();
  }

  public int getAllLinks() {
    return driver.findElements(By.cssSelector("i.fa-external-link")).size();
  }

  public ExpectedCondition<String> anyWindowOtherThan(final Set<String> oldWindows) {
    return new ExpectedCondition<String>() {
      public String apply(WebDriver driver) {
        Set<String> handles = driver.getWindowHandles();
        handles.removeAll(oldWindows);
        return handles.size() > 0 ? handles.iterator().next() : null;
      }
    };
  }
}

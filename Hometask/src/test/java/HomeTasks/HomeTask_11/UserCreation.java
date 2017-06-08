package HomeTasks.HomeTask_11;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class UserCreation {
  private WebDriver driver;
  private WebDriverWait wait;
  //CreateUserForm user = new CreateUserForm();

  String taxID = "12345";
  String firstName = "Jason";
  String lastName = "Momoa";
  String address1 = "California";
  String postcode = "22014";
  String email = System.currentTimeMillis() + "@gmail.com";
  String password = "12345";
  String company = "Epam";
  String address2 = "New York";
  String city = "Minsk";
  String phone = "2608980";


  @Before
  public void start() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
    driver.get("http://localhost/litecart/en/create_account");

  }

  @Test
  public void createUser() {
    fillTaxID(taxID);
    fillCompany(company);
    fillFirstName(firstName);
    fillLastName(lastName);
    fillAddress1(address1);
    fillAddress2(address2);
    fillPostcode(postcode);
    fillCountry();
    fillEmail(email);
    fillPassword(password);
    fillConfirmPassword(password);
    fillCity(city);
    fillPhone(phone);
    submitCreateAcc();
    logout();
    login(email, password);
    logout();
  }

  public void fillTaxID(String taxID) {
    driver.findElement(By.cssSelector("input[name=tax_id]")).sendKeys(taxID);
  }

  public void fillCompany(String company) {
    driver.findElement(By.cssSelector("input[name=company]")).sendKeys(company);
  }

  public void fillFirstName(String firstName) {
    driver.findElement(By.cssSelector("input[name=firstname]")).sendKeys(firstName);
  }

  public void fillLastName(String lastName) {
    driver.findElement(By.cssSelector("input[name=lastname]")).sendKeys(lastName);
  }

  public void fillAddress1(String address1) {
    driver.findElement(By.cssSelector("input[name=address1]")).sendKeys(address1);
  }

  public void fillAddress2(String address2) {
    driver.findElement(By.cssSelector("input[name=address2]")).sendKeys(address2);
  }

  public void fillPostcode(String postcode) {
    driver.findElement(By.cssSelector("input[name=postcode]")).sendKeys(postcode);
  }

  public void fillCountry() {
    driver.findElement(By.cssSelector("form select[name=country_code]")).click();
   //driver.findElement(By.xpath("//select[@name='country_code']/option[text()='United States']")).click();
    Select select = new Select(driver.findElement(By.xpath("//select[@name='country_code']")));
    select.selectByVisibleText("United States");
  }

  public void fillEmail(String email) {
    driver.findElement(By.cssSelector("#box-create-account input[name=email]")).sendKeys(email);
  }

  public void fillPassword(String password) {
    driver.findElement(By.cssSelector("#box-create-account input[name=password]")).sendKeys(password);
  }

  public void fillConfirmPassword(String password) {
    driver.findElement(By.cssSelector("#box-create-account input[name=confirmed_password]")).sendKeys(password);
  }

  public void fillCity(String city) {
    driver.findElement(By.cssSelector("input[name=city]")).sendKeys(city);
  }

  public void fillPhone(String phone) {
    driver.findElement(By.cssSelector("input[name=phone]")).sendKeys(phone);
  }

  public void submitCreateAcc() {
    driver.findElement(By.cssSelector("button[name=create_account]")).click();
  }

  public void logout() {
    driver.findElement(By.cssSelector("#box-account ul [href *=logout]")).click();

  }

  public void login(String email, String password) {
    driver.findElement(By.cssSelector("#box-account-login input[name=email]")).sendKeys(email);
    driver.findElement(By.cssSelector("#box-account-login input[name=password]")).sendKeys(password);
    driver.findElement(By.cssSelector("#box-account-login button[name=login]")).click();
  }

  @After
  public void stop() {
    driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    driver.quit();
  }
}


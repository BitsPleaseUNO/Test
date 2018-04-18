package me.gtrst.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AddBook {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "/home/james/Downloads/chromedriver");
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testAddBook() throws Exception {
    driver.get("http://localhost:5000/Book/Add");
    driver.findElement(By.id("inputISBN")).click();
    driver.findElement(By.id("inputISBN")).clear();
    driver.findElement(By.id("inputISBN")).sendKeys("12345");
    driver.findElement(By.id("inputTitle")).clear();
    driver.findElement(By.id("inputTitle")).sendKeys("Test Book");
    driver.findElement(By.id("inputAuthor")).clear();
    driver.findElement(By.id("inputAuthor")).sendKeys("Test Author");
    driver.findElement(By.id("inputPages")).clear();
    driver.findElement(By.id("inputPages")).sendKeys("100");
    driver.findElement(By.id("inputPublisher")).clear();
    driver.findElement(By.id("inputPublisher")).sendKeys("Test Publisher");
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

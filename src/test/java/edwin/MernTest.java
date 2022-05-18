package edwin;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class MernTest {

	private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  JavascriptExecutor js;
	  
	  @Before
	  public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    baseUrl = "https://www.google.com/";
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    js = (JavascriptExecutor) driver;
	    driver.get("https://mern-crud.herokuapp.com/");
	  }

	  @Test
	  public void testAgregar() throws Exception {	    
	    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
	    driver.findElement(By.name("name")).click();
	    driver.findElement(By.name("name")).clear();
	    driver.findElement(By.name("name")).sendKeys("Edwin Mac");
	    driver.findElement(By.name("email")).click();
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys("edwinmac@gmail.com");
	    driver.findElement(By.name("age")).click();
	    driver.findElement(By.name("age")).clear();
	    driver.findElement(By.name("age")).sendKeys("22");
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[1]/following::div[2]")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
	    pause(3000);
	    assertThat(driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p")).getText(),is("Successfully added!"));
	    driver.findElement(By.xpath("//i")).click();
	  }
	  
	  @Test
	  public void testBuscar() throws Exception {
		  pause(3000);
		  assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*edwinmac@gmail\\.com[\\s\\S]*$"));
	  }
	  
	  @Test
	  public void testCEditar() throws Exception {
		  driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button")).click();
		  driver.findElement(By.name("name")).click();
		  driver.findElement(By.name("name")).clear();
		  driver.findElement(By.name("name")).sendKeys("Edwin Pool");
		  driver.findElement(By.name("age")).clear();
		  driver.findElement(By.name("age")).sendKeys("21");
		  driver.findElement(By.name("age")).click();
		  driver.findElement(By.name("age")).clear();
		  driver.findElement(By.name("age")).sendKeys("20");
		  driver.findElement(By.name("age")).click();
		  driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
		  pause(3000);
		  assertThat(driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p")).getText(),is("Successfully updated!"));
		  driver.findElement(By.xpath("//i")).click();
	  }
	  
	  @Test
	  public void testDEliminar() throws Exception {
		  driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button[2]")).click();
		  driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Edwin Mac'])[2]/following::button[1]")).click();
		  pause(3000);
		  assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*edwinmac@gmail\\.com[\\s\\S]*$"));
	  }
	  
	  private void pause(long mils) {
		  try {
			  Thread.sleep(mils);
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
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

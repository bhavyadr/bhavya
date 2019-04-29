package Casestudy.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestMe 
{
	
	public WebDriver drivers;
	public WebDriverWait wait;
	String appURl="http://10.232.237.143:443/TestMeApp/";
	
	@BeforeTest
	public void beforeTest() throws MalformedURLException
	{
		System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
	    drivers=new ChromeDriver();
	    drivers.manage().window().maximize();
	    drivers.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	    wait=new WebDriverWait(drivers,30);
	    drivers.get(appURl);
	}
	
  @Test(dataProvider="credentials")
  public void testMeAppLogin(String username, String password,String bankname,String bankUN, String bankPWD, String tranPWD) throws InterruptedException 
  {
	  System.out.println("username: "+username+"password: "+password+"bankname: "+bankname+"bankusername: "+bankUN+"bankpassword: "+bankPWD+"transactionid: "+tranPWD);
	  drivers.findElement(By.linkText("SignIn")).click();
	  Thread.sleep(2000);
	  drivers.findElement(By.name("userName")).sendKeys(username);
	  drivers.findElement(By.name("password")).sendKeys(password);
	  drivers.findElement(By.name("Login")).click();
	  WebElement signout=wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("SignOut")));
	  
	 Assert.assertNotNull(signout);
	 
	 WebElement menu1=drivers.findElement(By.xpath("//*[@id=\"menu3\"]/li[2]/a/span"));
	 menu1.click();
	 Actions act1=new Actions(drivers);
	 act1.moveToElement(drivers.findElement(By.xpath("//span[contains(text(),'Electronics')]"))).click().build().perform();
	 act1.moveToElement(drivers.findElement(By.xpath("//span[contains(text(),'Head Phone')]"))).click().build().perform();

	 drivers.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click();
	 drivers.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]")).click();
	 String title=drivers.getTitle();
	 Assert.assertEquals("View Cart", title);
	 
	 drivers.findElement(By.partialLinkText("Checkout")).click();
	 drivers.findElement(By.xpath("/html/body/b/div/div/div[1]/div/div[2]/div[3]/div/form[2]/input")).click();
	 
	 switch(bankname)
	 {
	 case "Andhra Bank":
		 drivers.findElement(By.xpath("//label[contains(text(),'Andhra Bank')]")).click();
		 drivers.findElement(By.id("btn")).click();
		 bankCredentials(bankUN,bankPWD,tranPWD);
		 break;
		 
	 case "HDFC Bank":
		 drivers.findElement(By.xpath("//label[contains(text(),'HDFC Bank')]")).click();
		 drivers.findElement(By.id("btn")).click();
		 bankCredentials(bankUN,bankPWD,tranPWD);
		 break;
		 
	 case "IDBI Bank":
		 drivers.findElement(By.xpath("//label[contains(text(),'IDBI Bank')]")).click();
		 drivers.findElement(By.id("btn")).click();
		 bankCredentials(bankUN,bankPWD,tranPWD);
		 break;
		 
	 case "UCO Bank":
		 drivers.findElement(By.xpath("//label[contains(text(),'UCO Bank')]")).click();
		 drivers.findElement(By.id("btn")).click();
		 bankCredentials(bankUN,bankPWD,tranPWD);
		 break;
		 
		 default:
			 System.out.println("switch default case");
			 break;
	 }
	 
	 String title1=drivers.getTitle();
	 Assert.assertEquals("Order Details", title1);
	 drivers.findElement(By.linkText("SignOut")).click();
  }
  
  public void bankCredentials(String buser, String bpassword,String btpassword)
  {
	  drivers.findElement(By.name("username")).sendKeys(buser);
	  drivers.findElement(By.name("password")).sendKeys(bpassword);
	  drivers.findElement(By.xpath("//input[@value='LOGIN']")).click();
	  drivers.findElement(By.name("transpwd")).sendKeys(btpassword);
	  drivers.findElement(By.xpath("//input[@value='PayNow']")).click();
  }
  
  @DataProvider(name="credentials")
  public Object[][] getData() throws IOException
  {
	return ReadTestMeData.ReadData();
	  
  }
   @AfterTest
   public void afterTest()
   {
	   drivers.close();
   }
}
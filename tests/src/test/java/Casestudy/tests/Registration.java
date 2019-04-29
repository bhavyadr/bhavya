package Casestudy.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Registration 
{
	 WebDriver driver;
	   @BeforeTest
	   public void openApplication() throws InterruptedException
	   {
		   System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
		   driver=new ChromeDriver();
		   driver.get("http://10.232.237.143:443/TestMeApp");
		  // driver.manage().window().maximize();
	   }
	   
	   @Test(priority=0)
	   public void newuser() throws InterruptedException
	   {
		  driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/ul/li[2]/a")).click();
		  driver.findElement(By.xpath("//*[@id=\"userName\"]")).sendKeys("divyarani");
		  driver.findElement(By.xpath("//*[@id=\"firstName\"]")).sendKeys("divya");
		  driver.findElement(By.xpath("//*[@id=\"lastName\"]")).sendKeys("kg");
		  driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("bhavana123");
		  driver.findElement(By.xpath("//*[@id=\"pass_confirmation\"]")).sendKeys("bhavana123");
		  driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		/*  WebElement  femaleRadioBtn = driver.findElement (By.xpath("//*[@id=\"gender\"]"));
		  Select radio=new Select(femaleRadioBtn);
          radio.selectByVisibleText("Female");*/
		  
		  driver.findElement(By.xpath("//input[@value='Female']")).click();
          Thread.sleep(5000);
          //driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
          driver.findElement(By.cssSelector("#emailAddress")).sendKeys("divya.kg.m@gmail.com");
          driver.findElement(By.cssSelector("#mobileNumber")).sendKeys("9108608765");
         // driver.findElement(By.xpath("//*[@id=\"dob\"]")).sendKeys("07/05/1996");
          driver.findElement(By.xpath("//*[@id=\"dob\"]"));
          driver.findElement(By.xpath("/html/body/main/div/div/form/fieldset/div/div[9]/div/div/img")).click();
          WebElement dobmonth= driver.findElement(By.xpath("//*[@id=\'ui-datepicker-div\']/div/div/select[1]"));
          Select month= new Select(dobmonth);
          month.selectByIndex(6);
          WebElement dobyear=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[2]"));
          Select year=new Select(dobyear);
          year.selectByValue("1996");
          driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[1]/td[6]/a")).click();
       //   Select day=new Select(dobday);
         // day.selectByVisibleText("5").;
          driver.findElement(By.xpath("//*[@id=\"address\"]")).sendKeys("Tumkur, Karnataka");
          WebElement mySelectElement = driver.findElement(By.xpath("//*[@id=\"securityQuestion\"]"));
          Select dropdown= new Select(mySelectElement);
          dropdown.selectByIndex(2);
          driver.findElement(By.xpath("//*[@id=\"answer\"]")).sendKeys("yellow");
          driver.findElement(By.xpath("/html/body/main/div/div/form/fieldset/div/div[13]/div/input[1]")).click();
          //driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
          
   	   Assert.assertEquals(driver.getTitle(),"Sign Up");
          Thread.sleep(5000);
	   }
	   
	   @Test(priority=1)
        public void home() throws InterruptedException
        {
		   driver.findElement(By.xpath("/html/body/header/div/b/a")).click();
		   Thread.sleep(5000);
		   Assert.assertEquals(driver.getTitle(),"Home");
        }
	   @AfterTest
	   public void closeApplication()
	   {
		   driver.close();
	   }
	   
}

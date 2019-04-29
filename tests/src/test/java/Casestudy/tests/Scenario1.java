package Casestudy.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Scenario1 
{
	   WebDriver driver;
	   @BeforeTest
	   public void openApplication() 
	   {
		   System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
		   driver=new ChromeDriver();
		   driver.get("http://10.232.237.143:443/TestMeApp");//app open
		   driver.manage().window().maximize();
	   }
	   
	   @Test(priority=0)
	   public void login()
	   {
		   driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/ul/li[1]/a")).click();//signin
		   driver.findElement(By.xpath("//*[@id=\"userName\"]")).sendKeys("lalitha"); //username
		   driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("password123"); //password
		   driver.findElement(By.name("Login")).click(); // login
		   Assert.assertEquals(driver.getTitle(), "Home");  // title check
		   
	    /* WebElement string=driver.findElement(By.name("SignOut"));
	      Assert.assertEquals(string, "SignOut");
		   
		   
		   String Etext="Hi, Lalitha";
		   WebElement Atext=driver.findElement(By.name("Hi, lalitha"));
		   if(Etext.contains((CharSequence) Atext))
		   {
			   System.out.println("pass");
		   }
		   else
		   {
			   System.out.println("fail");
		   }*/
			
	   }
	    @Test(priority=1)
	   public void cart()
	   {
		   WebElement search = driver.findElement(By.xpath("//*[@id=\"myInput\"]"));  //search bar
		   search.sendKeys("Carpet");
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   driver.findElement(By.xpath("/html/body/div[1]/form/input")).click();//find details
		   //Thread.sleep(5000);
		   String msg=driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[1]/center[1]/h4")).getText();
		   Assert.assertEquals(msg, "Carpet"); //message check
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click(); //add to cart
		   driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]")).click();//cart
		   driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		   //Thread.sleep(5000);
		   Assert.assertEquals(driver.getTitle(), "View Cart"); //title check
		   driver.findElement(By.xpath("//*[@id=\"cart\"]/tfoot/tr[2]/td[5]/a")).click();//check out
		   driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		 
	   }
	  
	   
	    @Test(priority=2)
	    public void payment() 
	    {
	    	Assert.assertEquals(driver.getTitle(), "Cart Checkout"); // title check
	    	driver.findElement(By.xpath("/html/body/b/div/div/div[1]/div/div[2]/div[3]/div/form[2]/input")).click(); //proceed to pay
	    
	    	 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	    	// Thread.sleep(5000);
	    	  driver.findElement(By.xpath("//*[@id=\'swit\']/div[1]/div")).click(); //bank selection
	    	  driver.findElement(By.xpath("//*[@id=\"btn\"]")).click(); //continue
	    	  driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/input[1]")).sendKeys("123456"); //username
	    	  driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/input[2]")).sendKeys("Pass@456");// password
	    	  driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/div/div[3]/input")).click(); //login
	    	  driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/input")).sendKeys("Trans@456"); // transaction id
	    	  driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/div/div[2]/input")).click(); // paynow
	    	  Assert.assertEquals(driver.getTitle(), "Order Details"); //title check
	    }
	    
	    @Test(priority=3)
	    public void logout() throws InterruptedException 
	    {
	    	driver.findElement(By.xpath("/html/body/header/div/div/ul/b/a[2]")).click(); //sign out
	    	Assert.assertEquals(driver.getTitle(), "Sign Out"); //title check
	    	Thread.sleep(5000);
	    	 //driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	    	Assert.assertEquals(driver.getTitle(), "Home"); //title check
	    }
	    
	    
	    
	    /*@Test(priority=4)
	    public void check()
	    {
	    	driver.findElement(By.xpath("//*[@id=\'demo1\']"));
	    	WebElement value=driver.findElement(By.xpath("//*[@id=\'quantity_1\']"));
	    	Select quantity= new Select(value);
	    	quantity.selectByVisibleText("1");
	    	if(quantity.equals(null))
	    	{
	    		System.out.println("fail");
	    	}
	    	else if(value.equals(driver.findElement(By.xpath("//input[@value='1']"))))
	    	{
	    		WebElement price=driver.findElement(By.xpath("//input[@id='demo1']"));
	    		if(price.equals(driver.findElement(By.xpath("//*[@id=\"cart\"]/tbody/tr/td[2]"))))
	    		{
	    			System.out.println("item quantity is="+value);
	    		}
	    		else
	    		{
	    		  System.out.println("item quantity is="+value);
	    		}
	    		
	    	}
	    }*/
	   
	   @AfterTest
	   public void closeApplication()
	   {
		   driver.close();
	   }
}

package com.seleniumfundamental.task11;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class windowhandle {

	protected static String url="https://the-internet.herokuapp.com/windows";
	 WebDriver driver;
	@BeforeSuite
	 public void startChromeBrowser() {
		 WebDriverManager.chromedriver().setup();//setup required initially.
		  driver=new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	 }
	 @BeforeClass
	 public void openUrl() {
		  driver.get(url);
	
	 }	 
	 @Test
	  public void handlewindow() throws InterruptedException {
	  driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		 
       //get all window handles
	   Set<String> windowhandles= driver.getWindowHandles();
	   List<String> listwindows=new ArrayList<>(windowhandles);
		 
	    //navigate to child window
		driver.switchTo().window(listwindows.get(1));
		String childwindow= driver.findElement(By.xpath("//h3[.='New Window']")).getText();
		String actual="New Window";
		
		 //condition checking , whether reached new window  or not
		Assert.assertEquals(childwindow, actual);
		if(childwindow.equals(actual)) {
			System.out.println("The visible text in the window  "+childwindow);
		}
		else {
			System.out.println("The text is not visible in the window  "+childwindow);
		}
		
		 driver.close();
	  }
	 
	  
//	  @AfterSuite		
//	  public void closeChromeBrowser() {
//			  driver.quit();
//	  }
}
 


package com.seleniumfundamental.task11;

import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class framehandle {
	
	protected static String url=" http://the-internet.herokuapp.com/nested_frames";
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
	  public void framehandle() throws InterruptedException {
		  		  
		  WebElement topFrame = driver.findElement(By.xpath("//frame[@name='frame-top']"));
			// Switching to top frame
			driver.switchTo().frame(topFrame);

			List<WebElement> frames = driver.findElements(By.tagName("frame"));
			String elementSize = String.valueOf(frames.size());
			if (elementSize.equals("3")) {
				System.out.println("Three framesin the page");
			} else {
				System.out.println("Not three frames in the page");
			}
						
		  //left frame
		  WebElement frameleftelement=driver.findElement(By.xpath("//frame[@name='frame-left']"));
		  driver.switchTo().frame(frameleftelement);//web element to frame as argument.		 
		  WebElement lefttext=driver.findElement(By.xpath("//body[contains(text(),'LEFT')]"));
		  Assert.assertEquals(lefttext.getText(), "LEFT");
		  System.out.println(lefttext.getText());
		  
		  //outside of left frame			
		  driver.switchTo().defaultContent();
		  driver.switchTo().frame(topFrame);

		  //middle frame
		  WebElement framemiddleelement=driver.findElement(By.xpath("//frame[@name='frame-middle']"));
		  driver.switchTo().frame(framemiddleelement);//web element to frame as argument.
	      WebElement middletext=driver.findElement(By.id("content"));
	      Assert.assertEquals(middletext.getText(), "MIDDLE");
		  System.out.println(middletext.getText());
		  
		  //outside of middle frame			
		  driver.switchTo().defaultContent();	
		  driver.switchTo().frame(topFrame);
		  
		  //right frame
		  WebElement framerightelement=driver.findElement(By.xpath("//frame[@name='frame-right']"));
		  driver.switchTo().frame(framerightelement);//web element to frame as argument.
		  WebElement righttext=driver.findElement(By.xpath("//body[contains(text(),'RIGHT')]"));
		  Assert.assertEquals(righttext.getText(), "RIGHT");
		  System.out.println(righttext.getText());
		  
		  //outside of right frame				
		  driver.switchTo().defaultContent();	
				  
		   WebElement bottomFrame = driver.findElement(By.xpath("//frame[@name='frame-bottom']"));
			// Switching to bottom frame
			driver.switchTo().frame(bottomFrame);
			WebElement bottomtext=driver.findElement(By.xpath("//body[contains(text(),'BOTTOM')]"));
			Assert.assertEquals(bottomtext.getText(), "BOTTOM");
			System.out.println(bottomtext.getText());
			driver.switchTo().defaultContent();	
	  }
	  @AfterSuite
		 public void closeChromeBrowser() {
			  driver.quit();
		 }
}
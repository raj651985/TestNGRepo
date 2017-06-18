package org.app;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HeadlessBrowserDemo {
	

	
	  private WebDriver driver;
	  private String baseUrl;
	  private StringBuffer verificationErrors = new StringBuffer();
	  protected static DesiredCapabilities dCaps;
	  
	  @BeforeClass
		public void setUp() {
			System.out.println("*******************");
			System.out.println("Headless Browser....");
			System.out.println("Without launching IE browser..");
			dCaps = new DesiredCapabilities();
			  dCaps.setJavascriptEnabled(true);
			  dCaps.setCapability("takesScreenshot", false);
			//File file = new File("F:/Jenkins_Tomcat/phantomjs-2.1.1-windows/bin/phantomjs.exe");	
			File file = new File("/usr/local/share/phantomjs");
			
		    System.setProperty("phantomjs.binary.path", file.getAbsolutePath());	
		    driver = new PhantomJSDriver(dCaps);
		    baseUrl = "http://zual55.demos.hclets.com/";
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//System.setProperty("webdriver.ie.driver", "D:\\Rajkumar\\IEDriverServer_Win32_3.3.0\\IEDriverServer.exe");
			//driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		}
	  
	  @Test
	  public void testSearchReturnsResults() {
		  
		  DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
		    PhantomJSDriver driver = new PhantomJSDriver(capabilities);
		    
		    driver.get(baseUrl);
		    driver.manage().window().setSize( new Dimension( 1124, 850 ) );
		    System.out.println(" Title is " +driver.getTitle());
		    String strPageTitle = driver.getTitle();
		    Assert.assertTrue(strPageTitle.equalsIgnoreCase("Welcome TMO"), "Page title doesn't match");
		    
		    driver.findElement(By.linkText("Search")).click();
		    driver.navigate().refresh();

		    try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		   
		    String searchTerm = "iPod";
		    driver.findElement(By.name("query")).sendKeys(searchTerm);
		    System.out.println("searchItem is Ipod....");
		    driver.findElement(By.name("submit")).click();
		    
		    
		    
		    List<WebElement> results = driver.findElements(By.className("thead-default"));
		    System.out.println("result size " + results.size());
		    Assert.assertTrue(results.size() > 0);
		    //Assert.assertTrue(strPageTitle.equalsIgnoreCase("Welcome TMO"), "Page title doesn't match");
		    for(int i = 0; i < results.size(); i++)
		    {
		       System.out.println("Title is " +driver.getTitle());
		       System.out.println("Table Header is \t \n" + results.get(i).getText());
		       
		    }
		    
		    System.out.println("Show All the links");
			  List<WebElement> list = driver.findElements(By.xpath("//ul[@class='nav navbar-nav']//a[@href]"));
		        
		        for (WebElement e : list) {
		            String link = e.getAttribute("href");
		               
		            System.out.println(e.getTagName() + "=" + link + " , " + e.getText());
		        }
		    Assert.assertTrue(driver.getPageSource().toLowerCase().contains(searchTerm.toLowerCase()));
	  }
	  
	  @Test
	  public void getAllLinks() {
		  System.out.println("Test case 2");
		 /* List<WebElement> list = driver.findElements(By.xpath("//ul[@class='nav navbar-nav']//a[@href]"));
	        
	        for (WebElement e : list) {
	            String link = e.getAttribute("href");
	               
	            System.out.println(e.getTagName() + "=" + link + " , " + e.getText());
	        }*/
	  }
	  
	  
	  @AfterClass
		public void tearDown() {
			if(driver!=null) {
				System.out.println("Headless Browser is done..");
				driver.quit();
				String verificationErrorString = verificationErrors.toString();
			    //System.out.println("Error is " + verificationErrorString);
			}
		}

	  
	
	

}

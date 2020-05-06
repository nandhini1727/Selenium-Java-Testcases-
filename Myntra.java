package selenium.workout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Myntra {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		 //1) Open https://www.myntra.com/
		driver.get("http://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//2) Mouse over on WOMEN 
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("//a[text()='Women']"))
		.pause(2000)
		 //3) Click Jackets & Coats
		.click(driver.findElementByXPath("//a[text()='Jackets & Coats']"))
		.perform();
		String str = driver.findElementByClassName("title-count").getText();
		String text = str.replaceAll("\\D", "");
		// 4) Find the total count of item (top) -> getText -> String	
		int totalno = Integer.parseInt(text);
		String jackets = driver.findElementByXPath("(//span[@class='categories-num'])[1]").getText();
		String jack = jackets.replaceAll("\\D", "");
		int jacketcount = Integer.parseInt(jack);
		System.out.println(jacketcount);
		String coats = driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();
		String coat = coats.replaceAll("\\D", "");
		int coatscount = Integer.parseInt(coat);
		System.out.println(coatscount);
		int categorycount = jacketcount+coatscount;
		  //5) Validate the sum of categories count matches 
		 if(totalno==categorycount)
		{
			System.out.println("Its matching");
			
		}
		  // 6) Check Coats
		driver.findElementByXPath("(//div[@class='common-checkboxIndicator'])[2]").click();
		  //7) Click + More option under BRAND
		driver.findElementByXPath("//div[@class='brand-more']").click();
		  //8) Type MANGO and click checkbox
		driver.findElementByXPath("//input[@placeholder='Search brand']").sendKeys("Mango");
		Thread.sleep(2000);
		driver.findElementByXPath("//label[@class=' common-customCheckbox']").click();
		 //9) Close the pop-up x driver.
		driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
		Thread.sleep(2000);
		 // 10) Confirm all the Coats are of brand MANGO
		List <WebElement> brandslist = driver.findElementsByXPath("//div[@class='product-productMetaInfo']/h3");
	    for(WebElement brandName : brandslist)
	    {
	    	String brandtext = brandName.getText();
	    	if(brandtext.equals("Mango"))
	    	{
	    		int c=0;
	    	   c=c+1;
	    	}
      	
	    }
	    Thread.sleep(2000);
	    WebElement elesrc = driver.findElementByXPath("//div[@class='sort-sortBy']");
		builder.moveToElement(elesrc).perform();
		  //11) Sort by Better Discount
		driver.findElementByXPath("//label[text()='Better Discount']").click();
		List<WebElement> productprice = driver.findElementsByXPath("//span[@class='product-discountedPrice']");
		Thread.sleep(2000);
		//12) Find the price of first displayed item
		String proprice1 = productprice.get(0).getText();
		System.out.println(proprice1);
		//13) Mouse over on size of the first item 
		WebElement ele1 = driver.findElementByXPath("//img[@alt='MANGO Women Orange Solid Double-Breasted Longline Overcoat']");
		builder.moveToElement(ele1).perform();
		//driver.findElementByXPath("//span[text()='Shop after lockdown']").click();
		driver.close();
		System.out.println("Run success");
		

	}
}
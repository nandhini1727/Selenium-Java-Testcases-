package selenium.workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class Ajio {


	public static void main(String[] args) throws InterruptedException {
		//1) Go to https://www.ajio.com/  
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.ajio.com/shop/sale");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		/*
		 * Try {driver.findElementByXPath("//div[@class='ic-close-quickview']").click();
		 * catch (Exception e) {
		 * 
		 * } }
		 */
		ChromeOptions options = new ChromeOptions();
		  options.addArguments("--disable-notifications"); 
		  
			//2) Enter Bags in the Search field and Select Bags in Women Handbags  
		  driver.findElementByXPath("//input[@placeholder='Search AJIO']").sendKeys("Bags");
		  driver.findElementByXPath("(//a[@class='search-suggestionList  '])[3]").click();
			//3) Click on five grid and Select SORT BY as "What's New"
		  driver.findElementByXPath("//div[@class='five-grid']").click();
		  WebElement drpdwn = driver.findElementByXPath("//div[@class='filter-dropdown']/select");
		  Select dropdown=new Select(drpdwn);
		  dropdown.selectByVisibleText("What's New");
			//4) Enter Price Range Min as 2000 and Max as 5000  
			driver.findElementByXPath("//span[text()='price']").click();
		  driver.findElementByXPath("//input[@id='minPrice']").sendKeys("2000");
		  driver.findElementByXPath("//input[@id='maxPrice']").sendKeys("5000");
		  driver.findElementByXPath("(//button[@type='submit'])[2]").click();

		  //5) Click on the product "Puma Ferrari LS Shoulder Bag"  
		  driver.findElementByXPath("(//div[@class='brand'])[2]").click();
			Set<String> winSet1=driver.getWindowHandles();
			List<String> winLis=new ArrayList<String>(winSet1);
			driver.switchTo().window(winLis.get(1));

		/*
		 * //6) Verify the Coupon code for the price above 2690 is applicable for your
		 * product, if applicable the get the Coupon Code and Calculate the discount
		 * price for the coupon	
		 */
			String text = driver.findElementByXPath("//div[@class='prod-sp']").getText();
			String price = text.replaceAll("\\D", "");
			int totalPrice = Integer.parseInt(price);
			String couponCode = driver.findElementByXPath("//div[text()='Use Code']/br").getText();
			
			if(totalPrice>=2690)
			{
				String couponCode1 = driver.findElementByXPath("//div[text()='Use Code']/br").getText();
			}
				
			String offerPrice = driver.findElementByXPath("//div[text()='Get it for ']/span").getText();
			String pri = offerPrice.replaceAll("\\D", "");
			int finalPrice3 = Integer.parseInt(pri);
			int discountPrice =totalPrice - finalPrice3 ;
			System.out.println(discountPrice);
			Thread.sleep(2000);
		  
			//7) Check the availability of the product for pincode 560043, print the expected delivery date if it is available
			driver.findElementByXPath("//span[text()='Enter pin-code to know estimated delivery date.']").click();
			driver.findElementByXPath("//input[@name='pincode']").sendKeys("560043");
			driver.findElementByXPath("//button[@class='edd-pincode-modal-submit-btn']").click();

	    //8) Click on Other Informations under Product Details and Print the Customer Care address, phone and email
			driver.findElementByXPath("//div[@class='other-info-toggle']").click();
			String text1 = driver.findElementByXPath("(//span[@class='other-info'])[6]").getText();
			System.out.println(text);

			//9) Click on ADD TO BAG and then GO TO BAG  
			driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
			Thread.sleep(7000);
			driver.findElementByXPath("//span[text()='GO TO BAG']").click();

			//10) Check the Order Total before apply coupon  
			String text2 = driver.findElementByXPath("//div[@class='net-price best-price-strip']").getText();
			String replaceAll = text2.replaceAll("\\D", "");
			int orderTotal = Integer.parseInt(replaceAll);

			//11) Enter Coupon Code and Click Apply  
			driver.findElementById("EPIC").click();
			driver.findElementByXPath("//button[text()='Apply']").click();
			Thread.sleep(2000);

			//12) Verify the Coupon Savings amount(round off if it in decimal) under Order Summary and the matches the amount calculated in Product details  
			String text3 = driver.findElementByXPath("//span[@class='applied-coupon-section']/p").getText();
			String repAll = text3.replaceAll("\\D", "");
			float f1 = Float.parseFloat(repAll);
			int a = Math.round(f1);

			//13) Click on Delete and Delete the item from Bag 
			driver.findElementByXPath("//div[@class='product-delete']/div").click();
			Thread.sleep(1000);
			driver.findElementByXPath("//div[@class='card-delete-button']//div[text()='DELETE']").click();
			driver.findElementByXPath("//div[@class='empty-cart']/p").click();
			String Msge = driver.findElementByXPath("//div[@class='empty-cart']/p").getText();
			if(Msge.contains("Shopping Bag is Empty"))
			{
				System.out.println("Item is deleted from bag");
			}

			//14) Close all the browsers
			driver.close();
			
	}

}

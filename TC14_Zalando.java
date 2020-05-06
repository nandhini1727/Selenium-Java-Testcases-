package selenium.workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TC14_Zalando {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		//1) Go to https://www.zalando.com/
		driver.get("https://www.zalando.com/");
		
			//2) Get the Alert text and print it
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert message: "+alert.getText());
		alert.accept();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);		

			//3) Close the Alert box and click on Zalando.uk
		driver.findElementByXPath("//a[text()='Zalando.uk']").click();
			//4) Click Women--> Clothing and click Coat 
		driver.findElementByXPath("(//span[@class='z-text z-navicat-header_genderText z-text-cta z-text-black'])[1]").click();
		driver.findElementByXPath("(//span[@class='z-text z-navicat-header_categoryListLinkText z-text-cta z-text-black'])[3]").click();
driver.findElementByXPath("//a[@aria-label='Browse Coats by category']").click();
driver.findElementByXPath("//button[@class='uc-btn uc-btn-primary']").click();
		//5) Choose Material as cotton (100%) and Length as thigh-length
driver.findElementByXPath("//span[text()='Material']").click();
driver.findElementByXPath("//span[text()='cotton (100%)']").click();
driver.findElementByXPath("//button[text()='Save']").click();
Thread.sleep(3000);
driver.findElementByXPath("//span[text()='Length']").click();
driver.findElementByXPath("//span[text()='thigh-length']").click();
driver.findElementByXPath("//button[text()='Save']").click();
Thread.sleep(3000);
			//6) Click on Q/S designed by MANTEL - Parka coat
driver.findElementByXPath("//div[text()='Q/S designed by']").click();
		
		
			//7) Check the availability for Color as Olive and Size as 'M'
driver.findElementByXPath("(//img[@alt='olive'])[2]").click();
Thread.sleep(2000);
driver.findElementByXPath("//span[text()='XS']").click();
driver.findElementByXPath("//span[text()='M']").click();
String msg = driver.findElementByXPath("//h2[text()='Out of stock']").getText();
			//8) If the previous preference is not available, check  availability for Color Navy and Size 'M'
if (msg.contains("Out of stock")) {
	driver.findElementByXPath("(//img[@alt='navy'])[2]").click();
}
Thread.sleep(3000);
driver.findElementByXPath("//span[text()='Choose your size']").click();
driver.findElementByXPath("//span[text()='M']").click();

			//9) Add to bag only if Standard Delivery is free
String delivery = driver.findElementByXPath("(//span[@class='AtOZbZ'])[2]").getText();
if (delivery.contains("Free")) {
	System.out.println("Standard delivery is : " + delivery);
	driver.findElementByXPath("//span[text()='Add to bag']").click();
}

			//10) Mouse over on Your Bag and Click on "Go to Bag"
driver.findElementByXPath("//div[text()='Go to bag']").click();

			//11) Capture the Estimated Deliver Date and print
Thread.sleep(2000);
String date = driver.findElementByXPath("//div[@data-id='delivery-estimation']/span").getText();
System.out.println("Estimated delivery date is: " + date);

			//12) Mouse over on FREE DELIVERY & RETURNS*, get the tool tip text and print
WebElement msehvr = driver.findElementByXPath("//a[text()='Free delivery & returns*']");
Actions builder=new Actions(driver);
builder.moveToElement(msehvr).perform();
String title = driver.findElementByXPath("//a[text()='Free delivery & returns*']/ancestor::span")
		.getAttribute("title");
System.out.println("Tool tip text is: " + title);

			//13) Click on Start chat in the Start chat and go to the new window
driver.findElementByXPath("//a[text()='Free delivery & returns*']").click();
Thread.sleep(2000);
driver.findElementByXPath("//span[text()='Start chat']").click();
Set<String> winset = driver.getWindowHandles();
List<String> winlist = new ArrayList<String>(winset);
driver.switchTo().window(winlist.get(2));

			//14) Enter you first name and a dummy email and click Start Chat
Thread.sleep(3000);
driver.findElementByXPath("//input[@id='prechat_customer_name_id']").sendKeys("Nandhini");
driver.findElementByXPath("//input[@id='prechat_customer_email_id']").sendKeys("Nandy@gmail.com");
driver.findElementByXPath("//button[@id='prechat_submit']").click();

			//15) Type Hi, click Send and print thr reply message and close the chat window.
Thread.sleep(6000);
driver.findElementByXPath("//textarea[@id='liveAgentChatTextArea']").sendKeys("Hi", Keys.ENTER);
String replyMsg = driver.findElementByXPath("(//span[@class='messageText'])[4]").getText();
System.out.println("Reply Msg is: " + replyMsg);
driver.close();

	}

}

package selenium.workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Makemytrip {
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Click on Hotels
		driver.findElementByXPath("//span[text()='Hotels']").click();

		// Enter the City
		driver.findElementById("city").click();
		driver.findElementByXPath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']").sendKeys("Goa");

		// Choose Goa India
		driver.findElementByXPath("//p[text()='Goa, India']").click();

		// Click on Checkin Date
		driver.findElementById("checkin").click();
		driver.findElementByXPath("//div[@aria-label='Fri May 15 2020']").click();
		String startDate = driver.findElementByXPath("//div[@aria-label='Fri May 15 2020']").getText();

		int startDateConverted = Integer.parseInt(startDate);
		int endDate = startDateConverted + 5;

		System.out.println(endDate);
		driver.findElementByXPath("//div[@aria-label='Wed May " + endDate + " 2020']").click();

		// Click on Rooms and Guests
		driver.findElementByXPath("//input[@id='guest']").click();

		// Click on Adults
		driver.findElementByXPath("//li[@class='selected'][text()='2']").click();

		// Click on Children
		driver.findElementByXPath("//li[@data-cy='children-1']").click();

		// Select Children
		Select obj1 = new Select(driver.findElementByXPath("//select[@class='ageSelectBox']"));

		// Select Child age
		obj1.selectByIndex(11);

		// Click on Apply button
		driver.findElementByXPath("//button[@class='primaryBtn btnApply']").click();

		// Click on Search button
		driver.findElementByXPath("//button[@id='hsw_search_button']").click();

		// Click on outside window
		driver.findElementByXPath("//div[@class='mmBackdrop wholeBlack']").click();

		// select 5 Star under Star Category
		driver.findElementByXPath("(//span[@class='checkmarkOuter'])[13]").click();

		// Click on First resulting Hotel
		driver.findElementByXPath("(//div[@class='listingRowOuter'][1]//span)[2]").click();

		Thread.sleep(2000);

		Set<String> winSet = driver.getWindowHandles();
		List<String> winLis = new ArrayList<String>(winSet);
		driver.switchTo().window(winLis.get(1));

		// Navigate to the new window
		Thread.sleep(2000);

		// Print the Hotel Name
		String HotelName = driver.findElementByXPath("//h1[@id='detpg_hotel_name']").getText();
		System.out.println("Hotel name is:" + HotelName);

		// Click on More options
		driver.findElementByXPath("//span[text()='MORE OPTIONS']").click();

		// click on Book this now
		driver.findElementByXPath("//a[@id='detpg_headerright_book_now']").click();

		// Print the Total payable amount
		String text1 = driver.findElementByXPath("//span[@id='revpg_total_payable_amt']").getText();
		String text2 = text1.replaceAll("\\D", "");
		int totalPayableAmt = Integer.parseInt(text2);
		System.out.println("Total Payable amount is: " + totalPayableAmt);

		// Close the Browser
		driver.quit();

	}

}
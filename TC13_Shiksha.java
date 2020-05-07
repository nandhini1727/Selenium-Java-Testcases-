package selenium.workout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TC13_Shiksha {


	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		//1.Go to https://studyabroad.shiksha.com/
        driver.get("https://studyabroad.shiksha.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		
		
		//2.mousehover on colleges and click in computer science& engg under MS colleges
		Thread.sleep(2000);
		WebElement msehvr = driver.findElementByXPath("(//label[@class='menuTab-div fnt-wt melabel'])[3]");
		Actions builder=new Actions(driver);
		builder.moveToElement(msehvr).perform();
		driver.findElementByXPath("//a[text()='MS in Computer Science &Engg']").click();
		
		//3.select GRE Under Exam Accepted and score 300 & below
		driver.findElementByXPath("(//p[text()='GRE'])[1]").click();
		Thread.sleep(1000);
		WebElement slctScore = driver.findElementByXPath("(//select[@class='score-select-field'])[1]");
		Select score = new Select(slctScore);
		score.selectByVisibleText("300 & below");
		Thread.sleep(2000);

		
		//4.Max 10 lakhs under 1st yr total fees,USA under countries
		driver.findElementByXPath("//label[@for='fee-0']//p").click();
		driver.findElementByXPath("//label[@for='country-27']").click();
		
		//5.select sort by:low to high  1st yr total fees
		WebElement drpdwn1 = driver.findElementByXPath("//select[@id='categorySorter']");
		Select dropdown1=new Select(drpdwn1);
		dropdown1.selectByVisibleText("Low to high 1st year total fees");
		
		//6.click add to compare of the college having least fees with public university, scholarship and Accomadation facilities
		List<WebElement> collegesList = driver.findElementsByXPath("//div[@class='tuple-box']");
		
	     for (int i = 1; i <=collegesList.size() ; i++) {
	    	 
		String univer= driver.findElementByXPath("(//div[@class='detail-col flLt']/p[text()='Public university']/span)[" + i + "]").getAttribute("class");
		String scholar = driver.findElementByXPath("(//div[@class='detail-col flLt']/p[text()='Scholarship']/span)[" + i + "]").getAttribute("class");
		String accom = driver.findElementByXPath("(//div[@class='detail-col flLt']/p[text()='Accommodation']/span)[" + i + "]").getAttribute("class");
				
			if ((univer.equalsIgnoreCase("tick-mark")) && (scholar.equalsIgnoreCase("tick-mark"))&& (accom.equalsIgnoreCase("tick-mark"))) {
				System.out.println("public univer, scholar and Accom facilities enabled");
				driver.findElementByXPath("(//p[text()='Add to compare'])[" + i + "]").click();
				break;
				
				} else {
					System.out.println(" facilities Not Enabled");
				}
			}
     //7.select the first college under compare with similar colleges
	     Thread.sleep(2000);
	     driver.findElementByXPath("(//a[@class='add-tag-title'])[1]").click();
 
     //8.click on compare colleges>
	     Thread.sleep(2000);
	     driver.findElementByXPath("//strong[text()='Compare Colleges >']").click();

	//9.select when to study as 2021
	     Thread.sleep(2000);
	     driver.findElementByXPath("//label[@for='year1']/span").click();

	//10.select preferred countries as USA
	     Thread.sleep(2000);
	     driver.findElementByXPath("(//div[@class='input'])[1]").click();
	     driver.findElementByXPath("//label[@class='nolnht']/span").click();
	     driver.findElementByXPath(" //a[@class='ok-btn']").click();

     //11.select level of study as masters
	     Thread.sleep(1000);
	     driver.findElementByXPath("(//input[@value='Masters']/following::label/span)[1]").click();


	//12.select preferred course as MS
	     Thread.sleep(2000);
	     driver.findElementByXPath("(//div[@class='input'])[2]").click();
	     driver.findElementByXPath("//li[text()='MS']").click();

	//13.select specialization as "computer science  & Engineering"
	     Thread.sleep(2000);
	     driver.findElementByXPath("//div[text()='All specializations']").click();
	     driver.findElementByXPath("//li[text()='Computer Science & Engineering']").click();

     //14.click on signup
	     Thread.sleep(2000);
	     driver.findElementByXPath("//a[@id='signup']").click();

     
	//15.print all the warning messages displayed on the screen for missed mandatory fields
	     List<WebElement> errormsgs = driver.findElementsByXPath("//div[@class='helper-text']");
	     for (WebElement warningmsg : errormsgs) {
	    	 if(warningmsg.getText().length()>1) {
	    		 System.out.println(warningmsg.getText());

	

}}}}
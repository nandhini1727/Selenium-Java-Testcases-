package selenium.workout;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Honda {
@Test
	public void honda() throws InterruptedException {
		//1) Go to https://www.honda2wheelersindia.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.honda2wheelersindia.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElementByXPath("//button[@class='close']").click();
		
			//2) Click on scooters and click dio
		Thread.sleep(2000);
		
		driver.findElementById("link_Scooter").click();
		driver.findElementByXPath("//img[@src='/assets/images/thumb/dioBS6-icon.png']").click();
			//3) Click on Specifications and mouseover on ENGINE
		driver.findElementByXPath("//a[text()='Specifications']").click();
		WebElement msehvr = driver.findElementByXPath("//a[text()='ENGINE']");
		Actions builder= new Actions(driver);
		builder.moveToElement(msehvr).perform();
		System.out.println("Selected Engine");
			//4) Get Displacement value
		String dispValue = driver.findElementByXPath("//span[text()='109.51cc']").getText();
		System.out.println("Displacement value is: ");
		float diodisval = Float.parseFloat(dispValue.replaceAll("c",""));
		System.out.println(dispValue);
		
		
			//5) Go to Scooters and click Activa 125
		driver.findElementById("link_Scooter").click();
//driver.findElementByXPath("//div[@class='owl-next'][1]").click();
		driver.findElementByXPath("//img[@src='/assets/images/thumb/activa-125new-icon.png']").click();

		
			//6) Click on Specifications and mouseover on ENGINE
		driver.findElementByXPath("//a[text()='Specifications']").click();
WebElement msehvr1 = driver.findElementByXPath("//a[text()='ENGINE']");
Actions builder1=new Actions(driver);
builder1.moveToElement(msehvr1).perform();
System.out.println("Selected Activa125 Engine");

			//7) Get Displacement value
String dispValue1 = driver.findElementByXPath("//span[text()='124 cc']").getText();
System.out.println("Displacement value of Activa125 is: ");
float Activa125disval = Float.parseFloat(dispValue1.replaceAll("c",""));
System.out.println(dispValue1);


			//8) Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
if (diodisval > Activa125disval) {
	System.out.println("Dio contains better displacement value than Activa");
}
else
{
	System.out.println("Activa contains better displacement value than Dio");
}

			//9) Click FAQ from Menu 
driver.findElementByXPath("(//a[text()='FAQ'])[1]").click();

			//10) Click Activa 125 BS-VI under Browse By Product
driver.findElementByXPath("(//a[text()='Activa 125 BS-VI'])").click();

			//11) Click  Vehicle Price 
driver.findElementByXPath("(//a[@href='#6a'])/i").click();

			//12) Make sure Activa 125 BS-VI selected and click submit
String activa125BsVI=driver.findElementById("ModelID6").getText();
if (activa125BsVI.equalsIgnoreCase("Activa 125 BS-VI")) {
	System.out.println("Activa 125 BS-VI is selected");
}
driver.findElementByXPath("(//button[text()='Submit'])[6]").click();

			//13) click the price link
driver.findElementByXPath("//a[text()='Click here to know the price of Activa 125 BS-VI.']").click();

			//14)  Go to the new Window and select the state as Tamil Nadu and  city as Chennai
Set<String> winset=driver.getWindowHandles();
List<String> winList=new ArrayList<String>(winset);
driver.switchTo().window(winList.get(1));
WebElement drpdwn1=driver.findElementByXPath("//select[@id='StateID']");
Select dropdown=new Select(drpdwn1);
dropdown.selectByVisibleText("Tamil Nadu");
WebElement drpdwn2=driver.findElementByXPath("//select[@id='CityID']");
Select dropdown1=new Select(drpdwn2);
dropdown1.selectByVisibleText("Chennai");

			//15) Click Search
driver.findElementByXPath("//button[text()='Search']").click();

			//16) Print all the 3 models and their prices
Thread.sleep(6000);
List<WebElement> rows = driver.findElementsByXPath("//tbody[@style='background-color:white']//tr");

for(int i = 0; i < rows.size(); i++) {
	WebElement eachRow = rows.get(i);
	List<WebElement> cols = eachRow.findElements(By.xpath("//tbody[@style='background-color:white']//tr["+(i+1)+"]//td"));
	if(cols.size()==3) {
		for(int j=0 ; j < cols.size()-1;j++) {
			System.out.print(driver.findElementByXPath("//tbody[@style='background-color:white']//tr["+(i+1)+"]//td["+(j+2)+"]").getText());
			System.out.print(" ");				
		}
	}
		else {
			for(int j=0 ; j < cols.size();j++) {
			System.out.print(driver.findElementByXPath("//tbody[@style='background-color:white']//tr["+(i+1)+"]//td["+(j+1)+"]").getText());
			System.out.print("\t");
		}
	}
	System.out.println();
}

			//17) Close the Browser
driver.close();
	}


	}



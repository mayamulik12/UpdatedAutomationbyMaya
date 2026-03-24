package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LaunchBrowser {

	public void main (String[] args)
	{
		
        System.setProperty("webdriver.chrome.driver", "C://Users//mayav//Downloads//chromedriver_win32//chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        
        driver.get("https://demowebshop.tricentis.com/");
        driver.close();
        
	
	
	
}
}
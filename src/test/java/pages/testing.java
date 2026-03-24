package pages;




	import org.openqa.selenium.WebDriver;
	//import org.openqa.selenium.chrome.ChromeDriver;

	import org.testng.annotations.Test;

	public class testing {
		private WebDriver driver;
	@Test
		public void setup() {
		
	    
	    driver.get("https://demowebshop.tricentis.com/");
		
	    driver.quit();
	  
		}    
	}




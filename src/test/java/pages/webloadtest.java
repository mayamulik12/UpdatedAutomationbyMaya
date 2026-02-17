package pages;


	import io.github.bonigarcia.wdm.WebDriverManager;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Assert;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	public class webloadtest {

	    private static final String TEXTRUL = "https://www.google.com";
	    private WebDriver driver;

	    @BeforeMethod
	    public void setup() {

	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	    }

	    @Test
	    public void verifyPageLoad() {
	  
	        driver.get(TEXTRUL);

	        String pageTitle = driver.getTitle();
	        
	        Assert.assertNotNull(pageTitle, "Page title is null. The page did not load.");
	        Assert.assertFalse(pageTitle.isEmpty(), "Page title is empty. The page might still be loading or failed.");
	        
	        System.out.println("Page loaded successfully with title: " + pageTitle);
	    }

	    @AfterMethod
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}


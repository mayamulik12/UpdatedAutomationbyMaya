package pages;


	import io.github.bonigarcia.wdm.WebDriverManager;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Assert;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	public class webloadtest {
	    // Class-level variable for the URL
	    // Note: Ensure this is a valid URL (e.g., "https://www.google.com")
	    private static final String TEXTRUL = "https://www.google.com";
	    private WebDriver driver;

	    @BeforeMethod
	    public void setup() {
	        // Setup ChromeDriver using WebDriverManager
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	    }

	    @Test
	    public void verifyPageLoad() {
	        // Use the class variable to navigate
	        driver.get(TEXTRUL);

	        // Verification: Check if the title is captured
	        String pageTitle = driver.getTitle();
	        
	        // Use a TestNG Assertion to verify the page loaded
	        // If the title is empty or null, the page likely failed to load
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


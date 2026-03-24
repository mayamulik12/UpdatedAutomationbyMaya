package Webtest;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Assert;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;
	import io.github.bonigarcia.wdm.WebDriverManager;
	import pages.Loginpage;

	public class Logintest1 {
		private WebDriver driver;
		private Loginpage loginPage;

		@BeforeMethod
		public void setUp() {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			loginPage = new Loginpage(driver);
		}

		@Test(priority = 1, description = "Verify successful login with valid credentials")

		public void testSuccessfulLogin() 
		{

			loginPage.navigateToLoginPage();
			loginPage.clickLogin();
			loginPage.login("mrpote123@gmail.com", "Maya@3641");
			Assert.assertTrue(loginPage.isUserLoggedIn(), "User should be logged in successfully.");
		}

		@Test(priority = 2, description = "Verify failed login with invalid credentials")

		public void testFailedLogin() {
			loginPage.navigateToLoginPage();
			loginPage.clickLogin();
			loginPage.login("Mrpote123@gmail.com", "Maya@3341");
			String expectedError = "The credentials provided are incorrect";
			Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError), "Error message should be displayed.");
		}

		@AfterMethod
		public void tearDown() {
			if (driver != null) {
				driver.quit();
			}
		}
}

package Webtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.Loginpage;
import utility.ExcelUtils;

public class Logintest2 {
	private WebDriver driver;
	private Loginpage loginPage;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		loginPage = new Loginpage(driver);
	}

	@DataProvider(name = "loginData")
	public Object[][] getExcelData() throws Exception {
		return ExcelUtils.getExcelData("src/test/resources/TestData.xlsx", "LoginData");
	}

	@Test(dataProvider = "loginData", priority = 1)
	public void testLogin(String email, String password, String expectedStatus) {
		loginPage.navigateToLoginPage();
		loginPage.clickLogin();
		loginPage.login(email, password);

		if (expectedStatus.equalsIgnoreCase("valid")) {
			Assert.assertTrue(loginPage.isUserLoggedIn(), "Login should be successful.");
		} else {
			String expectedError = "The credentials provided are incorrect";
			Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError), "Error should be displayed.");
		}
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}
}

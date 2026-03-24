package Webtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.Loginpage;
import pages.ProductSearch;
import utility.ExcelUtils;

public class Logintest {
	private WebDriver driver;
	Loginpage loginPage;
	ProductSearch searchPage;
	String item = "14.1-inch Laptop";

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		loginPage = new Loginpage(driver);
		 searchPage = new ProductSearch(driver);
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
	
	 @DataProvider(name = "testData", parallel = true)
	 public void verifyProductAddToCart()
    {    	
        int cartCount=searchPage.verifyCartCount();
        int newcartCount=0;
        searchPage.searchProduct(item);
        searchPage.addToCart(item);
        String msg = searchPage.getSuccessMessage();
        Assert.assertTrue(msg.contains("The product has been added"), "Failed to add product to cart!");
        newcartCount=searchPage.verifyCartCount();
        Assert.assertTrue(newcartCount==(cartCount+1),"cartcountt is not correct");
    }
	 
    @DataProvider(name = "testData", parallel = true)
	public void  verifyShoppingCartitems()
	{
	boolean isloaded=searchPage.clickoncartandloadedElement();
	Assert.assertTrue(isloaded,"page is not loaded");
	
	boolean isadded=searchPage.verifyElementName(item);
	Assert.assertFalse(isadded,"Item is not added");
		
	}
	@AfterMethod
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}
}

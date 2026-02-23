package Webtest;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.Loginpage;
import pages.ProductSearch;
//import pages.SearchPage;

public class Producttest {
    WebDriver driver;
    ProductSearch searchPage;
    Loginpage loginpage;
    String item = "14.1-inch Laptop";
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com");
        searchPage = new ProductSearch(driver);
        loginpage = new Loginpage(driver);
        loginpage.clickLogin();
        loginpage.login("mrpote123@gmail.com", "Maya@3641");
        
        
    }

    @Test
    @DataProvider(name = "testData", parallel = true)
    public void verifyProductAddToCart()
    {
        //String item = "14.1-inch Laptop";
    	
        int cartCount=searchPage.verifyCartCount();
        int newcartCount=0;
        searchPage.searchProduct(item);
        searchPage.addToCart(item);
        String msg = searchPage.getSuccessMessage();
        Assert.assertTrue(msg.contains("The product has been added"), "Failed to add product to cart!");
        newcartCount=searchPage.verifyCartCount();
        
        Assert.assertTrue(newcartCount==(cartCount+1),"cartcountt is not correct");
    }
    @Test
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
        if (driver != null) {
            driver.quit();
        }
        
    }
}

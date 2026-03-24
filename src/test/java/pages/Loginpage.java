
package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
;

public class Loginpage {
	private WebDriver driver;
	WebDriverWait wait;
	
	By loginLink = By.linkText("Log in");
	By emailInput = By.id("Email");
	By passwordInput = By.id("Password");
	By loginButton = By.xpath("//input[@value='Log in']");
	By errorMessage = By.xpath("//div[@class='validation-summary-errors']/ul/li");
	By logoutLink = By.linkText("Log out");
	By searchBox = By.id("small-searchterms");
	By searchButton = By.cssSelector("input.search-box-button");
	By successNotification = By.cssSelector(".bar-notification.success");

	public Loginpage(WebDriver driver) {
		this.driver = driver;
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void navigateToLoginPage() {
		driver.get("https://demowebshop.tricentis.com");
	}	
	public void clickLogin() {

		driver.findElement(loginLink).click();
	}

	public void login(String email, String password) 
	{
		driver.findElement(emailInput).sendKeys(email);
		driver.findElement(passwordInput).sendKeys(password);
		driver.findElement(loginButton).click();
	}

	public boolean isUserLoggedIn() {
		return driver.findElement(logoutLink).isDisplayed();
	}

	public String getErrorMessage() {
		return driver.findElement(errorMessage).getText();
	}

    public void searchProduct(String productName) {
        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    public void addToCart(String productName) {
    
        String dynamicXpath = "//a[text()='" + productName + "']/ancestor::div[@class='details']//input[@value='Add to cart']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath))).click();
    }
    public int verifyCartCount(){
    	String rawQty = driver.findElement(By.className("cart-qty")).getText();
    	int cartCount = Integer.parseInt(rawQty.replaceAll("\\D", ""));
    	return cartCount;
    }
    

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotification)).getText();
    }
    public boolean verifyElementName(String addedProductname)
    {
    		Boolean result= false;
    		List<WebElement> productNameElements = driver.findElements(By.className("product-name"));
    		List<String> productNames = new ArrayList<>();
    		for (WebElement product : productNameElements) 
    		{
    		String name = product.getText();
    		productNames.add(name);
    		}
    		for (String productName : productNames) 
    		{
    		if (addedProductname==productName)
    			result=true;
    		}
    		return result;
    		
    }

public boolean clickoncartandloadedElement()
		{
	boolean result=false;
		WebElement cartLink = wait.until
		(
        ExpectedConditions.elementToBeClickable(By.cssSelector("a.ico-cart"))
		);
		cartLink.click();


		WebElement cartTitle = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Shopping cart']"))
		);
		if (cartTitle.isDisplayed()) 
			result=true;
		return result;
		}
		
}

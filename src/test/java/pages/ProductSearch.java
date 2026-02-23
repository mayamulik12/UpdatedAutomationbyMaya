package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductSearch {
    WebDriver driver;
    WebDriverWait wait;


    By searchBox = By.id("small-searchterms");
    By searchButton = By.cssSelector("input.search-box-button");
    By successNotification = By.cssSelector(".bar-notification.success");

    public ProductSearch(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void searchProduct(String productName) {
        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    public void addToCart(String productName) {
    	
    
        String dynamicXpath = "//a[text()='" + productName + "']/ancestor::div[@class='details']//input[@value='Add to cart']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath))).click();
    }
    public int verifyCartCount()
    {

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

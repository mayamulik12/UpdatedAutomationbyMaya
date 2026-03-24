package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage1 {
	private WebDriver driver;

	By loginLink = By.linkText("Log in");
	By emailInput = By.id("Email");
	By passwordInput = By.id("Password");
	By loginButton = By.xpath("//input[@value='Log in']");
	By errorMessage = By.xpath("//div[@class='validation-summary-errors']/ul/li");
	By logoutLink = By.linkText("Log out");

	public LoginPage1(WebDriver driver) {
		this.driver = driver;
	}

	public void navigateToLoginPage() {
		driver.get("https://demowebshop.tricentis.com");
	}

	public void clickLogin() {
		driver.findElement(loginLink).click();
	}

	public void login(String email, String password) {
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
}

package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// By locators: OR
	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[title='naveenopencart']");
	private By registerLink = By.linkText("Register");

	// page const...
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	@Step("getting loginpage title")
	// page actions/methods:
	public String getLoginPageTitle() {
 
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFULT_WAIT);
		System.out.println("login page title:" + title);
		return title;

	}

	@Step("getting loginpage url")
	public String getCurrentUrl() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFULT_WAIT);
		System.out.println("login page url:" + url);
		return url;

	}
	@Step("cheking forot pass link exist or not")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFULT_WAIT).isDisplayed();

	}
	@Step("cheking logo pass link exist or not")
	public boolean isLogoExist() {
		return eleUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFULT_WAIT).isDisplayed();

	}

	@Step("user name is: {0} and password {1}")
	public AccountPage doLogin(String username, String pwd) {
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFULT_WAIT).sendKeys("ab123456789@yahoo.com");
		eleUtil.doSendKeys(password, "ab123456789");
		eleUtil.doClick(loginBtn);
		return new AccountPage(driver);

	}
	@Step("naviget to tegister page")
	public RegisterPage naviGateTORegisterPage() {

		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFULT_WAIT).click();

		return new RegisterPage(driver);

	}

}

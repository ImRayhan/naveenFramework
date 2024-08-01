package com.qa.opencart.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.testcom.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design opencart login page")
@Story("Us 101: Login page feature")
@Feature("F50: Feature login Page")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {
	
	private static final Logger log = LogManager.getLogger(LoginPageTest.class);


	@Description("Login page title test......")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitle() {

		String actualTitle = loginPage.getLoginPageTitle();
		log.info("Actual loginpage title: "+actualTitle);
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);

	}
	@Description("Login page url test......")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actualUrl = loginPage.getCurrentUrl();
		Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));

	}
	@Description("verifying forgot pwd link test.....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());

	}
	@Description("verifying app logo exist test......")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void appLogoExistTest() {
		Assert.assertTrue(loginPage.isLogoExist());

	}
	@Description("verifying user is able to login with correct credentials......")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());

	}
//
}

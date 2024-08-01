package com.qa.opencart.testcom.qa.opencart.base;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResulPage;


public class BaseTest {

	protected WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	protected LoginPage loginPage;
	protected AccountPage accPage;
	protected SearchResulPage searchResulPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;
	protected SoftAssert softAassert;
	
	private static final Logger log = LogManager.getLogger(BaseTest.class);


	@Parameters({ "browser","browserversion","testname"})
	@BeforeTest
	public void setup(String browserName,String browserVersion,String testName) {
		log.info(browserName+" : "+browserVersion+" : " + testName);
		df = new DriverFactory();
		prop = df.initProp();

		if (browserName != null) {
			prop.setProperty("browser", browserName);
			prop.setProperty( "browserversion", browserVersion);
			prop.setProperty( "testname", testName);

		}

		driver = df.initDriver(prop);
		loginPage = new LoginPage(this.driver);
		softAassert = new SoftAssert();

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
		log.info("browser closed....");

	}

}

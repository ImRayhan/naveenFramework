package com.qa.opencart.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.testcom.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void regSetup() {
		registerPage = loginPage.naviGateTORegisterPage();

	}

	public String getRandomEmailID() {
		return "testautomation" + System.currentTimeMillis() + "@opencart.com";
		// return "testautomation" + UUID.randomUUID() + "@gmail.com";
	}

	@DataProvider
	public Object getUserRegData() {

		return new Object[][] {

				{ "ray", "sam", "123313414", "23423432", "yes" },
				{ "sam", "ra", "1233fgdg13414", "2334fgd2342", "yes" },
				{ "dfsfgd", "fdgfg", "12dg3fd313414", "2342334gdfgd2", "yes" }

		};

	}
	
	@DataProvider
	public Object[][] getUserRegExelData() {
		Object regData[][] = ExelUtil.getTestData(AppConstants.REGiSTER_DATA_SHEET_NAME);
		return regData;

	}

	@Test(dataProvider = "getUserRegData")
	public void userRegisterTest(String firstName, String lastName, String teliPhone, String passWord,
			String subscrived) {
		boolean isRegDone = registerPage.userRegister(firstName, lastName, getRandomEmailID(), teliPhone, passWord,
				subscrived);

		assertTrue(isRegDone);

	}
//	@Test(dataProvider = "getUserRegData")
//	public void userRegisterTest(String firstName, String lastName, String teliPhone, String passWord,
//			String subscrived) {
//		boolean isRegDone = registerPage.userRegister(firstName, lastName, getRandomEmailID(), teliPhone, passWord,
//				subscrived);
//		
//		assertTrue(isRegDone);
//		
//	}

}

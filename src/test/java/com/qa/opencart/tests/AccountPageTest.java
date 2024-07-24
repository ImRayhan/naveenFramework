package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.testcom.qa.opencart.base.BaseTest;

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void setUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);

	}

	@Test
	public void accPageUrlTest() {
		Assert.assertTrue(accPage.getAccPageUrl().contains(AppConstants.ACC_PAGE_URL_FRACTION));

	}

	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());

	}

	@Test
	public void searchFieldExistTest() {
		Assert.assertTrue(accPage.isSearchFieldExist());

	}

	@Test
	public void accPageHeadersCountTest() {
		List<String> actualAccHeadersPageList = accPage.getAccountHeader();
		System.out.println(actualAccHeadersPageList);
		Assert.assertEquals(actualAccHeadersPageList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);

	}

	@Test
	public void accPageHeadersTest() {
		List<String> actualAccHeadersPageList = accPage.getAccountHeader();
		System.out.println(actualAccHeadersPageList);
		Assert.assertEquals(actualAccHeadersPageList, AppConstants.ACCOUNT_PAGE_HEADER_LIST);

	}

	@Test
	public void searchTest() {
		searchResulPage = accPage.doSearch("Macbook");
		productInfoPage = searchResulPage.selectProduct("MacBook Pro");

		String actualProductHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actualProductHeader, "MacBook Pro");

	}

}

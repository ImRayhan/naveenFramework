package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.testcom.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExelUtil;

public class ProductResultPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@DataProvider
	public Object getSearchData() {

		return new Object[][] { 
			{ "Macbook", "MacBook Pro", 4 },
			{ "Macbook", "MacBook Air", 4 },
			{ "iMac", "iMac", 3 },
			{ "Samsung", "Samsung SyncMaster 941BW", 1 } };

	}
	
	@DataProvider
	public Object[][] getUserSearchExelData() {
		return ExelUtil.getTestData(AppConstants.PRODUCT_DATA_SHEET_NAME);
		

	}

	@Test(dataProvider = "getUserSearchExelData")
	public void productImagesTest(String searchKey, String ProductName, String imageCount) {
		searchResulPage = accPage.doSearch(searchKey);
		productInfoPage = searchResulPage.selectProduct(ProductName);
		Assert.assertEquals(String.valueOf(productInfoPage.getProductImagesCount()), imageCount);

	}
	
//	
//	@Test(dataProvider = "getSearchData")
//	public void productImagesTest(String searchKey, String ProductName, int imageCount) {
//		searchResulPage = accPage.doSearch(searchKey);
//		productInfoPage = searchResulPage.selectProduct(ProductName);
//		Assert.assertEquals(productInfoPage.getProductImagesCount(), imageCount);
//		
//	}
//	

	@Test
	public void productInfoTest() {
		searchResulPage = accPage.doSearch("MacBook");
		productInfoPage = searchResulPage.selectProduct("MacBook Pro");
		Map<String, String> productDetailsMap = productInfoPage.getProductDetails();

		softAassert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		softAassert.assertEquals(productDetailsMap.get("Availability"), "In Stock");
		softAassert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		softAassert.assertEquals(productDetailsMap.get("Reward Points"), "800");

		softAassert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
		softAassert.assertEquals(productDetailsMap.get("ExTaxPrice"), "$2,000.00");
		softAassert.assertAll();

	}

}

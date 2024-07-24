package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResulPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public SearchResulPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public ProductInfoPage selectProduct(String productName) {

		eleUtil.waitForVisibilityOfElement(By.linkText(productName), AppConstants.MEDIUM_DEFULT_WAIT).click();
		return new ProductInfoPage(driver);

	}

}

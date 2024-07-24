package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By logoutLink = By.linkText("Logout");
	private By search = By.cssSelector("#search input[class='form-control input-lg']");
	private By accountHeaders = By.cssSelector("div#content>h2");
	private By searchIcn = By.xpath("//i[@class='fa fa-search']");

	// page const...
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// page actions:
	public boolean isLogoutLinkExist() {

		return eleUtil.waitForVisibilityOfElement(logoutLink, AppConstants.SHORT_DEFULT_WAIT).isDisplayed();

	}

	public void logOut() {
		if (isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);

		}

	}

	public boolean isSearchFieldExist() {

		return eleUtil.waitForVisibilityOfElement(search, AppConstants.SHORT_DEFULT_WAIT).isDisplayed();

	}

	public List<String> getAccountHeader() {
		List<WebElement> headerList = eleUtil.waitForVisibilityOfElements(accountHeaders,
				AppConstants.MEDIUM_DEFULT_WAIT);
		List<String> headerValueList = new ArrayList<String>();
		for (WebElement e : headerList) {
			String text = e.getText();
			headerValueList.add(text);

		}
		return headerValueList;

	}

	public String getAccPageTitle() {

		String title = eleUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.SHORT_DEFULT_WAIT);
		System.out.println("AccPage page title:" + title);
		return title;

	}

	public String getAccPageUrl() {
		String url = eleUtil.waitForURLContains(AppConstants.ACC_PAGE_URL_FRACTION, AppConstants.SHORT_DEFULT_WAIT);
		System.out.println("AccPage page url:" + url);
		return url;

	}

	public SearchResulPage doSearch(String searchKey) {
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFULT_WAIT).clear();
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFULT_WAIT).sendKeys(searchKey);
		eleUtil.doClick(searchIcn);
		return new SearchResulPage(driver); // TDD

	}

}

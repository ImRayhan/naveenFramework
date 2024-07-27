package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exeption.FrameworkExeption;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionManager optionManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static String highlight = null;

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");


		// String browserName = System.getProperty("browser");

		System.out.println("browser name is  " + browserName);
		highlight = prop.getProperty("highlight");

		optionManager = new OptionManager(prop);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			// driver = new ChromeDriver(optionManager.getChromeOption());
			tlDriver.set(new ChromeDriver(optionManager.getChromeOption()));

			break;

		case "firefox":
			// driver = new FirefoxDriver(optionManager.getFirefoxOption());
			tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOption()));

			break;
		case "edge":
			// driver = new EdgeDriver(optionManager.getEdgeOption());
			tlDriver.set(new EdgeDriver(optionManager.getEdgeOption()));

			break;
		case "safari":
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());

			break;

		default:
			System.out.println("please pas the right browsername..." + browserName);
			throw new FrameworkExeption("No Browser Found...");

		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}

	public static WebDriver getDriver() {
		return tlDriver.get();

	}

	public Properties initProp() {
		// mvn clean install -Denv="qa"
		// mvn clean install
		//ngrok http 8080 - for ngrock server which is auto trigger i=jenkins if i push any new code
		
		FileInputStream ip = null;
		prop = new Properties();

		String envName = System.getProperty("env");
		System.out.println("env name is:" + envName);

		try {

			if (envName == null) {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
				System.out.println("Your env is null running on prod env");
			}

			else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");

					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");

					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");

					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");

					break;

				default:
					System.out.println("please pass the right env" + envName);
					throw new FrameworkExeption("Wrong env name" + envName);

				}
			}
		} catch (FileNotFoundException e) {

		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}

	/**
	 * take screenshot
	 */
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}

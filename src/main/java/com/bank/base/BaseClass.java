package com.bank.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	
	protected static Properties prop;
	protected static WebDriver driver;

	@BeforeSuite
	public void loadConfig() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		prop.load(fis);
	}

	@BeforeMethod
	public void setup() {
		System.out.println("Setting webdriver for: " + this.getClass().getSimpleName());
		launchBrowser();
		configureBrowser();
	}

	public static String getAdminUsername() {
		return prop.getProperty("username");
	}

	public static String getAdminPassword() {
		return prop.getProperty("password");
	}

	private void launchBrowser() {

		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Browser Not Supported: " + browser);
		}

	}

	private void configureBrowser() {
		int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

		driver.manage().window().maximize();

		try {
			driver.get(prop.getProperty("url"));
		} catch (Exception e) {
			System.out.println("Faild to Navigate to the URL: " + e.getMessage());

		}
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception e) {
				System.out.println("Unable to quite the browser: " + e.getMessage());
			}
		}
	}

	// Prop getter method
	public static Properties getProp() {
		return prop;
	}

	// Driver getter method
	public WebDriver getDriver() {
		return driver;
	}

	// Driver setter method
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	// static wait for pause
	public void staticWait(int secounds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(secounds));
	}

}

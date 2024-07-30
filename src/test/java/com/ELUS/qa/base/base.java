package com.ELUS.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ELUS.qa.utils.Utils;

public class base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;

	public base() {
		prop = new Properties();
		dataProp = new Properties();
		File fis2 = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\ELUS\\qa\\testdata\\testdata.properties");
		try {
			FileInputStream dataFis = new FileInputStream(fis2);
			dataProp.load(dataFis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		File PropFile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\ELUS\\qa\\config\\config.properties");

		try {
			FileInputStream fis = new FileInputStream(PropFile);
			prop.load(fis);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public WebDriver intializeBrowserAndOpenApplicationUrl(String Browser) {

		if (Browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();

		} else if (Browser.equals("firefox")) {

			driver = new FirefoxDriver();
		} else if (Browser.equals("edge")) {

			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utils.pageLoadTime));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utils.implicitWait));
		driver.get(prop.getProperty("url"));
		return driver;

	}

}

package com.qa.trcrm.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;

public class BasePage {

	public WebDriver driver;
	public Properties prop;
	OptionsManager option;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public WebDriver init_driver(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println(browserName + " not found");
		}
		return driver;
	}

	/**
	 * This method is used to intialized the web driver on the basis of browser name
	 * 
	 * @param prop
	 * @return web driver
	 */
	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser");

		option = new OptionsManager(prop);

		switch (browserName) {
		case "chrome":

			tlDriver.set(new ChromeDriver(option.getChromeOptions()));
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(option.getFirefoxOptions()));
			break;
		case "edge":
			tlDriver.set(new EdgeDriver());
			break;
		case "safari":
			driver = new SafariDriver();
			break;

		default:
			System.out.println(browserName + " not found");
			try {
				throw new Exception("NoSuchBrowserFound");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			break;
		}

		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		return getDriver();

	}

	/**
	 * This method is use to get the property file
	 * 
	 * @return
	 */
	public Properties init_prop() {
		prop = new Properties();

		String path;

		String env = System.getProperty("env");
		if (env==null) {
			env="qa";
		}

		if (env.equalsIgnoreCase("qa")) {
			path = "./src/main/java/com/qa/trcrm/config/config_qa.properties";
		} else if (env.equalsIgnoreCase("prod")) {
			path = "./src/main/java/com/qa/trcrm/config/config_prod.properties";
		} else {
			path = "./src/main/java/com/qa/trcrm/config/config.properties";
		}

		try {
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	public String getScreenshot() {
		String path = null;

		try {
			File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

			path = System.getProperty("user.dir") + "/screenshots/" + getDateTime()+".png";
			System.out.print(path);
			File desc = new File(path);
			FileUtils.copyFile(src, desc);
			System.out.print(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print(path);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("**********************");
			e.printStackTrace();
			System.out.println("**********************");
		}
		return path;

	}
	public String getScreenshot(ITestResult iTest)
	{
		File src=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/test-output/"+iTest.getMethod().getConstructorOrMethod().getName()+"-"+getDateTime()+".png";
		
		try {
			FileUtils.copyFile(src, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;

	}
	public String getDateTime()
	{
		Date date=new Date();
		SimpleDateFormat dateFormat=new SimpleDateFormat("ddMMyyhhmma");
		return dateFormat.format(date);
	}
}

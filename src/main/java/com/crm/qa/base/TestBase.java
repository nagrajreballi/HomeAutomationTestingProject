package com.crm.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.qa.util.TestUtil;


public class TestBase 
{
	public static WebDriver driver;
	public static Properties prop;
	public static File Rootpath;
	
	public TestBase()//constructor
	{
		try
		{
			prop=new Properties();
			Rootpath = new File(System.getProperty("user.dir"));
			FileInputStream ip = new FileInputStream(Rootpath + "/src/main/java/com/crm/qa/config/config.properties");
			prop.load(ip);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void initialization()//init
	{
		String browserName=prop.getProperty("browser");
		
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", Rootpath + "/Drivers/Chrome/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equals("FF"))
		{
			System.setProperty("webdriver.gecko.driver", "./Drivers/Firefox/geckodriver");	
			driver = new FirefoxDriver(); 
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);//here we need to add dynamic waits
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}
}

package com.crm.qa.testCases;



import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class LoginPageTest extends TestBase 
{
	LoginPage loginPage;
	String sheetName = "login";
	
	public LoginPageTest()
	{
		super();
	}

	@BeforeMethod
	public void setUp()
	{
		initialization();
		loginPage=new LoginPage();
	}
	
	
	@Test(priority=1)//LoginPageTitleTest
	public void loginPageTitleTest()
	{
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "CRMPRO - CRM software for customer relationship management, sales, and support.");
	}
	
	@Test(priority=2)//ImageTests
	public void crmLogoImageTest()
	{
		boolean flag = loginPage.validateCRMImage();
		Assert.assertTrue(flag);
	}
	
		//LoginPageTestlogin
		//to get data from excel sheet
		@DataProvider
		public Object[][] getCRMTestData()
		{
			Object data[][] = TestUtil.getTestData(sheetName);
			return data;
		}
		
		@Test(priority=3,dataProvider="getCRMTestData") //login pageTest
		public void loginTest(String un, String pwd) throws Exception
		{
			loginPage.login(un, pwd);
				 
			//TestUtil.takeScreenshotAtEndOfTest(driver, "loginPage");  //if I comment this line only failed TC SS will show and
		//If I Not committed this line  fail and pass TC SC will show.
		}
			
	
		@AfterMethod
		public void tearDown(ITestResult result)
		{
			if(ITestResult.FAILURE==result.getStatus())
			{
				TestUtil.takeScreenshotAtEndOfTest(driver,result.getName());
			}
			driver.quit();
		}
	}

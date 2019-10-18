package com.crm.qa.testCases;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase
{
	LoginPage loginPage;
	HomePage homePage;
	public TestUtil testUtil;
	ContactsPage contactsPage;
	public HomePageTest() 
	{
		super();
	}
	
	@BeforeMethod
	public void setUp() throws Exception 
	{
		initialization();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	 @Test(priority=1) 
	 public void verifyHomePageTitleTest() 
	 {
		 String homePageTitle = homePage.verifyHomePageTitle();
		 Assert.assertEquals(homePageTitle, "CRMPRO","Home page title not matched"); 
	  }
	 
	
	@Test(priority=2)
	public void verifyUserNameTest() throws Exception
	{
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyCorrectUserName());
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest()
	{
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
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


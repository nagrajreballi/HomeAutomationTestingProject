package com.crm.qa.testCases;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase
{

	TestUtil testUtil = new TestUtil();
	ContactsPage contactsPage = new ContactsPage();
	LoginPage loginPage = new LoginPage();
	HomePage homePage=new HomePage();
	String sheetName = "contacts";
	
	public ContactsPageTest()
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
		TestUtil.runTimeInfo("error", "login successful");
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
	
	//Contacts Present or not on the page
	@Test(priority=1)
	public void verifyContactsPageLabel()
	{
		
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "contacts label is missing on the page");
	}
	
	//
	@Test(priority=2)
	public void selectSingleContactsTest()
	{
		contactsPage.selectContactsByName("Gopal Reballi");
	}
	
	@Test(priority=3)
	public void selectMultipleContactsTest()
	{
		contactsPage.selectContactsByName("Gopal Reballi");
		contactsPage.selectContactsByName("test test");
	}
	
	
	@DataProvider
	public Object[][] getCRMTestData()
	{
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	@Test(priority=4, dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title, String firstName, String lastName, String company)
	{
		
		homePage.clickOnNewContactLink();

		contactsPage.createNewContact(title, firstName, lastName, company);	
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


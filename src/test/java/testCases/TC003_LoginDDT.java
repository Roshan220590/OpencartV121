package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;



public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven")  //getting data provider from different class
	public void verify_lofinDDT(String email, String pwd, String exp)
	{
		logger.info("*********** Started TC003_LoginDDT ******************");
		
		try
		{
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
			
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//MyAccount
		MyAccountPage mypage=new MyAccountPage(driver);
		boolean status=mypage.isMyAccountPageExists();
		
		/*Data is valid  - login success - test pass  - logout
							login failed - test fail
		  Data is invalid - login success - test fail  - logout
							login failed - test pass
		*/
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(status==true)
			{
				mypage.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(status==true)
			{
				mypage.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("*********** Finished TC003_LoginDDT ******************");

		
	}

}

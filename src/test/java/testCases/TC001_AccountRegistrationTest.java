package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression", "Master"})
	public void verify_account_registration()
	{
		logger.info("******** Starting TC001_AccountRegistrationTest ********* ");
		
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount link");
		
		hp.clickRegister();
		logger.info("Clicked on register link");
		
		AccountRegistrationPage reg=new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details");
		reg.setFirstName(randomString().toUpperCase());
		reg.setLastName(randomString().toUpperCase());
		reg.setEmail(randomString()+"@yahoo.com");
		reg.setTelephone(randomNumber());
		
		String password=randomAlphaNumeric();
		reg.setPassword(password);
		reg.setConfirmPassword(password);
		
		reg.setPrivacyPolicy();
		reg.clickContinue();
		
		logger.info("Validating expected message");
		String conmsg=reg.getConfirmationMsg();
		
		if(conmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else {
			logger.error("...Test is failed.....");
			logger.debug("....Debug logs...");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(conmsg, "Your Account Has Been Created!!!");
		}
		catch(Exception e) 
		{
			Assert.fail();
		}
		
		logger.info("******** Finished TC001_AccountRegistrationTest ********* ");
	}
	
}

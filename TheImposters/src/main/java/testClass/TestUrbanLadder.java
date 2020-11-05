package testClass;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import HackathonProject.TheImposters.BAH;
import HackathonProject.TheImposters.GiftCard;
import HackathonProject.TheImposters.HomePage;
import baseClass.BaseSetup;




public class TestUrbanLadder extends BaseSetup
{

	HomePage hp;
	BAH bah;
	GiftCard gc;
	
	@Test(priority = 1)
	public void testing() throws Exception
	{
		logger = report.createTest("Report");
		
		BaseSetup bs = new BaseSetup();
		
		//logger.log(Status.INFO, "Opening the browser");
		
		//logger.log(Status.PASS, "Browser opened");
		
		logger.log(Status.INFO, "Signing In");
		hp = openUrl();
		logger.log(Status.PASS, "Signed In");
		
		logger.log(Status.INFO, "Clicking On Bookshelves Icon");
		hp.clickBookshelvesIcon();
		logger.log(Status.PASS, "Bookshelves Page Opened");
		
		logger.log(Status.INFO, "Setting Price Range");
		hp.setPrice();
		logger.log(Status.PASS, "Range set");
		
		logger.log(Status.INFO, "Selecting Storage Type");
		hp.selectStorageType();
		logger.log(Status.PASS, "Open Storage Type Selected");
		
		logger.log(Status.INFO, "Excluding Out Of Stock Products");
		hp.excludeOutOfStock();
		logger.log(Status.PASS, "Excluded Out Of Stock Products");
		
		logger.log(Status.INFO, "Getting products and their prices");
		bah = hp.bookshelves();
		logger.log(Status.PASS, "Products and their prices retrieved");
		
		logger.log(Status.INFO, "Saving the data in the excel workbook");
		hp.saveToXls();
		logger.log(Status.PASS, "Data Saved!");
		
		logger.log(Status.INFO, "Navigating to HomePage");
		bah.navigateHomePage();
		logger.log(Status.PASS, "HomePage opened");
		
		logger.log(Status.INFO, "Going to the Collections Menu");
		gc = bah.beingAtHomeSubMenu();
		logger.log(Status.PASS, "Being At Home Submenu categories retrieved");
		
		logger.log(Status.INFO, "Saving the data in the excel workbook");
		bah.saveToXls();
		logger.log(Status.PASS, "Data Saved!");
		
		logger.log(Status.INFO, "Opening the GiftCard Page");
		gc.giftCard();
		logger.log(Status.PASS, "GiftCard Page opened");
		
		logger.log(Status.INFO, "Selecting the Birthday?Anniversary");
		gc.birthdayAnniversary();
		logger.log(Status.PASS, "Birthday?Anniversary selected");
		
		logger.log(Status.INFO, "Choosing GiftCard amount");
		gc.choosingAmount();
		logger.log(Status.PASS, "Amount chosen");
		
		logger.log(Status.INFO, "Filling form details");
		gc.fillForm();
		logger.log(Status.PASS, "Form details filled");
		
		logger.log(Status.INFO, "Clicking Proceed To Payment");
		gc.proceedToPayment();
		logger.log(Status.PASS, "Clicked Proceed To Payment");
		
		logger.log(Status.INFO, "Locating the Error Message");
		gc.invalidText();
		logger.log(Status.PASS, "Error Message retrieved");
		
		logger.log(Status.INFO, "Saving the data in the excel workbook");
		gc.saveToXls();
		logger.log(Status.PASS, "Data Saved!");
	}
	
}

package HackathonProject.TheImposters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import baseClass.BaseSetup;

public class BAH extends BaseSetup {

	// private static WebDriver driver;
	// public ExtentTest logger;
	List<String> subMenuValues = new ArrayList<String>();

	static Properties prop = readProperties();

	public RemoteWebDriver driver;

	public BAH(WebDriver driver) {
		this.driver=(RemoteWebDriver) driver;
	}

	public void waitTime() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/*
	 * private static Properties readProperties() { // TODO Auto-generated method
	 * stub return null; }
	 */
	public void navigateHomePage() {
		waitTime();
		System.out.println("Navigating To Home Page...");
		driver.get(prop.getProperty("URL2"));
		String pageTitle = driver.getTitle();
		System.out.println(pageTitle);
	}

	public GiftCard beingAtHomeSubMenu() {

		waitTime();
		Actions action = new Actions(driver);
		WebElement CollectionButton = driver.findElement(By.xpath(prop.getProperty("collectionButton")));
		action.moveToElement(CollectionButton).perform();
		System.out.println("Collections Button Clicked...");
		waitTime();
		System.out.println("Items under Being At Home submenu are:");
		for (int i = 0; i < 13; i++) {
			String valueXpath = "//div[@class='subnavlist_wrapper clearfix']/ancestor::li[@class='topnav_item collectionsunit']/div[1]/div[1]/ul[1]/li[1]/ul/li["
					+ (i + 1) + "]/a/span";
			String subMenuName = driver.findElement(By.xpath(valueXpath)).getText();
			subMenuValues.add(i, subMenuName);
			System.out.println(i + 1 + ". " + subMenuName);
		}
		System.out.println(subMenuValues);
		waitTime();

		return PageFactory.initElements(driver, GiftCard.class);
	}

	public void saveToXls() throws IOException {

		FileInputStream file = new FileInputStream("UrbanLadder.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet2 = workbook.createSheet("Being At Home Submenu");

		Row row2 = sheet2.createRow(0);
		row2.createCell(0).setCellValue("SNo.");
		row2.createCell(1).setCellValue("List Item");

		for (int i = 0; i < subMenuValues.size(); i++) {
			row2 = sheet2.createRow(i + 1);
			row2.createCell(0).setCellValue(i + 1);
			row2.createCell(1).setCellValue(subMenuValues.get(i));
		}

		try {
			FileOutputStream fos = new FileOutputStream(new File("UrbanLadder.xlsx"));
			workbook.write(fos);
			fos.close();
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
		}
	}

}

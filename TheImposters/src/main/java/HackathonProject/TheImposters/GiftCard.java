package HackathonProject.TheImposters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseClass.BaseSetup;

public class GiftCard extends BaseSetup {

	// private static WebDriver driver;
	// public ExtentTest logger;
	String text;
	WebDriverWait wait = null;

	static Properties prop = BaseSetup.readProperties();

	public RemoteWebDriver driver;

	public GiftCard(WebDriver driver) {
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

	public void giftCard() {
		System.out.println("Getting Gift Cards...");
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty("giftCards"))));
		driver.findElement(By.xpath(prop.getProperty("giftCards"))).click();
	}

	/*
	 * Choosing birthday/anniversary option
	 */
	public void birthdayAnniversary() {
		System.out.println("For Birthday/Anniversary...");
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty("Birthday/Anniversary"))));
		driver.findElement(By.xpath(prop.getProperty("Birthday/Anniversary"))).click();
		waitTime();
	}

	/*
	 * Choosing amount and clicking Next
	 */
	public void choosingAmount() {
		System.out.println("Choosing The Amount...");
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty("giftCardAmount"))));
		driver.findElement(By.xpath(prop.getProperty("giftCardAmount"))).sendKeys(prop.getProperty("amount"));
		driver.findElement(By.xpath(prop.getProperty("next"))).click();
		waitTime();
	}

	public void fillForm() {
		System.out.println("Filling The Details...");
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty("toNameXpath"))));
		driver.findElement(By.xpath(prop.getProperty("toNameXpath"))).sendKeys(prop.getProperty("toName"));
		driver.findElement(By.xpath(prop.getProperty("toEmailXpath"))).sendKeys(prop.getProperty("toEmail"));
		driver.findElement(By.xpath(prop.getProperty("fromNameXpath"))).sendKeys(prop.getProperty("fromName"));
		driver.findElement(By.xpath(prop.getProperty("fromEmailXpath"))).sendKeys(prop.getProperty("fromEmail"));
		driver.findElement(By.xpath(prop.getProperty("mobXpath"))).sendKeys(prop.getProperty("mob"));
		driver.findElement(By.xpath(prop.getProperty("confirm"))).click();
		waitTime();
	}

	/*
	 * Proceed to payment button
	 */
	public void proceedToPayment() {
		System.out.println("Completing The Payment...");
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty("payment"))));
		driver.findElement(By.xpath(prop.getProperty("payment"))).click();
		waitTime();
	}

	/*
	 * Printing the invalid text
	 */
	public void invalidText() {
		System.out.println("Oops! Here is an error -");
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty("errorMessage"))));
		text = driver.findElement(By.xpath(prop.getProperty("errorMessage"))).getText();
		System.out.println(text);
		waitTime();
	}

	public void saveToXls() throws IOException {

		FileInputStream file = new FileInputStream("UrbanLadder.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet3 = workbook.createSheet("Error Message");

		Row row3 = sheet3.createRow(0);
		row3.createCell(0).setCellValue(text);

		try {
			FileOutputStream fos = new FileOutputStream(new File("UrbanLadder.xlsx"));
			workbook.write(fos);
			fos.close();
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
		}
	}

}

package HackathonProject.TheImposters;

import java.io.File;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import baseClass.BaseSetup;

public class HomePage extends BaseSetup
{
	
	WebDriverWait wait = null;
	List <WebElement> products;
	List <WebElement> prices;
	List <String> finalProducts = new ArrayList<String>();
	List <String> finalPrices = new ArrayList<String>();
	

	static Properties prop = readProperties();
	
	public RemoteWebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver=(RemoteWebDriver) driver;
	}

	
	/*
	 * Click Bookshelves Icon
	 */
	public void clickBookshelvesIcon()
	{
		wait = new WebDriverWait(driver,30);
		System.out.println("Looking For The Bookshelves Icon...");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty("BookshelvesIcon"))));
		System.out.println("Found It...Taking you to the bookshelves...");
        driver.findElement(By.xpath(prop.getProperty("BookshelvesIcon"))).click();
        waitTime();
	}
	
	/*
	 * Applying Price Filter
	 */
	public void setPrice() 
	{
		Actions action = new Actions(driver);
	    wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty("priceSlider"))));
		System.out.println("Applying Price Filter...");
	    WebElement price = driver.findElement(By.xpath(prop.getProperty("priceSlider")));
	    action.moveToElement(price).perform();
	    WebElement Slider = driver.findElement(By.xpath(prop.getProperty("priceSliderValue")));
		action.dragAndDropBy(Slider, -207,0).build().perform();
	}
	
	/*
	 * Selecting Storage Type
	 */
	public void selectStorageType() 
	{
		Actions action = new Actions(driver);
		waitTime();
		System.out.println("Selecting Storage Type...");
		WebElement storageType = driver.findElement(By.xpath(prop.getProperty("storageType")));
    	action.moveToElement(storageType).perform();
 		WebElement open = driver.findElement(By.xpath(prop.getProperty("storageTypeOpen")));
     	action.click(open).build().perform();
    }
	
	/*
	 * Exclude Out Of Stock
	 */
	public void excludeOutOfStock() {
		wait = new WebDriverWait(driver,30);
  		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty("outOfStock"))));
  		System.out.println("Excluding Out Of Stock Products...");
     	driver.findElement(By.xpath(prop.getProperty("outOfStock"))).click(); 
	}
	
	/*
	 * Display The Product Details
	 */

	public BAH bookshelves() 
	{	
     	
     	products = driver.findElements(By.xpath(prop.getProperty("productNames")));
		prices = driver.findElements(By.xpath(prop.getProperty("productPrices")));
		System.out.println("Top Bookshelves Are:");
        for(int i=1;i<=3;i++) 
        {
			String product = products.get(i).getText();
			String price1 = prices.get(i).getText();
			System.out.println(i + ". " + product + " - Rs." + price1.substring(1));
			finalProducts.add(i-1, product);
			finalPrices.add(i-1, price1.substring(1));
		}
        System.out.println(finalProducts);
        System.out.println(finalPrices);
		
		return PageFactory.initElements(driver, BAH.class);

	}

	public void saveToXls() throws IOException 
	{

		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
	    XSSFSheet sheet1 = workbook.createSheet("Details");

	    Row row1 = sheet1.createRow(0);
	    row1.createCell(0).setCellValue("SNo.");
	    row1.createCell(1).setCellValue("Product Name");
	    row1.createCell(2).setCellValue("Price");

	    for(int i=0;i<3;i++)
	    {
	    	row1 = sheet1.createRow(i+1);
	    	row1.createCell(0).setCellValue(i+1);
	    	row1.createCell(1).setCellValue(finalProducts.get(i));
	    	row1.createCell(2).setCellValue(finalPrices.get(i));
	    }
	    try
	    {
	    	FileOutputStream fos = new FileOutputStream(new File("UrbanLadder.xlsx"));
	    	workbook.write(fos);
	    	fos.close();
	    }
	    catch(StaleElementReferenceException e)
	    {
	    	e.printStackTrace();
	    }

	}
	
}

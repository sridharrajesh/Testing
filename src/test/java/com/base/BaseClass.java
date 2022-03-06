package com.base;
import java.awt.Window;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.lang.model.element.Element;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;
	public void getDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();	
	}

	public void loadUrl(String url) {
		driver.get(url);
	}//1
	public void type(WebElement element,String text) {
		element.sendKeys(text);
	}//2
	public WebElement findLocatorById(String attributeValue) {
		WebElement element = driver.findElement(By.id(attributeValue));
		return element;
	}//3
	public WebElement findLocatorByName(String attributeValue) {
		WebElement element = driver.findElement(By.name(attributeValue));
		return element;
	}//4
	public WebElement findLocatorByClassName( String attributeValue) {
		WebElement element = driver.findElement(By.className(attributeValue));
		return element;
	}//5

	public WebElement findLocatorByXpath(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return element;
	}//6
	public String getTitle() {
		String title = driver.getTitle();
		return title;
	}//7
	public String getUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}//8
	public String getText( WebElement element) {
		String text = element.getText();
		return text;
	}//9
	public String getAttributeValue(WebElement element,String attributename) {
		String attribute = element.getAttribute(attributename);
		return attribute;
	}//10
	public String getAttributeValue1(WebElement element,String attributename) {
		return element.getAttribute(attributename);
	}//11
	public void selectOptionByText(WebElement element,String text) {
		new Select(element).selectByVisibleText(text);
	}//12
	public void selectByIndex(WebElement element,int index) {
		new Select(element).selectByIndex(index);
	}//13
	public void selectAttributeValue(WebElement element,String attributeValue) {
		new Select(element).selectByValue(attributeValue);
	}//14
	public void typejs(WebElement element,String text) {
		JavascriptExecutor executor  = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].setAttribute('value','text')", element);
	}//15
	//Navigate
	public void navigateUrl(String url) {
		driver.navigate().to(url);
	}
	public void navigateRefresh() {
		driver.navigate().refresh();
	}
	public void navigateForwrd() {
		driver.navigate().forward();
	}
	public void navigateBack() {
		driver.navigate().back();
	}
	public  void click(WebElement element) {
		element.click();
	}
	public void maximize() {
		driver.manage().window().maximize();
	}
	public void fullscreen() {
		driver.manage().window().fullscreen();
	}
	//Mouse overAction
	public void moveToElement(WebElement element) {
		new Actions(driver).moveToElement(element).perform();
	}
	//DragAndDown
	public void dragAndDrop(WebElement element) {
		new Actions(driver).dragAndDrop(element, element).perform();
	}
	//Doubleclick
	public void doubleClick(WebElement element) {
		new Actions(driver).doubleClick().perform();
	}

	//RightClick
	public void ContextClick(WebElement element) {
		new Actions(driver).contextClick().perform();
	}
	/*keyup
    public void keyup(String key) {
    	new Actions(driver).keyUp(key).perform();
    }
    //keyDown
    public void keyDown(String key) {
    	new Actions(driver).keyDown(key).perform();
    }
    //sendkeys
    public void sendkeys(WebElement element,String text) {
    	new Actions(driver).keyDown(Keys.-).sendKeys(element, text).keyUp(key).perform();
    }*/


	//Alert
	public void accept() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	public void dismiss() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public void promptAlert(String text) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(text);
		alert.accept();
	}
	//Screenshot
	public void screenshot(String path) throws IOException {
		TakesScreenshot screenshot =  (TakesScreenshot)driver;
		File s  = screenshot.getScreenshotAs(OutputType.FILE);
		File d = new File(path);
		FileUtils.copyFile(s, d);
	}
	//JS insert value
	public void jsinsertvalue(String text, WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].setAttribute('value','"+text+"')", element);
	}
	public Object jsGetattribute(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		Object executeScript = executor.executeScript("returnarguments[0].getattribute('value')", element);
		return executeScript;
	}
	public void jsClick(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", element);
	}
	public void jsScrollDown(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].scrollIntoView(true)",element);
	}
	public void jsScrollUp(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].scrollIntoView(false)", element);
	}
	public void ddnSelectByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}
	public void ddnSelectByText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	public List<WebElement> ddnGetOptions(WebElement element){
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		return options;
	}
	public List<WebElement> ddnGetAllSelectedOptions(WebElement element){
		Select select = new Select(element);
		List<WebElement> options = select.getAllSelectedOptions();
		return options;

	}
	public WebElement ddnGetFirstSelectedOption(WebElement element) {
		Select select = new Select(element);
		WebElement options = select.getFirstSelectedOption();
		return options;
	}
	public Boolean ddnIsMultiple(WebElement element) {
		Select select = new Select(element);
		boolean multiple = select.isMultiple();
		return multiple;
	}
	public void ddnDeselecgtByIndex(WebElement element, int i) {
		Select select = new Select(element);
		select.deselectByIndex(i);
	}
	public void ddnDeselectByValue(WebElement element,String value) {
		Select select = new Select(element);
		select.deselectByValue(value);
	}
	public void ddnDeselectByText(WebElement element, String text) {
		Select select = new Select(element);
		select.deselectByVisibleText(text);
	}
	public void ddnDeselectAll(WebElement element) {
		Select select = new Select(element);
		select.deselectAll();
	}
	public void frameByIndex(int i) {
		driver.switchTo().frame(i);
	}
	public void frameById(String id) {
		driver.switchTo().frame(id);
	}
	public void frameByElement(WebElement element) {
		driver.switchTo().frame(element);
	}
	public void parentFrame() {
		driver.switchTo().parentFrame();
	}
	public void returnFrame() {
		driver.switchTo().defaultContent();

	}
	public String getParentWindowId() {
		String windowHandle = driver.getWindowHandle();
		return windowHandle;
	}
	public Set<String> allWindowsId(){
		Set<String> windowHandles = driver.getWindowHandles();
		return windowHandles;
	}
	public void closeCurrentWindow() {
		driver.close();
	}
	public void closeAllWindows() {
		driver.quit();
	}
	public void implicitWait(long seconds,TimeUnit unit) {
		driver.manage().timeouts().implicitlyWait(seconds, unit);
	}
	public void webDriverWait(int seconds) {
		WebDriverWait driverwait = new WebDriverWait(driver, seconds);

	}
	public String getData(String sheetname,int rownum,int cellnum) throws IOException {
		String res = null;
		File file = new File("C:\\Users\\Sridhar\\Documents\\Test\\Adactin.xlsx");
		FileInputStream stream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(stream);
		Sheet sheet = workbook.getSheet(sheetname);
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		CellType type = cell.getCellType();
		switch (type) {
		case STRING:
			res = cell.getStringCellValue();
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				Date dateCellValue = cell.getDateCellValue();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
				dateFormat.format(dateCellValue);
			}else {
				double d = cell.getNumericCellValue();
				BigDecimal num = BigDecimal.valueOf(d);
				res = num.toString();
			}
		default:
			break;
		}
		return res;
	}    
	public void updateData(String sheetname,int rownum,int cellnum,String olddata,String newdata) throws IOException {

		File file = new File("C:\\Users\\Sridhar\\Documents\\Test\\Adactin.xlsx");
		FileInputStream stream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(stream);
		Sheet sheet = workbook.getSheet(sheetname);
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		CellType type = cell.getCellType();
		String value = cell.getStringCellValue();
		if (value.equals(olddata)) {
			cell.setCellValue(newdata);
			FileOutputStream out = new FileOutputStream(file);
			workbook.write(out);
		}


	}

	public void writeData(String sheetname,int rownum,int cellnum,String data) throws IOException {

		File file = new File("C:\\Users\\Sridhar\\Documents\\Test\\Adactin.xlsx");
		FileInputStream stream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(stream);
		Sheet sheet = workbook.getSheet(sheetname);
		Row row = sheet.createRow(rownum);
		Cell cell = row.createCell(cellnum);
		cell.setCellValue(data);
		FileOutputStream out = new FileOutputStream(file);
		workbook.write(out);
	}
}
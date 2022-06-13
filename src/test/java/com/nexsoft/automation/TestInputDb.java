package com.nexsoft.automation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestInputDb {

	private WebDriver driver;

	@BeforeAll
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@DisplayName("Login web with username and password")
	@Order(1)
	@Test
	public void gotoWeb_andLogin_withUsername_withPassword() {
		driver.get("http://localhost/cicool/");

		driver.findElement(By.cssSelector(".fa.fa-sign-in")).click();

		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("mnaauval@gmail.com");

		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("admin123");

		driver.findElement(By.cssSelector(".btn.btn-primary.btn-block.btn-flat")).click();

//		driver.close();
	}

	@DisplayName("Go to CRUD Builder")
	@Order(2)
	@Test
	public void gotoCRUDBuilder() {
		driver.findElement(By.cssSelector("a[href='http://localhost:80/cicool/administrator/crud']")).click();

		driver.findElement(By.cssSelector("tbody tr a:nth-child(1)")).click();

//		driver.findElement(By.cssSelector("#btn_add_new")).click();
	}

	@DisplayName("Input Data")
	@Order(3)
	@ParameterizedTest
	@CsvFileSource(resources = "/absensi.csv", delimiter = ';', numLinesToSkip = 1)
	public void inputDataCRUD(String no, String uname, String email, String location) {
		driver.findElement(By.cssSelector("#btn_add_new")).click();
		driver.findElement(By.cssSelector("#username")).click();
		driver.findElement(By.cssSelector("#username")).sendKeys(uname);

		driver.findElement(By.cssSelector("#email")).click();
		driver.findElement(By.cssSelector("#email")).sendKeys(email);

		driver.findElement(By.cssSelector("#location")).click();
		driver.findElement(By.cssSelector("#location")).sendKeys(location);
		driver.findElement(By.xpath("//a[@id='btn_save']")).click();
	}

	@DisplayName("Show All Data")
	@Order(4)
	@Test
	public void showAllData() {
		List<WebElement> lstUname = driver.findElements(By.xpath(
				"//html[1]/body[1]/div[1]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/table[1]/tbody[1]/tr/td[2]"));
		List<WebElement> lstEmail = driver.findElements(By.xpath(
				"//html[1]/body[1]/div[1]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/table[1]/tbody[1]/tr/td[3]"));
		List<WebElement> lstLocation = driver.findElements(By.xpath(
				"//html[1]/body[1]/div[1]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/table[1]/tbody[1]/tr/td[4]"));

		//
		for (WebElement webElement : lstUname) {
			System.out.println(webElement.getText());
		}
		System.out.println("Username data terakhir adalah " + lstUname.get(lstUname.size() - 1).getText());
		System.out.println();

		//
		for (WebElement webElement : lstEmail) {
			System.out.println(webElement.getText());
		}
		System.out.println("Email data terakhir adalah " + lstEmail.get(lstEmail.size() - 1).getText());
		System.out.println();

		//
		for (WebElement webElement : lstLocation) {
			System.out.println(webElement.getText());
		}
		System.out.println("Lokasi data terakhir adalah " + lstLocation.get(lstLocation.size() - 1).getText());
		System.out.println();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		driver.close();
	}

//	@Disabled
	@DisplayName("Delete Data Directly")
	@Order(5)
	@Test
	public void deleteDataCRUD() {
		driver.findElement(By.cssSelector(".iCheck-helper")).click();
		driver.findElement(By.cssSelector("#apply")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//button[@class='confirm']")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.close();
	}
}

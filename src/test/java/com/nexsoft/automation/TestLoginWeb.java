package com.nexsoft.automation;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Disabled
public class TestLoginWeb {

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

		String username = driver.findElement(By.cssSelector("span[class='hidden-xs']")).getText();
		assertEquals("Mnaauval", username);

		driver.findElement(By.cssSelector("img.user-image")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector(".pull-right a.btn.btn-default.btn-flat")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		driver.close();
	}

	@DisplayName("Login web with username and password fail")
	@Order(2)
	@Test
	public void gotoWeb_andLogin_withUsername_withFalsePassword() {
//		driver.get("http://localhost/cicool/");

		driver.findElement(By.cssSelector(".fa.fa-sign-in")).click();

		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("mnaauval@gmail.com");

		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("123456");

		driver.findElement(By.cssSelector(".btn.btn-primary.btn-block.btn-flat")).click();

		String errorActual = driver.findElement(By.cssSelector("div[class='callout callout-error'] p")).getText();
		String errorExpected = "E-mail, Username or Password do not match.";
		assertEquals(errorActual, errorExpected);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		driver.close();
	}

	@DisplayName("Login web with username and no password")
	@Order(3)
	@Test
	public void gotoWeb_andLogin_withUsername_withoutPassword() {
		driver.get("http://localhost/cicool/");

		driver.findElement(By.xpath("//a[@class='page-scroll']")).click();

		driver.findElement(By.xpath("//input[@placeholder='Email']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("mnaauval@gmail.com");

		driver.findElement(By.xpath("//input[@placeholder='Password']")).clear();

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		String errorActual = driver.findElement(By.xpath("//p[normalize-space()='The Password field is required.']"))
				.getText();
		String errorExpected = "The Password field is required.";
		assertEquals(errorActual, errorExpected);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		driver.close();

	}

	@DisplayName("Login web with no username and password")
	@Order(4)
	@Test
	public void gotoWeb_andLogin_withoutUsername_withPassword() {
		driver.get("http://localhost/cicool/");

		driver.findElement(By.xpath("//a[@class='page-scroll']")).click();

		driver.findElement(By.xpath("//input[@placeholder='Email']")).clear();

		driver.findElement(By.xpath("//input[@placeholder='Password']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		String errorActual = driver.findElement(By.xpath("//p[normalize-space()='The Username field is required.']"))
				.getText();
		String errorExpected = "The Username field is required.";
		assertEquals(errorActual, errorExpected);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		driver.close();

	}

	@DisplayName("Login web with no username and no password")
	@Order(5)
	@Test
	public void gotoWeb_andLogin_withoutUsername_withoutPassword() {
		driver.get("http://localhost/cicool/");

		driver.findElement(By.xpath("//a[@class='page-scroll']")).click();

		driver.findElement(By.xpath("//input[@placeholder='Email']")).clear();

		driver.findElement(By.xpath("//input[@placeholder='Password']")).clear();

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		List<WebElement> error = driver.findElements(By.xpath("//body/div[1]/div[2]/div/p"));
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.close();

	}

}

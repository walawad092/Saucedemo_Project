package swaglab;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class MyTestCases {

	WebDriver driver = new EdgeDriver();
	String MyWebSite = "https://www.saucedemo.com/";
	
	Random rand = new Random();

	@BeforeTest
	public void MySetup() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(MyWebSite);
		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void LogInWithNormalUser() {

		String theusername = "standard_user";
		String thepassword = "secret_sauce";

		// Elemants
		WebElement UserNameInput = driver.findElement(By.id("user-name"));
		WebElement PasswordInput = driver.findElement(By.id("password"));
		WebElement LoginButton = driver.findElement(By.id("login-button"));

		// Actions
		UserNameInput.sendKeys(theusername);
		PasswordInput.sendKeys(thepassword);
		LoginButton.click();

	}

	@Test(priority = 2,enabled = false)
	public void AddItems() {

		int randomItem = rand.nextInt(6);
		int randomItem2;
		int randomItem3;

		if (randomItem == 5) {

			randomItem2 = randomItem - 1;
		} else {

			randomItem2 = randomItem + 1;
		}
		if (randomItem == 5 || randomItem == 4) {

			randomItem3 = randomItem - 2;
		} else {

			randomItem3 = randomItem + 2;
		}

		List<WebElement> AddtoCartButtons = driver.findElements(By.className("btn"));
		List<WebElement> ItemsNames = driver.findElements(By.className("inventory_item_name"));

		AddtoCartButtons.get(randomItem).click();
		AddtoCartButtons.get(randomItem2).click();
		AddtoCartButtons.get(randomItem3).click();

		System.out.println(
				ItemsNames.get(randomItem).getText() + " has been added and the index for it is " + randomItem);
		System.out.println(randomItem);
		System.out.println(randomItem2);
		System.out.println(randomItem3);

	}
	
	@Test(priority = 2)
	public void  AddRandomITem () {
		
		int randomItem1 = rand.nextInt(2); // 0 OR 1
		int randomItem2 = rand.nextInt(2,4); // 2 OR 3
		int randomItem3 = rand.nextInt(4,6); // 4 OR 5
		
		System.out.println(randomItem1);
		System.out.println(randomItem2);
		System.out.println(randomItem3);
		
		List<WebElement> AddtoCartButtons = driver.findElements(By.className("btn"));
		
		AddtoCartButtons.get(randomItem1).click();
		AddtoCartButtons.get(randomItem2).click();
		AddtoCartButtons.get(randomItem3).click();
		
	}

	@Test(priority = 3)
	public void CheckoutProcess() throws InterruptedException {

		// Options
		// 1
		// driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
		// 2 driver.findElement(By.className("shopping_cart_link")).click();
		// 3 driver.navigate().to("https://www.saucedemo.com/cart.html");

		// Correct Code

		WebElement Thecart = driver.findElement(By.className("shopping_cart_link"));
		Thecart.click();

		WebElement CheckoutButton = driver.findElement(By.id("checkout"));
		CheckoutButton.click();

		// Fill the form of the user data

		// The input that need to be filled
		WebElement FirstNameInput = driver.findElement(By.id("first-name"));
		WebElement LastNameInput = driver.findElement(By.id("last-name"));
		WebElement PostalCodeInput = driver.findElement(By.id("postal-code"));

		// The data to fill
		FirstNameInput.sendKeys("Waleed");
		LastNameInput.sendKeys("Yousef");
		PostalCodeInput.sendKeys("2002");

		// Finish the checkout process
		WebElement ContinueButton = driver.findElement(By.name("continue"));
		ContinueButton.click();

		WebElement FinishButton = driver.findElement(By.id("finish"));
		FinishButton.click();

		driver.navigate().back();

		driver.navigate().forward();

		Thread.sleep(3000);
		driver.navigate().refresh();

		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".btn.btn_primary.btn_small")).click();

	}

}

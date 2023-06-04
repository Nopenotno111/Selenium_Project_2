package Selenium_testing;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.util.Random;

public class Task_1 {

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		
		//Webdriver Configuration
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        WebDriver driver = new ChromeDriver(options);	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.get("https://www.wunderground.com/");
		
		//Random data
		Random random = new Random();
        int randomNumber = random.nextInt(50);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(randomNumber));
		
		//Explicit Wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Log in')]")));
		driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
		
		//Page object pattern
		class LoginPage {
		    private WebDriver driver;
		    private By usernameInput = By.xpath("//input[@id='form-signin-email']");
		    private By passwordInput = By.xpath("//input[@id='form-signin-password']");
		    private By loginButton = By.xpath("//input[@id='signIn']");

		    public LoginPage(WebDriver driver) {
		        this.driver = driver;
		    }

		    public void enterUsername(String username) {
		        WebElement usernameElement = driver.findElement(usernameInput);
		        usernameElement.sendKeys(username);
		    }

		    public void enterPassword(String password) {
		        WebElement passwordElement = driver.findElement(passwordInput);
		        passwordElement.sendKeys(password);
		    }

		    public void clickLoginButton() {
		        WebElement loginButtonElement = driver.findElement(loginButton);
		        loginButtonElement.click();
		    }
		
		}
		
		//Form Fill and Send
		LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("mingkchou@163.com");
        loginPage.enterPassword("testthisweb.");
        loginPage.clickLoginButton();
		Thread.sleep(2000);
		
		//Fill any form and send
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//wu-header/sidenav[1]/mat-sidenav-container[1]/mat-sidenav-content[1]/div[1]/section[1]/section[4]/div[1]/div[1]/div[1]/div[1]/app-conditions-card[1]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/div[1]")));
		driver.findElement(By.xpath("//button[@id='wuSettings']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'More Settings')]")));
		driver.findElement(By.xpath("//a[contains(text(),'More Settings')]")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("mingkchou2@163.com");
		driver.findElement(By.xpath("//wu-header/sidenav[1]/mat-sidenav-container[1]/mat-sidenav-content[1]/div[1]/section[1]/div[2]/div[1]/wu-form-change-email[1]/div[1]/div[1]/form[1]/div[2]/input[1]")).click();
		Thread.sleep(2000);
		
		//Static Page check
		driver.findElement(By.xpath("//wu-header/div[1]/div[1]/lib-menu[1]/nav[1]/menu-item[4]/button[1]/span[1]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"mat-menu-content ng-tns-c121-6\"]/button[2]")));
		
		//Reading data from drop-down
		System.out.println(driver.findElement(By.xpath("//div[@class=\"mat-menu-content ng-tns-c121-6\"]/button[2]")).getText());
		driver.findElement(By.xpath("//div[@class=\"mat-menu-content ng-tns-c121-6\"]/button[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'Upside Down Pattern Could Lead to More Fire Danger')]")));
		
		//Multiple Page check		
		List<WebElement> myElements = driver.findElements(By.xpath("//div[@class='news-item ng-star-inserted']"));
		int number = myElements.size();
		driver.findElement(By.xpath("//button[contains(text(),'Show More Articles')]")).click();
		Thread.sleep(5000);
		myElements = driver.findElements(By.xpath("//div[@class='news-item ng-star-inserted']"));
		Assert.assertTrue(myElements.size()> number, "Elements not loaded."); 
		
		//Reading data from Paragraph
		System.out.println(driver.findElement(By.xpath("//p[contains(text(),'Wildfires in Canada have recently been in the news')]")).getText());
		
		//Reading Heading
		System.out.println(driver.findElement(By.xpath("//h3[contains(text(),'Upside Down Pattern Could Lead to More Fire Danger')]")).getText());
		
		// Hover the mouse over the element
		Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//a[@id='header-logo']"))).perform();
        Thread.sleep(2000);
        
        //History test (browser back button)
        driver.navigate().back();
        driver.navigate().back();
        Thread.sleep(2000);
        
        //Javascript Executor
        js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[@id='header-logo']")));
        Thread.sleep(2000);
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[contains(text(),'You May Like')]")));
        Thread.sleep(2000);
        
        //Sign out
        driver.findElement(By.xpath("//button[contains(text(),'My Profile')]")).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//wu-header/div[1]/div[1]/lib-login[1]/ul[1]/li[4]")));
        driver.findElement(By.xpath("//wu-header/div[1]/div[1]/lib-login[1]/ul[1]/li[4]")).click();
        Thread.sleep(2000);
        
        System.out.println("completed");
        driver.quit();
		
	}
	
	
	
}

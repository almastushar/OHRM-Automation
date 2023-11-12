package org.redqa.testUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.redqa.utils.SeleniumUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;


public class BaseTest extends SeleniumUtils {

	@BeforeMethod
	public void ConfigureAppium() throws IOException {
		WebDriverManager.chromedriver().clearDriverCache().setup();
		driver = WebDriverManager.chromedriver().create();
		driver.manage().window().maximize();

		prop = new Properties();
		fs = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//org//redqa//resources//data.properties");
		
		prop.load(fs);
		
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}

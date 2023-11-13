package org.redqa.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.redqa.pageObject.DashboardPage;
import org.redqa.pageObject.LoginPage;
import org.redqa.testUtils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test_LoginPage extends BaseTest {

	@Test(dataProvider = "getData", groups = { "Regression" })
	public void LoginTest(HashMap<String, String> input) throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUserName(input.get("username"));
		loginPage.setPassword(input.get("pass"));
		loginPage.clickLoginBtn();

		DashboardPage dashboardPage = new DashboardPage(driver);
		Assert.assertEquals(dashboardPage.getDashboardText(), input.get("assertDashboard"));

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")
				+ "//src//test//java//org//redqa//testData//testdata.json");

		return new Object[][] { { data.get(0) } };
	}

}

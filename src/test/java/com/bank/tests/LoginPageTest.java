package com.bank.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bank.base.BaseClass;
import com.bank.pages.LoginPage;


public class LoginPageTest extends BaseClass{
	
private LoginPage loginPage;
	
	
	@BeforeMethod
	public void setupPAges() {
		loginPage =new LoginPage(getDriver());
		
	}
	
	@Test
	public void verifyValidLoginTest() {
		loginPage.Login(BaseClass.getAdminUsername(),BaseClass.getAdminPassword());
		staticWait(2);
	}

}

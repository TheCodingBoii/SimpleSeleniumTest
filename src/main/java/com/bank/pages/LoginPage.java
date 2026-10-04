package com.bank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.bank.actiondriver.ActionDriver;



public class LoginPage {
	
	private ActionDriver actionDriver;
	
	//Define locattors using By class
	
		private By userNameField = By.id("login-username");
		private By passwordField = By.id("login-password");
		private By loginButton = By.xpath("//button[@type='submit']");
		
		public LoginPage(WebDriver driver) {
			this.actionDriver=new ActionDriver(driver);
		}
		
		public void Login(String username, String password) {
			actionDriver.enterText(userNameField, username);
			actionDriver.enterText(passwordField, password);
			actionDriver.click(loginButton);
			
		}

}

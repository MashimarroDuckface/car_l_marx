/*   Car-L-Marx
 *
 *   Oct 25, 2014  
 *   CS 320 Fall 2014
 *
 *		Michael Allen-Bond
 *		Lise Driggers
 *		Jesse Pomerenk
 *
 *		controller
 *
 *   logonController.java
*/
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import security.PasswordEncrypt;
import views.*;
import model.*;

public class LogonController
{
	private LoginFrame loginView;
	private DbAccess dbHandle;
	
	private boolean goodLogin = false;
	
	public LogonController(LoginFrame loginView, DbAccess dbHandle)
	{	
		this.loginView = loginView;
		this.dbHandle = dbHandle;
	}
	
	public boolean startLogon()
	{
		this.loginView.addSubmitButtonListener(new SubmitListener());
		this.loginView.addbtnForgotButtonListener(new ForgotListener());	
		this.loginView.addbtnNewUserButtonListener(new NewUserListener());		
		
		//  TODO  need to find out when the goodLogin field changes
		return false;
	//	return this.goodLogin;
	}
	
	public String getuser()
	{
		return loginView.getUserName();
	}
	
	public class SubmitListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			System.out.println("Inside SubmitListener");
			String userName = loginView.getUserName();
			System.out.println("User " + userName);
			
			char[] userPassword = loginView.getPassword();
			String stringPassword = new String(userPassword);
			
			if ( (userName.isEmpty()) || (userPassword.length == 0))   //  No input!
			{
				System.out.println("everything is empty");
				
				goodLogin = false;
				return; 
			}
			
			String salt = dbHandle.getSalt(userName);
			String dbPass = dbHandle.getPass(userName);
			System.out.println(userName  + " salt from server " + salt);
//			System.out.println(userName + " from server  - password-->" + dbPass + " password from input-->" + stringPassword);
			
			PasswordEncrypt pass = new PasswordEncrypt();
			String encPassword = pass.getSecurePassword(stringPassword, salt);
			
			goodLogin = (encPassword.equals(dbPass));   //  will be true if the user passed the login
			
			if (goodLogin)
			{
				loginView.setVisible(false); //you can't see me!
				loginView.dispose(); //Destroy the JFrame object
			}
			
			System.out.println("Good user ? " + goodLogin);
		}
	}
	
	public class ForgotListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			System.out.println("Inside ForgotListener");
		}
	}
	
	public class NewUserListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			System.out.println("Inside NewUserListener");
		}
	}
}

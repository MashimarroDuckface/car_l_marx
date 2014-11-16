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

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import security.PasswordEncrypt;
import views.*;
import model.*;

public class LogonController
{
	private LoginFrame loginView;
	private DbAccess dbHandle;
	public MainController mController;
	public LogonController lController;
	
	private boolean goodLogin = false;
	
	public LogonController(LoginFrame loginView, DbAccess dbHandle, MainController mController)
	{	
		this.loginView = loginView;
		this.dbHandle = dbHandle;
		this.mController = mController;
		this.lController = this;
	}
	
	public boolean startLogon()
	{
		this.loginView.addSubmitButtonListener(new SubmitListener());
		this.loginView.addEnter(new SubmitListener());              //  Call same method as Submit button
		this.loginView.addbtnForgotButtonListener(new ForgotListener());	
		this.loginView.addbtnNewUserButtonListener(new NewUserListener());		
		this.loginView.addbtnResetButtonListener(new ResetListener());	
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
		public void actionPerformed(ActionEvent e) 
		{
			String userName = loginView.getUserName();
			
			char[] userPassword = loginView.getPassword();
			String stringPassword = new String(userPassword);
			
			if (userName.isEmpty())
			{
				loginView.lblUsrId.setText("User Name is blank");
				loginView.lblUsrId.setForeground(Color.RED);
				return;
			}
			
			if (!dbHandle.validUser(userName))
			{
				loginView.lblUsrId.setText("User Name is not on file");
				loginView.lblUsrId.setForeground(Color.RED);
				return;
			}
			
			if (userPassword.length == 0)
			{
				loginView.lblUsrId.setForeground(Color.BLACK);
				loginView.lblPassword.setText("Password is blank");
				loginView.lblPassword.setForeground(Color.RED);
				return;
			}
			
			String salt = dbHandle.getSalt(userName);
			String dbPass = dbHandle.getPass(userName);
			
			PasswordEncrypt pass = new PasswordEncrypt();
			String encPassword = pass.getSecurePassword(stringPassword, salt);
			
			goodLogin = (encPassword.equals(dbPass));   //  will be true if the user passed the login
			
			if (goodLogin)
			{
				loginView.setVisible(false); //you can't see me!
				loginView.dispose(); //Destroy the JFrame object
				mController.goodUser(userName);
			}
			else   //  Logon was not successful
			{
				loginView.lblPassword.setText("Password not valid for this user");
				loginView.lblPassword.setForeground(Color.RED);
				return;
			}
			
		}
	}
	
	public class ForgotListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			
				System.out.println("Inside ForgotListener");
				ForgotFrame forgotView = new ForgotFrame();
				DbAccess DbHandle = DbAccess.getInstance();
				ForgotController Forgot = new ForgotController( forgotView,  DbHandle, lController);
				Forgot.startForgot();
				forgotView.setVisible(true);
				System.out.println("Inside NewUserListener");
		}
	}
	
	public class ResetListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			
				System.out.println("Inside resetListener");
				ResetFrame resetView = new ResetFrame();
				DbAccess DbHandle = DbAccess.getInstance();
				ResetController reset = new ResetController( resetView,  DbHandle, lController);
				reset.startReset();
				resetView.setVisible(true);
		}
	}
	
	public class NewUserListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
	//		System.out.println("Inside ForgotListener");
			NewUserFrame newUserView = new NewUserFrame();
			DbAccess DbHandle = DbAccess.getInstance();
			NewUserController newUser = new NewUserController( newUserView,  DbHandle, lController);
			newUser.startNewUser();
			newUserView.setVisible(true);
	//		System.out.println("Inside NewUserListener");
		}
	}
}

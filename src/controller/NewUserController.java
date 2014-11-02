/*   Car-L-Marx
 *
 *   Nov 1, 2014  
 *   CS 320 Fall 2014
 *
 *		Michael Allen-Bond
 *		Lise Driggers
 *		Jesse Pomerenk
 *
 *		controller
 *
 *   NewUserController.java
*/
package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import security.PasswordEncrypt;
import views.*;
import model.*;

public class NewUserController
{
	private NewUserFrame userView;
	public DbAccess dbHandle;
	private LogonController lController;
	
	public NewUserController(NewUserFrame userFrame, DbAccess dbHandle, LogonController lController)
	{
		this.userView = userFrame;
		this.dbHandle = dbHandle;
		this.lController = lController;
	}
	
	public void startNewUser()
	{
		this.userView.addSubmitButtonListener(new SubmitListener());
	}
	
	public class SubmitListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			//  Reset all labels
			userView.lblUserName.setText("* User Name");
			userView.lblUserName.setForeground(Color.BLACK);
			userView.lblPassword.setText("* Password");
			userView.lblPassword.setForeground(Color.BLACK);
			userView.lblFirstName.setText("* First Name");
			userView.lblFirstName.setForeground(Color.BLACK);
			userView.lblLastName.setText("* Last Name");
			userView.lblLastName.setForeground(Color.BLACK);
			userView.lblEmailAddress.setText("* Email");
			userView.lblEmailAddress.setForeground(Color.BLACK);
			
			//  TODO  sanitize all input
			String userName = userView.txtUserName.getText();
			
			if (userName.isEmpty())
			{
				userView.lblUserName.setText("User Name is blank");
				userView.lblUserName.setForeground(Color.RED);
				return;
			} 
			else if (dbHandle.validUser(userName))
			{
				userView.lblUserName.setText("User Name is not available");
				userView.lblUserName.setForeground(Color.RED);
				return;
			}
			
			char[] password = userView.txtPass.getPassword();
			char[] rePass = userView.txtRePass.getPassword();
			
			// TODO  the password should probably be verified so that it is secure
			if (password.length == 0)
			{
				userView.lblPassword.setText("Password is blank");
				userView.lblPassword.setForeground(Color.RED);
				return;
			}
			
			if (!Arrays.equals(password, rePass))
			{
				userView.lblPassword.setText("Password fields must match");
				userView.lblPassword.setForeground(Color.RED);
				return;
			}
			
			String firstName = userView.txtFirstName.getText();
			if (firstName.isEmpty())
			{
				userView.lblFirstName.setText("Please enter your first name");
				userView.lblFirstName.setForeground(Color.RED);
				return;
			}
			
			String lastName = userView.txtLastName.getText();
			if (lastName.isEmpty())
			{
				userView.lblLastName.setText("Please enter your last name");
				userView.lblLastName.setForeground(Color.RED);
				return;
			}
			
			//  TODO  when sanitizing the email address - make sure that it passes a regex for email
			String emailAdd = userView.txtEmail.getText();
			if (emailAdd.isEmpty())
			{
				userView.lblEmailAddress.setText("Please enter your email address");
				userView.lblEmailAddress.setForeground(Color.RED);
				return;
			}
			
			//  Passed all tests - get a password salt, encrypt the password, and insert the user
			PasswordEncrypt pass = new PasswordEncrypt();
			String salt = null;
			try {
			salt = pass.getSalt();
			} catch (Exception a)
			{
				
			}
			String encPassword = pass.getSecurePassword(new String(password), salt);

			dbHandle.insertNewUser(userName, encPassword, salt, firstName, lastName, emailAdd);
			
			userView.setVisible(false); //you can't see me!
			userView.dispose(); //Destroy the JFrame object
	//		mController.goodUser(userName);
		}
	}
}

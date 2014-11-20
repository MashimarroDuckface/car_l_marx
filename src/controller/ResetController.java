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

import controller.ForgotController.CancelListener;

import security.PasswordEncrypt;
import views.*;
import model.*;

public class ResetController
{
	private ResetFrame userView;
	public DbAccess dbHandle;
	private LogonController lController;
	
	public ResetController(ResetFrame userFrame, DbAccess dbHandle, LogonController lController)
	{
		this.userView = userFrame;
		this.dbHandle = dbHandle;
		this.lController = lController;
	}
	
	public void startReset()
	{
		this.userView.addSubmitButtonListener(new SubmitListener());
		this.userView.addCancelButtonListener(new CancelListener());
	}
	
	public class SubmitListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			//  Reset all labels
			userView.lblUserName.setText("* User Name");
			userView.lblUserName.setForeground(Color.BLACK);
			
			userView.lblPassword.setText("* Password");
			userView.lblPassword.setForeground(Color.BLACK);
			
			//  TODO  sanitize all input
			String userName = userView.txtUserName.getText();
			
			if (userName.isEmpty())
			{
				userView.lblUserName.setText("User Name is blank");
				userView.lblUserName.setForeground(Color.RED);
				return;
			} 
			else if (!dbHandle.validUser(userName))
			{
				userView.lblUserName.setText("User Name Unknown");
				userView.lblUserName.setForeground(Color.RED);
				return;
			}
			
			char[] password = userView.txtPassword.getPassword();
			char[] newPass = userView.txtNewPass.getPassword();
			char[] reNewPass=userView.txtReNewPass.getPassword();
			// TODO  the password should probably be verified so that it is secure
			if (password.length == 0)
			{
				userView.lblPassword.setText("Password is blank");
				userView.lblPassword.setForeground(Color.RED);
				return;
			}
			
			if (newPass.length == 0)
			{
				userView.lblNewPass.setText("New Password is blank");
				userView.lblNewPass.setForeground(Color.RED);
				return;
			}
			
			if (!Arrays.equals(newPass, reNewPass))
			{
				userView.lblReNewPass.setText("New Password must match");
				userView.lblReNewPass.setForeground(Color.RED);
				return;
			}
			
			//  Passed all tests - get a password salt, encrypt the password, and insert the user
			PasswordEncrypt pass = new PasswordEncrypt();
			String salt = dbHandle.getSalt(userName);
			
			String encPassword = pass.getSecurePassword(new String(newPass), salt);
			//TODO check if old pass is users actual pass
			//SECURITY CONCERN
			
			dbHandle.updateUserPassword(userName,encPassword);
			
			userView.setVisible(false); //you can't see me!
			userView.dispose(); //Destroy the JFrame object
	//		mController.goodUser(userName);
		}
	}
	
	public class CancelListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			userView.setVisible(false); //you can't see me!
			userView.dispose();
	}
		}
}

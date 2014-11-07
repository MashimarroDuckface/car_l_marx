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

public class ForgotController
{
	private ForgotFrame userView;
	public DbAccess dbHandle;
	private LogonController lController;
	
	public ForgotController(ForgotFrame userFrame, DbAccess dbHandle, LogonController lController)
	{
		this.userView = userFrame;
		this.dbHandle = dbHandle;
		this.lController = lController;
	}
	
	public void startForgot()
	{
		this.userView.addSubmitButtonListener(new SubmitListener());
	}
	
	public class SubmitListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			//  Reset all labels
			userView.lblUserName.setText("* User Name");
			userView.lblUserName.setForeground(Color.BLACK);
			
			userView.lblEmail.setText("* Email");
			userView.lblEmail.setForeground(Color.BLACK);
			
			userView.lblReenterEmail.setText("* Email");
			userView.lblReenterEmail.setForeground(Color.BLACK);

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
			//TODO Check if email adresses match
			//TODO SQL side stuff: temp PW, email user
			userView.setVisible(false); //you can't see me!
			userView.dispose(); //Destroy the JFrame object
	//		mController.goodUser(userName);
		}
	}
}

/*   Car-L-Marx
 *
 *   Oct 9, 2014  
 *   CS 320 Fall 2014
 *
 *		Michael Allen-Bond
 *		Lise Driggers
 *		Jesse Pomerenk
 *
 *		home
 *
 *   Main.java
*/
package home;

import javax.swing.JFrame;

import model.DbAccess;
import views.LoginFrame;

public class Main
{
	private static DbAccess dbHandle;

	public static void main(String[] args)
	{
		dbHandle = DbAccess.getInstance();

		/**
		 *   TODO  Some thoughts - when the program is started up, launch threads
		 *   TODO  to get basic data from the db.  (make, model, etc) 
		 *   TODO  this should speed up the process for the user,
		 *   TODO  while they are logging in, we can be loading data in the background
		  */
//		dbHandle.getAllUser();
		
		LoginFrame login = new LoginFrame();
		login.setTitle("Car-L-Marx");
		login.setLocationRelativeTo(null);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setVisible(true);

	}

}

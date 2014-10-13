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

import views.LoginFrame;
import dbAccess.DbAccess;

public class Main
{
	private static DbAccess dbHandle;

	public static void main(String[] args)
	{
		dbHandle = DbAccess.getInstance();
//		dbHandle.getVehicle();
//		dbHandle.getVehicleInOrder();
//		dbHandle.getVehiclebyMake("Toyota");
//		dbHandle.getVehiclebyId(3);
//		dbHandle.getAllUser();
//		System.out.println(dbHandle.getSalt("Lacemaker"));
//		System.out.println(dbHandle.getPass("Lacemaker"));
		
		
		LoginFrame login = new LoginFrame(dbHandle);
		login.setTitle("Car-L-Marx");
		login.setLocationRelativeTo(null);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setVisible(true);

	}

}

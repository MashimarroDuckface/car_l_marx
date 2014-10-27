/*   Car-L-Marx
 *
 *   Oct 26, 2014  
 *   CS 320 Fall 2014
 *
 *		Michael Allen-Bond
 *		Lise Driggers
 *		Jesse Pomerenk
 *
 *		controller
 *
 *   MainController.java
*/
package controller;

public class MainController
{
	/**
	 *   TODO  Some thoughts - when the program is started up, launch threads
	 *   TODO  to get basic data from the db.  (make, model, etc) 
	 *   TODO  this should speed up the process for the user,
	 *   TODO  while they are logging in, we can be loading data in the background
	  */
	
	public MainController()
	{
//		LogonStart logon = new LogonStart();
//		
//		boolean goodLogon = logon.logonGetGoing();
//	
//		System.out.println("LogonStart in Main " + goodLogon);
		
//		if (goodLogon)
//		{
//			String user = logon.getUser();
//			VehicleStart cars = new VehicleStart(user);
//		}
		
		VehicleStart cars = new VehicleStart("Lacemaker");
		
	}
}

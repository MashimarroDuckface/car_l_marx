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
	 * TODO Some thoughts - when the program is started up, launch threads 
	 * to get basic data from the db. (make, model, etc)  this should speed
	 * up the process for the user, while they are logging in, we can be
	 * loading data in the background
	 */

	public MainController()
	{
		LogonStart logon = new LogonStart(this);

		logon.logonGetGoing();
		
//		goodUser("Lacemaker");

	}

	public void goodUser(String userName)
	{
		// TODO  wait for the above threads to complete before doing this
		VehicleStart cars = new VehicleStart(this, userName);
//		vehicleTabbedFrame(userName);
	}
	
	public void vehicleTabbedFrame(String userName)
	{
		VehicleTabbedStart vController = new VehicleTabbedStart(this, userName);
	}
}

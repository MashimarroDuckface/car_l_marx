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
 *   VehicleStart.java
*/
package controller;

import model.*;
import views.*;

public class VehicleStart
{
	private String user;
	private VehicleFrame vehicleView;
	private DbAccess DbHandle;
	private VehicleController vehicleController;
	
	public VehicleStart(String user)
	{
		this.user = user;
		
		vehicleView = new VehicleFrame();
		DbHandle = DbAccess.getInstance();
//		DbHandle.getUserVehicle("Lacemaker");
		vehicleController = new VehicleController(vehicleView, DbHandle, user);
		vehicleController.startVehicle();
		vehicleView.setVisible(true);
		
	}
}

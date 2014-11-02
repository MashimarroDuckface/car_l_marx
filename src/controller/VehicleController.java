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
 *   VehicleController.java
*/
package controller;

import java.util.ArrayList;

import views.*;
import model.*;

public class VehicleController
{
	private VehicleFrame vehicleView;
	private DbAccess dbHandle;
	private String userName;

	public VehicleController(VehicleFrame vehicleView, DbAccess dbHandle, String userName)
	{
		this.vehicleView = vehicleView;
		this.dbHandle = dbHandle;
		this.userName = userName;
	}
	
	public void startVehicle()
	{
		ArrayList<VehiclesObject> vehicleList = dbHandle.getUserVehicle("Lacemaker");
		//  This is how you can read the data back from the array list
		for (VehiclesObject v:vehicleList)
		{
			System.out.println(v.color + v.licensePlate + v.idvehicle + v.make + v.model + v.mileage);
		}
	}
	
	private void getVehicleData()
	{
		
	}
}

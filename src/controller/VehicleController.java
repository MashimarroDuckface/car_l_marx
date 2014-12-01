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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	public ArrayList<VehiclesObject> startVehicle()
	{
//		this.vehicleView.addSubmitButtonListener(new SubmitListener());
		ArrayList<VehiclesObject> vehicleList = dbHandle.getUserVehicle(userName);
		//  This is how you can read the data back from the array list
		for (VehiclesObject v:vehicleList)
		{
			System.out.println(v.color + v.licensePlate + v.idvehicle + v.make + v.model + v.mileage);
		}
		return vehicleList;
	}
	
	private ArrayList<VehiclesObject> getVehicleData()
	{
		ArrayList<VehiclesObject> vehicleList = dbHandle.getUserVehicle(userName);
		//  This is how you can read the data back from the array list
		for (VehiclesObject v:vehicleList)
		{
			System.out.println(v.color + v.licensePlate + v.idvehicle + v.make + v.model + v.mileage);
		}
		return vehicleList;
	}
	
	public class SubmitListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {

		}
	}
}

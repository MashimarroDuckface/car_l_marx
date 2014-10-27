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
		
	}
	
	private void getVehicleData()
	{
		/*SELECT idvehicle, makeTable.make, modelTable.model, colorTable.color, licensePlate,  `mileage` 
FROM  `vehicleTable` 
INNER JOIN makeTable ON vehicleTable.idmake = makeTable.idmake
INNER JOIN modelTable ON vehicleTable.idmodel = modelTable.idmodel
INNER JOIN colorTable ON vehicleTable.idColor = colorTable.idcolor
WHERE idUser =1
		
		*/
	}
}

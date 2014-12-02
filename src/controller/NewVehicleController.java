/*   Car-L-Marx
 *
 *   Nov 30, 2014  
 *   CS 320 Fall 2014
 *
 *		Michael Allen-Bond
 *		Lise Driggers
 *		Jesse Pomerenk
 *
 *		controller
 *
 *   NewVehicleController.java
*/
package controller;

import java.util.ArrayList;

import model.DbAccess;
import model.MakeObject;
import model.ModelObject;
import views.NewVehicleFrame;

public class NewVehicleController
{
	private DbAccess dbHandle;
	private NewVehicleFrame newVehicleView;
	private String userName;
	private MainController mController;
	private NewVehicleStart newVController;
	
	public NewVehicleController(NewVehicleStart newVController, DbAccess dbHandle, MainController mController)
	{
//		this.newVehicleView = vehicleView;
		this.dbHandle = dbHandle;
		this.userName = userName;
		this.mController = mController;
		this.newVController = newVController;
	}

	public ArrayList<MakeObject> getMake()
	{
		return this.dbHandle.getMake();
//		ArrayList<MakeObject> makeList = this.dbHandle.getMake();
//		return makeList;
	}

	public ArrayList<ModelObject> getModel(String make)
	{
		int makeId = dbHandle.getMakeId(make);
		ArrayList<ModelObject>modelList = this.dbHandle.getModel(makeId);
		return modelList;
	}

	public ArrayList<String> getColor()
	{
		return dbHandle.getColor();
	}
	
	public void validateNumeric(String testText)
	{
//		 int intValue = 0;
//         try {
//            intValue = Integer.parseInt(testText.trim());
//         } catch (NumberFormatException e) {
//            this.newVehicleView.con
//         }
	}

	public void next()
	{
		this.newVController.nextTires();
	}

	public void submit()
	{
		this.newVController.submit();
	}

}

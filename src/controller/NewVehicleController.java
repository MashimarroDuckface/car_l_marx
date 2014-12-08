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
import java.util.regex.Pattern;

import model.DbAccess;
import model.MakeObject;
import model.ModelObject;
import views.NewVehicleFrame;
import views.NewVehiclePanel;

public class NewVehicleController
{
	private DbAccess dbHandle;
	private NewVehicleFrame newVehicleView;
	private NewVehiclePanel newVehiclePanel;
	private String userName;
	private MainController mController;
	private NewVehicleStart newVController;
	
	public NewVehicleController(NewVehicleStart newVController, NewVehicleFrame newVehicleView, DbAccess dbHandle, MainController mController)
	{
		this.newVehicleView = newVehicleView;
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
		if (Pattern.matches("[a-zA-Z]+", testText) == false || testText.length() == 0){
			
		}
		//		 int intValue = 0;
//         try {
//            intValue = Integer.parseInt(testText.trim());
//         } catch (NumberFormatException e) {
//            this.newVehicleView.con
//         }
	}

	public void next()
	{
		String NickName = newVehiclePanel.txtNickName.getText();
		System.out.println("NewVehicleController - next --> nickName " + NickName );
		//test nickname
	//	if (Pattern.matches("[a-zA-Z0-9]+", newContentPane.txtNickName.getText()) == false || newContentPane.txtNickName.getText().length() == 0){
//			
//		}
//		
//		//test licenceplate
//		if (Pattern.matches("[a-zA-Z0-9]+", licensePlate) == false || licensePlate.length() < 5|| licensePlate.length() > 7){
//			
//		}
//		
//		//test milage
//		if (Pattern.matches("[0-9]+", mileage) == false){
//			
//		}		
		this.newVController.nextTires();
	}

	public void submit()
	{
		this.newVController.submit();
	}

	public void setController(NewVehiclePanel newVehiclePanel)
	{
		this.newVehiclePanel = newVehiclePanel;
		// TODO Auto-generated method stub
		
	}

}

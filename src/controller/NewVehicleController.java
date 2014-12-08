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

import java.awt.Color;
import java.util.ArrayList;
import java.util.regex.Pattern;

import model.DbAccess;
import model.MakeObject;
import model.ModelObject;
import views.NewVehicleFrame;
import views.NewVehiclePanel;
import views.NewVehicleTirePanel;

public class NewVehicleController
{
	private DbAccess dbHandle;
	private NewVehicleFrame newVehicleView;
	private NewVehiclePanel newVehiclePanel;
	private String userName;
	private MainController mController;
	private NewVehicleStart newVController;
	private NewVehicleTirePanel newVehicleTirePanel;
	
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
		//String NickName = newVehiclePanel.txtNickName.getText();
		//System.out.println("NewVehicleController - next --> nickName " + NickName );
		//test nickname
		if (Pattern.matches("[a-zA-Z0-9]+", newVehiclePanel.txtNickName.getText()) == false || newVehiclePanel.txtNickName.getText().length() == 0){
			return;
		}
		
		//test licenceplate
		if (Pattern.matches("[a-zA-Z0-9]+", newVehiclePanel.txtLicensePlate.getText()) == false 
				|| (newVehiclePanel.txtLicensePlate.getText().length() < 6
				|| newVehiclePanel.txtLicensePlate.getText().length() > 7)){
			 newVehiclePanel.lblLicensePlaate.setText("Plate Num Invalid");
			 newVehiclePanel.lblLicensePlaate.setForeground(Color.RED);
			return;
		}
		
		//test milage
		if (Pattern.matches("[0-9]+", newVehiclePanel.txtMileage.getText()) == false){
			return;
		}		
		this.newVController.nextTires();
	}

	public void submit()
	{	
		if (newVehicleTirePanel.txtTireType.getText().length() ==0){
			newVehicleTirePanel.lblTireType.setText("Type Required");
			newVehicleTirePanel.lblTireType.setForeground(Color.RED);
			return;
		}
		this.newVController.submit();
	}

	public void setController(NewVehiclePanel newVehiclePanel)
	{
		this.newVehiclePanel = newVehiclePanel;
		// TODO Auto-generated method stub
		
	}
	public void setController(NewVehicleTirePanel newVehiclePanel)
	{
		this.newVehicleTirePanel = newVehiclePanel;
		// TODO Auto-generated method stub
		
	}

}

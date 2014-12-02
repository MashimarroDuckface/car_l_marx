/*   Car-L-Marx
 *
 *   Nov 6, 2014  
 *   CS 320 Fall 2014
 *
 *		Michael Allen-Bond
 *		Lise Driggers
 *		Jesse Pomerenk
 *
 *		controller
 *
 *   VehicleTabbedController.java
*/
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

import views.*;
import model.DbAccess;
import model.MakeObject;
import model.ModelObject;

public class VehicleTabbedController
{
	private TabbedVehicleFrame tabView;
	private DbAccess dbHandle;
	private MainController mController;
	private VehicleTabbedController vTController;
	public String make;
	public int vehicleId;
	private String studsOnDate;
	private String studsOffDate;
	
	public VehicleTabbedController( DbAccess dbHandle, MainController mController, int vehicleId)
	{
//		this.tabView = tabFrame;
		this.dbHandle = dbHandle;
		this.mController = mController;
		this.vTController = this;
		this.vehicleId = vehicleId;
	//	this.tabView.setController(this);
	}
	
	public void getTabViewObject(TabbedVehicleFrame tabFrame)
	{
		this.tabView = tabFrame;
	}
	
	public void startTabbedView()
	{
		this.tabView.addCbxMakeListener(new CbxMakeListener());
		this.tabView.addTxtMileageListener(new addTxtMileageListener());
		//  set mileage on screen
//		System.out.println("currentMileage  " + this.getCurrentMileage());
	//	this.tabView.txtMileage.setText(Integer.toString(this.getCurrentMileage())); 
		this.tabView.txtCurrentMileage.setText("my new mileage"); 
//		System.out.println("VehicleTabbedController - startTabbedView");
	}
	
	public void updateMileage(String newMileage)
	{
		int miles = Integer.parseInt(newMileage);
		
		dbHandle.updateMileage(this.vehicleId, miles);
		
//		System.out.println("new miles " + miles);
		
	}
	
	public void updateNickName(String nickName)
	{
		dbHandle.updateNickName(this.vehicleId, nickName);
	}
	
	public int getCurrentMileage()
	{
		return dbHandle.getMileage(this.vehicleId);
	}
	
	public String getCurrentMileageString()
	{
		return Integer.toString(dbHandle.getMileage(this.vehicleId));
	}
	
	public void updateMakeAndModel(String model, String make)
	{
		
		int makeInt = dbHandle.getMakeIdFromMake(make);
	//	System.out.println("VehicleTabbedController UpdateMakeAndModel - makeInt " + makeInt + " String make-->  " + make);
		int modelInt = dbHandle.getModelIdFromModel(model);
		dbHandle.updateMakeAndModel(vehicleId, makeInt, modelInt);
	}
	
	public ArrayList<MakeObject> getMake()
	{
		ArrayList<MakeObject> makeList = this.dbHandle.getMake();
		
		return makeList;
	}
	
	public String getMakeString()
	{
		return this.dbHandle.getMakeForVehicle(this.vehicleId);
	}
	
	public int getMakeId()
	{
		return this.dbHandle.getMakeIdForVehicle(this.vehicleId);
	}
	
	public String getModelString()
	{
		return this.dbHandle.getModelString(vehicleId);
	}
	
	public ArrayList<ModelObject> getModel(String make)
	{
		int makeId = dbHandle.getMakeId(make);
		ArrayList<ModelObject>modelList = this.dbHandle.getModel(makeId);
		return modelList;
	}
	
	public String getNickName()
	{
		return this.dbHandle.getNickName(this.vehicleId);
	}
	
	public void updateLicensePlate(String licensePlate)
	{
		dbHandle.updateLicensePlate(this.vehicleId, licensePlate);
	}
	
	public void updateColor(String color)
	{
		int idColor = dbHandle.getColorId(color);
		dbHandle.updateColor(this.vehicleId, idColor);
	}
	
	public String getLicensePlate()
	{
		return dbHandle.getLicensePlate(this.vehicleId);
	}
	
	public ArrayList<String> getColor()
	{
		ArrayList<String> colorList = dbHandle.getColor();
		
		return colorList;
	}
	
	public String getColorString()
	{
		return dbHandle.getColorString(vehicleId);
	}
	
	public void updateStudsOnDate(String currentDate)
	{
		dbHandle.updateTiresStudsOnDate(vehicleId, currentDate);
	}
	
	public void updateStudsOffDate(String currentDate)
	{
		dbHandle.updateTiresStudsOffDate(vehicleId, currentDate);
	}
	
	public int getStudsOnMonth()
	{   //  Get month first - gets date from db
		getStudsOnDate();
		
		return Integer.parseInt(this.studsOnDate.substring(5, 7))-1;      //  Months are 0 based
	}
	public int getStudsOnYear()
	{
		return Integer.parseInt(this.studsOnDate.substring(0, 4));
	}
	public int getStudsOnDay()
	{
		return Integer.parseInt(this.studsOnDate.substring(8, 10));
	}
	public int getStudsOffMonth()
	{   //  Get month first - gets date from db
		getStudsOffDate();
		
		return Integer.parseInt(this.studsOffDate.substring(5, 7))-1;      //  Months are 0 based
	}
	public int getStudsOffYear()
	{
		return Integer.parseInt(this.studsOffDate.substring(0, 4));
	}
	public int getStudsOffDay()
	{
		return Integer.parseInt(this.studsOffDate.substring(8, 10));
	}
	
	private void getStudsOnDate()
	{
		this.studsOnDate = dbHandle.getStudsOnDate(vehicleId);
	}
	
	private void getStudsOffDate()
	{
		this.studsOffDate = dbHandle.getStudsOffDate(vehicleId);
	}
	
	public String getTireType()
	{
		return dbHandle.getTireType(vehicleId);
	}
	
	public void updateTIreType(String tireType)
	{
		dbHandle.updateTireType(vehicleId, tireType);
		
	}
	
/*  This listener isn't working.  I don't know why at this time  - Lise Nov 11, 2014 */	
	public class CbxMakeListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox)e.getSource();
	        String selectedMake = (String)cb.getSelectedItem();
	//        System.out.println("VehicleTabbedController - CbxMakeListener --> make selected " + selectedMake);
	        int makeId = dbHandle.getMakeId(selectedMake);
	        
	        ArrayList<ModelObject> models = dbHandle.getModel(makeId);
	       for ( ModelObject m:models)
	        {
//	        	tabView.cbxModel.addElement(models[i]);   //  add models to the model combo box based on the make that was selected
	        }
		}
	}
	
	public class SubmitListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {

		}
	}
	//  This listener is not working - no idea what is going on
	public class addTxtMileageListener implements FocusListener
	{
		public addTxtMileageListener()
		{
	//		System.out.println ("VehicleTabbedController - class addTxtMileageListener");
		}
		@Override
		public void focusGained(FocusEvent arg0)
		{
		}

		@Override
		public void focusLost(FocusEvent arg0)
		{
	//		System.out.println("Lost focus on additional mileage text area");
		}
	}

}

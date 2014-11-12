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

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComboBox;

import controller.LogonController.SubmitListener;
import security.PasswordEncrypt;
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
	
	public VehicleTabbedController( DbAccess dbHandle, MainController mController)
	{
//		this.tabView = tabFrame;
		this.dbHandle = dbHandle;
		this.mController = mController;
		this.vTController = this;
	//	this.tabView.setController(this);
	}
	
	public void getTabViewObject(TabbedVehicleFrame tabFrame)
	{
		this.tabView = tabFrame;
	}
	
	public void startTabbedView()
	{
		this.tabView.addCbxMakeListener(new CbxMakeListener());
	//	this.tabView.addSubmitButtonListener(new SubmitListener());
	//	System.out.println("VehicleTabbedController - startTabbedView");
	//	this.tabView.addCbxMakeListener(new CbxMakeListener());
	}
	
	public ArrayList<MakeObject> getMake()
	{
		System.out.println("VehicleTabbedController - getMake");
		ArrayList<MakeObject> makeList = this.dbHandle.getMake();
		
		return makeList;
	}
	
	public ArrayList<ModelObject> getModel(String make)
	{
		int makeId = dbHandle.getMakeId(make);
		ArrayList<ModelObject>modelList = this.dbHandle.getModel(makeId);
		return modelList;
	}
/*  This listener isn't working.  I don't know why at this time  - Lise Nov 11, 2014 */	
	public class CbxMakeListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox)e.getSource();
	        String selectedMake = (String)cb.getSelectedItem();
	        System.out.println("VehicleTabbedController - CbxMakeListener --> make selected " + selectedMake);
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
}

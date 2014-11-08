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

import controller.LogonController.SubmitListener;
import security.PasswordEncrypt;
import views.*;
import model.DbAccess;
import model.MakeObject;

public class VehicleTabbedController
{
	private TabbedVehicleFrame tabView;
	private DbAccess dbHandle;
	private MainController mController;
	private VehicleTabbedController vTController;
	public String make;
	
	public VehicleTabbedController(TabbedVehicleFrame tabFrame, DbAccess dbHandle, MainController mController)
	{
		this.tabView = tabFrame;
		this.dbHandle = dbHandle;
		this.mController = mController;
		this.vTController = this;
	}
	
	public void startTabbedView()
	{
	//	this.tabView.addSubmitButtonListener(new SubmitListener());
		System.out.println("VehicleTabbedController - startTabbedView");
	//	this.tabView.addCbxMakeListener(new CbxMakeListener());
	}
	
	public ArrayList<MakeObject> getMake()
	{
		System.out.println("VehicleTabbedController - getMake");
		ArrayList<MakeObject> makeList = this.dbHandle.getMake();
		
		return makeList;
	}
	
	public String[] getModel()
	{
		String[] model = null;
		return model;
	}
	
	public class CbxMakeListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
	//		JComboBox cb = (JComboBox)e.getSource();
	        String selectedMake = (String)tabView.cbxMake.getSelectedItem();
	        make = selectedMake;
	        
	        String[] models = dbHandle.getModel(make);
	        for (int i = 0 ; i < models.length ; i++ )
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

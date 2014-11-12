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
 *   VehicleTabbedStart.java
*/
package controller;

import javax.swing.JFrame;

import model.*;
import views.*;

public class VehicleTabbedStart
{
	private String userName;
	private TabbedVehicleFrame vehicleTabView;
	private DbAccess DbHandle;
	private VehicleTabbedController vController;
	private MainController mController;
	
	public VehicleTabbedStart(MainController mController, String userName)
	{	
		this.userName = userName;
		DbHandle = DbAccess.getInstance();
		this.mController = mController;
	//	this.vehicleTabView = new TabbedVehicleFrame();
		vController = new VehicleTabbedController(DbHandle, mController);
		vehicleTabView = new TabbedVehicleFrame(vController);
	
		vController.startTabbedView();
		createAndShowGUI();
	}
	
	private void createAndShowGUI()
	{
	    //Create and set up the window.
	    JFrame frame = new JFrame("Car-L-Marx");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    //Create and set up the content pane.
	    TabbedVehicleFrame vehicleTabView = new TabbedVehicleFrame(vController);
	    vehicleTabView.setOpaque(true); //content panes must be opaque
	    frame.setContentPane(vehicleTabView);

	    //Display the window.
	    frame.pack();
	    frame.setVisible(true);
	}
}

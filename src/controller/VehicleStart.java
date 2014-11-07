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
 *   VehicleStart.java
*/
package controller;

import java.util.ArrayList;

import javax.swing.JFrame;

import model.*;
import views.*;

public class VehicleStart
{
	private String user;
	private VehicleFrame vehicleView;
	private DbAccess DbHandle;
	private VehicleController vehicleController;
	ArrayList <VehiclesObject> vlist;
	
	
	public VehicleStart(String user)
	{
		this.user = user;
		
		vehicleView = new VehicleFrame();
		DbHandle = DbAccess.getInstance();
//		DbHandle.getUserVehicle("Lacemaker");
		vehicleController = new VehicleController(vehicleView, DbHandle, user);
		vlist=vehicleController.startVehicle();
		createAndShowGUI(vlist);
		//vehicleView.paintVehicleTablePanel(vehicleController);
		//vehicleView.setVisible(true);
		
	}

void createAndShowGUI(ArrayList <VehiclesObject> vlist) {
    //Create and set up the window.
    JFrame frame = new JFrame("SimpleTableDemo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Create and set up the content pane.
    VehicleTablePanel newContentPane = new VehicleTablePanel(vlist);
    newContentPane.setOpaque(true); //content panes must be opaque
    frame.setContentPane(newContentPane);

    //Display the window.
    frame.pack();
    frame.setVisible(true);
}
}
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private MainController mController;
	ArrayList<VehiclesObject> vlist;

	public VehicleStart(MainController mController, String user)
	{
		this.mController = mController;
		this.user = user;

		vehicleView = new VehicleFrame();
		DbHandle = DbAccess.getInstance();
		vehicleController = new VehicleController(vehicleView, DbHandle, user);
		vlist = vehicleController.startVehicle();
		createAndShowGUI(vlist);
		// vehicleView.paintVehicleTablePanel(vehicleController);
		// vehicleView.setVisible(true);

	}

	void createAndShowGUI(ArrayList<VehiclesObject> vlist)
	{
		// Create and set up the window.
		JFrame frame = new JFrame("Car-L-Marx");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		VehicleTablePanel newContentPane = new VehicleTablePanel(vlist);
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		newContentPane.addSubmitButtonListener(new SubmitListener());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public class SubmitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Vehicle Start -- SubmitListener");
			mController.vehicleTabbedFrame(user);
		}
	}
}

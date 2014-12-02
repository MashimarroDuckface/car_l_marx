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
 *   NewVehicleStart.java
*/
package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JFrame;

import views.NewVehicleFrame;
import views.NewVehiclePanel;
import views.NewVehicleTirePanel;
import views.TabbedVehicleFrame;
import model.DbAccess;

public class NewVehicleStart
{
	private MainController mController;
	private String userName;
	private DbAccess DbHandle;
	private NewVehicleController vController;
	private NewVehicleFrame newVehicleView;
	private JFrame frame;
	private NewVehiclePanel  newContentPane;
	private NewVehicleTirePanel  newTirePane;
	
	public NewVehicleStart(MainController mController, String userName)
	{
		this.mController = mController;
		this.userName = userName;
		DbHandle = DbAccess.getInstance();
		
		newVehicleView = new NewVehicleFrame();
		vController = new NewVehicleController(this, DbHandle, mController);
	
//		vController.startNewVehicleView();
		createAndShowGUI();
	}
	
	private void createAndShowGUI()
	{
	    //Create and set up the window.
	    frame = new JFrame("Car-L-Marx");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    //Create and set up the content pane.
	    newContentPane = new NewVehiclePanel(this.vController);
	    newContentPane.setSize(200, 500);
//	    newContentPane.setBounds(50, 50, 500, 500);
	    newContentPane.setOpaque(true);
	    frame.setContentPane(newContentPane);
	    newContentPane.setPreferredSize(new Dimension(400,500));
	    
//	    newContentPane.addSubmitButtonListener(newSubmitListener());

	    //Display the window.
	    frame.setSize(200, 500);
	    frame.pack();
	    frame.setVisible(true);
	}
	
	public class SubmitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			frame.setVisible(false); //you can't see me!
			frame.dispose(); //Destroy the JFrame object
	//		mController.vehicleTabbedFrame(user);
		}
	}

	public void nextTires()
	{
		newContentPane.setVisible(false);
		
	    //Create and set up the content pane.
	    newTirePane = new NewVehicleTirePanel(this.vController);
	    newTirePane.setSize(200, 500);
	    newTirePane.setOpaque(true);
	    frame.setContentPane(newTirePane);
	    newTirePane.setPreferredSize(new Dimension(400,500));
	}

	public void submit()
	{
		String nickName = newContentPane.txtNickName.getText();
		String make = (String) newContentPane.cbxMake.getSelectedItem();
		String model = (String) newContentPane.cbxModel.getSelectedItem();
		String color = (String) newContentPane.cbxcolor.getSelectedItem();
		String licensePlate = newContentPane.txtLicensePlate.getText();
		int mileage = Integer.parseInt(newContentPane.txtMileage.getText()); 
		
		String tireType = newTirePane.txtTireType.getText();
		Date selectedDate = (Date) newTirePane.datePickerOn.getModel()
				.getValue();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		String studsOnDate = sdf.format(selectedDate);
		
		selectedDate = (Date) newTirePane.datePickerOff.getModel()
				.getValue();
		sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		String studsOffDate = sdf.format(selectedDate);
		
		int makeId = this.DbHandle.getMakeIdFromMake(make);
		int modelId = this.DbHandle.getModelIdFromModel(model);
		int colorId = this.DbHandle.getColorId(color);
		
		this.DbHandle.insertNewVehicle(this.userName, nickName, makeId, modelId, colorId, licensePlate, mileage);
		int vehicleId = this.DbHandle.getVehicleId(this.userName, licensePlate);
		this.DbHandle.insertNewTire(vehicleId, studsOnDate, studsOffDate, tireType);
		
		//  TODO close new vehicle window, reopen table window
		
		frame.setVisible(false); //you can't see me!
		frame.dispose(); //Destroy the JFrame object
//		mController.vehicleTabbedFrame(user);
		mController.goodUser(userName);
		
		
	}
}

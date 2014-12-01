package views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.VehicleController;
import model.VehiclesObject;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;

public class VehicleTablePanel extends JPanel {
    private boolean DEBUG = false;
    public JButton btnSubmit;
    public JButton btnNewVehicle;
    ArrayList<VehiclesObject> vehiclelist;
    VehicleController vController;
//    Object[][] data;

    public VehicleTablePanel(ArrayList <VehiclesObject> vlist) {
    	setBackground(new Color(255, 255, 240));
    	this.setBounds(50, 50, 562, 260);
        
    //    Object[][] data = null;
     
        String[] columnNames = {"Color",
                                "License Plate",
                                "Vehicle ID",
                                "Make",
                                "Model",
                                "Mileage"};

//        vehiclelist = vlist;
//        System.out.println(vehiclelist);
//		int i=0;
//		for (VehiclesObject v:vehiclelist)
//		{
//			data[i][0]=v.color;
//			data[i][1]=v.licensePlate;
//			data[i][2]=v.idvehicle;
//			data[i][3]=v.make;
//			data[i][4]=v.model;
//			data[i][5]=v.mileage;
//			i++;
//			System.out.println(v.color + v.licensePlate + v.idvehicle + v.make + v.model + v.mileage);
//		}
		
		Object[][] data = {{"Blue", "MMR 234", "3", "GM", "Truck", "45602"},
				{"White", "SWV 123", "1", "Toyota", "Camry", "43100"},
				{"Red", "FTT 123", "2", "Toyota", "Corolla", "34500"},
				{"Black", "TR9472", "4", "Tesla", "S", "25300"}
		};
		

        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
		
		btnNewVehicle = new JButton("New Vehicle");
		btnNewVehicle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
		btnSubmit = new JButton("Go to next screen");
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(scrollPane);
		add(btnNewVehicle);
		add(btnSubmit);
    }

    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
   /*void createAndShowGUI(VehicleController vController) {
        //Create and set up the window.
        JFrame frame = new JFrame("SimpleTableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        VehicleTablePanel newContentPane = new VehicleTablePanel(vList);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }*/

	public void addSubmitButtonListener(ActionListener listenerForSubmitButton) 
	{
		this.btnSubmit.addActionListener(listenerForSubmitButton);
	}
	
	public void newVehicleButtonListener(ActionListener listenerForNewVehicleButton) 
	{
		this.btnNewVehicle.addActionListener(listenerForNewVehicleButton);
	}
}
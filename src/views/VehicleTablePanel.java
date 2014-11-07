package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.VehicleController;
import model.VehiclesObject;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class VehicleTablePanel extends JPanel {
    private boolean DEBUG = false;
    ArrayList<VehiclesObject> vehiclelist;
    VehicleController vController;

    public VehicleTablePanel(VehicleController vController) {
        super(new GridLayout(1,0));
        this.vController=vController;

        String[] columnNames = {"Color",
                                "License Plate",
                                "Vehicle ID",
                                "Make",
                                "Model",
                                "Mileage"};

        vehiclelist = this.vController.getVehicleData();
        System.out.println(vehiclelist);
		Object[][] data=null;
		int i=0;
		for (VehiclesObject v:vehiclelist)
		{
			data[i][0]=v.color;
			data[i][1]=v.licensePlate;
			data[i][2]=v.idvehicle;
			data[i][3]=v.make;
			data[i][4]=v.model;
			data[i][5]=v.mileage;
			i++;
			System.out.println(v.color + v.licensePlate + v.idvehicle + v.make + v.model + v.mileage);
		}

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

        //Add the scroll pane to this panel.
        add(scrollPane);
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
    void createAndShowGUI(VehicleController vController) {
        //Create and set up the window.
        JFrame frame = new JFrame("SimpleTableDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        VehicleTablePanel newContentPane = new VehicleTablePanel(vController);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


}
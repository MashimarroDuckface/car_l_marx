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
    	ArrayList<Object[]> data = new ArrayList<Object[]>();
    	int i=0;
    	for(VehiclesObject v:vlist){
    		data.add(new Object[]{v.idvehicle, v.make, v.model, v.color, v.licensePlate, v.mileage});
    	}
    	
        String[] columnNames = {"Vehicle ID",
                                "Make",
                                "Model",
                                "Color",
                                "License Plate",
                                "Mileage"};
        

        
        JTable table = new JTable(new VehicleTableModel(data, columnNames));

	
        table.setPreferredScrollableViewportSize(new Dimension(500, 500));
        table.setFillsViewportHeight(true);

        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
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

	public void addSubmitButtonListener(ActionListener listenerForSubmitButton) 
	{
		this.btnSubmit.addActionListener(listenerForSubmitButton);
	}
	
	public void newVehicleButtonListener(ActionListener listenerForNewVehicleButton) 
	{
		this.btnNewVehicle.addActionListener(listenerForNewVehicleButton);
	}
}
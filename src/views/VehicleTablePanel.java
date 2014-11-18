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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;

public class VehicleTablePanel extends JPanel {
    private boolean DEBUG = false;
    public JButton btnEdit;
    public JButton btnAddNewCar;
    public JButton btnSubmit;
    ArrayList<VehiclesObject> vehiclelist;
    VehicleController vController;
//    Object[][] data;

    public VehicleTablePanel(ArrayList <VehiclesObject> vlist) {
        
    	ArrayList<Object[]> data = new ArrayList<Object[]>();
    	int i=0;
    	for(VehiclesObject v:vlist){
    		data.add(new Object[]{v.idvehicle, v.make, v.model, v.color, v.licensePlate, v.mileage});
    	}
    	
        String[] columnNames = {"Color",
                                "License Plate",
                                "Vehicle ID",
                                "Make",
                                "Model",
                                "Mileage"};
        

        
        JTable table = new JTable(new VehicleTableModel(data, columnNames));

	
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                }
            });
        }

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        
        btnSubmit = new JButton("Go to next screen");
		
		btnAddNewCar = new JButton("Add New Car");
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(scrollPane);
		add(btnSubmit);
		add(btnAddNewCar);
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
}
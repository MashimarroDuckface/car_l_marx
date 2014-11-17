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

public class VehicleTablePanel extends JPanel {
    private boolean DEBUG = false;
    public JButton btnSubmit;
    ArrayList<VehiclesObject> vehiclelist;
    VehicleController vController;
//    Object[][] data;

    public VehicleTablePanel(ArrayList <VehiclesObject> vlist) {
        
    	VehicleTableModel data=new VehicleTableModel();
    	
        String[] columnNames = {"Color",
                                "License Plate",
                                "Vehicle ID",
                                "Make",
                                "Model",
                                "Mileage"};
        

        
        JTable table = new JTable(data);
        int i=0;
        for(VehiclesObject v:vlist){
        data.setValueAt(v.idvehicle, i, 0);
    	data.setValueAt(v.make, i, 1);
    	data.setValueAt(v.model, i, 2);
    	data.setValueAt(v.color, i, 3);
    	data.setValueAt(v.licensePlate, i, 4);
    	data.setValueAt(v.mileage, i, 5);
    	i++;
        }

	
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
        
		btnSubmit = new JButton("Go to next screen");
		btnSubmit.setBounds(10, 220, 95, 29);
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
}
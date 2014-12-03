package views;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.VehicleController;
import model.VehiclesObject;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import java.awt.Component;

public class VehicleTablePanel extends JPanel {
	private boolean DEBUG = false;
	public JButton btnNewVehicle;
	ArrayList<VehiclesObject> vehiclelist;
	VehicleController vController;

	public VehicleTablePanel(ArrayList<VehiclesObject> vlist) {
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		int i = 0;
		for (VehiclesObject v : vlist) {
			data.add(new Object[] { v.idvehicle, v.nickName, v.make, v.model,
					v.color, v.licensePlate, v.mileage });
		}

		String[] columnNames = { "Vehicle ID", "Nickname", "Make", "Model",
				"Color", "License Plate", "Mileage" };

		JTable table = new JTable(new VehicleTableModel(data, columnNames));

		table.setPreferredScrollableViewportSize(new Dimension(500, 500));
		table.setFillsViewportHeight(true);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >1) {
					try {
						final JTable target = (JTable) e.getSource();
						final int row = target.getSelectedRow();
						final int column = 0;

						final int value = (Integer) target.getValueAt(row,
								column);
						System.out.println(value);
					} catch (Exception exception) {
						System.out.println("Area outside table.");
					}
				}
			}
		});

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

		btnNewVehicle = new JButton("New Vehicle");
		btnNewVehicle.setAlignmentX(Component.CENTER_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(scrollPane);
		add(btnNewVehicle);
	}

	private void printDebugData(JTable table) {
		int numRows = table.getRowCount();
		int numCols = table.getColumnCount();
		javax.swing.table.TableModel model = table.getModel();

		System.out.println("Value of data: ");
		for (int i = 0; i < numRows; i++) {
			System.out.print("    row " + i + ":");
			for (int j = 0; j < numCols; j++) {
				System.out.print("  " + model.getValueAt(i, j));
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}

	public void newVehicleButtonListener(
			ActionListener listenerForNewVehicleButton) {
		this.btnNewVehicle.addActionListener(listenerForNewVehicleButton);
	}
}
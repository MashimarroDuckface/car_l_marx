/*   Car-L-Marx
 *
 *   Oct 26, 2014  
 *   CS 320 Fall 2014
 *
 *		Michael Allen-Bond
 *		Lise Driggers
 *		Jesse Pomerenk
 *
 *		views
 *
 *   VehicleFrame.java
*/
package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import controller.*;

public class VehicleFrame extends JFrame
{

	private JPanel contentPane;
	private VehicleTablePanel vTable;
	private VehicleController vController;

	/**
	 * Create the frame.
	 */
	public VehicleFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
	}

	public void paintVehicleTablePanel(VehicleController vController)
	{
		vTable = new VehicleTablePanel(vController);
		/*vTable.setBackground(new Color(255, 255, 240));
		vTable.setBounds(0, 0, 450, 425);
		contentPane.add(vTable);
		vTable.setLayout(null);*/
		vTable.createAndShowGUI(vController);
		
	}
	

}

/*   Car-L-Marx
 *
 *   Nov 5, 2014  
 *   CS 320 Fall 2014
 *
 *		Michael Allen-Bond
 *		Lise Driggers
 *		Jesse Pomerenk
 *
 *		views
 *
 *   TabbedVehicleFrame.java
 */
package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.MutableComboBoxModel;

import controller.VehicleTabbedController;
import model.*;

public class TabbedVehicleFrame extends JPanel
{

	private JPanel panelSummary;
	private JPanel panelEdit;
	private JPanel panelMaint;
	private JPanel panelTires;
	private VehicleTabbedController vTController;
	public JComboBox cbxMake;
	public MutableComboBoxModel cbxModel;

	/**
	 * Create the panel.
	 */
	public TabbedVehicleFrame(VehicleTabbedController vTController)
	{
		this.vTController = vTController;
		JTabbedPane tabbedPane = new JTabbedPane();

		JComponent panel1 = makeTextPanelSummary("");
		tabbedPane.addTab("Summary", new ImageIcon(TabbedVehicleFrame.class.getResource("/images/car.png")), panel1, "");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = makeTextPanelEditCar("Panel #2");
		tabbedPane.addTab("Edit Car", new ImageIcon(TabbedVehicleFrame.class.getResource("/images/car-purple-icon.png")), panel2, "");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		JComponent panel3 = makeTextMaintenance("Panel #3");
		tabbedPane.addTab("Maintenance", new ImageIcon(TabbedVehicleFrame.class.getResource("/images/wrench.png")), panel3, "");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		JComponent panel4 = makeTextPanelTires("");
		panel4.setPreferredSize(new Dimension(400, 260));
		tabbedPane.addTab("Tires", new ImageIcon(TabbedVehicleFrame.class.getResource("/images/tire.png")), panel4, "");
		tabbedPane.setEnabledAt(3, true);
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

		// Add the tabbed pane to this panel.
		add(tabbedPane);

		// The following line enables to use scrolling tabs.
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	protected JComponent makeTextPanelSummary(String text)
	{
		panelSummary = new JPanel(false);
		panelSummary.setBackground(new Color(255, 255, 240));

		panelSummary.setLayout(null);
		{
			JLabel lblSummary = new JLabel("Vehicle Name, Info Summary");
			lblSummary.setBounds(21, 20, 225, 19);
			lblSummary.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			lblSummary.setBackground(new Color(255, 255, 240));
			panelSummary.add(lblSummary);
		}
		{
			JLabel lblToDo = new JLabel("To-Do List");
			lblToDo.setBounds(21, 80, 225, 19);
			lblToDo.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			lblToDo.setBackground(new Color(255, 255, 240));
			panelSummary.add(lblToDo);
		}
		{
			JLabel lblOptional = new JLabel("Optional Information");
			lblOptional.setBounds(21, 140, 225, 19);
			lblOptional.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			lblOptional.setBackground(new Color(255, 255, 240));
			panelSummary.add(lblOptional);
		}

		return panelSummary;
	}

	protected JComponent makeTextPanelEditCar(String text)
	{
		panelEdit = new JPanel(false);
		panelEdit.setBackground(new Color(255, 255, 240));

		panelEdit.setLayout(null);
  //  	String[] makeStrings = { "Select Make", "Honda", "Accord", "Ford", "Chevrolet", "Toyota", "Tesla" };
    	{
    		JLabel lblMake = new JLabel("Make");
    		lblMake.setBounds(36, 30, 61, 16);
    		panelEdit.add(lblMake);
    	}
    	ArrayList<MakeObject> makes = vTController.getMake();
    	Object[] makeArray = makes.toArray();
    	{
            cbxMake = new JComboBox(makeArray);
            cbxMake.setBounds(36, 50, 178, 27);
            panelEdit.add(cbxMake);
    	}
    	{
    		JLabel lblModel = new JLabel("Model");
    		lblModel.setBounds(36, 80, 61, 16);
    		panelEdit.add(lblModel);
    	}
    	String[] modelStrings = { "select model" };
//    	{
//    		cbxModel =  (MutableComboBoxModel) cbxModel;  
//  //  		((Component) cbxModel).setBounds(36, 100, 178, 27);
//    		panelEdit.add((Component) cbxModel);
//    	}
    	{
    		JLabel lblModel = new JLabel("Mileage");
    		lblModel.setBounds(36, 130, 61, 16);
    		panelEdit.add(lblModel);
    	}
    	{
            JTextField txtMileage = new JTextField();
            txtMileage.setBounds(36, 150, 134, 28);
            panelEdit.add(txtMileage);
            txtMileage.setColumns(10);
    	}
    	{
    		JLabel lblOilChange = new JLabel("Last Oil Change");
    		lblOilChange.setBounds(36, 180, 134, 16);
    		panelEdit.add(lblOilChange);
    	}
    	{
    		JTextField txtOilChange = new JTextField();
    		txtOilChange.setBounds(36, 200, 134, 28);
    		panelEdit.add(txtOilChange);
    		txtOilChange.setColumns(10);
    	}

		return panelEdit;
	}

	protected JComponent makeTextMaintenance(String text)
	{
		panelMaint = new JPanel(false);
		panelMaint.setBackground(new Color(255, 255, 240));

		panelMaint.setLayout(null);
		{
			JLabel lblSummary = new JLabel("Vehicle Name, Info Summary");
			lblSummary.setBounds(21, 21, 225, 19);
			lblSummary.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			lblSummary.setBackground(new Color(255, 255, 240));
			panelMaint.add(lblSummary);
		}

		return panelMaint;
	}

	protected JComponent makeTextPanelTires(String text)
	{
		panelTires = new JPanel(false);
		panelTires.setBackground(new Color(255, 255, 240));

		panelTires.setLayout(null);
		{
			JLabel lblSummary = new JLabel("Tires tab");
			lblSummary.setBounds(25, 16, 225, 19);
			lblSummary.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			lblSummary.setBackground(new Color(255, 255, 240));
			panelTires.add(lblSummary);
		}

		return panelTires;
	}
	
	public void addCbxMakeListener(ActionListener listenerForMakeCbx) 
	{
		this.cbxMake.addActionListener(listenerForMakeCbx);
	}

}

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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import controller.VehicleTabbedController;
import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class TabbedVehicleFrame extends JPanel
{

	private JPanel panelSummary;
	private JPanel panelEdit;
	private JPanel panelMaint;
	private JPanel panelTires;
	private VehicleTabbedController vTController;
	public JComboBox cbxMake;
	public JComboBox cbxModel;
	private ArrayList<ModelObject> modelList;
	public JTextField txtCurrentMileage;
	public JTextField txtMileage;
	private DefaultComboBoxModel model;

	/**
	 * Create the panel.
	 */
	public TabbedVehicleFrame(VehicleTabbedController vTController)
	// public TabbedVehicleFrame()
	{
		this.vTController = vTController;
		this.vTController.getTabViewObject(this);
		JTabbedPane tabbedPane = new JTabbedPane();

		JComponent panel1 = makeTextPanelSummary("");
		tabbedPane.addTab(
				"Summary",
				new ImageIcon(TabbedVehicleFrame.class
						.getResource("/images/car.png")), panel1, "");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = makeTextPanelEditCar("Panel #2");
		tabbedPane.addTab(
				"Edit Car",
				new ImageIcon(TabbedVehicleFrame.class
						.getResource("/images/car-purple-icon.png")), panel2,
				"");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		JComponent panel3 = makeTextMaintenance("Panel #3");
		tabbedPane.addTab(
				"Maintenance",
				new ImageIcon(TabbedVehicleFrame.class
						.getResource("/images/wrench.png")), panel3, "");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		JComponent panel4 = makeTextPanelTires("");
		panel4.setPreferredSize(new Dimension(400, 500));
		tabbedPane.addTab(
				"Tires",
				new ImageIcon(TabbedVehicleFrame.class
						.getResource("/images/tire.png")), panel4, "");
		tabbedPane.setEnabledAt(3, true);
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

		// Add the tabbed pane to this panel.
		add(tabbedPane);

		// The following line enables to use scrolling tabs.
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	public void setController(VehicleTabbedController vTController)
	{
		this.vTController = vTController;
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
		{
			JLabel lblMake = new JLabel("Make");
			lblMake.setBounds(36, 30, 61, 16);
			panelEdit.add(lblMake);
		}
		/* Get the values for the make combo box from the db */
		ArrayList<MakeObject> makes = vTController.getMake();
		String makeArray[] = new String[makes.size()];
		int i = 0;
		for (MakeObject m : makes)
		{
			makeArray[i++] = m.make;
		}
		{
			cbxMake = new JComboBox(makeArray);
			cbxMake.setSelectedItem(vTController.getMakeString());
			cbxMake.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JComboBox cb = (JComboBox) e.getSource();
					String selectedMake = (String) cb.getSelectedItem();
					modelList = vTController.getModel(selectedMake); //  get models based on the make selected
					
					model = (DefaultComboBoxModel) cbxModel.getModel();
					model.removeAllElements();
					model.addElement("-select model");
					for (ModelObject m : modelList)
					{
						model.addElement(m.model);
					}
					cbxModel.setModel(model);
				}
			});
			cbxMake.setBounds(36, 50, 178, 27);
			panelEdit.add(cbxMake);
		}
		{
			JLabel lblModel = new JLabel("Model");
			lblModel.setBounds(36, 80, 61, 16);
			panelEdit.add(lblModel);
		}

		String[] modelStrings =
		{ vTController.getModelString() };
		{
			cbxModel = new JComboBox(modelStrings);
			cbxModel.addPropertyChangeListener(new PropertyChangeListener()
			{
				public void propertyChange(PropertyChangeEvent arg0)
				{
					vTController.updateMakeAndModel(cbxModel.getSelectedItem().toString(),cbxMake.getSelectedItem().toString());
				}
			});
			cbxModel.setBounds(36, 100, 178, 27);
			/* Model stuff */
			modelList = vTController.getModel(vTController.getMakeString()); // model list for combobox model
																				
			model = (DefaultComboBoxModel) cbxModel.getModel();
			model.removeAllElements();
			model.addElement("-select model");
			for (ModelObject m : modelList)
			{
				model.addElement(m.model);
			}
			cbxModel.setSelectedItem(vTController.getModelString());
			cbxModel.setModel(model);
			panelEdit.add(cbxModel);
		}

		{
			JLabel lblModel = new JLabel("Mileage");
			lblModel.setBounds(36, 130, 61, 16);
			panelEdit.add(lblModel);
		}

		JLabel lblNewLabel = new JLabel("Additonal Mileage");
		lblNewLabel.setBounds(240, 130, 134, 16);
		panelEdit.add(lblNewLabel);

		txtCurrentMileage = new JTextField();
		txtCurrentMileage.setBackground(new Color(255, 255, 240));
		txtCurrentMileage.setEditable(false);
		txtCurrentMileage.setBounds(36, 149, 134, 28);
		txtCurrentMileage.setText(this.vTController.getCurrentMileageString());
		panelEdit.add(txtCurrentMileage);
		txtCurrentMileage.setColumns(10);
		// tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		{
			txtMileage = new JFormattedTextField(NumberFormat.getInstance());
			txtMileage.addFocusListener(new FocusAdapter()
			{
				@Override
				public void focusLost(FocusEvent arg0)
				{
					vTController.updateMileage(txtMileage.getText());
					txtCurrentMileage.setText(vTController
							.getCurrentMileageString());
					txtMileage.setText("");

	//				System.out.println("Focus Lost event");
				}
			});
			txtMileage.setBounds(240, 149, 134, 28);
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

	public void addTxtMileageListener(
			controller.VehicleTabbedController.addTxtMileageListener addTxtMileageListener)
	{
		this.txtMileage.addFocusListener(addTxtMileageListener);
		// txtMileage.addFocusListener(new FocusAdapter() {
		// @Override
		// public void focusLost(FocusEvent arg0) {
		// }
		// });

//		System.out.println("TabbedVehicleFrame addTxtMileageListener");

	}

	public void addCbxMakeListener(ActionListener listenerForMakeCbx)
	{
		this.cbxMake.addActionListener(listenerForMakeCbx);
	}
}

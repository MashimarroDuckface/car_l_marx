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
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

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

import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JTextArea;
import javax.swing.JButton;

public class TabbedVehicleFrame extends JPanel
{

	private JPanel panelSummary;
	private JPanel panelEdit;
	private JPanel panelMaint;
	private JPanel panelTires;
	private JTextArea textSummary;
	private VehicleTabbedController vTController;
	public JComboBox<String> cbxMake;
	public JComboBox<String> cbxModel;
	private ArrayList<ModelObject> modelList;
	public JTextField txtCurrentMileage;
	public JTextField txtMileage;
	private DefaultComboBoxModel model;

	private String nickName;
	public JLabel lblNickName;
	private JTextField txtTireType;

	private JDatePickerImpl datePickerOn;
	private JDatePickerImpl datePickerOff;
	private JLabel lblCarNickName;
	private JTextField txtNickName;
	private JLabel lblLicensePlate;
	private JTextField txtLicensePlate;
	private final JButton btnReturn = new JButton("Return to Vehicle List");

	/**
	 * Create the panel.
	 */
	public TabbedVehicleFrame(final VehicleTabbedController vTController)
	// public TabbedVehicleFrame()
	{
		this.vTController = vTController;
		this.vTController.getTabViewObject(this);

		this.nickName = vTController.getNickName();

		JTabbedPane tabbedPane = new JTabbedPane();

		JComponent panel1 = makeTextPanelSummary("");
		tabbedPane.addTab(
				"Summary",
				new ImageIcon(TabbedVehicleFrame.class
						.getResource("/images/car.png")), panel1, "");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vTController.returnToTable();
			}
		});
		btnReturn.setBounds(21, 450, 189, 29);
		panelSummary.add(btnReturn);
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
			createLblNickName(panelSummary);
		}
		{
			JLabel lblSummary = new JLabel("Vehicle Name, Info Summary");
			lblSummary.setBounds(21, 49, 225, 19);
			lblSummary.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			lblSummary.setBackground(new Color(255, 255, 240));
			panelSummary.add(lblSummary);
		}
		{
			String someText = " some summary stuff";
			textSummary = new JTextArea(someText, 5, 10);
			textSummary.setSize(285, 86);
			textSummary.setLocation(21, 84);
			textSummary.setPreferredSize(new Dimension(100, 100));
			panelSummary.add(textSummary);
		}
		{
			JLabel lblToDo = new JLabel("To-Do List");
			lblToDo.setBounds(21, 182, 225, 19);
			lblToDo.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			lblToDo.setBackground(new Color(255, 255, 240));
			panelSummary.add(lblToDo);
		}
		{
			String someText = "Time to schedule an oil change   \n\nWinter studs are now legal";
			JTextArea txtToDo = new JTextArea(someText, 5, 10);
			txtToDo.setSize(285, 86);
			txtToDo.setLocation(21, 213);
			txtToDo.setPreferredSize(new Dimension(100, 100));
			panelSummary.add(txtToDo);
		}
		{
			JLabel lblOptional = new JLabel("Optional Information");
			lblOptional.setBounds(21, 331, 225, 19);
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
		{
//			createLblNickName(panelEdit);
		}
		/*
		 * { this isn't working! Very Bad! Lise Nov 17, 2014 //
		 * panelSummary.remove(lblNickName); // createLblNickName(panelSummary);
		 * // panelEdit.remove(lblNickName); // createLblNickName(panelEdit); //
		 * panelMaint.remove(lblNickName); // createLblNickName(panelMaint); //
		 * panelTires.remove(lblNickName); // createLblNickName(panelTires); }
		 */
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
					modelList = vTController.getModel(selectedMake); // get models based on the make selected
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
			cbxMake.setBounds(36, 50, 170, 27);
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
					vTController.updateMakeAndModel(cbxModel.getSelectedItem()
							.toString(), cbxMake.getSelectedItem().toString());
				}
			});
			cbxModel.setBounds(36, 108, 170, 27);
			/* Model stuff */
			modelList = vTController.getModel(vTController.getMakeString()); // model
																				// list
																				// for
																				// combobox
																				// model

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
			lblCarNickName = new JLabel("Car Nick Name");
			lblCarNickName.setBounds(240, 30, 100, 16);
			panelEdit.add(lblCarNickName);
		}
		{
			txtNickName = new JTextField();
			txtNickName.setText(vTController.getNickName());
			txtNickName.addFocusListener(new FocusAdapter()
			{
				@Override
				public void focusLost(FocusEvent arg0)
				{
					vTController.updateNickName(txtNickName.getText());
					nickName = txtNickName.getText();
					lblNickName.setText("");
					createLblNickName(panelEdit);
				}
			});
			txtNickName.setBounds(230, 50, 134, 28);
			panelEdit.add(txtNickName);
			txtNickName.setColumns(10);
		}
		{
			JLabel lblColor = new JLabel("Color");
			lblColor.setBounds(240, 80, 61, 16);
			panelEdit.add(lblColor);
		}
		ArrayList<String> colors = vTController.getColor();
		String colorArray[] = new String[colors.size()];
		int k = 0;
		for (String c : colors)
		{
			colorArray[k++] = c;
		}
		{
			JComboBox cbxColor = new JComboBox(colorArray);
			cbxColor.setSelectedItem(vTController.getColorString());
			cbxColor.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JComboBox cb = (JComboBox) e.getSource();
					String selectedColor = (String) cb.getSelectedItem();
					vTController.updateColor(selectedColor);
				}
			});
			cbxColor.setBounds(240, 100, 124, 27);
			panelEdit.add(cbxColor);
		}
		{
			lblLicensePlate = new JLabel("License Plate");
			lblLicensePlate.setBounds(36, 139, 150, 16);
			panelEdit.add(lblLicensePlate);
		}
		{
			txtLicensePlate = new JTextField();
			txtLicensePlate.setText(vTController.getLicensePlate());
			txtLicensePlate.addFocusListener(new FocusAdapter()
			{
				@Override
				public void focusLost(FocusEvent arg0)
				{
					System.out.println("lost focus on license plate field");
					vTController.updateLicensePlate(txtLicensePlate.getText());
				}
			});
			txtLicensePlate.setBounds(36, 161, 134, 28);
			panelEdit.add(txtLicensePlate);
			txtLicensePlate.setColumns(10);
		}
		{
			JLabel lblModel = new JLabel("Mileage");
			lblModel.setBounds(36, 220, 61, 16);
			panelEdit.add(lblModel);
		}

		JLabel lblNewLabel = new JLabel("Additonal Mileage");
		lblNewLabel.setBounds(240, 220, 134, 16);
		panelEdit.add(lblNewLabel);

		txtCurrentMileage = new JTextField();
		txtCurrentMileage.setBackground(new Color(255, 255, 240));
		txtCurrentMileage.setEditable(false);
		txtCurrentMileage.setBounds(36, 250, 134, 28);
		txtCurrentMileage.setText(this.vTController.getCurrentMileageString());
		panelEdit.add(txtCurrentMileage);
		txtCurrentMileage.setColumns(10);
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
				}
			});
			txtMileage.setText("");
			txtMileage.setBounds(230, 250, 134, 28);
			panelEdit.add(txtMileage);
			txtMileage.setColumns(10);
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
			lblSummary.setBounds(19, 76, 225, 19);
			lblSummary.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			lblSummary.setBackground(new Color(255, 255, 240));
			panelMaint.add(lblSummary);
		}
		{
	//		createLblNickName(panelMaint);
		}

		return panelMaint;
	}

	protected JComponent makeTextPanelTires(String text)
	{
		panelTires = new JPanel(false);
		panelTires.setBackground(new Color(255, 255, 240));

		panelTires.setLayout(null);
		{
//			createLblNickName(panelTires);
		}
		{
			JLabel lblTIreType = new JLabel("Tire Type");
			lblTIreType.setBounds(22, 68, 225, 19);
			lblTIreType.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			lblTIreType.setBackground(new Color(255, 255, 240));
			panelTires.add(lblTIreType);
		}
		{
			txtTireType = new JTextField();
			txtTireType.setText(vTController.getTireType());
			txtTireType.addFocusListener(new FocusAdapter()
			{
				@Override
				public void focusLost(FocusEvent e)
				{
					vTController.updateTIreType(txtTireType.getText());

				}
			});
			txtTireType.setBackground(Color.WHITE);
			txtTireType.setBounds(22, 102, 134, 28);
			panelTires.add(txtTireType);
			txtTireType.setColumns(10);
		}
		{ // Calendar
			/*
			 * this stuff is for the new vehicle // int month =
			 * Calendar.getInstance().get(Calendar.MONTH); // int day =
			 * Calendar.getInstance().get(Calendar.DAY_OF_MONTH); // int year =
			 * Calendar.getInstance().get(Calendar.YEAR); // // UtilDateModel
			 * model = new UtilDateModel(); // model.setDate(year, month, day);
			 * // model.setSelected(true);
			 */
			int month = vTController.getStudsOnMonth();
			int day = vTController.getStudsOnDay();
			int year = vTController.getStudsOnYear();

			UtilDateModel model = new UtilDateModel();
			model.setDate(year, month, day);
			model.setSelected(true);

			Properties p = new Properties();
			p.put("text.today", "Today");
			p.put("text.month", "Month");
			p.put("text.year", "Year");

			JDatePanelImpl studsOnDate = new JDatePanelImpl(model, p);
			datePickerOn = new JDatePickerImpl(studsOnDate,
					new DateLabelFormatter());
			datePickerOn.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					Date selectedDate = (Date) datePickerOn.getModel()
							.getValue();
		//			System.out.println("Selected Date ofn" + selectedDate);
					java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
							"yyyy-MM-dd");

					String currentDate = sdf.format(selectedDate);
		//			System.out.println("Current Date " + currentDate);

					vTController.updateStudsOnDate(currentDate);
				}
			});
			datePickerOn.setBounds(22, 175, 200, 30);
			panelTires.add(datePickerOn);
		}
		{
			JLabel lblStudsOn = new JLabel("Date Studs can be installed");
			lblStudsOn.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			lblStudsOn.setBounds(22, 147, 257, 16);
			panelTires.add(lblStudsOn);
		}
		{
			JLabel lblStudsOff = new JLabel("Date Studs need to be removed by");
			lblStudsOff.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			lblStudsOff.setBounds(22, 256, 257, 16);
			panelTires.add(lblStudsOff);
		}
		{
			int month = vTController.getStudsOffMonth();
			int day = vTController.getStudsOffDay();
			int year = vTController.getStudsOffYear();

			UtilDateModel model = new UtilDateModel();
			model.setDate(year, month, day);
			model.setSelected(true);

			Properties p = new Properties();
			p.put("text.today", "Today");
			p.put("text.month", "Month");
			p.put("text.year", "Year");

			JDatePanelImpl studsOffDate = new JDatePanelImpl(model, p);
			datePickerOff = new JDatePickerImpl(studsOffDate,
					new DateLabelFormatter());
			datePickerOff.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					Date selectedDate = (Date) datePickerOff.getModel()
							.getValue();
					System.out.println("Selected Date  on" + selectedDate);
					java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
							"yyyy-MM-dd");

					String currentDate = sdf.format(selectedDate);
					System.out.println("Current Date " + currentDate);

					vTController.updateStudsOffDate(currentDate);
				}
			});
			datePickerOff.setBounds(22, 284, 200, 30);
			panelTires.add(datePickerOff);
		}

		return panelTires;
	}

	public void addTxtMileageListener(
			controller.VehicleTabbedController.addTxtMileageListener addTxtMileageListener)
	{
		this.txtMileage.addFocusListener(addTxtMileageListener);
	}

	private void createLblNickName(JPanel currentPanel)
	{
		 if (this.lblNickName != null)
		 {
		 currentPanel.remove(lblNickName);
		 }
		this.lblNickName = new JLabel("");
		lblNickName.setText(this.nickName);
		lblNickName.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		this.lblNickName.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNickName.setIcon(null);
		this.lblNickName.setBounds(36, 6, 320, 16);
		currentPanel.add(this.lblNickName);
	}

	public void addCbxMakeListener(ActionListener listenerForMakeCbx)
	{
		this.cbxMake.addActionListener(listenerForMakeCbx);
	}
}

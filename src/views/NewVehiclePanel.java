/*   Car-L-Marx
 *
 *   Nov 30, 2014  
 *   CS 320 Fall 2014
 *
 *		Michael Allen-Bond
 *		Lise Driggers
 *		Jesse Pomerenk
 *
 *		views
 *
 *   NewVehiclePanel.java
*/
package views;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import controller.NewVehicleController;
import model.MakeObject;
import model.ModelObject;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;

public class NewVehiclePanel extends JPanel
{
	public JTextField txtNickName;
	private NewVehicleController vController;
	public JComboBox cbxMake;
	public JComboBox cbxcolor;
	public JComboBox<String> cbxModel;
	private ArrayList<ModelObject> modelList;
	public DefaultComboBoxModel model;
	public JTextField txtLicensePlate;
	public JTextField txtMileage;
	public JLabel lblMileage;

	/**
	 * Create the panel.
	 */
	public NewVehiclePanel(final NewVehicleController vController)
	{
		this.vController = vController;
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBackground(new Color(255, 255, 240));
		setLayout(null);
		
		JLabel lblNickName = new JLabel("Vehicle Nick Name");
		lblNickName.setBounds(19, 20, 139, 16);
		add(lblNickName);
		
		txtNickName = new JTextField();
		txtNickName.setBounds(19, 50, 134, 28);
		add(txtNickName);
		txtNickName.setColumns(10);
		
		JLabel lblMake = new JLabel("Make");
		lblMake.setBounds(19, 107, 61, 16);
		add(lblMake);
		
		/* Get the values for the make combo box from the db */
		ArrayList<MakeObject> makes = vController.getMake();
		String makeArray[] = new String[makes.size()];
		int i = 0;
		for (MakeObject m : makes)
		{
			makeArray[i++] = m.make;
		}
		cbxMake = new JComboBox(makeArray);
		cbxMake.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JComboBox cb = (JComboBox) e.getSource();
				String selectedMake = (String) cb.getSelectedItem();
				modelList = vController.getModel(selectedMake); // get

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
		cbxMake.setBounds(19, 135, 134, 27);
		add(cbxMake);
		
		JLabel lnlModel = new JLabel("Model");
		lnlModel.setBounds(19, 188, 61, 16);
		add(lnlModel);
		
		String[] modelStrings =
			{ "- Selected Model" };
		cbxModel = new JComboBox(modelStrings);
		cbxModel.setBounds(19, 216, 139, 27);
		add(cbxModel);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(19, 275, 61, 16);
		add(lblColor);
		
		ArrayList<String> colors = vController.getColor();
		String colorArray[] = new String[colors.size()];
		int k = 0;
		for (String c : colors)
		{
			colorArray[k++] = c;
		}
		cbxcolor = new JComboBox(colorArray);
		cbxcolor.setBounds(19, 308, 139, 27);
		add(cbxcolor);
		
		JLabel lblLicensePlaate = new JLabel("License Plate");
		lblLicensePlaate.setBounds(220, 20, 144, 16);
		add(lblLicensePlaate);
		
		txtLicensePlate = new JTextField();
		txtLicensePlate.setBounds(220, 50, 134, 28);
		add(txtLicensePlate);
		txtLicensePlate.setColumns(10);
		
		lblMileage = new JLabel("Mileage");
		lblMileage.setToolTipText("Only Numeric Values are Allowed");
		lblMileage.setBounds(220, 107, 200, 16);
		add(lblMileage);
		
		txtMileage = new JTextField();
		txtMileage.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				  lblMileage.setText("Mileage");
		          lblMileage.setForeground(Color.BLACK);
				 int intValue = 0;
				 
		         try {
		            intValue = Integer.parseInt(txtMileage.getText().trim());
		         } catch (NumberFormatException e1) {
		            lblMileage.setText("Mileage must be numeric");
		            lblMileage.setForeground(Color.RED);
		         }
			}
		});
		txtMileage.setBounds(220, 133, 134, 28);
		add(txtMileage);
		txtMileage.setColumns(10);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vController.next();
			}
		});
		btnNext.setBounds(220, 307, 117, 29);
		add(btnNext);

	}
}

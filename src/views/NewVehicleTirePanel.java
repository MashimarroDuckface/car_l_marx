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
 *   NewVehicleTirePanel.java
 */
package views;

import javax.swing.JPanel;

import controller.NewVehicleController;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JTextField;

import model.DateLabelFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.JButton;

public class NewVehicleTirePanel extends JPanel
{
	private NewVehicleController vController;
	public JTextField txtTireType;
	public JDatePickerImpl datePickerOn;
	public JDatePickerImpl datePickerOff;
	public JLabel lblTireType;

	public NewVehicleTirePanel(final NewVehicleController vController)
	{
		
		setBackground(new Color(255, 255, 240));
		this.vController = vController;
		vController.setController(this);
		setLayout(null);

		lblTireType = new JLabel("Tire Type");
		lblTireType.setBounds(34, 19, 84, 16);
		add(lblTireType);

		txtTireType = new JTextField();
		txtTireType.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				lblTireType.setText("Tire Type");
				lblTireType.setForeground(Color.BLACK);
				 				 
				 try{
		        	 if (txtTireType.getText().length()==0)
		        	 {
		        		 throw new NumberFormatException("text is empty");
		        	 }
		         }catch (NumberFormatException e1){
		        	 lblTireType.setText("Tire Type Required");
		        	 lblTireType.setForeground(Color.RED);
		         }
		         }
		});
		txtTireType.setBounds(34, 48, 134, 28);
		add(txtTireType);
		txtTireType.setColumns(10);

		JLabel lblStudsOn = new JLabel("Date studs can be installed");
		lblStudsOn.setBounds(34, 104, 189, 16);
		add(lblStudsOn);

		int monthOn = Calendar.getInstance().get(Calendar.MONTH);
		int dayOn = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int yearOn = Calendar.getInstance().get(Calendar.YEAR);
		UtilDateModel model = new UtilDateModel();
		model.setDate(yearOn, monthOn, dayOn);
		model.setSelected(true);
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		JDatePanelImpl studsOnDate = new JDatePanelImpl(model, p);
		datePickerOn = new JDatePickerImpl(studsOnDate,
				new DateLabelFormatter());
		datePickerOn.setBounds(34, 130, 200, 30);
		add(datePickerOn);
		
		int monthOff = Calendar.getInstance().get(Calendar.MONTH);
		int dayOff = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int yearOff = Calendar.getInstance().get(Calendar.YEAR);
		UtilDateModel modelOff = new UtilDateModel();
		modelOff.setDate(yearOff, monthOff, dayOff);
		modelOff.setSelected(true);
		JLabel lblStudsOff = new JLabel("Date studs need to removed by");
		lblStudsOff.setBounds(34, 201, 219, 16);
		add(lblStudsOff);
		
		JDatePanelImpl studsOffDate = new JDatePanelImpl(modelOff, p);
		datePickerOff = new JDatePickerImpl(studsOffDate,
				new DateLabelFormatter());
		datePickerOff.setBounds(34, 230, 200, 30);
		add(datePickerOff);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vController.submit();
			}
		});
		btnSubmit.setBounds(79, 299, 117, 29);
		add(btnSubmit);
	}
}

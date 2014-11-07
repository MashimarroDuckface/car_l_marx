/*   Car-L-Marx
 *
 *   Nov 1, 2014  
 *   CS 320 Fall 2014
 *
 *		Michael Allen-Bond
 *		Lise Driggers
 *		Jesse Pomerenk
 *
 *		views
 *
 *   NewUserFrame.java
*/
package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class ForgotFrame extends JFrame
{

	private JPanel contentPane;
	
	public JLabel lblUserName;
	public JLabel lblEmail;
	public JLabel lblReenterEmail;
	public JLabel lblHeader;
	
	//public JLabel lblPassword;
	//public JLabel lblReenterPassword;
	//public JLabel lblFirstName;
	//public JLabel lblLastName;
	//public JLabel lblEmailAddress;
	public JTextField txtUserName;
	//public JPasswordField txtPass;
	//public JPasswordField txtRePass;
	public JTextField txtEmail;
	public JTextField txtReenterEmail;
	
	//public JTextField txtEmail;
	
	private JButton btnSubmit;
	private JButton btnCancel;
	
	/**
	 * Create the frame.
	 */
	public ForgotFrame()
	{
		setTitle("Car-L-Marx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 210, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUserName = new JLabel("Forgot Password");
		lblUserName.setBounds(10, 20, 190, 16);
		contentPane.add(lblUserName);
		
		lblUserName = new JLabel("* User Name");
		lblUserName.setBounds(34, 40, 185, 16);
		contentPane.add(lblUserName);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(10, 60, 190, 28);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		lblEmail = new JLabel("* Email Address");
		lblEmail.setBounds(34, 100, 199, 16);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(10, 120, 190, 28);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		lblReenterEmail= new JLabel("* Reenter Email");
		lblReenterEmail.setBounds(34, 160, 199, 16);
		contentPane.add(lblReenterEmail);
		
		txtReenterEmail = new JTextField();
		txtReenterEmail.setBounds(10, 180, 190, 28);
		contentPane.add(txtReenterEmail);
		txtReenterEmail.setColumns(10);
		
		/*
		lblFirstName = new JLabel("* First Name");
		lblFirstName.setBounds(34, 210, 199, 16);
		contentPane.add(lblFirstName);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(27, 230, 206, 28);
		contentPane.add(txtFirstName);
		txtFirstName.setColumns(10);
		
		lblLastName = new JLabel("* Last Name");
		lblLastName.setBounds(34, 270, 199, 16);
		contentPane.add(lblLastName);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(27, 290, 206, 28);
		contentPane.add(txtLastName);
		
		lblEmailAddress = new JLabel("* Email");
		lblEmailAddress.setBounds(34, 330, 199, 16);
		contentPane.add(lblEmailAddress);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(27, 350, 206, 28);
		contentPane.add(txtEmail);
		*/
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10, 220, 95, 29);
		contentPane.add(btnSubmit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(100, 220, 95, 29);
		contentPane.add(btnCancel);
	}
	
	public void addSubmitButtonListener(ActionListener listenerForSubmitButton) 
	{
		this.btnSubmit.addActionListener(listenerForSubmitButton);
	}
	public void addCancelButtonListener(ActionListener listenerForCancelButton) 
	{
		this.btnCancel.addActionListener(listenerForCancelButton);
	}
}

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

public class NewUserFrame extends JFrame
{

	private JPanel contentPane;
	
	public JLabel lblUserName;
	public JLabel lblPassword;
	public JLabel lblReenterPassword;
	public JLabel lblFirstName;
	public JLabel lblLastName;
	public JLabel lblEmailAddress;
	public JTextField txtUserName;
	public JPasswordField txtPass;
	public JPasswordField txtRePass;
	public JTextField txtFirstName;
	public JTextField txtLastName;
	public JTextField txtEmail;
	
	private JButton btnSubmit;

	/**
	 * Create the frame.
	 */
	public NewUserFrame()
	{
		setTitle("Car-L-Marx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 260, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUserName = new JLabel("* User Name");
		lblUserName.setBounds(34, 30, 185, 16);
		contentPane.add(lblUserName);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(27, 50, 206, 28);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		lblPassword = new JLabel("* Password");
		lblPassword.setBounds(34, 90, 199, 16);
		contentPane.add(lblPassword);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(27, 110, 206, 28);
		contentPane.add(txtPass);
		txtPass.setColumns(10);
		
		lblReenterPassword = new JLabel("* Reenter Password");
		lblReenterPassword.setBounds(34, 150, 199, 16);
		contentPane.add(lblReenterPassword);
		
		txtRePass = new JPasswordField();
		txtRePass.setBounds(27, 170, 206, 28);
		contentPane.add(txtRePass);
		txtRePass.setColumns(10);
		
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
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(66, 409, 117, 29);
		contentPane.add(btnSubmit);
	}
	
	public void addSubmitButtonListener(ActionListener listenerForSubmitButton) 
	{
		this.btnSubmit.addActionListener(listenerForSubmitButton);
	}
}

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

public class ResetFrame extends JFrame
{

	private JPanel contentPane;
	
	public JLabel lblUserName;
	public JLabel lblHeader;
	public JLabel lblPassword;
	public JLabel lblNewPass;
	public JLabel lblReNewPass;
	
	public JTextField txtUserName;
	public JPasswordField txtPassword;
	public JPasswordField txtNewPass;
	public JPasswordField txtReNewPass;
	
	private JButton btnSubmit;
	private JButton btnCancel;

	/**
	 * Create the frame.
	 */
	public ResetFrame()
	{
		setTitle("Car-L-Marx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 210, 340);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUserName = new JLabel("Reset Password");
		lblUserName.setBounds(10, 20, 190, 16);
		contentPane.add(lblUserName);
		
		lblUserName = new JLabel("* User Name");
		lblUserName.setBounds(20, 40, 185, 16);
		contentPane.add(lblUserName);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(10, 60, 190, 28);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		lblPassword = new JLabel("*Old Password");
		lblPassword.setBounds(20, 100, 199, 16);
		contentPane.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(10, 120, 190, 28);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		lblNewPass = new JLabel("*New Password");
		lblNewPass.setBounds(20, 160, 199, 16);
		contentPane.add(lblNewPass);
		
		txtNewPass = new JPasswordField();
		txtNewPass.setBounds(10, 180, 190, 28);
		contentPane.add(txtNewPass);
		txtNewPass.setColumns(10);
		
		lblReNewPass = new JLabel("*Reneter New Password");
		lblReNewPass.setBounds(20, 220, 199, 16);
		contentPane.add(lblReNewPass);
		
		txtReNewPass = new JPasswordField();
		txtReNewPass.setBounds(10, 240, 190, 28);
		contentPane.add(txtReNewPass);
		txtReNewPass.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10, 280, 95, 29);
		contentPane.add(btnSubmit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(100, 280, 95, 29);
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

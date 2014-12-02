/*   Car-L-Marx
 *
 *   Oct 10, 2014  
 *   CS 320 Fall 2014
 *
 *		Michael Allen-Bond
 *		Lise Driggers
 *		Jesse Pomerenk
 *
 *		views
 *
 *   Login.java
*/
package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.border.BevelBorder;

import javax.swing.JSeparator;

import java.awt.Font;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JButton btnLoginSubmit;
	private JButton btnForgotPassword;
	private JButton btnSignUp;
	private JButton btnResetPassword;
	private JTextField txtUserName;
	private JPasswordField txtUserPassword;
	public JLabel lblPassword;
	
	public LoginPanel loginImage;
	private JLabel lblSignIn;
	private JSeparator separator_1;
	private JLabel lblNewUser;
	public JLabel lblUsrId;
	
	/**
	 * Create the frame.
	 */
	public LoginFrame()
	{
		setTitle("Car-L-Marx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(250, 250, 210), new Color(248, 248, 255), new Color(211, 211, 211), new Color(128, 128, 128)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsrId = new JLabel("User id");
		lblUsrId.setBounds(25, 30, 200, 16);
		contentPane.add(lblUsrId);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(25, 50, 200, 28);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(25, 80, 200, 16);
		contentPane.add(lblPassword);
		
		txtUserPassword = new JPasswordField();
		txtUserPassword.setBounds(25, 100, 200, 28);
		contentPane.add(txtUserPassword);
		
		btnLoginSubmit = new JButton("SIGN IN");
		btnLoginSubmit.setBounds(25, 140, 200, 28);
		contentPane.add(btnLoginSubmit);
		
		btnForgotPassword = new JButton("Forgot Password ?");
		btnForgotPassword.setBounds(25, 200, 200, 28);
		contentPane.add(btnForgotPassword);
		
		btnResetPassword = new JButton("Reset Password");
		btnResetPassword.setBounds(25, 240, 200, 28);
		contentPane.add(btnResetPassword);
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(25, 332, 200, 29);
		contentPane.add(btnSignUp);
		
		lblSignIn = new JLabel("Sign In");
		lblSignIn.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblSignIn.setBounds(25, 5, 200, 16);
		contentPane.add(lblSignIn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(25, 23, 350, 12);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBackground(new Color(210, 180, 140));
		separator_1.setBounds(25, 308, 200, 12);
		contentPane.add(separator_1);
		
		lblNewUser = new JLabel("New User");
		lblNewUser.setBounds(27, 280, 100, 16);
		contentPane.add(lblNewUser);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(new Color(222, 184, 135));
		separator_2.setForeground(new Color(210, 180, 140));
		separator_2.setBounds(25, 180, 200, 12);
		contentPane.add(separator_2);
		
		paintLoginPanel();
	}
	
	public void paint()
	{
		
	}
	
	private void paintLoginPanel()
	{
		loginImage = new LoginPanel();
		loginImage.setBorder(null);
		loginImage.setBounds(364, 28, 201, 201);
	}
	
	public String getUserName()
	{
		return txtUserName.getText();
	}
	
	public char[] getPassword()
	{
		return this.txtUserPassword.getPassword();
	}
	
	/**
	 *   Fires when user presses enter after entering password
	 * @param listenerForEnter
	 */
	public void addEnter(ActionListener listenerForEnter) 
	{
		this.txtUserPassword.addActionListener(listenerForEnter);
	}
	
	public void addSubmitButtonListener(ActionListener listenerForSubmitButton) 
	{
		this.btnLoginSubmit.addActionListener(listenerForSubmitButton);
	}
	
	public void addbtnForgotButtonListener(ActionListener listenerForForgotButton) 
	{
		btnForgotPassword.addActionListener(listenerForForgotButton);
	}
	
	public void addbtnNewUserButtonListener(ActionListener listenerForNewUserButton) 
	{
		btnSignUp.addActionListener(listenerForNewUserButton);
	}
	public void addbtnResetButtonListener(ActionListener listenerForResetButton) 
	{
		btnResetPassword.addActionListener(listenerForResetButton);
	}
}

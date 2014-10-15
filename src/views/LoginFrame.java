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


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.border.BevelBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import dbAccess.DbAccess;
import security.PasswordEncrypt;

public class LoginFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static DbAccess dbHandle;
	
	private JButton btnLoginSubmit;
	private JButton btnForgotPassword;
	private JTextField txtUserName;
	private JPasswordField txtUserPassword;
	
	private String userName;
	private char[] userPassword;
	
	public LoginPanel loginImage;

	/**
	 * Create the frame.
	 */
	public LoginFrame()
	{
		dbHandle = DbAccess.getInstance();
		
		setTitle("Car-L-Marx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(250, 250, 210), new Color(248, 248, 255), new Color(211, 211, 211), new Color(128, 128, 128)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(43, 57, 199, 28);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		txtUserPassword = new JPasswordField();
		txtUserPassword.setBounds(43, 123, 195, 28);
		contentPane.add(txtUserPassword);
		
		JLabel lblPleaseEnterYour = new JLabel("User id");
		lblPleaseEnterYour.setBounds(43, 28, 57, 16);
		contentPane.add(lblPleaseEnterYour);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(43, 95, 61, 16);
		contentPane.add(lblNewLabel);
		
		paintLoginPanel();
	}
	
	private void paintLoginPanel()
	{
		loginImage = new LoginPanel();
		loginImage.setBorder(null);
		loginImage.setBounds(364, 28, 201, 201);
		
		ImageIcon image = new ImageIcon("/SteeringWheel.jpeg");
		new JLabel("",image,JLabel.CENTER);
		contentPane.add(loginImage);
		
		btnLoginSubmit = new JButton("Submit");
		btnLoginSubmit.setBounds(53, 174, 137, 29);
		btnLoginSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SubmitButtonLogin();
			}
		});
		contentPane.add(btnLoginSubmit);
		
		btnForgotPassword = new JButton("Forgot Password");
		btnForgotPassword.setBounds(53, 220, 137, 29);
		btnForgotPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				forgotPassword();
			}
		});
		
		contentPane.add(btnForgotPassword);
		
	}
	
	private void forgotPassword()
	{
		//  TODO  need to create a new form for user to enter their email address.  We will then 
		//  TODO  send a link to update password
	}
	private boolean SubmitButtonLogin()
	{
		userName = this.txtUserName.getText();
		userPassword = this.txtUserPassword.getPassword();
		
		String stringPassword = new String(userPassword);
			
		userName = this.txtUserName.getText();
		userPassword = this.txtUserPassword.getPassword();
		
		String salt = dbHandle.getSalt(userName);
		String dbPass = dbHandle.getPass(userName);
		System.out.println(userName  + " salt from server " + salt);
		System.out.println(userName + " from server  - password-->" + dbPass + " password from input-->" + stringPassword);
		
		PasswordEncrypt pass = new PasswordEncrypt();
		String encPassword = pass.getSecurePassword(stringPassword, salt);
		System.out.println("Password from passencypt--> " + encPassword);
		
		return (encPassword.equals(dbPass));    //  will return true if the password matches what is on file
		
	}

}

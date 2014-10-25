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
import java.awt.Image;

import javax.swing.border.BevelBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import security.PasswordEncrypt;

import javax.swing.JSeparator;

import model.DbAccess;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JButton btnSignUp;
	private JTextField txtUserName;
	private JPasswordField txtUserPassword;
	
	private String userName;
	private char[] userPassword;
	private boolean goodLogin = false;
	
	public LoginPanel loginImage;
	private JLabel lblSignIn;
	private JSeparator separator_1;
	private JLabel lblNewUser;
	private JLabel lblNewLabel_1;

	/**
	 * Create the frame.
	 */
	public LoginFrame()
	{
		dbHandle = DbAccess.getInstance();
		
		setTitle("Car-L-Marx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(250, 250, 210), new Color(248, 248, 255), new Color(211, 211, 211), new Color(128, 128, 128)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPleaseEnterYour = new JLabel("User id");
		lblPleaseEnterYour.setBounds(43, 45, 57, 16);
		contentPane.add(lblPleaseEnterYour);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(43, 65, 199, 28);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(43, 95, 61, 16);
		contentPane.add(lblNewLabel);
		
		txtUserPassword = new JPasswordField();
		txtUserPassword.setBounds(43, 115, 195, 28);
		contentPane.add(txtUserPassword);
		
		paintLoginPanel();
	}
	
	private void paintLoginPanel()
	{
		loginImage = new LoginPanel();
		loginImage.setBorder(null);
		loginImage.setBounds(364, 28, 201, 201);
		
		btnLoginSubmit = new JButton("SIGN IN");
		btnLoginSubmit.setBounds(43, 155, 137, 29);
		btnLoginSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SubmitButtonLogin();
			}
		});
		contentPane.add(btnLoginSubmit);
		
		btnForgotPassword = new JButton("Forgot Password ?");
		btnForgotPassword.setBounds(43, 232, 137, 29);
		btnForgotPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				forgotPassword();
			}
		});
		contentPane.add(btnForgotPassword);
		
		lblSignIn = new JLabel("Sign In");
		lblSignIn.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblSignIn.setBounds(43, 6, 61, 16);
		contentPane.add(lblSignIn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(43, 23, 304, 12);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(43, 273, 309, 12);
		contentPane.add(separator_1);
		
		lblNewUser = new JLabel("New User");
		lblNewUser.setBounds(43, 298, 61, 16);
		contentPane.add(lblNewUser);
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSignUp.setBounds(43, 326, 117, 29);
		btnSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				signUp();
			}
		});
		contentPane.add(btnSignUp);
		
//		lblNewLabel_1 = new JLabel("User Name and Passord are Invalid");
//		lblNewLabel_1.setEnabled(false);
//		lblNewLabel_1.setBounds(119, 45, 228, 16);
//		contentPane.add(lblNewLabel_1);
	}
	
	private void signUp()
	{
		System.out.println("signUp called");
	}
	
	private void forgotPassword()
	{
		//  TODO  need to create a new form for user to enter their email address.  We will then 
		//  TODO  send a link to update password
		System.out.println("forgotPassword called");
	}
	
	private void SubmitButtonLogin()
	{
		userName = this.txtUserName.getText();
		userPassword = this.txtUserPassword.getPassword();
		
		if ( (userName.isEmpty()) || (userPassword.length == 0))   //  No input!
		{
			
		}
		String stringPassword = new String(userPassword);
		
		String salt = dbHandle.getSalt(userName);
		String dbPass = dbHandle.getPass(userName);
//		System.out.println(userName  + " salt from server " + salt);
//		System.out.println(userName + " from server  - password-->" + dbPass + " password from input-->" + stringPassword);
		
		PasswordEncrypt pass = new PasswordEncrypt();
		String encPassword = pass.getSecurePassword(stringPassword, salt);
	//	System.out.println("Password from passencypt--> " + encPassword);
		
		this.goodLogin = (encPassword.equals(dbPass));   //  will be true if the user passed the login
		
	}
}

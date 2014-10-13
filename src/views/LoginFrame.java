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
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;

import dbAccess.DbAccess;
import security.PasswordEncrypt;

import java.awt.event.ActionListener;

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
	private final Action action = new SwingAction();
	private JButton btnNewButton_1;

	/**
	 * Create the frame.
	 */
	public LoginFrame(DbAccess dbHandle)
	{
		this.dbHandle = dbHandle;
		
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
		
		btnLoginSubmit = new JButton("Forgot Password");
		btnLoginSubmit.setBackground(new Color(255, 140, 0));
		btnLoginSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SubmitButtonLogin();
			}
		});
		contentPane.add(btnLoginSubmit);
		
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
		
		btnNewButton_1 = new JButton("Forgot Password");
		btnNewButton_1.setBounds(53, 220, 137, 29);
		contentPane.add(btnNewButton_1);
		
	}
	
	private boolean SubmitButtonLogin()
	{
		userName = this.txtUserName.getText();
		userPassword = this.txtUserPassword.getPassword();
		String userPassString = userPassword.toString();
		
		String salt = this.dbHandle.getSalt(userName);
		System.out.println(userName + " " + salt);
		String dbPass = this.dbHandle.getPass(userName);
		System.out.println(userName + " " + dbPass + " " + userPassString);
		
		PasswordEncrypt pass = new PasswordEncrypt();
		String encPassword = pass.getSecurePassword(userPassString, salt);
		System.out.println(encPassword);
		
		return (encPassword.equals(userPassString));    //  will return true if the password matches what is on file
		
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}

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
 *   LoginPanel.java
*/
package views;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.File;
import java.awt.Color;

public class LoginPanel extends JPanel
{
	public LoginPanel()
	{
		setBorder(null);
		setBackground(new Color(255, 255, 240));
		setLayout(null);
		
//		System.out.println(System.getProperty("java.class.path"));
		ImageIcon icon = createImageIcon("/SteeringWheel.jpeg","SteeringWheel");
		
		JLabel lblNewLabel = new JLabel("", icon ,JLabel.CENTER);
		lblNewLabel.setBounds(6, 6, 190, 154);
		add(lblNewLabel);
		
	}
	
	protected static ImageIcon createImageIcon(String path, String description)
	{
		java.net.URL imgURL = LoginPanel.class.getClass().getResource(path);
		
//		System.out.println("found file ? " + path + " " + new File(path).exists());
		if (imgURL != null)
		{
			return new ImageIcon(imgURL, description);
		}
		else
		{
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}

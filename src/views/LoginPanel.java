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
import javax.swing.JPanel;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

public class LoginPanel extends JPanel
{
	private Image bgimage = null;
	
	public LoginPanel()
	{
		MediaTracker mt = new MediaTracker(this);
		bgimage = Toolkit.getDefaultToolkit().getImage("images/SteeringWheel.jpeg");
		mt.addImage(bgimage,0);
		try {
			mt.waitForAll();
		} catch  (InterruptedException e) {
			e.printStackTrace();
		}
//		setBorder(null);
//		setBackground(new Color(255, 255, 240));
//		setLayout(null);
		
//		System.out.println(System.getProperty("java.class.path"));
//		ImageIcon icon = createImageIcon("/SteeringWheel.jpeg","SteeringWheel");
//		JLabel lblNewLabel = new JLabel("", icon ,JLabel.CENTER);
//		lblNewLabel.setBounds(6, 6, 190, 154);
//		add(lblNewLabel);
		
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

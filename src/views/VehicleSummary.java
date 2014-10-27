/*   Car-L-Marx
 *
 *   Oct 19, 2014  
 *   CS 320 Fall 2014
 *
 *		Michael Allen-Bond
 *		Lise Driggers
 *		Jesse Pomerenk
 *
 *		views
 *
 *   VehicleSummary.java
*/
package views;

import java.awt.Color;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class VehicleSummary extends JFrame
{

	private JPanel contentPane;
	private Image bgimage = null;


	/**
	 * Create the frame.
	 */
	public VehicleSummary()
	{
		setTitle("Car-L-Marx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(211, 211, 211), new Color(119, 136, 153)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		MediaTracker mt = new MediaTracker(this);
		bgimage = Toolkit.getDefaultToolkit().getImage("images/keys.jpg");
		mt.addImage(bgimage, 0);
		try {
			mt.waitForAll();
		} catch (InterruptedException e ) {
			e.printStackTrace();
		}
		panel.setBounds(0, 0, 1000, 800);
		contentPane.add(panel);
	}
}

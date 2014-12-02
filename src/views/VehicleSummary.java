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

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.VehicleController;

public class VehicleSummary extends JFrame
{

	private JPanel contentPane;
	private VehicleTablePanel carPanel;
	private Image bgimage = null;
	private VehicleController vController;

	/**
	 * Create the frame.
	 */
	/*public VehicleSummary()
	{
		this.vController = vController;
		setTitle("Car-L-Marx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		carPanel = new VehicleTablePanel(vController);
		carPanel.setBackground(new Color(255, 255, 255));
		carPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(211, 211, 211), new Color(119, 136, 153)));
		setContentPane(carPanel);
		carPanel.setLayout(null);
		
//		VehicleTablePanel panel = new VehicleTablePanel();
//		MediaTracker mt = new MediaTracker(this);
//		bgimage = Toolkit.getDefaultToolkit().getImage("images/keys.jpg");
//		mt.addImage(bgimage, 0);
//		try {
//			mt.waitForAll();
//		} catch (InterruptedException e ) {
//			e.printStackTrace();
//		}
		carPanel.setBounds(0, 0, 1000, 800);
		contentPane.add(carPanel);
		
		pack();
		setVisible(true);
	}*/
}

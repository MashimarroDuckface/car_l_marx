/*   Car-L-Marx
 *
 *   Nov 30, 2014  
 *   CS 320 Fall 2014
 *
 *		Michael Allen-Bond
 *		Lise Driggers
 *		Jesse Pomerenk
 *
 *		views
 *
 *   NewVehicleView.java
*/
package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.NewVehicleController;
import controller.NewVehicleStart;

public class NewVehicleFrame extends JFrame
{

	public JPanel contentPane;
	
	private NewVehicleController vController;
	private NewVehicleStart vStartController;

	public NewVehicleFrame(NewVehicleStart vStartController)
	{
		this.vStartController = vStartController;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

}

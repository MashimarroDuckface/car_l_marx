/*   Car-L-Marx
 *
 *   Oct 27, 2014  
 *   CS 320 Fall 2014
 *
 *		Michael Allen-Bond
 *		Lise Driggers
 *		Jesse Pomerenk
 *
 *		views
 *
 *   VehicleTablePanel.java
 */
package views;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.Color;

public class VehicleTablePanel extends JPanel
{
	private JTable table;
	private JTable testTable;

	public VehicleTablePanel()
	{
		// super(new GridLayout(1, 0));
		setBackground(new Color(255, 255, 240));
		setLayout(null);

//		JLabel lblTest = new JLabel("This is only a test");
//		lblTest.setBounds(24, 31, 112, 16);
//		add(lblTest);

		String[] columnNames =
		{ "First Name", "Last Name", "Sport", "# of Years", "Vegetarian" };

		Object[][] data =
		{
				{ "Kathy", "Smith", "Snowboarding", new Integer(5),
						new Boolean(false) },
				{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
				{ "Sue", "Black", "Knitting", new Integer(2),
						new Boolean(false) },
				{ "Jane", "White", "Speed reading", new Integer(20),
						new Boolean(true) },
				{ "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) } };

		final JTable table = new JTable(data, columnNames);
		add(table);
//		table.setToolTipText("I am a table");
//		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
//		table.setFillsViewportHeight(true);
//        //Create the scroll pane and add the table to it.
//        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setSize(12, 70);
//        scrollPane.setLocation(5, 5);
//        scrollPane.setToolTipText("I am a table");
        System.out.println(" I wish that a table had come out instead of me");

        //Add the scroll pane to this panel.
  //      add(scrollPane);
        
        JLabel lblIWishThere = new JLabel("I wish there was a table here instead of a label");
        lblIWishThere.setBounds(96, 235, 321, 16);
        add(lblIWishThere);

	}
}

/*   Car-L-Marx
 *
 *   Oct 9, 2014  
 *   CS 320 Fall 2014
 *
 *		Michael Allen-Bond
 *		Lise Driggers
 *		Jesse Pomerenk
 *
 *		home
 *
 *   Main.java
*/
package home;

import dbAccess.DbAccess;

public class Main
{
	private static DbAccess dbHandle;

	public static void main(String[] args)
	{
		dbHandle = DbAccess.getInstance();
//		dbHandle.getVehicle();
//		dbHandle.getVehicleInOrder();
		dbHandle.getVehiclebyMake("Toyota");
		dbHandle.getVehiclebyId(3);

	}

}

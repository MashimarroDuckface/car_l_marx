/*   Car-L-Marx
 *
 *   Nov 28, 2014  
 *   CS 320 Fall 2014
 *
 *		Michael Allen-Bond
 *		Lise Driggers
 *		Jesse Pomerenk
 *
 *		tests
 *
 *   dbTest.java
*/
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import controller.MainController;
import controller.VehicleStart;
import model.DbAccess;
import model.VehiclesObject;

public class GetallInfoTest
{
	private ArrayList<VehiclesObject> vehicles;
	private DbAccess dbHandle;
	   @Test
	   public void testSalt() {
	     dbHandle = DbAccess.getInstance();
	     String salt = dbHandle.getSalt("Lacemaker");
	     assertEquals(salt, "[C@6f347124");
	   }
	   
	   @Test(timeout=300)
	   public void testGetSaltTime() {	
		     dbHandle = DbAccess.getInstance();
		     String salt = dbHandle.getSalt("Lacemaker");
	   }
	   
	   @Test(timeout=400)

	   public void testGetVehiclesTime() {

	   dbHandle= DbAccess.getInstance();

	   ArrayList<VehiclesObject> vehicles = dbHandle.getUserVehicle("Lacemaker");

	   }
	   
	   @Test(timeout=5000000)
	   public void testJframes(){
		   dbHandle = DbAccess.getInstance();
		   String salt = dbHandle.getSalt("Lacemaker");
		   //MainController cont = new MainController.newVehicleFrame ("LaceMaker");
		   VehicleStart vehicle = new VehicleStart(new MainController(), "LaceMaker");
	   }
}
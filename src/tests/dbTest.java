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
import org.junit.Test;

import model.DbAccess;

public class dbTest
{
	private DbAccess dbHandle;
	   @Test
	   public void testSalt() {
	     dbHandle = DbAccess.getInstance();
	     String salt = dbHandle.getSalt("Lacemaker");
	     assertEquals(salt, "[C@6f347124");
	   }
	   
	   @Test(timeout=500)
	   public void testGetSaltTime() {	
		     dbHandle = DbAccess.getInstance();
		     String salt = dbHandle.getSalt("Lacemaker");
	   }

}
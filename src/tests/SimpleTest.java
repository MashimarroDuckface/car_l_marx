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
 *   SimpleTest.java
*/
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

public class SimpleTest
{
	  protected int value1, value2;
	   
	   // assigning the values
	   protected void setUp(){
	      value1=3;
	      value2=3;
	   }

	   // test method to add two values
	   @Test
	   public void testAdd1(){
		   this.setUp();
	      double result= value1 + value2;
	      assertTrue(result == 6);
	   }
	
    @Test
    public void thisAlwaysPasses() {

    }

    @Test
    @Ignore
    public void thisIsIgnored() {
    }
    
	   @Test
	   public void testAdd() {
	      String str= "Junit is working fine";
	      assertEquals("Junit is working fine",str);
	   }
}

/*   Car-L-Marx
 *
 *   Nov 1, 2014  
 *   CS 320 Fall 2014
 *
 *		Michael Allen-Bond
 *		Lise Driggers
 *		Jesse Pomerenk
 *
 *		model
 *
 *   VehiclesObject.java
*/
package model;

public class VehiclesObject
{
	public int idvehicle;
	public String make;
	public String model;
	public String nickName;
	public String color;
	public String licensePlate;
	public int mileage;
	
	public VehiclesObject(int idvehicle, String make, String model, String nickName, String color, String licensePlate, int mileage)
	{
		this.idvehicle = idvehicle;
		this.make = make;
		this.model = model;
		this.nickName = nickName;
		this.color = color;
		this.licensePlate = licensePlate;
		this.mileage = mileage;
	}
}

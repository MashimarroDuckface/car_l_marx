/*   Car-L-Marx
 *
 *   Oct 9, 2014  
 *   CS 320 Fall 2014
 *
 *		Michael Allen-Bond
 *		Lise Driggers
 *		Jesse Pomerenk
 *
 *		dbAccess
 *
 *   DbAccess.java
 */

package model;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DbAccess
{
	private Connection conn;
	private java.sql.Statement statement;

	// Prepared statements
	private PreparedStatement insertUser;
	private PreparedStatement getAllUser;
	private PreparedStatement getValidUser;
	private PreparedStatement getUserSalt;
	private PreparedStatement getUserPass;
	private PreparedStatement getUserVehicles;

	/**
	 * Note that the constructor is listed as private. That will disallow the
	 * instantiation of objects without going through the singleton pattern
	 */
	private DbAccess()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// System.out.println("got a connection!");

			/** my local sql database */
			// String url1 = "jdbc:mysql://localhost:8889/Car_L_Marx";
			String url1 = "jdbc:mysql://vps1.admalledd.com:1981/car_l_marx";

			Properties info = new Properties();
			info.put("user", "root");
			info.put("password", "houdini");

			conn = DriverManager.getConnection(url1, info);
			if (conn != null)
			{
				System.out.println("Connected to the database ");
			}
			statement = conn.createStatement();
		} catch (Exception e)
		{
			System.out.println("Error in database connection " + e);
		}
		// create all of the prepared statements one time
		this.createPreparedStatements();
	}

	private static class DbAccessHelper
	{
		private static final DbAccess INSTANCE = new DbAccess();
	}

	// This is the method that is called by the outside to create the object in
	// a singleton pattern
	public static DbAccess getInstance()
	{
		return DbAccessHelper.INSTANCE;
	}

	private void createPreparedStatements()
	{
		try
		{
			// String insertVehicleString =
			// "INSERT INTO `car_l_marx`.`vehicle` (`vehicleId`, `make`, `model`, `year`, `color`) VALUES (null, ?, ?, ?, ?);";
			// insertVehicle = conn.prepareStatement(insertVehicleString);
			// String deleteVehicleString =
			// "DELETE FROM `car_l_marx`.`vehicle` WHERE `vehicleID` = ?";
			// deleteVehicle = conn.prepareStatement(deleteVehicleString);

			// User statements
			String insertUserString = "INSERT INTO `car_l_marx`.`userTable` (`userName`, `userPassword`, `passSalt`, `fName`, `lname`, `userEmail`) VALUES (?, ?, ?, ?, ?, ?)";
			insertUser = conn.prepareStatement(insertUserString);
			String getAllUserString = "SELECT * FROM `userTable`";
			getAllUser = conn.prepareStatement(getAllUserString);
			String getValidUserString = "SELECT * FROM `userTable` WHERE `UserName` LIKE ?";
			getValidUser = conn.prepareStatement(getValidUserString);
			String getUserSaltString = "SELECT `passSalt` FROM  `userTable` WHERE  `userName` LIKE  ?";
			getUserSalt = conn.prepareStatement(getUserSaltString);
			String getUserPassString = "SELECT `userPassword` FROM  `userTable` WHERE  `userName` LIKE  ?";
			getUserPass = conn.prepareStatement(getUserPassString);
			String getUserVehiclesString = "SELECT  idvehicle, makeTable.make, modelTable.model, colorTable.color, licensePlate,  mileage FROM  `vehicleTable` INNER JOIN makeTable ON vehicleTable.idmake = makeTable.idmake INNER JOIN modelTable ON vehicleTable.idmodel = modelTable.idmodel INNER JOIN colorTable ON vehicleTable.idColor = colorTable.idcolor WHERE userName LIKE ?";
			getUserVehicles = conn.prepareStatement(getUserVehiclesString);
//			String getUserVehiclesString = "SELECT count(*) as rowCount, idvehicle, makeTable.make, modelTable.model, colorTable.color, licensePlate,  mileage FROM  `vehicleTable` INNER JOIN makeTable ON vehicleTable.idmake = makeTable.idmake INNER JOIN modelTable ON vehicleTable.idmodel = modelTable.idmodel INNER JOIN colorTable ON vehicleTable.idColor = colorTable.idcolor WHERE userName LIKE ?";
//			getUserVehicles = conn.prepareStatement(getUserVehiclesString);

		} catch (SQLException e)
		{
			System.out.println("problem creating prepared statements " + e);
		}
	}

	public String getSalt(String userName)
	{
		String cleanUserName = sanitizeUserName(userName);
		try
		{
			this.getUserSalt.setString(1, cleanUserName);
			ResultSet resultSet = this.getUserSalt.executeQuery();
			while (resultSet.next())
			{
				System.out.println ("inside DbAccess - getSalt " +
				 resultSet.getString("passSalt"));
				return resultSet.getString("passSalt");
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of user salt " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("User Table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return null;
	}

	public String getPass(String userName)
	{
		String cleanUserName = sanitizeUserName(userName);
		try
		{
			this.getUserPass.setString(1, cleanUserName);
			ResultSet resultSet = this.getUserPass.executeQuery();
			while (resultSet.next())
			{
				// System.out.println ("inside DbAccess - getPass " +
				// resultSet.getString("userPassword"));
				return resultSet.getString("userPassword");
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of user password " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("User Table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return null;
	}

	public void getAllUser()
	{
		try
		{
			ResultSet resultSet = statement.executeQuery("SELECT * from user");

			while (resultSet.next())
			{
				String userName = resultSet.getString("userName");
				String firstName = resultSet.getString("fName");
				String lastName = resultSet.getString("lName");
				String emailAdd = resultSet.getString("userEmail");
				System.out.println(userName + " " + firstName + " " + lastName
						+ " " + emailAdd);
			}
		} catch (SQLException ex)
		{
			// // handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			ex.printStackTrace();
		}
	}

	public boolean validUser(String userName)
	{
		String cleanUserName = sanitizeUserName(userName);

		try
		{
			this.getValidUser.setString(1, cleanUserName);
			ResultSet resultSet = this.getValidUser.executeQuery();
			while (resultSet.next())
			{
				return true; // we got the user
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of valid user" + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("User Table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return false; // did not get the user
	}
	
	public ArrayList<VehiclesObject> getUserVehicle(String userName)
	{
		
		System.out.println("**************************************************DbAccess - getUserVehicle");
		String cleanUserName = sanitizeUserName(userName);
		try
		{
			this.getUserVehicles.setString(1, cleanUserName);
			ResultSet resultSet = this.getUserVehicles.executeQuery();
			ArrayList<VehiclesObject> vehicleList = new ArrayList<VehiclesObject>();
			
	//		System.out.println("Vehicles row count " + resultSet.getInt("rowCount"));
			
			while (resultSet.next())
			{
				// TODO loop through result set getting all vehicles. Set them
				// into an array
				System.out.println(resultSet.getInt("idvehicle") + " "
						+ resultSet.getString("makeTable.make") + " "
						+ resultSet.getString("modelTable.model") + " "
						+ resultSet.getString("colorTable.color") + " "
						+ resultSet.getString("licensePlate") + " "
						+ resultSet.getInt("mileage") );
				
				VehiclesObject vehicle = new VehiclesObject(resultSet.getInt("idvehicle") , resultSet.getString("makeTable.make"), resultSet.getString("modelTable.model"), resultSet.getString("colorTable.color"), resultSet.getString("licensePlate"), resultSet.getInt("mileage"));
				vehicleList.add(vehicle);
			}
			return vehicleList;
		} catch (SQLException e)
		{
			System.out.println("error on fetch of getUserVehicles " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Vehicles Table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return null;
	}

	public void insertNewUser(String userName, String password, String salt,
			String firstName, String lastName, String email)
	{
		// TODO clean the in bound data before putting it into the database

		try
		{
			this.insertUser.setString(1, userName);
			this.insertUser.setString(2, password);
			this.insertUser.setString(3, salt);
			this.insertUser.setString(4, firstName);
			this.insertUser.setString(5, lastName);
			this.insertUser.setString(6, email);
			insertUser.execute();
		} catch (SQLException e)
		{
			System.out.println("error on insert of user " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("New User Error: " + e.getErrorCode());
			e.printStackTrace();
		}
	}

	// public void deleteVehicle(String name)
	// {
	// try
	// {
	// // this.conn1.setAutoCommit(false);
	// // using the prepared statement
	// deleteVehicle.setString(1, name);
	// deleteVehicle.execute();
	// } catch (SQLException e)
	// {
	// System.out.println("error on delete of vehicle " + e);
	// System.out.println("SQLException: " + e.getMessage());
	// System.out.println("SQLState: " + e.getSQLState());
	// System.out.println("VendorError: " + e.getErrorCode());
	// e.printStackTrace();
	// }
	// }

	private String sanitizeUserName(String name)
	{
		// TODO sanitize name string
		/*
		 * thoughts - white list length limit black list
		 */
		return name;
	}

	private String sanitizeEmail(String email)
	{
		// TODO sanitize email string - use regular expression?
		return email;
	}
}

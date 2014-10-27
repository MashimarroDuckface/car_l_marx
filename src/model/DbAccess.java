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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbAccess
{
	private Connection conn;
	private java.sql.Statement statement;

	// Prepared statements
	private PreparedStatement insertUser;
	private PreparedStatement getAllUser;
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
			String insertUserString = "INSERT INTO `car_l_marx`.`userTable` (`idUser`, `userName`, `userPassword`, `fName`, `lNname`, `userEmail`) VALUES (NULL, ?, ?, ?, ?, ?)";
			insertUser = conn.prepareStatement(insertUserString);
			String getAllUserString = "SELECT * FROM `userTable`";
			getAllUser = conn.prepareStatement(getAllUserString);
			String getUserSaltString = "SELECT `passSalt` FROM  `userTable` WHERE  `userName` LIKE  ?";
			getUserSalt = conn.prepareStatement(getUserSaltString);
			String getUserPassString = "SELECT `userPassword` FROM  `userTable` WHERE  `userName` LIKE  ?";
			getUserPass = conn.prepareStatement(getUserPassString);
			String getUserVehiclesString = "SELECT idvehicle, makeTable.make, modelTable.model, colorTable.color, licensePlate,  `mileage` ";
				getUserVehiclesString += " FROM  `vehicleTable` ";
				getUserVehiclesString +=	" INNER JOIN makeTable ON vehicleTable.idmake = makeTable.idmake";
				getUserVehiclesString +=	" INNER JOIN modelTable ON vehicleTable.idmodel = modelTable.idmodel";
				getUserVehiclesString +=	" INNER JOIN colorTable ON vehicleTable.idColor = colorTable.idcolor";
				getUserVehiclesString +=	" WHERE idUser =?";
			getUserVehicles = conn.prepareStatement(getUserVehiclesString);

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
//				System.out.println ("inside DbAccess - getSalt " + resultSet.getString("passSalt"));
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
	//			System.out.println ("inside DbAccess - getPass " + resultSet.getString("userPassword"));
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
	
	private int getUserId(String userName)
	{
		return 1;
	}
	
	public void getUserVehicle(String userName)
	{
		String cleanUserName = sanitizeUserName(userName);
		int userId = getUserId(userName);
		try
		{
			this.getUserVehicles.setInt(1, userId);
			ResultSet resultSet = this.getUserVehicles.executeQuery();
			while (resultSet.next())
			{
				//  TODO  loop through result set getting all vehicles.  Set them into an array
	//			System.out.println ("inside DbAccess - getSalt " + resultSet.getString("passSalt"));
			//	return resultSet.getString("passSalt");
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of user salt " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("User Table: " + e.getErrorCode());
			e.printStackTrace();
		}
//		return null;
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
	//
	// public void insertVehicle(String name, String url, int family)
	// {
	// // clean the in bound data before putting it into the database
	// String cleanName = sanitizeName(name);
	// String cleanURL = sanitizeURL(url);
	// int cleanFamily = sanitizeFamily(family);
	//
	// try
	// {
	// // this.conn1.setAutoCommit(false);
	// /**
	// * using the prepared statement note, using only sanitized data this
	// * will avoid sql injection attacks
	// */
	//
	// this.insertVehicle.setString(1, cleanName);
	// this.insertVehicle.setString(2, cleanURL);
	// this.insertVehicle.setInt(3, cleanFamily);
	// insertVehicle.execute();
	// } catch (SQLException e)
	// {
	// System.out.println("error on insert of vehicle " + e);
	// System.out.println("SQLException: " + e.getMessage());
	// System.out.println("SQLState: " + e.getSQLState());
	// System.out.println("VendorError: " + e.getErrorCode());
	// e.printStackTrace();
	// }
	// }
	//
	// public void getVehicleInOrder()
	// {
	// try
	// {
	// ResultSet resultSet = statement
	// .executeQuery("SELECT * FROM vehicle order by make, model");
	//
	// while (resultSet.next())
	// {
	// // the following statement also works. Showing options only
	// // System.out.println(resultSet.getString(1) + "\t" +
	// resultSet.getString(2) + " \t" + resultSet.getString(3));
	// String make = resultSet.getString("make"); // Note, these
	// // are
	// // backwards
	// // to show
	// int id = resultSet.getInt("vehicleId"); // that the order doesn't
	// // matter. It is using
	// // the field name
	// // (column name)
	// String model = resultSet.getString("model");
	// int year = resultSet.getInt("year");
	// String color = resultSet.getString("color");
	// System.out.println(id + " " + make + " " + model + " " + year + " " +
	// color);
	// }
	// } catch (SQLException ex)
	// {
	// // // handle any errors
	// System.out.println("SQLException: " + ex.getMessage());
	// System.out.println("SQLState: " + ex.getSQLState());
	// System.out.println("VendorError: " + ex.getErrorCode());
	// ex.printStackTrace();
	// }
	// }
	//
	// public void getVehiclebyId(int ID)
	// {
	// System.out.println("~~~~~~ vehicle by id");
	// try
	// {
	// this.getVehicleById.setInt(1, ID);
	// ResultSet resultSet = this.getVehicleById.executeQuery();
	//
	// while (resultSet.next())
	// {
	// String make = resultSet.getString("make");
	// int id = resultSet.getInt("vehicleId");
	// String model = resultSet.getString("model");
	// int year = resultSet.getInt("year");
	// String color = resultSet.getString("color");
	// System.out.println(id + " " + make + " " + model + " " + year + " " +
	// color);
	// }
	// } catch (SQLException ex)
	// {
	// // handle any errors
	// System.out.println("SQLException: " + ex.getMessage());
	// System.out.println("SQLState: " + ex.getSQLState());
	// System.out.println("VendorError: " + ex.getErrorCode());
	// ex.printStackTrace();
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

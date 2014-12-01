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
	private PreparedStatement insertNewVehicle;
	private PreparedStatement insertNewTire;
	private PreparedStatement getVehicleId;
	private PreparedStatement updateUserPassword;
	private PreparedStatement getAllUser;
	private PreparedStatement getValidUser;
	private PreparedStatement getUserSalt;
	private PreparedStatement getUserPass;
	private PreparedStatement getUserVehicles;
	private PreparedStatement updateMakeAndModel;
	private PreparedStatement getMake;
	private PreparedStatement getColor;
	private PreparedStatement getColorId;
	private PreparedStatement getColorString;
	private PreparedStatement getMakeForVehicle;
	private PreparedStatement getMakeId;
	private PreparedStatement getMakeIdFromMake;
	private PreparedStatement getModel;
	private PreparedStatement getModelIdFromModel;
	private PreparedStatement getModelforVehicle;
	private PreparedStatement updateMileage;
	private PreparedStatement getMileage;
	private PreparedStatement getNickName;
	private PreparedStatement updateTiresOnStudsDate;
	private PreparedStatement updateTiresOffStudsDate;
	private PreparedStatement updateNickName;
	private PreparedStatement updateColor;
	private PreparedStatement updateLicensePlate;
	private PreparedStatement getLicensePlate;
	private PreparedStatement getStudsOnDate;
	private PreparedStatement getStudsOffDate;
	private PreparedStatement getTireType;
	private PreparedStatement updateTireType;
	
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
			// User statements
			String insert = "INSERT INTO `car_l_marx`.`userTable` (`userName`, `userPassword`, `passSalt`, `fName`, `lname`, `userEmail`) VALUES (?, ?, ?, ?, ?, ?)";
			insertUser = conn.prepareStatement(insert);
			insert = "INSERT INTO `car_l_marx`.`vehicleTable` (`idVehicle`, `userName`, `idmake`, `idModel`, `nickName`, `idColor`, `licensePlate`, `mileage`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
			insertNewVehicle = conn.prepareStatement(insert);
			insert = "INSERT INTO `car_l_marx`.`tireTable` (`idVehicle`, `offStudsDate`, `onStudsDate`, `tireType`) VALUES (?, ?, ?, ?)";
			insertNewTire = conn.prepareStatement(insert);
			
			String update = "UPDATE `userTable` SET `userPassword`= ? WHERE `userName` = ?";
			updateUserPassword = conn.prepareStatement(update);
			
			String getAllUserString = "SELECT * FROM `userTable`";
			getAllUser = conn.prepareStatement(getAllUserString);
			String getValidUserString = "SELECT * FROM `userTable` WHERE `UserName` LIKE ?";
			getValidUser = conn.prepareStatement(getValidUserString);
			String getUserSaltString = "SELECT `passSalt` FROM  `userTable` WHERE  `userName` LIKE  ?";
			getUserSalt = conn.prepareStatement(getUserSaltString);
			String getUserPassString = "SELECT `userPassword` FROM  `userTable` WHERE  `userName` LIKE  ?";
			getUserPass = conn.prepareStatement(getUserPassString);
			String getUserVehiclesString = "SELECT  idvehicle, makeTable.make, modelTable.model, vehicleTable.nickName, colorTable.color, licensePlate,  mileage FROM  `vehicleTable` INNER JOIN makeTable ON vehicleTable.idmake = makeTable.idmake INNER JOIN modelTable ON vehicleTable.idmodel = modelTable.idmodel INNER JOIN colorTable ON vehicleTable.idColor = colorTable.idcolor WHERE userName LIKE ? ORDER BY `idVehicle`";
			getUserVehicles = conn.prepareStatement(getUserVehiclesString);

			String getMakeString = "UPDATE  `vehicleTable` SET  `idMake` = ?, `idModel` = ? WHERE  `idVehicle` = ? ";
			updateMakeAndModel = conn.prepareStatement(getMakeString);
			getMakeString = "SELECT * FROM `makeTable` ORDER BY `make`";
			getMake = conn.prepareStatement(getMakeString);
			getMakeString = "SELECT make FROM `makeTable` INNER JOIN vehicleTable ON vehicleTable.idMake = makeTable.idMake WHERE vehicleTable.idVehicle = ?";
			getMakeForVehicle = conn.prepareStatement(getMakeString);
			getMakeString = "SELECT idMake FROM  `makeTable` WHERE  `make` =  ?";
			getMakeIdFromMake = conn.prepareStatement(getMakeString);
			String getMakeIdString = "SELECT `idMake` FROM  `makeTable` WHERE  `make` =  ?";
			getMakeId = conn.prepareStatement(getMakeIdString);
			String getModelString = "SELECT  `idModel` FROM  `modelTable`WHERE  `model` =  ?";
			getModelIdFromModel = conn.prepareStatement(getModelString);
			getModelString = "SELECT model from modelTable WHERE idMake = ? ";
			getModel = conn.prepareStatement(getModelString);
			getModelString = "SELECT model FROM `modelTable` INNER JOIN vehicleTable ON vehicleTable.idModel = modelTable.idModel WHERE vehicleTable.idVehicle = ?";
			getModelforVehicle = conn.prepareStatement(getModelString);

			String getMileageString = "SELECT `mileage` FROM `vehicleTable` WHERE `idVehicle` = ? ";
			getMileage = conn.prepareStatement(getMileageString);
			String updateMileageString = "UPDATE  `vehicleTable` SET  `mileage` = ? WHERE  `idVehicle` = ? ";
			updateMileage = conn.prepareStatement(updateMileageString);
			
			String get = "SELECT  `nickName` FROM  `vehicleTable` WHERE  `idVehicle` = ?";
			getNickName = conn.prepareStatement(get );
			get = "SELECT `idVehicle` FROM `vehicleTable` WHERE `userName` = ? AND `licensePlate` = ?";
			getVehicleId = conn.prepareStatement(get );
			get = "SELECT  `licensePlate` FROM  `vehicleTable` WHERE  `idVehicle` = ?";
			getLicensePlate = conn.prepareStatement(get );
			get = "SELECT idColor FROM  `colorTable` WHERE  `Color` =  ?";
			getColorId = conn.prepareStatement(get);

			update = "UPDATE `tireTable` SET `onStudsDate`= ? WHERE idVehicle = ?";
			updateTiresOnStudsDate = conn.prepareStatement(update );
			update = "UPDATE `tireTable` SET `offStudsDate`= ? WHERE idVehicle = ?";
			updateTiresOffStudsDate = conn.prepareStatement(update );
			update = "UPDATE `tireTable` SET `tireType`= ? WHERE `idVehicle` = ?";
			updateTireType = conn.prepareStatement(update );
			update = "UPDATE `vehicleTable` SET `nickName`= ? WHERE `idVehicle` = ?";
			updateNickName = conn.prepareStatement(update );
			update = "UPDATE `vehicleTable` SET `licensePlate`= ? WHERE `idVehicle` = ?";
			updateLicensePlate = conn.prepareStatement(update );
			update = "UPDATE `vehicleTable` SET `idColor`= ? WHERE `idVehicle` = ?";
			updateColor = conn.prepareStatement(update );
			
			get = "SELECT `onStudsDate` FROM  `tireTable` WHERE  `idVehicle` = ?";
			getStudsOnDate = conn.prepareStatement(get );
			get = "SELECT `offStudsDate` FROM  `tireTable` WHERE  `idVehicle` = ?";
			getStudsOffDate = conn.prepareStatement(get );
			get = "SELECT `tireType` FROM `tireTable` WHERE `idVehicle` = ?";
			getTireType = conn.prepareStatement(get );
			get = "SELECT  `Color` FROM `colorTable` ORDER BY Color";
			getColor = conn.prepareStatement(get );
			get = "SELECT  `Color` FROM `colorTable`  INNER JOIN vehicleTable ON colorTable.idColor = vehicleTable.idColor  WHERE vehicleTable.idVehicle = ?";
			getColorString = conn.prepareStatement(get );
			
		} catch (SQLException e)
		{
			System.err.println("problem creating prepared statements " + e);
		}
	}

	/**
	 *   Get salt for user name
	 * @param userName
	 * @return String  - salt for password encryption
	 */
	public String getSalt(String userName)
	{
		String cleanUserName = sanitizeUserName(userName);
		try
		{
			this.getUserSalt.setString(1, cleanUserName);
			ResultSet resultSet = this.getUserSalt.executeQuery();
			while (resultSet.next())
			{
//				System.out.println("inside DbAccess - getSalt "
//						+ resultSet.getString("passSalt"));
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
	
	public String getNickName(int vehicleId)
	{
		try
		{
			this.getNickName.setInt(1, vehicleId);
			ResultSet resultSet = this.getNickName.executeQuery();
			while (resultSet.next())
			{
				return resultSet.getString("nickName");
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of  nick name " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VehicleTable " + e.getErrorCode());
			e.printStackTrace();
		}
		return null;
	}

	public void updateMakeAndModel(int vehicleId, int makeId, int modelId)
	{
	//	System.out.println("DbAccess - updateMakeAndModel " + modelId);
		try
		{
			this.updateMakeAndModel.setInt(1, makeId);
			this.updateMakeAndModel.setInt(2, modelId);
			this.updateMakeAndModel.setInt(3, vehicleId);
			this.updateMakeAndModel.execute();
		} catch (SQLException e)
		{
			System.out.println("error on update of make and model " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("vehicle Table: " + e.getErrorCode());
			e.printStackTrace();
		}
	}

	public ArrayList<MakeObject> getMake()
	{
		ArrayList<MakeObject> makeList = new ArrayList<MakeObject>();
		try
		{
			ResultSet resultSet = this.getMake.executeQuery();
			while (resultSet.next())
			{
				// System.out.println ("inside DbAccess - getMake " +
				// resultSet.getInt("idMake") + "  " +
				// resultSet.getString("make"));
				MakeObject makeItem = new MakeObject(
						resultSet.getString("make"));
				makeList.add(makeItem);
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of get make " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Make Table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return makeList;
	}
	
	public ArrayList<String> getColor()
	{
		ArrayList<String> colorList = new ArrayList<String>();
		try
		{
			ResultSet resultSet = this.getColor.executeQuery();
			while (resultSet.next())
			{
				String colorItem = new String(
						resultSet.getString("color"));
				colorList.add(colorItem);
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of get color " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Color Table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return colorList;
	}

	public String getColorString(int vehicleId)
	{
		try
		{
			this.getColorString.setInt(1, vehicleId);
			ResultSet resultSet = this.getColorString.executeQuery();
			while (resultSet.next())
			{
				return resultSet.getString("color");
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of get color " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Color Table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return ""; // no make found - problem - should not happen
	}

	public int getMakeId(String make)
	{
		try
		{
			this.getMakeId.setString(1, make);
			ResultSet resultSet = this.getMakeId.executeQuery();
			while (resultSet.next())
			{
				System.out.println("DbAccess - getMakeId " + resultSet.getInt("idMake"));
				return resultSet.getInt("idMake");
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of get makeId " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Make Table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return -999; // no make found - problem - should not happen
	}
	
	public int getColorId(String color)
	{
		try
		{
			this.getColorId.setString(1, color);
			ResultSet resultSet = this.getColorId.executeQuery();
			while (resultSet.next())
			{
				return resultSet.getInt("idColor");
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of get colorId " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Color Table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return -999; // no make found - problem - should not happen
	}

	public int getMakeIdForVehicle(int make)
	{
		try
		{
			this.getMakeId.setInt(1, make);
			ResultSet resultSet = this.getMakeId.executeQuery();
			while (resultSet.next())
			{
				return resultSet.getInt("idMake");
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of get makeId " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Make Table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return -999; // no make found - problem - should not happen
	}

	public String getMakeForVehicle(int vehicleId)
	{
		try
		{
			this.getMakeForVehicle.setInt(1, vehicleId);
			ResultSet resultSet = this.getMakeForVehicle.executeQuery();
			while (resultSet.next())
			{
				return resultSet.getString("make");
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of get make for vehicle " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Make Table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return null; // no make found - problem - should not happen
	}
	
	public int getMakeIdFromMake(String make)
	{
		try
		{
			this.getMakeIdFromMake.setString(1, make);
			ResultSet resultSet = this.getMakeIdFromMake.executeQuery();
			while (resultSet.next())
			{
				return resultSet.getInt("idMake");
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of get make id from make " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Make Table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return -999;
	}

	public int getModelIdFromModel(String model)
	{
		try
		{
			this.getModelIdFromModel.setString(1, model);
			ResultSet resultSet = this.getModelIdFromModel.executeQuery();
			while (resultSet.next())
			{
				return resultSet.getInt("idModel");
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of get model id from model " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Model Table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return -999;
	}
	
	public int getVehicleId(String userName, String licensePlate)
	{
		try
		{
			this.getVehicleId.setString(1, userName);
			this.getVehicleId.setString(2, licensePlate);
			ResultSet resultSet = this.getVehicleId.executeQuery();
			while (resultSet.next())
			{
				return resultSet.getInt("idVehicle");
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of get vehicle id from vehicle " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Vehicle Table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return -999;
	}

	public ArrayList<ModelObject> getModel(int make)
	{
		ArrayList<ModelObject> modelList = new ArrayList<ModelObject>();
		try
		{
			this.getModel.setInt(1, make);
			ResultSet resultSet = this.getModel.executeQuery();
			while (resultSet.next())
			{
				// System.out.println ("inside DbAccess - getModel " +
				// resultSet.getString("model"));
				ModelObject modelItem = new ModelObject(
						resultSet.getString("model"));
				modelList.add(modelItem);

			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of get model " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Model Table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return modelList;
	}

	public String getModelString(int vehicleId)
	{
		try
		{
			this.getModelforVehicle.setInt(1, vehicleId);
			ResultSet resultSet = this.getModelforVehicle.executeQuery();
			while (resultSet.next())
			{
				return resultSet.getString("model");
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of get model string" + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Model Table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return null;
	}

	public void updateMileage(int vehicleId, int newMileage)
	{
		int cleanVehicleId = sanitizeNum(vehicleId);
		int cleanNewMileage = sanitizeNum(newMileage);

		int oldMileage = this.getMileage(cleanVehicleId);
		int newMiles = oldMileage + cleanNewMileage;
		try
		{
			this.updateMileage.setInt(1, newMiles);
			this.updateMileage.setInt(2, cleanVehicleId);
			this.updateMileage.execute();
		} catch (SQLException e)
		{
			System.out.println("error on fetch of update mileage" + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("vehicle table: " + e.getErrorCode());
			e.printStackTrace();
		}
	}
	
	public void updateColor(int vehicleId, int colorId)
	{
		try
		{
			this.updateColor.setInt(1, colorId);
			this.updateColor.setInt(2, vehicleId);
			this.updateColor.execute();
		} catch (SQLException e)
		{
			System.out.println("error on fetch of update color" + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("vehicle table: " + e.getErrorCode());
			e.printStackTrace();
		}
	}

	public void updateTiresStudsOnDate(int vehicleId, String currentDate)
	{
		try
		{
			this.updateTiresOnStudsDate.setString(1, currentDate);
			this.updateTiresOnStudsDate.setInt(2, vehicleId);
			this.updateTiresOnStudsDate.execute();
		} catch (SQLException e)
		{
			System.out.println("error on fetch of update studs on date" + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Tire table: " + e.getErrorCode());
			e.printStackTrace();
		}
	}
	
	public void updateTiresStudsOffDate(int vehicleId, String currentDate)
	{
		try
		{
			this.updateTiresOffStudsDate.setString(1, currentDate);
			this.updateTiresOffStudsDate.setInt(2, vehicleId);
			this.updateTiresOffStudsDate.execute();
		} catch (SQLException e)
		{
			System.out.println("error on fetch of update studs off date" + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Tire table: " + e.getErrorCode());
			e.printStackTrace();
		}
	}
	
	public void updateTireType(int vehicleId, String tireType)
	{
		try
		{
			this.updateTireType.setString(1, tireType);
			this.updateTireType.setInt(2, vehicleId);
			this.updateTireType.execute();
		} catch (SQLException e)
		{
			System.out.println("error on fetch of update tire type " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Tire table: " + e.getErrorCode());
			e.printStackTrace();
		}
	}
	
	public int getMileage(int vehicleId)
	{
		int cleanVehicleId = sanitizeNum(vehicleId);
		try
		{
			this.getMileage.setInt(1, cleanVehicleId);
			ResultSet resultSet = this.getMileage.executeQuery();
			while (resultSet.next())
			{
				return (resultSet.getInt("mileage"));
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of get mileage" + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("vehicle table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateNickName(int vehicleId, String nickName)
	{
		int cleanVehicleId = sanitizeNum(vehicleId);
		String cleanNickName = sanitizeString(nickName);
		try
		{
			this.updateNickName.setString(1, cleanNickName);
			this.updateNickName.setInt(2, cleanVehicleId);
			this.updateNickName.execute();
		} catch (SQLException e)
		{
			System.out.println("error on update of nick name" + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("vehicle table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateLicensePlate(int vehicleId, String licensePlate)
	{
		int cleanVehicleId = sanitizeNum(vehicleId);
		String cleanLicensePlate = sanitizeString(licensePlate);
		try
		{
			this.updateLicensePlate.setString(1, cleanLicensePlate);
			this.updateLicensePlate.setInt(2, cleanVehicleId);
			this.updateLicensePlate.execute();
		} catch (SQLException e)
		{
			System.out.println("error on update of license plate" + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("vehicle table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return 0;
	}

	public String getLicensePlate(int vehicleId)
	{
		try
		{
			this.getLicensePlate.setInt(1, vehicleId);
			ResultSet resultSet = this.getLicensePlate.executeQuery();
			while (resultSet.next())
			{
				// System.out.println ("inside DbAccess - getPass " +
				// resultSet.getString("userPassword"));
				return resultSet.getString("licensePlate");
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of license plate " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Vehicle Table: " + e.getErrorCode());
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
	
	public String updateUserPassword(String userName, String newPass)
	{
		String cleanUserName = sanitizeUserName(userName);
		try
		{
			this.updateUserPassword.setString(1, newPass);
			this.updateUserPassword.setString(2, cleanUserName);
			
			this.updateUserPassword.execute();

		} catch (SQLException e)
		{
			System.out.println("error on update of user password " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("User Table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return null;
	}

	public String getStudsOnDate(int vehicleId)
	{
		try
		{
			this.getStudsOnDate.setInt(1, vehicleId);
			ResultSet resultSet = this.getStudsOnDate.executeQuery();
			while (resultSet.next())
			{
				return resultSet.getString("onStudsDate");
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of studs on date " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Tire Table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return null;
	}
	
	public String getStudsOffDate(int vehicleId)
	{
		try
		{
			this.getStudsOffDate.setInt(1, vehicleId);
			ResultSet resultSet = this.getStudsOffDate.executeQuery();
			while (resultSet.next())
			{
				return resultSet.getString("offStudsDate");
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of studs off date " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Tire Table: " + e.getErrorCode());
			e.printStackTrace();
		}
		return null;
	}

	public String getTireType(int vehicleId)
	{
		try
		{
			this.getTireType.setInt(1, vehicleId);
			ResultSet resultSet = this.getTireType.executeQuery();
			while (resultSet.next())
			{
				return resultSet.getString("tireType");
			}
		} catch (SQLException e)
		{
			System.out.println("error on fetch of  tire type " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Tire Table: " + e.getErrorCode());
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

//		System.out
//				.println("**************************************************DbAccess - getUserVehicle");
		String cleanUserName = sanitizeUserName(userName);
		try
		{
			this.getUserVehicles.setString(1, cleanUserName);
			ResultSet resultSet = this.getUserVehicles.executeQuery();
			ArrayList<VehiclesObject> vehicleList = new ArrayList<VehiclesObject>();

			// System.out.println("Vehicles row count " +
			// resultSet.getInt("rowCount"));

			while (resultSet.next())
			{
				// TODO loop through result set getting all vehicles. Set them
				// into an array
				System.out.println(resultSet.getInt("idvehicle") + " "
						+ resultSet.getString("makeTable.make") + " "
						+ resultSet.getString("modelTable.model") + " "
						+ resultSet.getString("vehicleTable.nickName") + " "
						+ resultSet.getString("colorTable.color") + " "
						+ resultSet.getString("licensePlate") + " "
						+ resultSet.getInt("mileage"));

				VehiclesObject vehicle = new VehiclesObject(
						resultSet.getInt("idvehicle"),
						resultSet.getString("makeTable.make"),
						resultSet.getString("modelTable.model"),
						resultSet.getString("vehicleTable.nickName"),
						resultSet.getString("colorTable.color"),
						resultSet.getString("licensePlate"),
						resultSet.getInt("mileage"));
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
	
	public void insertNewVehicle(String userName, String nickName, int makeId,
			int modelId, int colorId, String licensePlate, int mileage)
	{
		// TODO clean the in bound data before putting it into the database
		try
		{
			this.insertNewVehicle.setString(1, userName);
			this.insertNewVehicle.setInt(2, makeId);
			this.insertNewVehicle.setInt(3, modelId);
			this.insertNewVehicle.setString(4, nickName);
			this.insertNewVehicle.setInt(5, colorId);
			this.insertNewVehicle.setString(6, licensePlate);
			this.insertNewVehicle.setInt(7, mileage);
			insertNewVehicle.execute();
		} catch (SQLException e)
		{
			System.out.println("error on insert of new vehicle " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("new vehicle error: " + e.getErrorCode());
			e.printStackTrace();
		}
	}

	public void insertNewTire(int vehicleId, String studsOnDate,
			String studsOffDate, String tireType)
	{
		// TODO clean the in bound data before putting it into the database
		try
		{
			this.insertNewTire.setInt(1, vehicleId);
			this.insertNewTire.setString(2, studsOnDate);
			this.insertNewTire.setString(3, studsOffDate);
			this.insertNewTire.setString(4, tireType);
			insertNewTire.execute();
		} catch (SQLException e)
		{
			System.out.println("error on insert of new tire " + e);
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("new tire error: " + e.getErrorCode());
			e.printStackTrace();
		}
		
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
	
	private String sanitizeString(String string)
	{
		// TODO sanitize email string - use regular expression?
		return string;
	}

	private int sanitizeNum(int num)
	{
		// TODO actually sanitize the number
		return num;
	}

}

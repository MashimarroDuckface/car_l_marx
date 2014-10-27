/*   Car-L-Marx
 *
 *   Oct 25, 2014  
 *   CS 320 Fall 2014
 *
 *		Michael Allen-Bond
 *		Lise Driggers
 *		Jesse Pomerenk
 *
 *		controller
 *
 *   LogonStart.java
*/
package controller;

import model.*;
import views.*;

public class LogonStart
{
	private boolean goodLogon = false;
	private LoginFrame loginView;
	private DbAccess DbHandle;
	private LogonController logonController;
	private MainController mController;
	
	public LogonStart(MainController mController)
	{
		loginView = new LoginFrame(); 
		DbHandle = DbAccess.getInstance();
		this.mController  = mController;
		logonController  = new LogonController( loginView,  DbHandle,  mController);
		
	//	boolean good = logonGetGoing();
	}
	
	public void logonGetGoing()
	{
		loginView.setVisible(true);
		this.goodLogon = logonController.startLogon();
	}
	
	public boolean getGoodLogon()
	{
		return this.goodLogon;
	}
	
	public String getUser()
	{
		return logonController.getuser();
	}
}

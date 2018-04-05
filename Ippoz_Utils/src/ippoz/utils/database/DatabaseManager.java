/**
 * 
 */
package ippoz.utils.database;

import ippoz.utils.logging.AppLogger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Class DatabaseManager.
 * Instantiates a MYSQL Database manager
 *
 * @author Tommy
 */
public class DatabaseManager {
	
	/** The database connector. */
	private DatabaseConnector connector;
	
	/**
	 * Instantiates a new database manager.
	 *
	 * @param dbName the database name
	 * @param username the database username
	 * @param password the database password
	 */
	public DatabaseManager(String url, String dbName, String driver, String username, String password){
		try {
			connector = new DatabaseConnector(url, dbName, driver, username, password, false);
		} catch(Exception ex){
			AppLogger.logInfo(getClass(), "Need to start MySQL Server...");
		}
	}
	
	public ArrayList<HashMap<String, String>> executeQuery(String query){
		return connector.executeCustomQuery(null, query);
	}

	/**
	 * Flushes database manager.
	 *
	 * @throws SQLException the SQL exception
	 */
	public void flush() throws SQLException {
		connector.closeConnection();
		connector = null;
	}


}

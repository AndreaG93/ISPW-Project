package controllers.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDatabase {

	// JDBC driver name and database URL
	protected static final String JDBC_DRIVER = "org.postgresql.Driver";
	protected static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

	// Database credentials
	protected static final String USER = "postgres";
	protected static final String PASS = "123";

	/**
	 * This method is used to perform a query to database.
	 * 
	 * @param SQL
	 *            - A {@code String} object.
	 */
	protected void executeUpdate(String SQL) {
		Connection myConnection = null;
		Statement myStatement = null;

		try {
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// Open a connection
			System.out.println("Connecting to a selected database...");
			myConnection = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");

			// Execute a query
			myStatement = myConnection.createStatement();
			myStatement.executeUpdate(SQL);
			System.out.println("SQL query has been performed...");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/**
			 * Closing Statement Object
			 */
			try {
				if (myStatement != null)
					myConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			/**
			 * Closing Connection Object
			 */
			try {
				if (myConnection != null)
					myConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method is used to get a new ID.
	 * 
	 * @param SQL
	 *            - Represents a {@code String} object.
	 * @return An {@code int}.
	 */
	public int getNewerIDForInsert(String SQL) {
		int obj = -1;
		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet rs = null;

		try {
			/**
			 * Register JDBC driver
			 */
			Class.forName(JDBC_DRIVER);

			/**
			 * Open connection
			 */
			System.out.println("Connecting to a selected database...");
			myConnection = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");

			/**
			 * Execute SQL query
			 */
			myStatement = myConnection.createStatement();
			rs = myStatement.executeQuery(SQL.toString());

			/**
			 * Creating object if exist
			 */
			if (rs.next())
				obj = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			/**
			 * Closing Statement Object
			 */
			try {
				if (myStatement != null)
					myConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			/**
			 * Closing Connection Object
			 */
			try {
				if (myConnection != null)
					myConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ++obj;
	}

}

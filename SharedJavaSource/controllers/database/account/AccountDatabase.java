package controllers.database.account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import controllers.database.AbstractDatabase;
import entities.account.Account;
import entities.account.builder.AccountBuilderDirector;

/**
 * The {@code AccountDatabase} class is used to store and retrieve
 * {@code Account} object from database.
 * 
 * @author Andrea Graziani
 * @see Account
 * @version 1.0
 */
public class AccountDatabase extends AbstractDatabase {
	private static AccountDatabase myInstance = null;

	private static final String TABLE_NAME = "Account";

	private static final String COLUMN_ACCOUNT_ID = "ID";
	private static final String COLUMN_EMAIL = "Email";
	private static final String COLUMN_PASSWORD = "Password";
	private static final String COLUMN_TYPE = "Type";
	private static final String COLUMN_REGISTRATION_DATE = "RegistrationDate";

	/**
	 * This method is used to get an instance of {@code AccountDatabase} class.
	 * 
	 * @return An {@code AccountDatabase} object.
	 */
	public static AccountDatabase getInstance() {
		if (myInstance == null)
			myInstance = new AccountDatabase();
		return myInstance;
	}

	private AccountDatabase() {
	}

	/**
	 * This method is used to verify if specified email already exists.
	 * 
	 * @param arg0
	 *            - A {@code String} that represents the {@code Account}
	 *            object's Email
	 * @return - A {@code boolean} object.
	 */
	public int checkExistingEmail(String arg0) {
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
			 * Create SQL query
			 */
			StringBuilder SQL = new StringBuilder();
			SQL.append(" SELECT ");
			SQL.append(COLUMN_ACCOUNT_ID).append(",");
			SQL.append(COLUMN_EMAIL);
			SQL.append(" FROM ");
			SQL.append(TABLE_NAME);
			SQL.append(" WHERE ");
			SQL.append(COLUMN_EMAIL).append("=");
			SQL.append("'").append(arg0).append("'");

			/**
			 * Execute SQL query
			 */
			System.out.println(SQL.toString());
			myStatement = myConnection.createStatement();
			rs = myStatement.executeQuery(SQL.toString());

			if (rs.next())
				if (rs.getString(COLUMN_EMAIL).equals(arg0))
					obj = rs.getInt(COLUMN_ACCOUNT_ID);

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
		return obj;
	}

	/**
	 * This method is used to get a new ID for a new {@code Account} object.
	 */
	public int getNewID() {

		/**
		 * Create SQL query
		 */
		StringBuilder SQL = new StringBuilder();
		SQL.append(" SELECT MAX(");
		SQL.append(COLUMN_ACCOUNT_ID).append(")");
		SQL.append(" FROM ");
		SQL.append(TABLE_NAME);

		return getNewerIDForInsert(SQL.toString());
	}

	/**
	 * This method is used to get a {@code Account} object from database.
	 * 
	 * @param arg0
	 *            - A {@code int} that represents the {@code Account} object's
	 *            ID.
	 * @return a {@code Account} object
	 */
	public Account get(int arg0) {
		Account obj = null;
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
			 * Create SQL query
			 */
			StringBuilder SQL = new StringBuilder();
			SQL.append(" SELECT * FROM ");
			SQL.append(TABLE_NAME);
			SQL.append(" WHERE ");
			SQL.append(COLUMN_ACCOUNT_ID).append("=");
			SQL.append(arg0);

			/**
			 * Execute SQL query
			 */
			myStatement = myConnection.createStatement();
			rs = myStatement.executeQuery(SQL.toString());

			/**
			 * Creating object if exist
			 */
			if (rs.next())
				obj = AccountBuilderDirector.createsAccountObject(rs.getInt(COLUMN_ACCOUNT_ID),
						rs.getString(COLUMN_EMAIL), rs.getString(COLUMN_PASSWORD), rs.getString(COLUMN_TYPE),
						rs.getDate(COLUMN_REGISTRATION_DATE).toLocalDate());

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
		return obj;
	}

	/**
	 * This method inserts a new {@code Account} object.
	 * 
	 * @param ID
	 *            - Represents an {@code int}.
	 * @param email
	 *            - Represents a {@code String} object.
	 * @param pass
	 *            - Represents a {@code String} object.
	 * @param type
	 *            - Represents a {@code String} object.
	 */
	public void insert(int ID, String email, String pass, String type) {

		StringBuilder SQL = new StringBuilder();

		SQL.append(" INSERT INTO ");
		SQL.append(TABLE_NAME);
		SQL.append(" ( ");
		SQL.append(COLUMN_ACCOUNT_ID).append(", ");
		SQL.append(COLUMN_EMAIL).append(", ");
		SQL.append(COLUMN_PASSWORD).append(", ");
		SQL.append(COLUMN_TYPE).append(", ");
		SQL.append(COLUMN_REGISTRATION_DATE);
		SQL.append(" ) ");
		SQL.append("VALUES");
		SQL.append("(").append(ID).append(", ");
		SQL.append("'").append(email).append("', ");
		SQL.append("'").append(pass).append("', ");
		SQL.append("'").append(type).append("', ");
		SQL.append("'").append(LocalDate.now()).append("')");

		System.out.println("Query:\n" + SQL.toString());
		this.executeUpdate(SQL.toString());
	}

	/**
	 * This method is used to delete an existing {@code Account} object.
	 * 
	 * @param arg0
	 *            - Represents an {@code Account} object.
	 */
	public void remove(Account arg0) {
		StringBuilder SQL = new StringBuilder();

		SQL.append(" DELETE FROM ");
		SQL.append(TABLE_NAME);
		SQL.append(" WHERE ");
		SQL.append(COLUMN_ACCOUNT_ID).append(" = ");
		SQL.append(arg0.getID());

		System.out.println("Query:\n" + SQL.toString());
		this.executeUpdate(SQL.toString());
	}

	/**
	 * This method updates an existing {@code Account} object.
	 * 
	 * @param arg0
	 *            - Represents an {@code Account} object.
	 */
	public void update(Account arg0) {
		StringBuilder SQL = new StringBuilder();

		SQL.append(" UPDATE ");
		SQL.append(TABLE_NAME);
		SQL.append(" SET ");
		SQL.append(COLUMN_EMAIL).append(" = '");
		SQL.append(arg0.getEmail()).append("',");
		SQL.append(COLUMN_PASSWORD).append(" = '");
		SQL.append(arg0.getPassword()).append("',");
		SQL.append(COLUMN_TYPE).append(" = '");
		SQL.append(arg0.getType().getClass().getName()).append("'");
		SQL.append(" WHERE ");
		SQL.append(COLUMN_ACCOUNT_ID).append(" = ");
		SQL.append(arg0.getID());

		System.out.println("Query:\n" + SQL.toString());
		this.executeUpdate(SQL.toString());
	}
	
	
	

}

package controllers.database.announcement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Vector;

import controllers.database.AbstractDatabase;
import controllers.database.product.book.BookDatabase;
import controllers.database.product.dress.DressDatabase;
import entities.announcement.Announcement;
import entities.announcement.builder.AnnouncementBuilderDirector;
import entities.product.Product;

public class AnnouncementDatabase extends AbstractDatabase {
	private static AnnouncementDatabase myInstance = null;

	private static final String TABLE_NAME = "Announcement";

	private static final String COLUMN_ID = "ID";
	private static final String COLUMN_ACCOUNT_ID = "OwnerAccountID";
	private static final String COLUMN_PRICE = "Price";
	private static final String COLUMN_TYPE = "Type";
	private static final String COLUMN_STATUS = "Status";
	private static final String COLUMN_REGISTRATION_DATE = "RegistrationDate";

	/**
	 * This method is used to get an instance of {@code AnnouncementDatabase}
	 * class.
	 * 
	 * @return An {@code AnnouncementDatabase} object.
	 */
	public static AnnouncementDatabase getInstance() {
		if (myInstance == null)
			myInstance = new AnnouncementDatabase();
		return myInstance;
	}

	private AnnouncementDatabase() {
	}

	/**
	 * This method is used to delete an existing {@code Announcement} object.
	 * 
	 * @param arg0
	 *            - Represents an {@code Announcement} object.
	 */
	public void remove(Announcement arg0) {
		StringBuilder SQL = new StringBuilder();

		SQL.append(" DELETE FROM ");
		SQL.append(TABLE_NAME);
		SQL.append(" WHERE ");
		SQL.append(COLUMN_ID).append(" = ");
		SQL.append(arg0.getID());

		System.out.println("Query:\n" + SQL.toString());
		this.executeUpdate(SQL.toString());
	}
	
	/**
	 * This method is used to get a new ID for a new {@code Announcement} object.
	 */
	public int getNewID() {

		/**
		 * Create SQL query
		 */
		StringBuilder SQL = new StringBuilder();
		SQL.append(" SELECT MAX(");
		SQL.append(COLUMN_ID).append(")");
		SQL.append(" FROM ");
		SQL.append(TABLE_NAME);

		return getNewerIDForInsert(SQL.toString());
	}
	
	/**
	 * Get All stored 
	 * @return
	 */
	public Vector<Announcement> getAll()
	{
		StringBuilder SQL = new StringBuilder();
		SQL.append(" SELECT * FROM ");
		SQL.append(TABLE_NAME);
		
		return getBySQL(SQL.toString());
	}
	
	/**
	 * 
	 * @param arg0
	 * @return
	 */
	public Vector<Announcement> getAllByAccountID(int arg0)
	{
		StringBuilder SQL = new StringBuilder();
		SQL.append(" SELECT * FROM ");
		SQL.append(TABLE_NAME);
		SQL.append(" WHERE ");
		SQL.append(COLUMN_ACCOUNT_ID).append("=");
		SQL.append(arg0);
		
		return getBySQL(SQL.toString());
	}
	
	/**
	 * 
	 * @param SQL
	 * @return
	 */
	public Vector<Announcement> getBySQL(String SQL) {
		Vector<Announcement> obj = new Vector<Announcement>();
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
			System.out.println(SQL);
			myStatement = myConnection.createStatement();
			rs = myStatement.executeQuery(SQL);

			/**
			 * Creating object if exist
			 */
			while (rs.next()) {

				Product myProd = null;
				Product myDress = DressDatabase.getInstance().get(rs.getInt(COLUMN_ID));
				Product myBook = BookDatabase.getInstance().get(rs.getInt(COLUMN_ID));
				
				if(myDress == null)
					myProd = myBook;
				else
					myProd = myDress;
			
				Announcement myAD = AnnouncementBuilderDirector.createsAnnouncementObject(rs.getInt(COLUMN_ID), rs.getInt(COLUMN_ACCOUNT_ID),
						rs.getFloat(COLUMN_PRICE), rs.getString(COLUMN_STATUS), myProd,
						rs.getDate(COLUMN_REGISTRATION_DATE).toLocalDate(), rs.getString(COLUMN_TYPE));
				obj.add(myAD);
			}

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
	 * 
	 * @param ID
	 *            - Represents an {@code int}.
	 * @param OwnerAccountID
	 *            - Represents an {@code int}.
	 * @param price
	 *            - Represents a {@code float}.
	 * @param type
	 *            - Represents a {@code String} object.
	 * @param status
	 *            - Represents a {@code String} object.
	 */
	public void insert(int ID, int OwnerAccountID, float price, String type, String status) {
		StringBuilder SQL = new StringBuilder();

		SQL.append(" INSERT INTO ");
		SQL.append(TABLE_NAME);
		SQL.append(" ( ");
		SQL.append(COLUMN_ID).append(", ");
		SQL.append(COLUMN_ACCOUNT_ID).append(", ");
		SQL.append(COLUMN_PRICE).append(", ");
		SQL.append(COLUMN_TYPE).append(", ");
		SQL.append(COLUMN_STATUS).append(", ");
		SQL.append(COLUMN_REGISTRATION_DATE);
		SQL.append(" ) ");
		SQL.append("VALUES");
		SQL.append("(").append(ID).append(", ");
		SQL.append("'").append(OwnerAccountID).append("', ");
		SQL.append("'").append(price).append("', ");
		SQL.append("'").append(type).append("', ");
		SQL.append("'").append(status).append("', ");
		SQL.append("'").append(LocalDate.now()).append("')");

		System.out.println("Query:\n" + SQL.toString());
		this.executeUpdate(SQL.toString());
	}

	/**
	 * This method updates an existing {@code Announcement} object.
	 * 
	 * @param arg0
	 *            - An {@code Announcement} object
	 */
	public void update(Announcement arg0) {
		StringBuilder SQL = new StringBuilder();

		SQL.append(" UPDATE ");
		SQL.append(TABLE_NAME);
		SQL.append(" SET ");
		SQL.append(COLUMN_PRICE).append(" = '");
		SQL.append(arg0.getType().getCost()).append("', ");
		SQL.append(COLUMN_STATUS).append(" = '");
		SQL.append(arg0.getStatus()).append("', ");
		SQL.append(COLUMN_TYPE).append(" = '");
		SQL.append(arg0.getType().getClass().getName()).append("'");
		SQL.append(" WHERE ");
		SQL.append(COLUMN_ID).append(" = ");
		SQL.append(arg0.getID());

		System.out.println("Query:\n" + SQL.toString());
		this.executeUpdate(SQL.toString());
	}
}

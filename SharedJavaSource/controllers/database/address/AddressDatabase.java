package controllers.database.address;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.database.AbstractDatabase;
import entities.address.Address;
import entities.address.builder.AddressBuilderDirector;

public class AddressDatabase extends AbstractDatabase {
	private static AddressDatabase myIstance = null;

	private static final String TABLE_NAME = "Address";

	private static final String COLUMN_ID = "ID";
	private static final String COLUMN_FULL_NAME = "FullName";
	private static final String COLUMN_ADDRESS_LINE_1 = "AddressLine1";
	private static final String COLUMN_ADDRESS_LINE_2 = "AddressLine2";
	private static final String COLUMN_TOWN = "Town";
	private static final String COLUMN_POSTCODE = "PostCode";
	private static final String COLUMN_PROVINCE = "Province";
	private static final String COLUMN_PHONE_NUMBER = "PhoneNumber";

	public static AddressDatabase getInstance() {
		if (myIstance == null)
			myIstance = new AddressDatabase();
		return myIstance;
	}

	private AddressDatabase() {
	}

	/**
	 * This method is used to get a {@code Address} object identified by
	 * specified ID.
	 * 
	 * @param arg0
	 *            - An {@code int}.
	 * @return A {@code Address} object.
	 */
	public Address get(int arg0) {
		Address obj = null;
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
			SQL.append(COLUMN_ID).append("=");
			SQL.append(arg0);

			/**
			 * Execute SQL query
			 */
			System.out.println(SQL.toString());
			myStatement = myConnection.createStatement();
			rs = myStatement.executeQuery(SQL.toString());

			/**
			 * Creating object if exist
			 */
			if (rs.next())
				obj = AddressBuilderDirector.createsAddressObject(rs.getInt(COLUMN_ID), rs.getString(COLUMN_FULL_NAME),
						rs.getString(COLUMN_ADDRESS_LINE_1), rs.getString(COLUMN_ADDRESS_LINE_2),
						rs.getString(COLUMN_PHONE_NUMBER), rs.getString(COLUMN_TOWN), rs.getString(COLUMN_PROVINCE), rs.getString(COLUMN_POSTCODE));

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
	 * This method inserts a new {@code Address} object.
	 * 
	 * @param ID
	 *            - Represents an {@code int}.
	 * @param fullName
	 *            - Represents a {@code String} object.
	 * @param addressLine1
	 *            - Represents a {@code String} object.
	 * @param addressLine2
	 *            - Represents a {@code String} object.
	 * @param town
	 *            - Represents a {@code String} object.
	 * @param postcode
	 *            - Represents a {@code String} object.
	 * @param province
	 *            - Represents a {@code String} object.
	 * @param phoneNumber
	 *            - Represents a {@code String} object.
	 */
	public void insert(int ID, String fullName, String addressLine1, String addressLine2, String town, String postcode,
			String province, String phoneNumber)  {

		StringBuilder SQL = new StringBuilder();

		SQL.append(" INSERT INTO ");
		SQL.append(TABLE_NAME);
		SQL.append(" ( ");
		SQL.append(COLUMN_ID).append(", ");
		SQL.append(COLUMN_FULL_NAME).append(", ");
		SQL.append(COLUMN_ADDRESS_LINE_1).append(", ");
		SQL.append(COLUMN_ADDRESS_LINE_2).append(", ");
		SQL.append(COLUMN_TOWN).append(", ");
		SQL.append(COLUMN_POSTCODE).append(", ");
		SQL.append(COLUMN_PROVINCE).append(", ");
		SQL.append(COLUMN_PHONE_NUMBER);
		SQL.append(" ) ");
		SQL.append("VALUES");
		SQL.append("(").append(ID).append(", ");
		SQL.append("'").append(fullName).append("', ");
		SQL.append("'").append(addressLine1).append("', ");
		SQL.append("'").append(addressLine2).append("', ");
		SQL.append("'").append(town).append("', ");
		SQL.append("'").append(postcode).append("', ");
		SQL.append("'").append(province).append("', ");
		SQL.append("'").append(phoneNumber).append("')");

		System.out.println("Query:\n" + SQL.toString());
		this.executeUpdate(SQL.toString());
	}

	/**
	 * This method updates an existing {@code Address} object.
	 * 
	 * @param arg0
	 *            - A {@code Address} object
	 */
	public void update(Address arg0) {
		StringBuilder SQL = new StringBuilder();

		SQL.append(" UPDATE ");
		SQL.append(TABLE_NAME);
		SQL.append(" SET ");
		SQL.append(COLUMN_FULL_NAME).append(" = '");
		SQL.append(arg0.getFullName()).append("', ");
		SQL.append(COLUMN_ADDRESS_LINE_1).append(" = '");
		SQL.append(arg0.getAddressLine1()).append("', ");
		SQL.append(COLUMN_ADDRESS_LINE_2).append(" = '");
		SQL.append(arg0.getAddressLine2()).append("', ");
		SQL.append(COLUMN_TOWN).append(" = '");
		SQL.append(arg0.getTown()).append("', ");
		SQL.append(COLUMN_POSTCODE).append(" = '");
		SQL.append(arg0.getPostcode()).append("', ");
		SQL.append(COLUMN_PROVINCE).append(" = '");
		SQL.append(arg0.getProvince()).append("', ");
		SQL.append(COLUMN_PHONE_NUMBER).append(" = '");
		SQL.append(arg0.getPhoneNumber()).append("'");
		SQL.append(" WHERE ");
		SQL.append(COLUMN_ID).append(" = ");
		SQL.append(arg0.getID());

		System.out.println("Query:\n" + SQL.toString());
		this.executeUpdate(SQL.toString());
	}
}

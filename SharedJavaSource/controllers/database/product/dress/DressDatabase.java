package controllers.database.product.dress;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.database.AbstractDatabase;
import entities.product.dress.Dress;
import entities.product.dress.SizeDressEnum;
import entities.product.factory.ProductFactory;

public class DressDatabase extends AbstractDatabase {
	private static DressDatabase myInstance = null;

	private static final String TABLE_NAME = "Dress";

	private static final String COLUMN_ID = "ID";
	private static final String COLUMN_DRESS_NAME = "DressName";
	private static final String COLUMN_BRAND = "Brand";
	private static final String COLUMN_COLOR = "Color";
	private static final String COLUMN_GENDER = "Gender";
	private static final String COLUMN_SIZE = "Size";

	/**
	 * This method is used to get an instance of {@code DressDatabase} class.
	 * 
	 * @return An {@code DressDatabase} object.
	 */
	public static DressDatabase getInstance() {
		if (myInstance == null)
			myInstance = new DressDatabase();
		return myInstance;
	}

	/**
	 * This method is used to get a {@code Dress} object from database.
	 * 
	 * @param arg0
	 *            - A {@code int} that represents the {@code Announcement}
	 *            object's ID.
	 * @return a {@code Dress} object
	 */
	public Dress get(int arg0) {
		Dress obj = null;
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
				obj = ProductFactory.getProduct(rs.getInt(COLUMN_ID), rs.getString(COLUMN_DRESS_NAME), rs.getString(COLUMN_BRAND),
						rs.getString(COLUMN_COLOR), rs.getString(COLUMN_GENDER), rs.getString(COLUMN_SIZE));

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
	 * This method inserts a new {@code Dress} object.
	 * 
	 * @param ID
	 *            - Represents an {@code int}.
	 * @param dressName
	 *            - Represents a {@code String} object.
	 * @param brand
	 *            - Represents a {@code String} object.
	 * @param color
	 *            - Represents a {@code String} object.
	 * @param gender
	 *            - Represents a {@code String} object.
	 * @param size
	 *            - Represents a {@code String} object.
	 */
	public void insert(int ID, String dressName, String brand, String color, String gender, SizeDressEnum size) {
		StringBuilder SQL = new StringBuilder();

		SQL.append(" INSERT INTO ");
		SQL.append(TABLE_NAME);
		SQL.append(" ( ");
		SQL.append(COLUMN_ID).append(", ");
		SQL.append(COLUMN_DRESS_NAME).append(", ");
		SQL.append(COLUMN_BRAND).append(", ");
		SQL.append(COLUMN_COLOR).append(", ");
		SQL.append(COLUMN_GENDER).append(", ");
		SQL.append(COLUMN_SIZE);
		SQL.append(" ) ");
		SQL.append("VALUES");
		SQL.append("(").append(ID).append(", ");
		SQL.append("'").append(dressName).append("', ");
		SQL.append("'").append(brand).append("', ");
		SQL.append("'").append(color).append("', ");
		SQL.append("'").append(gender).append("', ");
		SQL.append("'").append(size).append("')");

		System.out.println("Query:\n" + SQL.toString());
		this.executeUpdate(SQL.toString());
	}

	/**
	 * This method updates an existing {@code Dress} object.
	 * 
	 * @param arg0
	 *            - A {@code Dress} object.
	 */
	public void update(Dress arg0) {
		StringBuilder SQL = new StringBuilder();

		SQL.append(" UPDATE ");
		SQL.append(TABLE_NAME);
		SQL.append(" SET ");
		SQL.append(COLUMN_DRESS_NAME).append(" = '");
		SQL.append(arg0.getDressName()).append("', ");
		SQL.append(COLUMN_BRAND).append(" = '");
		SQL.append(arg0.getBrand()).append("', ");
		SQL.append(COLUMN_COLOR).append(" = '");
		SQL.append(arg0.getColor()).append("', ");
		SQL.append(COLUMN_GENDER).append(" = '");
		SQL.append(arg0.getProductType()).append("', ");
		SQL.append(COLUMN_SIZE).append(" = '");
		SQL.append(arg0.getSize()).append("'");
		SQL.append(" WHERE ");
		SQL.append(COLUMN_ID).append(" = ");
		SQL.append(arg0.getProductID());

		System.out.println("Query:\n" + SQL.toString());
		this.executeUpdate(SQL.toString());
	}

}

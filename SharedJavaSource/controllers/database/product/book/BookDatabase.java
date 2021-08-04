package controllers.database.product.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.database.AbstractDatabase;
import entities.product.book.Book;
import entities.product.factory.ProductFactory;

public class BookDatabase extends AbstractDatabase {
	private static BookDatabase myIstance = null;

	private static final String TABLE_NAME = "Book";

	private static final String COLUMN_ID = "ID";
	private static final String COLUMN_ISBN = "ISBN";
	private static final String COLUMN_TITLE = "Title";
	private static final String COLUMN_SUBTITLE = "SubTitle";
	private static final String COLUMN_AUTHOR = "Author";
	private static final String COLUMN_EDITOR = "Editor";
	private static final String COLUMN_NUMBER_PAGE = "NumberOfPages";
	private static final String COLUMN_TYPE = "Type";

	public static BookDatabase getInstance() {
		if (myIstance == null)
			myIstance = new BookDatabase();
		return myIstance;
	}

	private BookDatabase() {
	}

	/**
	 * This method is used to get a {@code Book} object from database.
	 * 
	 * @param arg0
	 *            - A {@code int} that represents the {@code Book} object's ID.
	 * @return a {@code Book} object
	 */
	public Book get(int arg0) {
		Book obj = null;
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
				obj = ProductFactory.getProduct(rs.getInt(COLUMN_ID), rs.getString(COLUMN_ISBN),
						rs.getString(COLUMN_TITLE), rs.getString(COLUMN_SUBTITLE), rs.getString(COLUMN_EDITOR),
						rs.getString(COLUMN_AUTHOR), rs.getInt(COLUMN_NUMBER_PAGE), rs.getString(COLUMN_TYPE));

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
	 * This method inserts a new {@code Book} object.
	 * 
	 * @param ID
	 *            - Represents aN {@code int}.
	 * @param ISBN
	 *            - Represents a {@code String} object.
	 * @param title
	 *            - Represents a {@code String} object.
	 * @param subtitle
	 *            - Represents a {@code String} object.
	 * @param author
	 *            - Represents a {@code String} object.
	 * @param editor
	 *            - Represents a {@code String} object.
	 * @param numberOfPage
	 *            - Represents aN {@code int}.
	 * @param type
	 *            - Represents a {@code String} object.
	 */
	public void insert(int ID, String ISBN, String title, String subtitle, String author, String editor,
			int numberOfPage, String type) {
		StringBuilder SQL = new StringBuilder();

		SQL.append(" INSERT INTO ");
		SQL.append(TABLE_NAME);
		SQL.append(" ( ");
		SQL.append(COLUMN_ID).append(", ");
		SQL.append(COLUMN_ISBN).append(", ");
		SQL.append(COLUMN_TITLE).append(", ");
		SQL.append(COLUMN_SUBTITLE).append(", ");
		SQL.append(COLUMN_AUTHOR).append(", ");
		SQL.append(COLUMN_EDITOR).append(", ");
		SQL.append(COLUMN_NUMBER_PAGE).append(", ");
		SQL.append(COLUMN_TYPE);
		SQL.append(" ) ");
		SQL.append(" VALUES( ");
		SQL.append(ID).append(",");
		SQL.append("'").append(ISBN).append("',");
		SQL.append("'").append(title).append("',");
		SQL.append("'").append(subtitle).append("',");
		SQL.append("'").append(author).append("',");
		SQL.append("'").append(editor).append("',");
		SQL.append("'").append(numberOfPage).append("',");
		SQL.append("'").append(type).append("')");

		System.out.println("Query:\n" + SQL.toString());
		this.executeUpdate(SQL.toString());
	}

	/**
	 * This method updates an existing {@code Book} object.
	 * 
	 * @param arg0
	 *            - A {@code Book} object.
	 */
	public void update(Book arg0) {
		StringBuilder SQL = new StringBuilder();

		SQL.append(" UPDATE ");
		SQL.append(TABLE_NAME);
		SQL.append(" SET ");
		SQL.append(COLUMN_ISBN).append(" = '");
		SQL.append(arg0.getISBN()).append("', ");
		SQL.append(COLUMN_TITLE).append(" = '");
		SQL.append(arg0.getTitle()).append("', ");
		SQL.append(COLUMN_SUBTITLE).append(" = '");
		SQL.append(arg0.getSubTitle()).append("', ");
		SQL.append(COLUMN_AUTHOR).append(" = '");
		SQL.append(arg0.getAuthor()).append("', ");
		SQL.append(COLUMN_EDITOR).append(" = '");
		SQL.append(arg0.getEditor()).append("', ");
		SQL.append(COLUMN_NUMBER_PAGE).append(" = '");
		SQL.append(arg0.getNumberOfPages()).append("', ");
		SQL.append(COLUMN_TYPE).append(" = '");
		SQL.append(arg0.getProductType()).append("'");
		SQL.append(" WHERE ");
		SQL.append(COLUMN_ID).append(" = ");
		SQL.append(arg0.getProductID());

		System.out.println("Query:\n" + SQL.toString());
		this.executeUpdate(SQL.toString());
	}
}

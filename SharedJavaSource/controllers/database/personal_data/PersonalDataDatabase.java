package controllers.database.personal_data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
//hfdhdfhdfhdfh
import controllers.database.AbstractDatabase;
import entities.personal_data.GenderEnum;
import entities.personal_data.PersonalData;
import entities.personal_data.TitleEnum;
import entities.personal_data.builder.PersonalDataDirector;

public class PersonalDataDatabase extends AbstractDatabase {
	private static PersonalDataDatabase myIstance = null;

	private static final String TABLE_NAME = "PersonalData";

	private static final String COLUMN_ID = "ID";
	private static final String COLUMN_NAME = "Name";
	private static final String COLUMN_SURNAME = "Surname";
	private static final String COLUMN_TITLE = "Title";
	private static final String COLUMN_GENDER = "Gender";
	private static final String COLUMN_BIRTHDAY = "Birthday";

	private PersonalDataDatabase() {
	}

	public static PersonalDataDatabase getInstance() {
		if (myIstance == null)
			myIstance = new PersonalDataDatabase();
		return myIstance;
	}

	/**
	 * This method is used to get a {@code PersonalData} object identified by
	 * specified ID.
	 * 
	 * @param arg0
	 *            - An {@code int}.
	 * @return A {@code PersonalData} object.
	 */
	public PersonalData get(int arg0) {
		PersonalData obj = null;
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
				obj = PersonalDataDirector.createsPersonalDataObject(rs.getInt(COLUMN_ID), rs.getString(COLUMN_TITLE),
						rs.getString(COLUMN_NAME), rs.getString(COLUMN_SURNAME), rs.getString(COLUMN_GENDER),
						rs.getDate(COLUMN_BIRTHDAY).toLocalDate());

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
	 * This method inserts a new {@code PersonalData} object.
	 * 
	 * @param ID
	 *            - Represents a {@code int}.
	 * @param name
	 *            - Represents a {@code String} object.
	 * @param surname
	 *            - Represents a {@code String} object.
	 * @param title
	 *            - Represents a {@code String} object.
	 * @param gender
	 *            - Represents a {@code String} object.
	 * @param birthday
	 *            - Represents a {@code LocalDate} object.
	 */
	public void insert(int ID, String name, String surname, TitleEnum title, GenderEnum gender, LocalDate birthday) {
		StringBuilder SQL = new StringBuilder();

		SQL.append(" INSERT INTO ");
		SQL.append(TABLE_NAME);
		SQL.append(" ( ");
		SQL.append(COLUMN_ID).append(", ");
		SQL.append(COLUMN_NAME).append(", ");
		SQL.append(COLUMN_SURNAME).append(", ");
		SQL.append(COLUMN_TITLE).append(", ");
		SQL.append(COLUMN_GENDER).append(", ");
		SQL.append(COLUMN_BIRTHDAY);
		SQL.append(" ) ");
		SQL.append("VALUES");
		SQL.append("(").append(ID).append(", ");
		SQL.append("'").append(name).append("', ");
		SQL.append("'").append(surname).append("', ");
		SQL.append("'").append(title).append("', ");
		SQL.append("'").append(gender).append("', ");
		SQL.append("'").append(birthday).append("')");

		System.out.println("Query:\n" + SQL.toString());
		this.executeUpdate(SQL.toString());
	}

	/**
	 * This method updates an existing {@code PersonalData} object.
	 * 
	 * @param arg0
	 *            - Represents a {@code PersonalData} object.
	 */
	public void update(PersonalData arg0) {
		StringBuilder SQL = new StringBuilder();

		SQL.append(" UPDATE ");
		SQL.append(TABLE_NAME);
		SQL.append(" SET ");
		SQL.append(COLUMN_TITLE).append(" = '");
		SQL.append(arg0.getTitle()).append("', ");
		SQL.append(COLUMN_NAME).append(" = '");
		SQL.append(arg0.getName()).append("', ");
		SQL.append(COLUMN_SURNAME).append(" = '");
		SQL.append(arg0.getSurname()).append("', ");
		SQL.append(COLUMN_GENDER).append(" = '");
		SQL.append(arg0.getGender()).append("', ");
		SQL.append(COLUMN_BIRTHDAY).append(" = '");
		SQL.append(arg0.getBirthDate()).append("'");
		SQL.append(" WHERE ");
		SQL.append(COLUMN_ID).append(" = ");
		SQL.append(arg0.getID());

		System.out.println("Query:\n" + SQL.toString());
		this.executeUpdate(SQL.toString());
	}
}

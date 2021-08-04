package controllers.support;

import controllers.database.account.AccountDatabase;
import javafx.scene.control.Alert;

/**
 * The {@code Support} class is used to store some useful methods.
 * 
 * @author Andrea Graziani
 * @version 1.5
 */
public class Support {

	/**
	 * This method verifies if {@code arg0} isn't a null object, otherwise it
	 * raises an exception. The {@code field} argument represents the name of a
	 * field.
	 * 
	 * @param arg0
	 *            - Represents a {@code Object} object.
	 * @param field
	 *            - Represents a {@code String} object.
	 * @throws Exception
	 */
	public static void nullCheckField(Object arg0, String field) throws Exception {
		if (arg0 == null)
			throw new Exception("The " + field + " field is empty.");
	}

	/**
	 * This method verifies if {@code arg0} isn't empty or null, otherwise it
	 * raises an exception. The {@code field} argument represents the name of a
	 * field.
	 * 
	 * @param arg0
	 *            - Represents a {@code String} object.
	 * @param field
	 *            - Represents a {@code String} object.
	 * @throws Exception
	 */
	public static void emptyCheckField(String arg0, String field) throws Exception {
		Support.nullCheckField(arg0, field);
		if (arg0.isEmpty())
			throw new Exception("The " + field + " field is empty.");
	}

	/**
	 * This method verifies if {@code arg0} contains only letter, otherwise it
	 * raises an exception. The {@code field} argument represents the name of a
	 * field.
	 * 
	 * @param arg0
	 *            - Represents a {@code String} object.
	 * @param field
	 *            - Represents a {@code String} object.
	 * @throws Exception
	 */
	public static void onlyLetterCheckField(String arg0, String field) throws Exception {
		Support.emptyCheckField(arg0, field);

		for (char elem : arg0.toCharArray()) {
			if (!Character.isLetter(elem))
				throw new Exception("The " + field + " field contains invalid data: it must contains only letters!");
		}
	}

	/**
	 * This method verifies if {@code arg0} contains only digit, otherwise it
	 * raises an exception. The {@code field} argument represents the name of a
	 * field.
	 * 
	 * @param arg0
	 *            - Represents a {@code String} object.
	 * @param field
	 *            - Represents a {@code String} object.
	 * @throws Exception
	 */
	public static void onlyDigitCheckField(String arg0, String field) throws Exception {
		Support.emptyCheckField(arg0, field);

		for (char elem : arg0.toCharArray()) {
			if (!Character.isDigit(elem))
				throw new Exception("The " + field + " field contains invalid data: it must contains only digit!");
		}
	}

	/**
	 * This method verifies if {@code arg0} already exists and id it represents
	 * a valid email address. If {@code arg0} isn't a valid email address or
	 * already exists, it raises an exception.
	 * 
	 * @param arg0
	 *            - Represents a {@code String} object.
	 * @param field
	 *            - Represents a {@code String} object.
	 * @throws Exception
	 */
	public static void emailAddressCheckField(String arg0) throws Exception {
		Support.emptyCheckField(arg0, "'Email Address'");

		boolean invalid = true;

		// It verifies if specified email address contains the symbol '@'.
		for (char elem : arg0.toCharArray()) {
			if (elem == '@')
				invalid = false;
		}
		if (invalid)
			throw new Exception("Specifed email address is not valid");

		// If specified email address already exists, it throws an exception
		if (AccountDatabase.getInstance().checkExistingEmail(arg0) != -1)
			throw new Exception("Specified email address already exists.");
	}

	/**
	 * This method verifies if {@code password} respect minimal security
	 * requirements. otherwise it throws an exception. Furthermore it verifies
	 * if {@code password} and {@code repeatedPassword} match.
	 * 
	 * @param password
	 *            - Represents a {@code String} object.
	 * @param repeatedPassword
	 *            - Represents a {@code String} object.
	 * @throws Exception
	 */
	public static void passwordCheckField(String password, String repeatedPassword) throws Exception {
		Support.emptyCheckField(password, "'Password'");

		if (password.length() < 8)
			throw new Exception("The password must contain at least 8 characters");

		boolean foundDigit = false;
		boolean foundUpperCase = false;
		boolean foundLowerCase = false;

		for (char elem : password.toCharArray()) {
			if (Character.isLowerCase(elem))
				foundLowerCase = true;
			if (Character.isUpperCase(elem))
				foundUpperCase = true;
			if (Character.isDigit(elem))
				foundDigit = true;
		}

		if (!(foundDigit && foundUpperCase && foundLowerCase))
			throw new Exception(
					"Password must contain at least one digit, one upper case and one lower case character");

		Support.emptyCheckField(repeatedPassword, "'Retype Password'");

		if (!password.equals(repeatedPassword))
			throw new Exception("The specified passwords don't match.");
	}

	/**
	 * This method returns an {@code Alert} object.
	 * 
	 * @param alertType
	 *            - Represents a {@code Alert.AlertType} object.
	 * @param title
	 *            - Represents a {@code String} object.
	 * @param header
	 *            - Represents a {@code String} object.
	 */
	public static Alert getDialog(Alert.AlertType alertType, String title, String header) {
		Alert dialog = new Alert(alertType);
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		return dialog;
	}
}

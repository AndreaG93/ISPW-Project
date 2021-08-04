package entities.account;

import java.time.LocalDate;
import entities.account.type.AccountType;
import entities.address.Address;
import entities.personal_data.PersonalData;

/**
 * The Account {@code class} is used to represent a generic account.
 * 
 * @author Andrea Graziani
 * @version 1.0
 * @see PersonalData
 * @see Address
 */
public class Account implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private final int ID;
	private final LocalDate registrationDate;
	private String email;
	private String password;
	private AccountType type;

	/**
	 * Constructs a newly allocated {@code Account} object.
	 * 
	 * @param arg0
	 *            - Represents account's ID.
	 * @param arg1
	 *            - Represents account's e-Mail Address.
	 * @param arg2
	 *            - Represents account's password.
	 */
	public Account(int arg0, String arg1, String arg2, LocalDate arg3) {
		this.ID = arg0;
		this.email = arg1;
		this.password = arg2;
		this.registrationDate = arg3;
	}

	/*********************************************************************************************************
	 * Getter methods
	 */

	
	public int getID() {
		return ID;
	}
	
	/**
	 * Get account's registration date.
	 * 
	 * @return An {@code Calendar} object.
	 */
	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * Get account's email.
	 * 
	 * @return A {@link String} object.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Get account's password.
	 * 
	 * @return A {@link String} object.
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Get account's type.
	 * 
	 * @return An {@link AccountType} object.
	 * @see AccountType
	 */
	public AccountType getType() {
		return this.type;
	}

	/*********************************************************************************************************
	 * Setter methods
	 */
	
	/**
	 * Set account's email.
	 * 
	 * @param arg0
	 *            - a {@link String} object that contains account's email.
	 */
	public void setEmail(String arg0) {
		this.email = arg0;
	}

	/**
	 * Set account's password.
	 * 
	 * @param arg0
	 *            - a {@link String} object that contains account's password.
	 */
	public void setPassword(String arg0) {
		this.password = arg0;
	}

	/**
	 * Set account's type.
	 * 
	 * @param arg0
	 *            - an {@link AccountType} object that represents account's
	 *            type.
	 */
	public void setType(AccountType arg0) {
		this.type = arg0;
	}

}

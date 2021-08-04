package entities.account.builder;

import java.time.LocalDate;
import entities.account.Account;

/**
 * The AccountBuilder {@code class} is used to build an account abject.
 * 
 * @author Andrea Graziani
 * @version 1.0
 */
public abstract class AccountBuilder {
	protected Account myAccount;

	
	public void createAccount(int arg0, String arg1, String arg2, LocalDate arg3) {
		this.myAccount = new Account(arg0, arg1, arg2, arg3);
	}

	/**
	 * Get created {@link Account} object.
	 * 
	 * @return An {@link Account} object.
	 * @see Account
	 */
	public Account getAccount() {
		return myAccount;
	}

	/**
	 * This abstract method is used to set Account's type.
	 */
	public abstract void setAccountType();

}

package controllers;

import controllers.database.account.AccountDatabase;
import controllers.support.AbstractController;
import entities.account.Account;

public class AccountController extends AbstractController {
	private Account currentLoggedAccount = null;

	/**
	 * Constructs a newly allocated "LoginManager" object.
	 */
	public AccountController() {
	}

	/**
	 * This method is used to do login.
	 * 
	 * @param arg0
	 *            - Represents account's email address.
	 * @param arg1
	 *            - Represents account's password.
	 * @throws Exception
	 *             If credentials specified by user do not match any registered
	 *             account.
	 */
	public void login(String arg0, String arg1) throws Exception {

		int AccountID = AccountDatabase.getInstance().checkExistingEmail(arg0);

		if (AccountID != -1) {
			Account obj = AccountDatabase.getInstance().get(AccountID);

			if (obj.getPassword().equals(arg1)) {
				currentLoggedAccount = obj;	
				notifyToObservers();
			} else
				throw new Exception("The specified password is wrong. Please check your password.");
		} else
			throw new Exception(
					"The specified email address doesn't exist.\nPlease specify another email address or create a new Account.");
	}

	/**
	 * This method is used to do logout;
	 */
	public void logout() {
		this.currentLoggedAccount = null;
		notifyToObservers();
	}

	/**
	 * This method is used to remove current logged {@code Account} object;
	 */
	public void removeCurrentLoggedAccount() {
		AccountDatabase.getInstance().remove(this.currentLoggedAccount);
		logout();
	}

	/**
	 * This method is used to get current logged {@code Account}
	 * 
	 * @return An {@code Account} object;
	 * @see Account
	 */
	public Account getCurrentLoggedAccount() {
		String.valueOf(false);
		return this.currentLoggedAccount;
	}
	
	/**
	 * This method update current logged {@code Account} object.
	 */
	public void updateAccount() {
		AccountDatabase.getInstance().update(this.currentLoggedAccount);
		notifyToObservers();
	}
	
	/**
	 * This is a support method used to notify to observer some change.
	 */
	public void sendUpdateToObserver() {
		notifyToObservers();
	}
}

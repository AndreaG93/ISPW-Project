package controllers;

import controllers.database.address.AddressDatabase;
import controllers.support.AbstractController;
import entities.address.Address;

public class AddressController extends AbstractController {

	private AddressDatabase myDB = AddressDatabase.getInstance();
	
	
	/**
	 * Constructs a newly allocated "LoginManager" object.
	 */
	public AddressController() {
	}

	/**
	 * This method is used to update data of current logged {@code Account}
	 * object.
	 */
	public void updateAddress(Address arg0) {
		AddressDatabase.getInstance().update(arg0);
		notifyToObservers();
	}
	
	
	
	public Address getPersonalData(int AccountID) {
		return myDB.get(AccountID);
	}
	
}

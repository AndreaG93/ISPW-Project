package controllers;

import controllers.database.personal_data.PersonalDataDatabase;
import controllers.support.AbstractController;
import entities.personal_data.PersonalData;

public class PersonalDataController extends AbstractController {

	private PersonalDataDatabase myDB = PersonalDataDatabase.getInstance();

	/**
	 * Constructs a newly allocated {@code PersonalDataController} object.
	 */
	public PersonalDataController() {
	}

	/**
	 * This method updates an existing {@code PersonalData} object.
	 * 
	 * @param arg0
	 *            - Represents a {@code PersonalData} object.
	 */
	public void updatePersonalData(PersonalData arg0) {
		myDB.update(arg0);
		notifyToObservers();
	}

	public PersonalData getPersonalData(int AccountID) {
		return myDB.get(AccountID);
	}

}

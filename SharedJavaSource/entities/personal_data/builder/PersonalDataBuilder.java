package entities.personal_data.builder;

import java.time.LocalDate;

import entities.personal_data.GenderEnum;
import entities.personal_data.PersonalData;
import entities.personal_data.TitleEnum;

public class PersonalDataBuilder {

	protected PersonalData myPersonalData;

	/**
	 * Constructs a newly allocated {@link PersonalData} object.
	 * 
	 * @param arg0
	 *            - Represents an ID
	 */
	public void createPersonalData(int arg0) {
		this.myPersonalData = new PersonalData(arg0);
	}

	/**
	 * This method returns a {@link PersonalData} object.
	 * 
	 * @return a {@link PersonalData} object.
	 */
	public PersonalData getPersonalData() {
		return this.myPersonalData;
	}

	/**
	 * This method is used to set {@link PersonalData} object.
	 * 
	 * @param arg0
	 *            - Represents user's title.
	 * @param arg1
	 *            - Represents user's name.
	 * @param arg2
	 *            - Represents user's surname.
	 * @param arg3
	 *            - Represents user's gender
	 * @param year
	 *            - Represents user's birth year.
	 * @param month
	 *            - Represents user's birth month.
	 * @param dayOfMonth
	 *            - Represents user's birth day.
	 * @see TitleEnum
	 * @see GenderEnum
	 */
	public void setPersonalData(TitleEnum arg0, String arg1, String arg2, GenderEnum arg3, LocalDate arg4) {
		this.myPersonalData.setTitle(arg0);
		this.myPersonalData.setName(arg1);
		this.myPersonalData.setSurname(arg2);
		this.myPersonalData.setGender(arg3);
		this.myPersonalData.setBirthDate(arg4);
	}

}

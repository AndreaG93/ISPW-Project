package entities.personal_data.builder;

import java.time.LocalDate;

import entities.personal_data.GenderEnum;
import entities.personal_data.PersonalData;
import entities.personal_data.TitleEnum;

public class PersonalDataDirector {

	public static PersonalData createsPersonalDataObject(int accountID, String title, String name, String surname,
			String gender, LocalDate birthday) {
		TitleEnum var0 = null;
		GenderEnum var1 = null;

		for (TitleEnum obj : TitleEnum.values()) {
			if (title.equals(obj.toString())) {
				var0 = obj;
				break;
			}
		}

		for (GenderEnum obj : GenderEnum.values()) {
			if (gender.equals(obj.toString())) {
				var1 = obj;
				break;
			}
		}

		PersonalDataBuilder myBuilder = new PersonalDataBuilder();

		myBuilder.createPersonalData(accountID);
		myBuilder.setPersonalData(var0, name, surname, var1, birthday);
		return myBuilder.getPersonalData();
	}
}

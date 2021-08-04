package entities.personal_data;

import java.io.Serializable;
import java.time.LocalDate;

public class PersonalData implements Serializable {

	private static final long serialVersionUID = 1L;
	private final int ID;
	private TitleEnum title;
	private String name;
	private String surname;
	private GenderEnum gender;
	private LocalDate birthDate;

	/**
	 * Constructs a newly allocated 'AccountPersonalData' object.
	 */
	public PersonalData(int arg0) {
		this.ID = arg0;
	}

	/**
	 * Set user's title.
	 * 
	 * @param title
	 *            - Represents user's title.
	 */
	public void setTitle(TitleEnum title) {
		this.title = title;
	}

	/**
	 * Set user's name.
	 * 
	 * @param name
	 *            - Represents user's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set user's surname.
	 * 
	 * @param surname
	 *            - Represents user's surname.
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Set user's gender.
	 * 
	 * @param gender
	 *            - Represents user's gender.
	 */
	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	
	public void setBirthDate(LocalDate arg0) {
		this.birthDate = arg0;
	}

	/*********************************************************************************************************
	 * Getter methods
	 */

	public int getID() {
		return ID;
	}
	
	
	public TitleEnum getTitle() {
		return this.title;
	}

	/**
	 * Get user's name.
	 * 
	 * @return - User's name {@link String}.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get user's surname.
	 * 
	 * @return - User's surname {@link String}.
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Get user's gender.
	 * 
	 * @return A representative {@link GenderEnum} object.
	 */
	public GenderEnum getGender() {
		return this.gender;
	}

	/**
	 * Get user's birthday.
	 * 
	 * @return A "Calendar" object that represents user's birthday.
	 * @see Calendar
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}

}

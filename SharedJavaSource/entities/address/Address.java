package entities.address;

/**
 * The {@code Address} class is used to represent a generic address.
 * 
 * @author Andrea Graziani
 * @version 1.3
 */
public class Address implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private final int ID;
	private String fullName;
	private String addressLine1;
	private String addressLine2;
	private String postcode;
	private String town;
	private String province;
	private String phoneNumber;

	/**
	 * Constructs a newly allocated {@code Address} object.
	 * 
	 * @param arg0
	 *            - Represents an ID.
	 */
	public Address(int arg0) {
		this.ID = arg0;
	}



	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getID() {
		return ID;
	}

	

	
	
	
	
}

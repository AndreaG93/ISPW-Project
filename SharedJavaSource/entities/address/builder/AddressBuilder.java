package entities.address.builder;

import entities.address.Address;

public class AddressBuilder {
	protected Address myAddress;

	public void createAddress(int arg0) {
		this.myAddress = new Address(arg0);
	}

	public Address getAddress() {
		return this.myAddress;
	}

	public void setAddress(String fullName, String addressLine1, String addressLine2, String phoneNumber, String town,
			String province, String postcode) {
		this.myAddress.setFullName(fullName);
		this.myAddress.setAddressLine1(addressLine1);
		this.myAddress.setAddressLine2(addressLine2);
		this.myAddress.setPhoneNumber(phoneNumber);
		this.myAddress.setTown(town);
		this.myAddress.setProvince(province);
		this.myAddress.setPostcode(postcode);

	}
}

package entities.address.builder;

import entities.address.Address;

public class AddressBuilderDirector {

	public static Address createsAddressObject(int id, String fullName, String addressLine1, String addressLine2,
			String phoneNumber, String town, String province, String postcode) {
		AddressBuilder myBuilder = new AddressBuilder();

		myBuilder.createAddress(id);
		myBuilder.setAddress(fullName, addressLine1, addressLine2, phoneNumber, town, province, postcode);
		return myBuilder.getAddress();
	}
}

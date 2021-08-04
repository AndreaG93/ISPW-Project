package entities.account.builder;

import entities.account.type.Standard;

public class StandardAccountBuilder extends AccountBuilder {

	@Override
	public void setAccountType() {
		this.myAccount.setType(new Standard());
	}
}

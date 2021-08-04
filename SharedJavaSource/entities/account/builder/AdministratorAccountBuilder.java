package entities.account.builder;

import entities.account.type.Administrator;

public class AdministratorAccountBuilder extends AccountBuilder {

	@Override
	public void setAccountType() {
		this.myAccount.setType(new Administrator());
	}
}

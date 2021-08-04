package entities.account.builder;

import entities.account.type.Manager;

public class ManagerAccountBuilder extends AccountBuilder {
	
	@Override
	public void setAccountType() {
		this.myAccount.setType(new Manager());
	}
}

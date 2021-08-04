package entities.account.builder;

import java.time.LocalDate;
import entities.account.Account;
import entities.account.type.Administrator;
import entities.account.type.Manager;

public class AccountBuilderDirector {

	public static Account createsAccountObject(int ID, String arg0, String arg1, String type, LocalDate RegDate) {
		AccountBuilder myBuilder;

		if (type.equals(Administrator.class.getName()))
			myBuilder = new AdministratorAccountBuilder();
		if (type.equals(Manager.class.getName()))
			myBuilder = new ManagerAccountBuilder();
		else 
			myBuilder = new StandardAccountBuilder();

		myBuilder.createAccount(ID, arg0, arg1, RegDate);
		myBuilder.setAccountType();

		return myBuilder.getAccount();
	}
}

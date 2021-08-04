package entities.product.dress.builder;

import entities.product.dress.type.ManDress;

public class ManDressBuilder extends DressBuilder {

	@Override
	public void setDressType() {
		this.myDress.setType(new ManDress());;
	}
	
}
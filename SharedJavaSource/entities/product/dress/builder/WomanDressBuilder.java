package entities.product.dress.builder;

import entities.product.dress.type.WomanDress;

public class WomanDressBuilder extends DressBuilder {

	@Override
	public void setDressType() {
		this.myDress.setType(new WomanDress());;
	}
	
}

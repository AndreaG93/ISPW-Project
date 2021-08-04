package entities.product.dress.builder;

import entities.product.dress.Dress;
import entities.product.dress.SizeDressEnum;

public abstract class DressBuilder
{
	protected Dress myDress;
	
	public void createDress(int arg0)
	{
		this.myDress = new Dress(arg0);
	}

	public abstract void setDressType();
	
	public void setDress(String dressName, String brand, String color, SizeDressEnum size) {
		this.myDress.setDressName(dressName);
		this.myDress.setBrand(brand);
		this.myDress.setColor(color);
		this.myDress.setSize(size);
	}
	
	public Dress getDress()
	{
		return this.myDress;
	}
}

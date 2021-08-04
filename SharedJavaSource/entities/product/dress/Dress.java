package entities.product.dress;

import java.io.Serializable;

import entities.product.Product;
import entities.product.dress.type.DressType;

/**
 * The {@code Dress} class is used to represents a generic dress.
 * 
 * @author Andrea Graziani
 * @version 1.2
 * @see Product
 */
public class Dress implements Product, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int ID;



	private String dressName;
	private String brand;
	private String color;
	private SizeDressEnum size;
	private DressType type;


	public Dress(int arg0) {
		this.ID = arg0;
	}

	
	
	
	@Override
	public String getProductType() {
		return this.getClass().getName();
	}
	
	@Override
	public String getProductName() {
		return this.dressName;
	}

	@Override
	public String getProductDescription() {
		return "Brand:    " + this.getBrand() + "\n" + "Color:    " + this.getColor() + "\n" + "Size:     "
				+ this.getSize() + "\n" + "Type:     " + this.type.toString() + "\n";
	}

	@Override
	public int getProductID() {
		return ID;
}
	
	/*********************************************************************************************************
	 * Getter methods
	 */
	
	public String getDressName() {
		return dressName;
	}


	public void setDressName(String dressName) {
		this.dressName = dressName;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public SizeDressEnum getSize() {
		return size;
	}


	public void setSize(SizeDressEnum size) {
		this.size = size;
	}
	
	public DressType getType() {
		return type;
	}


	public void setType(DressType type) {
		this.type = type;
	}
}

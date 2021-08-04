package entities.product;

/**
 * The {@code Product} interface is used to represents a generic product.
 * 
 * @author Andrea Graziani
 * @version 1.0
 * @see Book
 * @see Dress
 */
public interface Product {

	public int getProductID();

	public String getProductType();

	/**
	 * This method is used to get a {@code String} that contains the name of a
	 * product.
	 * 
	 * @return A {@code String}.
	 */
	public String getProductName();

	/**
	 * This method is used to get a {@code String} that contains a description
	 * of a product.
	 * 
	 * @return A {@code String}.
	 */
	public String getProductDescription();

}

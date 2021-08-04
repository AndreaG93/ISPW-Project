package entities.product.book;

import java.io.Serializable;

import entities.product.Product;
import entities.product.book.type.BookType;

/**
 * The {@code Book} class is used to represents a generic book.
 * 
 * @author Andrea Graziani
 * @version 1.2
 * @see Product
 */
public class Book implements Product, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int ID;
	private String ISBN;
	private String title;
	private String subTitle;
	private String author;
	private String editor;
	private int numberOfPages;
	private BookType type;

	

	public Book(int arg0) {
		this.ID = arg0;
	}

	@Override
	public String getProductName() {
		return this.getTitle();
	}

	@Override
	public String getProductDescription() {
		return "Type:     " + this.type.toString() + "\n" + "Subtitle: " + this.getSubTitle() + "\n" + "ISBN:     "
				+ this.getISBN() + "\n" + "Author:   " + this.getAuthor() + "\n" + "Editor:   " + this.getEditor()
				+ "\n" + "Pages:    " + this.getNumberOfPages() + "\n";
	}

	@Override
	public int getProductID() {
		return this.ID;
	}

	@Override
	public String getProductType() {
		return this.getClass().getName();
	}
	
	/*********************************************************************************************************
	 * Getter methods
	 */

	/**
	 * Get book's ISBN code.
	 * 
	 * @return A {@code String} that contains book's ISBN code.
	 */
	public String getISBN() {
		return ISBN;
	}

	/**
	 * Get book's title.
	 * 
	 * @return A {@code String} that contains book's title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Get book's subtitle.
	 * 
	 * @return A {@code String} that contains book's subtitle.
	 */
	public String getSubTitle() {
		return subTitle;
	}

	/**
	 * Get book's author.
	 * 
	 * @return A {@code String} that contains book's author.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Get book's editor name.
	 * 
	 * @return A {@code String} that contains book's editor name.
	 */
	public String getEditor() {
		return editor;
	}

	/**
	 * Get number of pages of book.
	 * 
	 * @return A integer.
	 */
	public int getNumberOfPages() {
		return numberOfPages;
	}

	/*********************************************************************************************************
	 * Setter methods
	 */

	/**
	 * Set book's ISBN code.
	 * 
	 * @param arg0
	 *            - A {@link String} that contains book's ISBN code.
	 */
	public void setISBN(String arg0) {
		ISBN = arg0;
	}

	/**
	 * Set book's title.
	 * 
	 * @param arg0
	 *            - A {@link String} that contains book's title.
	 */
	public void setTitle(String arg0) {
		this.title = arg0;
	}

	/**
	 * Set book's subtitle.
	 * 
	 * @param arg0
	 *            - A {@link String} that contains book's subtitle.
	 */
	public void setSubTitle(String arg0) {
		this.subTitle = arg0;
	}

	/**
	 * Set the author of book.
	 * 
	 * @param arg0
	 *            - A {@link String} that contains book's author
	 */
	public void setAuthor(String arg0) {
		this.author = arg0;
	}

	/**
	 * Set book's editor name.
	 * 
	 * @param arg0
	 *            - A {@link String} that contains book's editor name.
	 */
	public void setEditor(String arg0) {
		this.editor = arg0;
	}

	/**
	 * Set number of pages of book.
	 * 
	 * @param arg0
	 *            - A integer.
	 */
	public void setNumberOfPages(int arg0) {
		this.numberOfPages = arg0;
	}
	
	public BookType getType() {
		return type;
	}

	public void setType(BookType type) {
		this.type = type;
	}

}

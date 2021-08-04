package entities.product.book.type;

import entities.product.book.Book;

/**
 * The {@code SchoolBook} class is used to represents a generic school book.
 * 
 * @author Andrea Graziani
 * @see Book
 * @version 1.0
 */
public class SchoolBook implements BookType {

	@Override
	public String toString() {
		return "School Book";
	}

}
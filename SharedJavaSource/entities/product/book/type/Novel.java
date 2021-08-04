package entities.product.book.type;

import entities.product.book.Book;

/**
 * The {@code Novel} class is used to represents a generic book novel.
 * 
 * @author Andrea Graziani
 * @see Book
 * @version 1.0
 */
public class Novel implements BookType {
	
	@Override
	public String toString() {
		return "Novel";
	}
	
}
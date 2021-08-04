package entities.product.book.builder;

import entities.product.book.type.SchoolBook;

public class SchoolBookBuilder extends BookBuilder {

	public void setBookType() {
		this.myBook.setType(new SchoolBook());
	}
}

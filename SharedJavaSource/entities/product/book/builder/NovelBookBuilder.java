package entities.product.book.builder;

import entities.product.book.type.Novel;

public class NovelBookBuilder extends BookBuilder {
	
	public void setBookType() {
		this.myBook.setType(new Novel());
	}
}

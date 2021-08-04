package entities.product.book.builder;

import entities.product.book.Book;

public abstract class BookBuilder {

	protected Book myBook;

	public abstract void setBookType();
	
	public void createBook(int arg0)
	{
		this.myBook = new Book(arg0);
	}
	
	public void setBook(String ISBN, String title, String subTitle, String editor, String author, int numberOfPage) {
		this.myBook.setISBN(ISBN);
		this.myBook.setAuthor(author);
		this.myBook.setEditor(editor);
		this.myBook.setSubTitle(subTitle);
		this.myBook.setTitle(title);	
		this.myBook.setNumberOfPages(numberOfPage);
	}
	
	public Book getBook()
	{
		return this.myBook;
	}
}

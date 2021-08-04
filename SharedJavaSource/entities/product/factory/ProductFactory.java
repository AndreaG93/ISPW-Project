package entities.product.factory;

import entities.product.book.Book;
import entities.product.book.builder.BookBuilder;
import entities.product.book.builder.NovelBookBuilder;
import entities.product.book.builder.SchoolBookBuilder;
import entities.product.book.type.Novel;
import entities.product.dress.Dress;
import entities.product.dress.SizeDressEnum;
import entities.product.dress.builder.DressBuilder;
import entities.product.dress.builder.ManDressBuilder;
import entities.product.dress.builder.WomanDressBuilder;
import entities.product.dress.type.ManDress;

public class ProductFactory {

	public static Book getProduct(int ID, String ISBN, String title, String subTitle, String editor, String author,
			int numberOfPages, String type) 
	{
		BookBuilder myBookBuilder;
		
		if (type.equals(Novel.class.getName()))
			myBookBuilder = new NovelBookBuilder();
		else
			myBookBuilder = new SchoolBookBuilder();

		myBookBuilder.createBook(ID);
		myBookBuilder.setBookType();
		myBookBuilder.setBook(ISBN, title, subTitle, editor, author, numberOfPages);
		return myBookBuilder.getBook();
	}

	public static Dress getProduct(int ID, String dressName, String brand, String color, String type, String size) {

		SizeDressEnum var = null;
		DressBuilder myDressBuilder;
		
		if (type.equals(ManDress.class.getName()))
			myDressBuilder = new ManDressBuilder();
		else
			myDressBuilder = new WomanDressBuilder();

		for (SizeDressEnum obj : SizeDressEnum.values()) {
			if (size.equals(obj.toString())) {
				var = obj;
			}
		}

		myDressBuilder.createDress(ID);
		myDressBuilder.setDressType();
		myDressBuilder.setDress(dressName, brand, color, var);	
		return myDressBuilder.getDress();
	}

}

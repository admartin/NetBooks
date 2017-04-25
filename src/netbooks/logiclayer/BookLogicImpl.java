package netbooks.logiclayer;

import netbooks.persist.*;
import netbooks.objectlayer.Book;
import java.util.List;

public class BookLogicImpl {
	
	public static List<Book> getBooksByGenre(String genre)
	{
		return BookPersistImpl.getBooksByGenre(genre);
	}
	
	public static List<Book> getBooksByTitle(String title)
	{
		return BookPersistImpl.getBooksByTitle(title);
	}
	
	public static List<Book> getBooksByAuthor(String author)
	{
		return BookPersistImpl.getBooksByAuthor(author);
	}
	
	public static void checkOutBook(String username, String title)
	{
		BookPersistImpl.checkOutBook(username, title);
	}
	
	public static List<Book> getWaitlistProfile(String username)
	{
		return BookPersistImpl.getWaitlist(username);
	}
	
	public static List<Book> getWaitlistEdit(String username)
	{
		return BookPersistImpl.getWaitlist(username);
	}
}

package netbooks.logiclayer;

import netbooks.objectlayer.Book;
import java.util.List;
import netbooks.persistlayer.BookPersistImpl;

public class BookLogicImpl {
	
	public static List<Book> getBooksById(int id)
	{
		return BookPersistImpl.getBooksById(id);
	}
	
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
	
	public static List<Book> getAllBooks()
	{
		return BookPersistImpl.getAllBooks();
	}
	
	public static void checkOutBook(String username, int book_id)
	{
		BookPersistImpl.checkOutBook(username, book_id);
	}
	
	public static List<Book> getWaitlistProfile(String username)
	{
		return BookPersistImpl.getWaitlist(username);
	}
	
	public static List<Book> getWaitlistEdit(String username)
	{
		return BookPersistImpl.getWaitlist(username);
	}
	
	public static List<Book> getCheckedOutBooks(String username)
	{
		return BookPersistImpl.getCheckedOut(username);
	}
}
package netbooks.persist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import netbooks.objectlayer.Author;
import netbooks.objectlayer.Book;
import netbooks.objectlayer.Review;
import netbooks.objectlayer.User;

public class BookPersistImpl {

	private static Connection conn = null; //establish connection to db

	public static List<Book> getBooksByGenre(String genre) {

		List<Book> bookList = new ArrayList<Book>();
		List<Review> reviewList = new ArrayList<Review>();

		String sql = "SELECT * FROM netbooks.books JOIN authors ON books.Authors_id= authors.id JOIN genres ON books.Genres_type = genres.type WHERE genres.type = ?";
		PreparedStatement stmt = null;
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);

		condition.setLength(0);

		query.append(sql);

		try {
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, genre);
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while(rs.next){
				int id = rs.getInt(1);
				String title = rs.getString(2);
				int numCopies = rs.getInt(3);
				String pubDate = rs.getDate(4).toString();
				int checkedOut = rs.getInt(5);
				boolean ebook = rs.getBoolean(6);
				String textURL = rs.getString(7);
				String coverURL = rs.getString(8);
				int rating = rs.getInt(9);
				String description = rs.getString(10);
				int authorId = rs.getInt(11);
				String genreCol = rs.getString(12);
				String fname = rs.getString(14);
				String lname = rs.getString(15);
				String birthday = rs.getDate(16).toString();
				String gender = rs.getString(17);
				String bio = rs.getString(18);

				String name = fname + " " + lname;
				Author author = new Author(authorId, name, birthday, gender, bio);
				reviewList = ReviewPersistImpl.selectReviewsById(id);
				Book book = new Book(id, title, numCopies, pubDate, checkedOut, ebook, textURL, coverURL, rating, author, genreCol, description, reviewList);
				bookList.add(book);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}

	public static List<Book> getBooksByTitle(String title) {

		List<Book> bookList = new ArrayList<Book>();
		List<Review> reviewList = new ArrayList<Review>();

		String sql = "SELECT * FROM netbooks.books JOIN authors ON books.Authors_id= authors.id JOIN genres ON books.Genres_type = genres.type WHERE books.title = ?";
		PreparedStatement stmt = null;
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);

		condition.setLength(0);

		query.append(sql);

		try {
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while( rs.next() ){
				int id = rs.getInt(1);
				String titleCol = rs.getString(2);
				int numCopies = rs.getInt(3);
				String pubDate = rs.getDate(4).toString();
				int checkedOut = rs.getInt(5);
				boolean ebook = rs.getBoolean(6);
				String textURL = rs.getString(7);
				String coverURL = rs.getString(8);
				int rating = rs.getInt(9);
				String description = rs.getString(10);
				int authorId = rs.getInt(11);
				String genre = rs.getString(12);
				String fname = rs.getString(14);
				String lname = rs.getString(15);
				String birthday = rs.getDate(16).toString();
				String gender = rs.getString(17);
				String bio = rs.getString(18);

				String name = fname + " " + lname;
				Author author = new Author(authorId, name, birthday, gender, bio);
				reviewList = ReviewPersistImpl.selectReviewsById(id);
				Book book = new Book(id, titleCol, numCopies, pubDate, checkedOut, ebook, textURL, coverURL, rating, author, genre, description, reviewList);
				bookList.add(book);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bookList;
	}

	public static List<Book> getBooksByAuthor(String author) {

		List<Book> bookList = new ArrayList<Book>();
		List<Review> reviewList = new ArrayList<Review>();

		String sql = "SELECT * FROM netbooks.books JOIN authors ON books.Authors_id= authors.id JOIN genres ON books.Genres_type = genres.type WHERE books.title = ?";
		PreparedStatement stmt = null;
		try {
			conn = DbUtils.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, author);
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while( rs.next() ) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				int numCopies = rs.getInt(3);
				String pubDate = rs.getDate(4).toString();
				int checkedOut = rs.getInt(5);
				boolean ebook = rs.getBoolean(6);
				String textURL = rs.getString(7);
				String coverURL = rs.getString(8);
				int rating = rs.getInt(9);
				String description = rs.getString(10);
				int authorId = rs.getInt(11);
				String genre = rs.getString(12);
				String fname = rs.getString(14);
				String lname = rs.getString(15);
				String birthday = rs.getDate(16).toString();
				String gender = rs.getString(17);
				String bio = rs.getString(18);

				String name = fname + " " + lname;
				Author authorName = new Author(authorId, name, birthday, gender, bio);
				reviewList = ReviewPersistImpl.selectReviewsById(id);
				Book book = new Book(id, title, numCopies, pubDate, checkedOut, ebook, textURL, coverURL, rating, authorName, genre, description, reviewList);
				bookList.add(book);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bookList;
	}

	public static void checkOutBook(String username, int bookId, String title) {

		//INSERT
		String insertSql = "INSERT INTO checkedout (Books_id, Users_username) VALUES (?, ?)";            
		PreparedStatement stmt1;

		try {
			try {
				conn = DbUtils.connect();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stmt1 = (PreparedStatement) conn.prepareStatement(insertSql);
			
			stmt1.setString(1, username);
			stmt1.setInt(2, bookId);
			
			stmt1.executeUpdate();

		} catch( SQLException e ) {
			e.printStackTrace();
			System.out.println( "Could not check out book at this time.\nError:\t" + e );
		}
		
		//UPDATE
		String updateSql = "UPDATE books SET numOut = numOut - 1 WHERE title = ?";
		PreparedStatement stmt2;
		
		try {
			stmt2 = (PreparedStatement) conn.prepareStatement(updateSql);
			
			stmt2.setString(2, title);
			
			stmt2.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println( "Could not check out book at this time.\nError:\t" + e );
		}
	}

	public static List<Book> getWaitlist(String username) {
		
		List<Book> bookList = new ArrayList<Book>();
		List<Review> reviewList = new ArrayList<Review>();

		String sql = "SELECT waitlist.id, waitlist.Books_id, waitlist.Users_username, books.cover FROM waitlist JOIN books ON waitlist.books_id = books.id WHERE waitlist.Users_username = ? ORDER BY id";
		PreparedStatement stmt = null;
		try {
			conn = DbUtils.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(2, username);
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while( rs.next() ) {
				int id = rs.getInt(1);
				String user = rs.getString(3); //user_username
				int booksId = rs.getInt(2); 
				String cover = rs.getString(4);

				Review review = new Review(-1, null, -1, null);
				reviewList.add(review);
				Book book = new Book(id, null, -1, null, -1, false, null, cover, -1, null, null, null, reviewList);
				bookList.add(book);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bookList;
		
	}

}

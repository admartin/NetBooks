package netbooks.persistlayer;

import netbooks.objectlayer.Book;
import netbooks.objectlayer.Review;
import netbooks.objectlayer.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

public class ReviewPersistImpl {

    private static Connection  conn = null;
    
	public static void createReview(Review review)
	{
		String insertSql = "insert into netbooks.reviews (rating, details, books_id, users_username) values (?,?,?,?)";            
		PreparedStatement stmt;

		try {
			try {
				conn = DbUtils.connect();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stmt = (PreparedStatement) conn.prepareStatement(insertSql);



			if( review.getRating() != -1 ) // ballot is unique, so it is sufficient to get a ballot
				stmt.setInt(1, review.getRating());

			if(review.getDetails() != null ) // ballot is unique, so it is sufficient to get a ballot
				stmt.setString(2, review.getDetails());
		
			if(review.getBookID() != -1)
				stmt.setInt(3, review.getBookID());
			
			if(review.getUser().getUsername() != null)
				stmt.setString(4, review.getUser().getUsername());
			
			stmt.executeUpdate();

		}
		catch( SQLException e ) {
			e.printStackTrace();
			System.out.println( "Review.save: failed to save a Review: " + e );
		}
	}
	
	public static List<Review> selectReviewsById(int bookId) {
		List<Review> reviews = new ArrayList<Review>();
		String insertSql = "select * from reviews where reviews.Books_id = " + bookId;            
		PreparedStatement stmt;
		try {

			try {
				conn = DbUtils.connect();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stmt = (PreparedStatement) conn.prepareStatement(insertSql);
			stmt.executeUpdate();
			ResultSet rs = stmt.getResultSet();
			int id;
			int rating;
			String details;
			int bookID;
			String username;
			
			while( rs.next() ) {
				id = rs.getInt(1);
                rating = rs.getInt(2);
                details = rs.getString(3);
                bookId = rs.getInt(4);
                username = rs.getString(5);
                
                User temp = new User(-1, username, null, null, null, null, null, null, null, -1, -1, -1, null, null);
                Review review = new Review(id, rating, details, bookId, temp);
                
                reviews.add(review);
			}
			
			return reviews;
		}
		catch( SQLException e ) {
			e.printStackTrace();
			System.out.println( "Review.select: failed to select reviews " + e );
		}
		
		return reviews;
	} 

}

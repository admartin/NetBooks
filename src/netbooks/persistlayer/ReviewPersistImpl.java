package netbooks.persist;

import netbooks.objectlayer.Review;
import netbooks.objectlayer.User;

import java.sql.Connection;
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
		// TODO Auto-generated method stub
		return reviews;
	} 

}

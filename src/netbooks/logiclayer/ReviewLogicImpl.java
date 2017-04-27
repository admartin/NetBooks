package netbooks.logiclayer;

import netbooks.objectlayer.Review;
import netbooks.persistlayer.ReviewPersistImpl;
import netbooks.objectlayer.User;

import java.util.List;

public class ReviewLogicImpl {

	public static void createReview(int id, int rating, String details, int bookID, String username)
	{
		Review review = new Review(-1, rating, details, bookID, username);
		ReviewPersistImpl.createReview(review);
	}
	
	public static List<Review> selectReviews(int bookid){
		return ReviewPersistImpl.selectReviewsById(bookid);
	}
	
}

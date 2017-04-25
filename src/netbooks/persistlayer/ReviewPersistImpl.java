package netbooks.persist;

import netbooks.objectlayer.Review;
import netbooks.objectlayer.User;

import java.util.List;

public class ReviewPersistImpl {

	public static void createReview(int id, int rating, String details, int bookID, String username)
	{
		User user = UserLogicImpl.getFullUserInfo(username).get(0);
		Review review = new Review(-1, rating, details, bookID, user);
		ReviewPersistImpl.createReview(review);
	}
	
}

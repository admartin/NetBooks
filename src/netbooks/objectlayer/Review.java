package netbooks.objectlayer;


public class Review {
	private int id;
	private int rating;
	private String details;
	private int bookID;
	private User user;
	
	public Review(int rating, String details, int bookID, User username) {
		this.rating = rating;
		this.details = details;
		this.bookID = bookID;
		this.user = username;
	}
	
	public Review(int id, int rating, String details, int bookID, User username) {
		this.id = id;
		this.rating = rating;
		this.details = details;
		this.bookID = bookID;
		this.user = username;
	}

	/**
	 * @return the id
	 */
	public int getID() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * @return the bookID
	 */
	public int getBookID() {
		return bookID;
	}

	/**
	 * @param bookID the bookID to set
	 */
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	/**
	 * @return the username
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param username the username to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
}

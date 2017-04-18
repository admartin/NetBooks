package netbooks.objectlayer;

import java.util.List;

public class Book {
	private int id;
	private String title;
	private int numCopies;
	private String pubDate;
	private int numOut;
	private boolean ebook;
	private String link;
	private String cover;
	private int rating;
	private Author author;
	private String genre;
	private List<Review> reviews;

	public Book(int id, String title, int numCopies, String pubDate, int numOut, boolean ebook, String link,
			String cover, int rating, Author author, String genre, List<Review> reviews) {
		this.id = id;
		this.title = title;
		this.numCopies = numCopies;
		this.pubDate = pubDate;
		this.numOut = numOut;
		this.ebook = ebook;
		this.link = link;
		this.cover = cover;
		this.rating = rating;
		this.author = author;
		this.genre = genre;
		this.reviews = reviews;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the numCopies
	 */
	public int getNumCopies() {
		return numCopies;
	}

	/**
	 * @param numCopies the numCopies to set
	 */
	public void setNumCopies(int numCopies) {
		this.numCopies = numCopies;
	}

	/**
	 * @return the pubDate
	 */
	public String getPubDate() {
		return pubDate;
	}

	/**
	 * @param pubDate the pubDate to set
	 */
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * @return the numOut
	 */
	public int getNumOut() {
		return numOut;
	}

	/**
	 * @param numOut the numOut to set
	 */
	public void setNumOut(int numOut) {
		this.numOut = numOut;
	}

	/**
	 * @return the ebook
	 */
	public boolean isEbook() {
		return ebook;
	}

	/**
	 * @param ebook the ebook to set
	 */
	public void setEbook(boolean ebook) {
		this.ebook = ebook;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the cover
	 */
	public String getCover() {
		return cover;
	}

	/**
	 * @param cover the cover to set
	 */
	public void setCover(String cover) {
		this.cover = cover;
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
	 * @return the authorID
	 */
	public Author getAuthor() {
		return author;
	}

	/**
	 * @param authorID the authorID to set
	 */
	public void setAuthor(Author author) {
		this.author = author;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		reviews.add(review);
	}
}

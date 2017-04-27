package netbooks.objectlayer;

import java.util.List;
import com.google.gson.Gson;

public class Book {
	public int bookid;
	public String title;
	public int numCopies;
	public String pubDate;
	public int numOut;
	public boolean ebook;
	public String link;
	public String cover;
	public int rating;
	public Author author;
	public String genre;
	public String desr;
	public List<Review> reviews;
	public String jsonReview;

	public Book(int id, String title, int numCopies, String pubDate, int numOut, boolean ebook, String link,
			String cover, int rating, Author author, String genre, String desr, List<Review> reviews) {
		this.bookid = id;
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
		this.desr = desr;
		this.reviews = reviews;
		this.jsonReview = convertReviews();
	}

	/**
	 * @return the id
	 */
	public int getID() {
		return bookid;
	}

	/**
	 * @param id the id to set
	 */
	public void setID(int id) {
		this.bookid = id;
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

	public String getDesr() {
		return desr;
	}

	public void setDesr(String desr) {
		this.desr = desr;
	}
	
	public String convertReviews()
	{ 
		Gson gson = new Gson();
		String json = gson.toJson(this.reviews);
		System.out.println(json);
        return json;
	}

	public String getJsonReview() {
		return jsonReview;
	}

	public void setJsonReview(String jsonReview) {
		this.jsonReview = jsonReview;
	}
}

package netbooks.objectlayer;


public class Waitlist {
	private int id;
	private String username;
	private int bookID;
	
	public Waitlist(String username, int bookID){
		this.username = username;
		this.bookID = bookID;
	}
	public Waitlist(int id, String username, int bookID){
		this.id = id;
		this.username = username;
		this.bookID = bookID;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	
}

package netbooks.objectlayer;

public class User {
	private int id;
	private String username;
	private String password;
	private String fname;
	private String lname;
	private String email;
	private String birthdate;
	private String address;
	private String city;
	private String state;
	private int zipcode;
	private int cardNum;
	private int subscription;
	private Book[] checkedOut;
	private Book[] booksRead;
	
	public User(int id, String username, String password, String fname, String lname, String birthdate, String address, 
			String city, String state, int zipcode, int cardNum, int subscription, Book[] checkedOut, Book[] booksRead,
			String email){
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.birthdate = birthdate;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.cardNum = cardNum;
		this.subscription = subscription;
		this.checkedOut = checkedOut;
		this.booksRead = booksRead;
		this.email = email;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * @return the birthdate
	 */
	public String getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zipcode
	 */
	public int getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the cardNum
	 */
	public int getCardNum() {
		return cardNum;
	}

	/**
	 * @param cardNum the cardNum to set
	 */
	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}

	/**
	 * @return the subscription
	 */
	public int getSubscription() {
		return subscription;
	}

	/**
	 * @param subscription the subscription to set
	 */
	public void setSubscription(int subscription) {
		this.subscription = subscription;
	}

	/**
	 * @return the checkedOut
	 */
	public Book[] getCheckedOut() {
		return checkedOut;
	}

	/**
	 * @param checkedOut the checkedOut to set
	 */
	public void setCheckedOut(Book[] checkedOut) {
		this.checkedOut = checkedOut;
	}

	/**
	 * @return the booksread
	 */
	public Book[] getBooksRead() {
		return booksRead;
	}

	/**
	 * @param waitlist the waitlist to set
	 */
	public void setWaitlist(Book[] booksRead) {
		this.booksRead = booksRead;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
}

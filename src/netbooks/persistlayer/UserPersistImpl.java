package netbooks.persist;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import netbooks.objectlayer.Book;
import netbooks.objectlayer.User;

public class UserPersistImpl {

	public static Connection conn = null;
	
	public static List<User> getUserForLogin(String username) {

		try {
			conn = DbUtils.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<User> userList = new ArrayList<User>();

		String sql = "SELECT u.username, u.password, u.subscription FROM users u WHERE u.username = ?";
		PreparedStatement stmt;

		try {
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, username);                         
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			
			while( rs.next() ) {
				String user = rs.getString(1);
				String pass = rs.getString(2);
				int subscription = rs.getInt(3);

				User builder = new User().buildString(user).buildPassword(pass).buildSub(subscription).build();
				userList.add(builder);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;


	}

	public static List<User> getFullUserInfo(String username) {

		try {
			conn = DbUtils.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<User> userList = new ArrayList<User>();
		Book booksCheckedOut[] = null;                       
		Book booksRead[] = null;

		String sql = "SELECT * FROM users u WHERE u.username = ?";
		PreparedStatement stmt;

		try {
			stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, username);      

			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();

			while( rs.next() ) {
				String user = rs.getString(1);
				String password = rs.getString(2);
				String fname = rs.getString(3);
				String lname = rs.getString(4); //some of these are probably off, I don't think my 
				String email = rs.getString(5);//database is up to date with correct columns
				String birthdate = rs.getString(6);
				String address = rs.getString(7);
				int zipcode = rs.getInt(8);
				String city = rs.getString(9);
				String state = rs.getString(10);
				int subscription = rs.getInt(11);

				User user1 = new User(username, password, fname, lname, birthdate, address, city, state, zipcode, -1, subscription, booksCheckedOut, booksRead, email);
				userList.add(user1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;
	}

	public static void createUser(User user) {

		try {
			conn = DbUtils.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String insertSql = "insert into netbooks.users (username, password, email, fname, lname, birthdate, address, city, state, zipcode, subscription) values (?,?,?,?,?,?,?,?,?,?,?)";            
		PreparedStatement stmt;

		try {
			stmt = (PreparedStatement) conn.prepareStatement(insertSql);

			if( user.getUsername() != null)
				stmt.setString(1, user.getUsername());
			if(user.getPassword() != null )
				stmt.setString(2, user.getPassword());
			if(user.getEmail() != null)
				stmt.setString(3, user.getEmail());
			if(user.getFname() != null)
				stmt.setString(4, user.getFname());
			if(user.getLname() != null)
				stmt.setString(5, user.getLname());
			if(user.getBirthdate() != null)
				stmt.setString(6, user.getBirthdate());
			if(user.getAddress() != null)
				stmt.setString(7, user.getAddress());
			if(user.getCity() != null)
				stmt.setString(8, user.getCity());
			if(user.getState() != null)
				stmt.setString(9, user.getState());
			if(user.getZipcode() != -1)
				stmt.setInt(10, user.getZipcode());
			if(user.getSubscription() != -1)
				stmt.setInt(11, user.getSubscription());

			stmt.executeUpdate();

		}
		catch( SQLException e ) {
			e.printStackTrace();
			System.out.println( "User.save: failed to save a User: " + e );
		}

	}

	public static void updateEmail(String username, String email) {

		try {
			conn = DbUtils.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String updateSql = "UPDATE users SET email = ? WHERE username = ?";
		PreparedStatement stmt2;

		try {
			stmt2 = (PreparedStatement) conn.prepareStatement(updateSql);
			stmt2.setString(1, email);    
			stmt2.setString(2, username);               //may be different index other than 3

			stmt2.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println( "Could not update email at this time.\nError:\t" + e );
		}


	}

	public static void updatePassword(String username, String password) {

		try {
			conn = DbUtils.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String updateSql = "UPDATE users SET password = ? WHERE username = ?";
		PreparedStatement stmt2;

		try {
			stmt2 = (PreparedStatement) conn.prepareStatement(updateSql);
			stmt2.setString(2, username);    
			stmt2.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println( "Could not update password at this time.\nError:\t" + e );
		}

	}

	public static void updateAddress(String username, String street, String city, String state, int zip) {

		try {
			conn = DbUtils.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String updateSql = "UPDATE users SET address = ?, city = ?, state = ?, zipcode = ? WHERE username = ?";
		PreparedStatement stmt2;

		try {
			stmt2 = (PreparedStatement) conn.prepareStatement(updateSql);

			stmt2.setString(1, street);
			stmt2.setString(2, city);              //these indeces may be wrong
			stmt2.setString(3, state);
			stmt2.setInt(4, zip);
			stmt2.setString(5, username);    
			stmt2.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println( "Could not update address at this time.\nError:\t" + e );
		}

	}

	public static void updateSub(String username, int sub) {

		try {
			conn = DbUtils.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String updateSql = "UPDATE users SET subscription = ? WHERE username = ?";
		PreparedStatement stmt2;

		try {
			stmt2 = (PreparedStatement) conn.prepareStatement(updateSql);
			stmt2.setInt(1, sub);
			stmt2.setString(2, username);    
			stmt2.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println( "Could not update subscription at this time.\nError:\t" + e );
		}

	}

	public static void addWaitlistEntry(String username, int bookID) {   //changed from book title to id to match waitlist table

		try {
			conn = DbUtils.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String insertSql = "INSERT INTO waitlist (Users_username, Books_id) VALUES (?, ?)";
		PreparedStatement stmt2;

		try {
			stmt2 = (PreparedStatement) conn.prepareStatement(insertSql);

			stmt2.setString(1, username);               //may be different indeces
			stmt2.setInt(2, bookID);

			stmt2.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println( "Could not add waitlist entry at this time.\nError:\t" + e );
		}

	}
	
	public static void deleteUser(String username) {

		try {
			conn = DbUtils.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String deleteReviewsSql = "DELETE FROM reviews WHERE Users_username = ?";
		PreparedStatement stmt1;

		try {
			stmt1 = (PreparedStatement) conn.prepareStatement(deleteReviewsSql);

			stmt1.setString(1, username);

			stmt1.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println( "Could not delete user at this time.\nError:\t" + e );
		}
		
		String deleteUserSql = "DELETE FROM users WHERE username = ?";
		PreparedStatement stmt2;
		
		try {
			stmt2 = (PreparedStatement) conn.prepareStatement(deleteUserSql);
			
			stmt2.setString(1,  username);
			
			stmt2.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Could not delete user at this time. \nError\t" + e);
		}

	}

}

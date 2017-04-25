package netbooks.persist;

import java.sql.SQLException;
import java.util.List;


import java.sql.Connection;
import com.mysql.jdbc.PreparedStatement;

import netbooks.objectlayer.User;

public class UserPersistImpl {
	
	public static Connection conn;

	public static List<User> getUserForLogin(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<User> getFullUserInfo(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void createUser(User user) {
		
		try {
			conn = DbUtils.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		// TODO Auto-generated method stub
		
	}

	public static void updatePassword(String username, String password) {
		// TODO Auto-generated method stub
		
	}

	public static void updateAddress(String username, String street, String city, String state, int zip) {
		// TODO Auto-generated method stub
		
	}

	public static void updateSub(String username, int sub) {
		// TODO Auto-generated method stub
		
	}

	public static void addWaitlistEntry(String username, String title) {
		// TODO Auto-generated method stub
		
	}

}

package netbooks.persistlayer;

import java.sql.SQLException;
import java.util.List;


import java.sql.Connection;
import com.mysql.jdbc.PreparedStatement;

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
	    PreparedStatement stmt = null;
	    
	    StringBuffer query = new StringBuffer(100);
	    StringBuffer condition = new StringBuffer(100);
	    
	    condition.setLength(0);
	    
	    query.append(sql);
	    
	    try {
		stmt = (PreparedStatement) conn.prepareStatement(sql);
		stmt.setString(1, username);                          //may be different index other than 1
		int assign = stmt.executeUpdate();
		if(assign != -1) {
		    ResultSet rs = stmt.getResultSet();
		    String user = rs.getString(1);
		    String pass = rs.getString(2);
		    int subscription = rs.getInt(3);
		    
		    User user1 = new User(user, pass, subscription);
		    userList.add(user1);
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
	    Book booksCheckedOut[] = null;                       //not sure how to retrieve these books
	    Book booksRead[] = null;
	    
	    
	    String sql = "SELECT * FROM users u WHERE u.username = ?";
	    PreparedStatement stmt = null;
	    
	    StringBuffer query = new StringBuffer(100);
	    StringBuffer condition = new StringBuffer(100);
	    
	    condition.setLength(0);
	    
	    query.append(sql);
	    
	    try {
		stmt = (PreparedStatement) conn.prepareStatement(sql);
		stmt.setString(1, username);                               //may be other index other than 1
		int assign = stmt.executeUpdate();
		if(assign != -1) {
		    ResultSet rs = stmt.getResultSet();
		    int id = rs.getInt(1);
		    String user = rs.getString(2);
		    String password = rs.getString(3);
		    String fname = rs.getString(4);
		    String lname = rs.getString(5); //some of these are probably off, I don't think my 
		    String email = rs.getString(6);//database is up to date with correct columns
		    String birthdate = rs.getString(7);
		    String address = rs.getString(8);
		    String city = rs.getString(9);
		    String state = rs.getString(10);
		    int zipcode = rs.getInt(11);
		    int cardNum = rs.getInt(12);
		    int subscription = rs.getInt(13);
		    
		    User user1 = new User(id, username, password, fname, lname, birthdate, address, city, state, zipcode, cardNum, subscription, booksCheckedOut, booksRead, email);
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
			
			System.out.println(stmt.toString());
			
			stmt.executeUpdate();

		}
		catch( SQLException e ) {
			e.printStackTrace();
			System.out.println( "User.save: failed to save a User: " + e );
		}
		
	}

	public static void updateEmail(String username, String email) {
	    
	    String updateSql = "UPDATE users SET email = ? WHERE username = ?";
	    PreparedStatement stmt2;
	    
	    try {
		stmt2 = (PreparedStatement) conn.prepareStatement(updateSql);
		
		stmt2.setString(3, email);               //may be different index other than 3
		
		stmt2.executeUpdate();
	    } catch(SQLException e) {
		e.printStackTrace();
		System.out.println( "Could not update email at this time.\nError:\t" + e );
	    }
	    
		
	}

	public static void updatePassword(String username, String password) {
	    
	    String updateSql = "UPDATE users SET password = ? WHERE username = ?";
	    PreparedStatement stmt2;
	    
	    try {
		stmt2 = (PreparedStatement) conn.prepareStatement(updateSql);
		
		stmt2.setString(2, password);               //may be different index other than 2
		
		stmt2.executeUpdate();
	    } catch(SQLException e) {
		e.printStackTrace();
		System.out.println( "Could not update password at this time.\nError:\t" + e );
	    }
	    
	}

	public static void updateAddress(String username, String street, String city, String state, int zip) {
	    
	    String updateSql = "UPDATE users SET address = ?, city = ?, state = ?, zipcode = ? WHERE username = ?";
	    PreparedStatement stmt2;
	    
	    try {
		stmt2 = (PreparedStatement) conn.prepareStatement(updateSql);
		
		stmt2.setString(7, street);
		stmt2.setString(9, city);              //these indeces may be wrong
		stmt2.setString(10, state);
		stmt2.setInt(8, zip);
		
		stmt2.executeUpdate();
	    } catch(SQLException e) {
		e.printStackTrace();
		System.out.println( "Could not update address at this time.\nError:\t" + e );
	    }
	    
	}

	public static void updateSub(String username, int sub) {
		// TODO Auto-generated method stub
		
	}

	public static void addWaitlistEntry(String username, String title) {
		// TODO Auto-generated method stub
		
	}

}

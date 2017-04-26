package netbooks.logiclayer;

import netbooks.objectlayer.User;
import netbooks.persistlayer.UserPersistImpl;
import java.util.List;

public class UserLogicImpl {
	
	public static List<User> getUserForLogin(String username)
	{
		return UserPersistImpl.getUserForLogin(username);
	}
	
	public static List<User> getFullUserInfo(String username)
	{
		return UserPersistImpl.getFullUserInfo(username);
	}
	
	
	public static void createUser(String username, String password, String fname, String lname, String birthdate, String address, 
			String city, String state, int zipcode, int subscription,String email)
	{
		User user = new User(username, password, fname, lname, birthdate, address, 
			city, state, zipcode, -1, subscription, null, null,email);
		UserPersistImpl.createUser(user);
	}
	
	public static void updateEmail(String username, String email)
	{
		UserPersistImpl.updateEmail(username, email);
	}
	
	public static void updatePassword(String username, String password)
	{
		UserPersistImpl.updatePassword(username, password);
	}
	
	public static void updateAddress(String username, String street, String city, String state, int zip)
	{
		UserPersistImpl.updateAddress(username, street, city, state, zip);
	}
	
	public static void updateSub(String username, int sub)
	{
		UserPersistImpl.updateSub(username, sub);
	}
	
	public static void addWaitlistEntry(String username, int book_id)
	{
		UserPersistImpl.addWaitlistEntry(username, book_id);
	}
}
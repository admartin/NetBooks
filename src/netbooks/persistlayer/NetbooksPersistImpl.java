package netbooks.persist;

import netbooks.objectlayer.*;

public class NetbooksPersistImpl {
	
	//insert movie into database
	public int select(Movie mov) {
		String sql = "INSERT INTO movies (name, year, rank) VALUES" + "('" + mov.getName() + "','" + mov.getYear() + "','" + mov.getRank() + "')";
		return DbAccessImpl.update(sql);
	}
	
	//show info for a movie
	public String persistMovieSearch(String movName) {
		String sql = "SELECT * FROM movies LEFT JOIN reviews ON movies.id = reviews.movie_id WHERE name = " + "'" + movName + "'";
		return DbAccessImpl.create(sql, true); 
	}
	
	//show all movies of a genre
	public String persistGenre(String genre) {
		String sql = "SELECT * FROM movies LEFT JOIN movies_genres ON movies.id = movies_genres.movie_id WHERE genre = " + "'" + genre + "'";
		return DbAccessImpl.create(sql, false);
	}
	
	//insert review into database
	public int persistReview(Review rev) {
		String sql = "INSERT INTO reviews ("; //INCOMPLETE
		return DbAccessImpl.update(sql);
	}
	
}

/* This class stores the information of a given song (artist and title).
 * It also includes getters for its private variables, and a toString
 * method for standard printing format.
 */
public class Song {

	// private variables
	private String artist;
	private String title;
	
	// constructor - create new song with specified artist and title
	Song(String artist, String title) {
		this.artist = artist;
		this.title = title;
	}
	
	// getters
	public String getArtist() { return artist; }
	public String getTitle() { return title; }
	
	// returns song details in an easily readable format
	public String toString() {
		return (artist + " - " + title);
	}
	
}

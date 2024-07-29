import java.util.NoSuchElementException;
import java.util.Random;	// Thomas's addition - for use in shuffle method
import java.io.*;			// Thomas's addition - for saving/loading
import java.util.Scanner;	// Thomas's addition - for loading (reading lines from file)

//Linked list implementation
class LList implements List {
private Link head;         // Pointer to list header
private Link tail;         // Pointer to last element
private Link curr;         // Access to current element
private int listSize;      // Size of list

// Constructors
LList(int size) { this(); }     // Constructor -- Ignore size
LList() { clear(); }

// Remove all elements
public void clear() {
 curr = tail = new Link(null); // Create trailer
 head = new Link(tail);        // Create header
 listSize = 0;
}

// Insert "it" at current position
public boolean insert(Object it) {
 curr.setNext(new Link(curr.element(), curr.next()));
 curr.setElement(it);
 if (tail == curr) tail = curr.next();  // New tail
 listSize++;
 return true;
}

// Append "it" to list
public boolean append(Object it) {
 tail.setNext(new Link(null));
 tail.setElement(it);
 tail = tail.next();
 listSize++;
 return true;
}

// Remove and return current element
public Object remove () throws NoSuchElementException {
 if (curr == tail) // Nothing to remove
   throw new NoSuchElementException("remove() in LList has current of " + curr + " and size of "
     + listSize + " that is not a a valid element");
 Object it = curr.element();             // Remember value
 curr.setElement(curr.next().element()); // Pull forward the next element
 if (curr.next() == tail) tail = curr;   // Removed last, move tail
 curr.setNext(curr.next().next());       // Point around unneeded link
 listSize--;                             // Decrement element count
 return it;                              // Return value
}

public void moveToStart() { curr = head.next(); } // Set curr at list start
public void moveToEnd() { curr = tail; }          // Set curr at list end

// Move curr one step left; no change if now at front
public void prev() {
 if (head.next() == curr) return; // No previous element
 Link temp = head;
 // March down list until we find the previous element
 while (temp.next() != curr) temp = temp.next();
 curr = temp;
}

// Move curr one step right; no change if now at end
public void next() { if (curr != tail) curr = curr.next(); }

public int length() { return listSize; } // Return list length


// Return the position of the current element
public int currPos() {
 Link temp = head.next();
 int i;
 for (i=0; curr != temp; i++)
   temp = temp.next();
 return i;
}

// Move down list to "pos" position
public boolean moveToPos(int pos) {
 if ((pos < 0) || (pos > listSize)) return false;
 curr = head.next();
 for(int i=0; i<pos; i++) curr = curr.next();
 return true;
}

// Return true if current position is at end of the list
public boolean isAtEnd() { return curr == tail; }

// Return current element value.
public Object getValue() throws NoSuchElementException {
 if (curr == tail) // No current element
   throw new NoSuchElementException("getvalue() in LList has current of " + curr + " and size of "
     + listSize + " that is not a a valid element");
 return curr.element(); 
}

// Check if the list is empty
public boolean isEmpty() { return listSize == 0; }





//Thomas's Project Work


/*
 * This method iterates through the playlist, front to back, and prints each song.
 */
public void play() {
	moveToStart();
	while(!isAtEnd()) {
		System.out.println(curr.element());
		next();
	}
}


/* 
 * This method will pseudo-randomly shuffle the playlist using a specified seed. It (1) generates
 * a pseudo-random index within the list. Then, it (2) moves curr to this index, stores this Song,
 * and removes it. Finally, (3) the stored Song is appended to the end of the list. These steps are
 * repeated listSize*10 times to ensure a thourough shuffle.
 * 
 * Returns null on empty playlists.
 * Otherwise, returns an array of ints. The elements of this array indicate the initial position
 * of each song in the shuffled playlist.
 */
public int[] shuffle(long seed) {
	
	if(isEmpty()) {	// catch empty playlists, return empty array
		return new int[0];
	}
	
	String[][] dict = new String[listSize][2]; // store initial song positions
	moveToStart();
	for (Integer i = 0; i < listSize; i++) {	// for every song in the playlist
		dict[i][0] = curr.element().toString();	// store it's information
		dict[i][1] = i.toString();	// and store it's initial position
		next();
	}
	
	Random r = new Random();
	r.setSeed(seed);	// setting seed for randomizer
	
	for (int i = 0; i < listSize*10; i++) { // shuffles [10 * listSize] times
		int index = r.nextInt(listSize);	// generate random index in list
		moveToPos(index);	// move to chosen index
		Song chosen = (Song) remove();	// removes and stores song at index
		append(chosen);	// appends it to the back of the list
	}
	
	int[] newOrder = new int[listSize];	// array of initial positions to be returned
	moveToStart();
	String song;
	for (int i = 0; i < listSize; i++) {	// for each song in the shuffled playlist
		song = curr.element().toString();	// get song
		for (int j = 0; j < dict.length; j++) {	// search for it in dict
			if (dict[j][0].equals(song)) {	// when song is found...
				newOrder[i] = Integer.parseInt(dict[j][1]);	// ...add it's corresponding initial position to array
				break;	// stop searching, move to next song
			}
		}
		next();
	}
	return newOrder; // return array of initial positions
}


/*
 * This method reverses the order of the playlist. It moves to the second to last song, removes it, and appends
 * it to the end of the list. Then it moves to the song before that one, removes and appends it, and so on. It
 * continues until the song that was initially at the end is now at the beginning.
 */
public void reverse() {
	if (listSize <= 1) { return; } // catch playlists of length 0 or 1 (no work to do)

	for (int i = listSize-2; i >= 0; i--) {	// starting from second to last song
		moveToPos(i);
		Song chosen = (Song) remove();	// removes and stores song
		append(chosen);	// append to end of the list
	}	
}


/*
 * This file saves the playlsit to a given text file. Artists are stored on odd number lines, and titles
 * are stored on even number lines. The end of the file is denoted by "#@$@%$@#$@#end_of_file".
 */
public void save(String fileName) {
	try {
		PrintWriter file = new PrintWriter(fileName);	// open file for writing
		moveToStart();	// starting from beginning of playlist
		Song s;
		while(!isAtEnd()) {	// for every song in playlist
			s = (Song) curr.element();	// get song
			file.println(s.getArtist());	// print artist to file
			file.println(s.getTitle());		// print title to file
			next();
		}
		file.println("#@$@%$@#$@#end_of_file");	// EOF marker
		file.close();	// close file
	} catch (FileNotFoundException e) {	// file not found
		System.out.print("File not found");
		return;
	}
}


/*
 * This method loads in a playlist from a specified save file. It reads artist and titles until it
 * reaches the EOF marker.
 */
public void load(String fileName) {
	clear();	// clear current playlist
	try {
	    FileInputStream file = new FileInputStream(fileName);	// open file for reading
	    Scanner in = new Scanner(file);	// set up scanner to read lines from file
	    String artist = "";
	    String title = "";
	    while (true) {	// run until EOF marker
	    	artist = in.nextLine();	// load artist name
	    	if (artist.equals("#@$@%$@#$@#end_of_file")) {	// EOF marker found, break out of loop
	    		break;
	    	}
	    	title = in.nextLine();	// load song title
	    	this.append(new Song(artist, title));	// add loaded song details to playlist
	    }
	    in.close();	// close file
	} catch (FileNotFoundException e) {	// file not found
		System.out.print("File not found");
		return;
	}
}

}
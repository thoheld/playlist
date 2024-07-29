import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LListTest {

	/*
	 * This method "tests" the play method, though no assertion are actually run. It just
	 * prints some sample playlists
	 */
	@Test
	void test_play() {
		
		// playlist with 5 songs
		LList a = new LList();
		a.append(new Song("artist1", "good song"));
		a.append(new Song("artist2", "good song"));
		a.append(new Song("artist is good", "great song"));
		a.append(new Song("artist is okay", "SONG 4"));
		a.append(new Song("artist1", "good song pt. 2"));
		a.play();
		System.out.println();
		
		
		// playlist with 1 song
		LList b = new LList();
		b.append(new Song("lonely band", "lonelier song")); // add song
		b.play();
		
		
		// empty playlist
		LList c = new LList();
		c.play();
		
	}
	
	
	/*
	 * This method tests the shuffle method. It generates two identical playlist, shuffles one, and stores
	 * the shuffle key returned by the shuffle method. It then passes the initial playlist, the shuffled
	 * playlist, and the shuffle key into isShuffled to determine whether or not the shuffle was successful
	 * and if the returned key is correct.
	 */
	@Test
	void test_shuffle() {
		
		// testing on playlist of length 10
		LList a = new LList();	// playlist to be shuffled
		LList aInit = new LList();	// playlist before shuffling
		for (int i = 0; i < 10; i++) {	// adding 10 songs
			a.append(new Song("a" + i, "s" + i));	// generate names for artist, title
			aInit.append(new Song("a" + i, "s" + i));	// generate names for artist, title
		}
		int actualShuffle1[] = a.shuffle(07072001);	// shuffle, store returned array
		assertEquals(isShuffled(aInit, a, actualShuffle1), true);	// pass into isShuffled

		
		// testing on playlist of length 1000
		LList b = new LList();	// playlist to be shuffled
		LList bInit = new LList();	// playlist before shuffling
		for (int i = 0; i < 1000; i++) {	// adding 1000 songs
			b.append(new Song("a" + i, "s" + i));	// generate names for artist, title
			bInit.append(new Song("a" + i, "s" + i));	// generate names for artist, title
		}
		int actualShuffle2[] = b.shuffle(07072001);	// shuffle, store returned array
		assertEquals(isShuffled(bInit, b, actualShuffle2), true);	// pass into isShuffled
		
		
		// testing on playlist of length 0
		LList c = new LList();	// playlist to be shuffled
		LList cInit = new LList();	// playlist before shuffling
		int actualShuffle2point5[] = c.shuffle(07072001);	// shuffle, store returned array
		assertEquals(isShuffled(cInit, c, actualShuffle2point5), true);	// pass into isShuffled
		
		
		// testing on playlist of length 1
		LList d = new LList();	// playlist to be shuffled
		LList dInit = new LList();	// playlist before shuffling
		d.append(new Song("Wonder", "One Hit"));
		dInit.append(new Song("Wonder", "One Hit"));
		int actualShuffle3[] = d.shuffle(07072001);	// shuffle, store returned array
		assertEquals(isShuffled(dInit, d, actualShuffle3), true);	// pass into isShuffled
		
		
		// testing on playlist of length 2
		LList e = new LList();	// playlist to be shuffled
		LList eInit = new LList();	// playlist before shuffling
		e.append(new Song("art0", "son0"));
		e.append(new Song("art1", "son1"));
		eInit.append(new Song("art0", "son0"));
		eInit.append(new Song("art1", "son1"));
		int actualShuffle4[] = e.shuffle(07072001);	// shuffle, store returned array
		assertEquals(isShuffled(eInit, e, actualShuffle4), true);	// pass into isShuffled
		
		
		// testing unrelated shuffles (should return false)
		assertEquals(isShuffled(aInit, b, actualShuffle1), false);
		assertEquals(isShuffled(b, c, actualShuffle2point5), false);
		
		
		
		
	}
	
	
	/*
	 * This method tests the reverse method. It creates two identical playlists, reverses one
	 * and then passes both into isReversed to check that the reverse was done properly.
	 */
	@Test
	void test_reverse() {
		
		// testing on playlist of length 10
		LList a = new LList();	// playlist to be reversed
		LList aInit = new LList(); // initial order
		for (int i = 0; i < 10; i++) {	// adding 10 songs to each
			a.append(new Song("a" + i, "s" + i));
			aInit.append(new Song("a" + i, "s" + i));
		}
		a.reverse();	// reverse
		assertEquals(isReversed(a, aInit), true);	// pass into isReversed
		
		
		// testing on playlist of length 1000
		LList b = new LList();	// playlist to be reversed
		LList bInit = new LList(); // initial order
		for (int i = 0; i < 1000; i++) {	// adding 1000 songs to each
			b.append(new Song("b" + i, "s" + i));
			bInit.append(new Song("b" + i, "s" + i));
		}
		b.reverse();	// reverse
		assertEquals(isReversed(b, bInit), true);	// pass into isReversed
		
		
		// testing on playlist of length 0
		LList c = new LList();	// playlist to be reversed
		LList cInit = new LList(); // initial order
		c.reverse();	// reverse
		assertEquals(isReversed(c, cInit), true);	// pass into isReversed
		
		
		// testing on playlist of length 1
		LList d = new LList();	// playlist to be reversed
		LList dInit = new LList();	// initial order
		d.append(new Song("a0", "s0"));	// add song
		dInit.append(new Song("a0", "s0"));
		d.reverse();	// reverse
		assertEquals(isReversed(d, dInit), true);	// pass into isReversed

		
		// testing on playlist of length 2
		LList e = new LList();	// playlist to be reversed
		LList eInit = new LList();	// initial order
		e.append(new Song("a0", "s0"));	// add songs
		e.append(new Song("a1", "s1"));
		eInit.append(new Song("a0", "s0"));
		eInit.append(new Song("a1", "s1"));
		e.reverse();	// reverse
		assertEquals(isReversed(e, eInit), true);	// pass into isReversed
		
		
		// testing two unrelated playlist (should return false)
		assertEquals(isReversed(a, b), false);
		assertEquals(isReversed(b, c), false);


		
	}

	
	/*
	 * This method "tests" the save method, though no assertion are actually run.
	 */
	@Test
	void test_save() {
		
		// saving 10-song playlist
		LList a = new LList();
		for (int i = 0; i < 10; i++) {	// adding 10 songs
			a.append(new Song("a" + i, "s" + i));	// generate names for artist, title
		}
		a.save("save1.txt");	// save playlist to save1.txt
	}
	
	
	/*
	 * This method tests the load method. A playlist gets created and saved to a file. This file
	 * is then loaded into a different playlist. The initial playlist and loaded playlist are then
	 * compared to ensure that the load was successful. (I guess this technically tests save as well,
	 * since the tests wouldn't pass if save didn't work properly).
	 */
	@Test
	void test_load() {
		
		// testing load with content in the file
		LList a1 = new LList();	// playlist used to set up save file
		LList a2 = new LList();	// playlist to load to
		a1.append(new Song("random0", "song0"));
		a1.append(new Song("random1", "song1"));
		a1.append(new Song("random2", "song2"));
		a1.append(new Song("random3", "song3"));
		a1.append(new Song("random4", "song4"));
		a1.save("save1.txt");	// save a1
		a2.load("save1.txt");	// load to a2
		a1.reverse();	// reverse a1 (just so I can isReversed() to compare)
		assertEquals(isReversed(a1, a2), true);	// compare initial and loaded playlist

		
		// testing load with empty file
		LList b1 = new LList();	// playlist used to set up save file
		LList b2 = new LList();	// playlist to load to
		b1.save("save1.txt");	// save b1
		b2.load("save1.txt");	// load to b2
		b1.reverse();	// reverse b1 (just so I can isReversed() to compare)
		assertEquals(isReversed(b1, b2), true);	// compare initial and loaded playlist

		
	}
	
	/*
	 * This method ensures that 2 playlists are opposites (i.e., one is the reversed version of the other)
	 * and returns true or false accordingly. It goes through each song in playlists a and b, ensuring that
	 * the first song in a == the last song in b, the second song in a == the second to last song in b, and so on.
	 */
	boolean isReversed(LList a, LList b) {
		if (a.length() != b.length()) { return false; }	// lists are different lengths, cannot be corresponding reverse
		if (a.length() == b.length() && b.length() == 0) { return true; } // 2 empty lists, automatically true
		
		a.moveToStart();	// move playlists to opposite ends
		b.moveToEnd();
		b.prev();	// (move off of tail)
		Song songA;
		Song songB;
		while(!a.isAtEnd()) {	// compare every pair of songs
			songA = (Song) a.getValue();	// get songs
			songB = (Song) b.getValue();
			if (!(songA.getArtist().equals(songB.getArtist()))) {	// if artists are different, return false
				return false;
			}
			if (!(songA.getTitle().equals(songB.getTitle()))) {	// if titles are different, return false
				return false;
			}
			a.next();	// move to compare the next pair of songs
			b.prev();
		}
		return true;	// all songs are matching
	}
	
	
	/*
	 * This method helps test the shuffle method. It takes an initial playlist and its corresponding shuffled
	 * playlist and compares them based on a given shuffle key. The shuffle key stores the initial positions
	 * of the songs in the shuffled playlist. For example, key[0] corresponds to the first song of the shuffled
	 * LList, and its value denotes the original position of said song (before the shuffle took place).
	 */
	boolean isShuffled(LList init, LList shuffled, int[] key) {
		// if lengths are different, return false
		if (init.length() != shuffled.length() || key.length != shuffled.length()) { return false; }
		
		Song a;
		Song b;
		shuffled.moveToStart();	// starting from the beginning of the shuffled playlist
		for (int i : key) {	// for every position in the key
			init.moveToPos(i);	// move the initial playlist to the specified position
			a = (Song) init.getValue();	// store song at the specified position
			b = (Song) shuffled.getValue();	// store shuffled song being checked
			if (!(a.getArtist().equals(b.getArtist()))) { return false; }	// ensure they match up
			if (!(a.getTitle().equals(b.getTitle()))) { return false; }
			shuffled.next();	// move to check the next song in the shuffled playlist
		}
		return true;	// the key matched the inital playlist with the shuffled playlist
	}
	
}

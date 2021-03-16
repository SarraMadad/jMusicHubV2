package musichub.business;

import java.util.*;

/**
 * ElementList is the class containing all elements in a list.
 * It represents the librairy itself.
 * <p>
 * There is only one ElementList object with all songs
 * and audio books informations.
 * <p>
 * Elements can be added or removed from the list.
 * 
 * @author FERNANDES Mickael and LECLERC Maxence
 * @version 1.0
 * @see Element
 */
public class ElementList extends UserList  {
	/** List containing all songs and audio books. */
	private LinkedList<Element> list = new LinkedList<Element>();

	/**
	 * Sole constructor. (For invocation by subclass 
	 * constructors, typically implicit.)
	 */
	public ElementList() {}

	/**
	 * Overrides the standard toString method.
	 * <p>
	 * Used for displaying the content of the librairy.
	 * Elements are not sorted.
	 * 
	 * @return all informations about the librairy
	 * @see Chanson#toString()
	 * @see LivreAudio#toString()
	 */
	public String toString() {
		String elements = "Elements de la bibliothèque :\n";

		for(Element e : getList()) {
			elements += e.toString() + "\n";
		}

		return elements;
	}

	/** 
	 * Returns all the elements in the librairy.
	 * 
	 * @return the entire librairy
	 * @see Element
	 */
	public LinkedList<Element> getList() {
		return list;
	}

	/** 
	 * Registers the elements list of the librairy when deserialized.
	 * 
	 * @param list the element list of this serialized librairy
	 * @see Element
	 */
	public void setList(LinkedList<Element> list) {
		this.list = list;
	}

	/**
	 * Adds an element to its element list.
	 * 
	 * @param e the element to add
	 * @see Element
	 */
	public void add(Element e) {
		list.add(e);
	}

	/**
	 * Removes a song from its elements list.
	 * 
	 * @param c the song to remove
	 * @see Chanson
	 */
	public void delChanson(Chanson c) {
		list.remove(c);
	}

	/**
	 * Removes an audio book from its elements list.
	 * 
	 * @param l the audio book to remove
	 * @see LivreAudio
	 */
	public void delLivreAudio(LivreAudio l) {
		list.remove(l);
	}
	
	/**
	 * Displays the last element that has been added to its list.
	 * 
	 * @return the last song or audio book as a String
	 * @see Chanson#toString()
	 * @see LivreAudio#toString()
	 */
	public String peekLast() {
		Element last = list.peekLast();
		return last.toString();
	}

	/**
	 * Displays the songs as a String, sorted by title.
	 * 
	 * @return the sorted songs as a String
	 * @see Chanson#toString()
	 */
	public String listeChanson() {
		LinkedList<Chanson> sortedList = new LinkedList<Chanson>();

		for(Element e : getList()) {
			if(e instanceof Chanson) {
				Chanson chanson = (Chanson) e;
				sortedList.add(chanson);
			}
		}

		for(Chanson c : sortedList) {
			Collections.sort(sortedList, new Comparator<Chanson>() {
		    	@Override
		    	public int compare(Chanson c1, Chanson c2) {
			        if (c1.getTitre() == c2.getTitre()) {
			        	return c1.getArtiste().compareTo(c2.getArtiste());
			        } else {
			        	return c1.getTitre().compareTo(c2.getTitre());
			        }
		    	}
		    });
		}

		String chansons = "Chansons disponibles :\n";
		for(Chanson c : sortedList) {
			chansons += c.toString() + "\n";
		}

		return chansons;
	}

	/**
	 * Displays the audio books as a String, sorted by writter.
	 * 
	 * @return the sorted audio books as a String
	 * @see LivreAudio#toString()
	 */
	public String listeLivreAudio() {
		LinkedList<LivreAudio> sortedList = new LinkedList<LivreAudio>();

		for(Element e : getList()) {
			if(e instanceof LivreAudio) {
				LivreAudio livre = (LivreAudio) e;
				sortedList.add(livre);
			}
		}

		for(LivreAudio l : sortedList) {
			Collections.sort(sortedList, new Comparator<LivreAudio>() {
		    	@Override
		    	public int compare(LivreAudio l1, LivreAudio l2) {
			        if (l1.getAuteur() == l2.getAuteur()) {
			        	return l1.getTitre().compareTo(l2.getTitre());
			        } else {
			        	return l1.getAuteur().compareTo(l2.getAuteur());
			        }
		    	}
		    });
		}

		String livres = "Livres audio disponibles :\n";
		for(LivreAudio l : sortedList) {
			livres += l.toString() + "\n";
		}

		return livres;
	}
}
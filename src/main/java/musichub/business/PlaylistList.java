package musichub.business;

import java.util.*;

/**
 * PlaylistList is the class containing all playlists in a list.
 * <p>
 * There is only one PlaylistList object with all playlists informations.
 * <p>
 * Playlists can be added or removed from the list.
 *
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 * @see Playlist
 */
public class PlaylistList extends UserList {
	/** List containing all playlists. */
	private LinkedList<Playlist> list = new LinkedList<Playlist>();

	/**
	 * Sole constructor. (For invocation by subclass 
	 * constructors, typically implicit.)
	 */
	public PlaylistList() {}

	/**
	 * Overrides the standard toString method.
	 * <p>
	 * Used for displaying the playlists, sorted by name.
	 * 
	 * @return all informations about the playlists
	 * @see Playlist#toString()
	 */
	public String toString() {
		LinkedList<Playlist> sortedList = new LinkedList<Playlist>(getList());

		/* Sorts all playlists by name. */
		for(Playlist a : sortedList) {
			Collections.sort(sortedList, new Comparator<Playlist>() {
		    	@Override
		    	public int compare(Playlist p1, Playlist p2) {
		        	return p1.getNom().compareTo(p2.getNom());
		    	}
		    });
		}

		String playlists = "Playlists disponibles :\n";

		for(Playlist p:sortedList) {
			playlists += p.toString() + "\n";
		}

		return playlists;
	}

	/** 
	 * Returns all the playlists in the librairy.
	 * 
	 * @return all the playlists
	 * @see Playlist
	 */
	public LinkedList<Playlist> getList() {
		return list;
	}

	/** 
	 * Registers the playlists list of the librairy when deserialized.
	 * 
	 * @param list the playlists list of this serialized object
	 * @see Playlist
	 */
	public void setList(LinkedList<Playlist> list) {
		this.list = list;
	}

	/**
	 * Adds a playlist to its list.
	 * 
	 * @param p the playlist to add
	 * @see Playlist
	 */
	public void add(Playlist p) {
		list.add(p);
	}

	/**
	 * Removes a playlist from its list.
	 * 
	 * @param p the playlist to remove
	 * @see Playlist
	 */
	public void del(Playlist p) {
		list.remove(p);
	}

	/**
	 * Displays the last playlist that has been added to its list.
	 * 
	 * @return the last playlist as a String
	 * @see Playlist#toString()
	 */
	public String peekLast() {
		Playlist last = list.peekLast();
		return last.toString();
	}

	/**
	 * Displays the elements in a playlist.
	 * 
	 * @param playlistName user input
	 * @return the songs in the album as a String
	 */
	public String displaySongsOfPlaylist(String playlistName) {
		String list = "";
        for(Playlist p : getList()) {
            if(p.getNom().equals(playlistName)) {
                list = p.getList();
            }
        }
        return list;
	}

	/**
	 * Displays the elements in a random order in a playlist.
	 *
	 * @param playlistName user input
	 * @return the songs in the album as a String
	 */
    public String randomDisplaySongsOfPlaylist(String playlistName) {
    	String list = "";
        for(Playlist p : getList()) {
            if(p.getNom().equals(playlistName)) {
                list = p.getList(p.aleatoire());
            }
        }
        return list;
    }

    /**
	 * Adds a song or audio book to a playlist.
	 * 
	 * @param playlistName user input
	 * @param elementName user input
	 * @param elements the ElementList to select a music from
	 */
    public void addElement(String playlistName, String elementName, ElementList elements)
    {
        for(Playlist p : getList()) {
            if(p.getNom().equals(playlistName)) {
                for(Element e : elements.getList()) {
                    if(e instanceof Chanson) {												// If it's a song
                        Chanson chanson = (Chanson) e;
                        String ajout = chanson.getTitre() + " - " + chanson.getArtiste();
                           	if(ajout.equals(elementName)) {
                               	p.add(chanson);
                               	System.out.println("Chanson ajoutée avec succès dans la playlist.");
                           	}
                    } else {																// If it's an audio book
                        LivreAudio livre = (LivreAudio) e;
                        String ajout = livre.getTitre() + " - " + livre.getAuteur();
                        	if(ajout.equals(elementName)) {
                            	p.add(livre);
                            	System.out.println("Livre audio ajouté avec succès dans la playlist.");
                           	}
                    }
                   }
            }
        }
    }

    /**
	 * Removes an element from a playlist.
	 * 
	 * @param playlistName user input
	 * @param elementName user input
	 * @param elements the ElementList to select a music from
	 */
    public void delSongOfPlaylist(String playlistName, String elementName, ElementList elements)
    {
        for(Playlist p : getList()){
            if(p.getNom().equals(playlistName)) {
                LinkedList<Element> tempElement = new LinkedList<Element>(p.getElementList());
                for(Element e : tempElement) {
                      if(e instanceof Chanson) {
                        Chanson chanson = (Chanson) e;
                        String supp = chanson.getTitre() + " - " + chanson.getArtiste();
                        if(supp.equals(elementName)) {
                            p.delChanson(chanson);
                            System.out.println("Chanson supprimée avec succès de la playlist.");
                        }
                    } else {
                        LivreAudio livre = (LivreAudio) e;
                        String supp = livre.getTitre() + " - " + livre.getAuteur();
                        if(supp.equals(elementName)) {
                            p.delLivreAudio(livre);
                            System.out.println("Livre audio supprimé avec succès de la playlist.");
                        }
                    }
                }
            }
        }
    }
}
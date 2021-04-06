package musichub.business;

import java.util.*;

/**
 * AlbumList is the class containing all albums in a list.
 * <p>
 * There is only one AlbumList object with all albums informations.
 * <p>
 * Albums can be added or removed from the list.
 * 
 * @author FERNANDES Mickael and LECLERC Maxence
 * @version 1.0
 * @see Album
 */
public class AlbumList extends UserList {
	/** List containing all albums. */
	private LinkedList<Album> list = new LinkedList<Album>();

	/**
	 * Sole constructor. (For invocation by subclass 
	 * constructors, typically implicit.)
	 */
	public AlbumList() {}

	/**
	 * Overrides the standard toString method.
	 * <p>
	 * Used for displaying the albums, sorted by release date.
	 * 
	 * @return all informations about the albums
	 * @see Album#toString()
	 */
	public String toString() {
		LinkedList<Album> sortedList = new LinkedList<Album>(getList());

		/* Sorts all albums by release date. */
		for(Album a : sortedList) {
			Collections.sort(sortedList, new Comparator<Album>() {
		    	@Override
		    	public int compare(Album a1, Album a2) {
			        if (a1.getDateSortie() == a2.getDateSortie()) {
			        	return a1.getTitre().compareTo(a2.getTitre());
			        } else {
			        	return a1.getDateSortie().compareTo(a2.getDateSortie());
			        }
		    	}
		    });
		}

		String albums = "Albums disponibles :\n";

		for(Album a:sortedList) {
			albums += a.toString() + "\n";
		}

		return albums;
	}

	/** 
	 * Returns all the albums in the librairy.
	 * 
	 * @return all the albums
	 * @see Album
	 */
	public LinkedList<Album> getList() {
		return list;
	}

	/** 
	 * Registers the albums list of the librairy when deserialized.
	 * 
	 * @param list the albums list of this serialized object
	 * @see Album
	 */
	public void setList(LinkedList<Album> list) {
		this.list = list;
	}

	/**
	 * Adds an album to its list.
	 * 
	 * @param a the album to add
	 * @see Album
	 */
	public void add(Album a) {
		list.add(a);
	}

	/**
	 * Removes an album from its list.
	 * 
	 * @param a the album to remove
	 * @see Album
	 */
	public void del(Album a) {
		list.remove(a);
	}

	/**
	 * Displays the last album that has been added to its list.
	 * 
	 * @return the last album as a String
	 * @see Album#toString()
	 */
	public String peekLast() {
		Album last = list.peekLast();
		return last.toString();
	}

	/**
	 * Displays the musics in an album.
	 * 
	 * @param albumName user input
	 */
	public String displaySongsOfAlbum(String albumName) {
		String list = "";
		for(Album a : getList()) {
			if(a.getTitre().equals(albumName)) {
				list = a.getList();
			}
		}
		return list;
	}

	/**
	 * Displays the musics sorted by type in an album.
	 * 
	 * @param albumName user input
	 */
	public String displaySongsOfAlbumSorted (String albumName) {
		String list = "";
        for(Album a : getList()) {
            if(a.getTitre().equals(albumName)) {

            	/* Sorts the song by type. */
                LinkedList<Chanson> sortedList = new LinkedList<Chanson>(a.getSongList());
                Collections.sort(sortedList, new Comparator<Chanson>() {
                    @Override
                    public int compare(Chanson c1, Chanson c2) {
                        if (c1.getGenre() == c2.getGenre()) {
                            if (c1.getTitre() == c2.getTitre()) {
                                return c1.getArtiste().compareTo(c2.getArtiste());
                            } else {
                                return c1.getTitre().compareTo(c2.getTitre());
                            }
                        } else {
                            return c1.getGenre().compareTo(c2.getGenre());
                        }
                    }
                });
                list = a.getList(sortedList);
            }
        }
        return list;
    }

    /**
	 * Displays the musics in a random order in an album.
	 * 
	 * @param albumName user input
	 */
    public String randomDisplaySongsOfAlbum (String albumName) {
    	String list = "";
        for(Album a : getList()) {
            if(a.getTitre().equals(albumName)) {
                list = a.getList(a.aleatoire());
            }
        }
        return list;
    }

    /**
	 * Adds a music to an album.
	 * 
	 * @param albumName user input
	 * @param musicName user input
	 * @param elements the ElementList to select a music from
	 */
    public void addMusic(String albumName,String musicName,ElementList elements){
        for(Album a : getList()) {
            if(a.getTitre().equals(albumName)) {
                for(Element e : elements.getList()) {
                    if(e instanceof Chanson) {
                        Chanson chanson = (Chanson) e;
                        String ajout = chanson.getTitre() + " - " + chanson.getArtiste();
                        if(ajout.equals(musicName)) {
                            a.add(chanson);
                            System.out.println("Chanson ajoutée avec succès à l'album.");
                           }
                       }
                  }
            }
        }
    }

    /**
	 * Removes a music from an album.
	 * 
	 * @param albumName user input
	 * @param musicName user input
	 * @param elements the ElementList to select a music from
	 */
    public void delSongOfAlbum(String albumName, String musicName, ElementList elements)
    {
        for(Album a : getList()) {
            if(a.getTitre().equals(albumName)) {
                LinkedList<Chanson> tempChanson = new LinkedList<Chanson>(a.getSongList());
                for(Chanson c : tempChanson) {
                    String supp = c.getTitre() + " - " + c.getArtiste();
                    if(supp.equals(musicName)) {
                           a.del(c);
                           System.out.println("Chanson supprimée avec succès de l'album.");
                       }
                 }
            }
        }
    }
}
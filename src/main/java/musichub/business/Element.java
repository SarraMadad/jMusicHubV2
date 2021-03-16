package musichub.business;

import java.util.*;
import java.io.*;

/**
 * Element is the base class for Chanson and LivreAudio.
 * <p>
 * It contains all attributes and methodes commons to those classes.
 * 
 * @author FERNANDES Mickael and LECLERC Maxence
 * @version 1.0
 * @see ElementList
 * @see Chanson
 * @see LivreAudio
 */
public class Element { //implements IntInfos {
	/** ID counter for all objects. */
	protected static int compteurId = 0;
	/** ID of the object. */
	protected int id;
	/** Title of the Chanson or LivreAudio. */
	protected String titre;
	/** Length of the Chanson or LivreAudio. */
	protected int duree;
	/** Location of the Chanson or LivreAudio. */
	protected String contenu;

	/** 
	 * Returns the ID of this Chanson or LivreAudio.
	 * 
	 * @return the ID of this element
	 */
	public int getId() {
		return id;
	}

	/** 
	 * Registers the ID of this Chanson or LivreAudio when deserialized.
	 * 
	 * @param id the ID number of this serialized object
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** 
	 * Returns the title of this Chanson or LivreAudio.
	 * <p>
	 * The title of this element is displayed in the librairy.
	 * 
	 * @return the title of this element
	 * @see Chanson#toString()
	 * @see LivreAudio#toString()
	 * @see Album#getList()
	 * @see Playlist#getList()
	 */
	public String getTitre() {
		return titre;
	}

	/** 
	 * Registers the title of this Chanson or LivreAudio when deserialized.
	 * 
	 * @param titre the title of this serialized object
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/** 
	 * Returns the length of this Chanson or LivreAudio.
	 * <p>
	 * The length of this element is displayed in the librairy.
	 * 
	 * @return the length of this element
	 * @see Chanson#toString()
	 * @see LivreAudio#toString()
	 * @see Album#getList()
	 * @see Playlist#getList()
	 */
	public int getDuree() {
		return duree;
	}

	/** 
	 * Registers the length of this Chanson or LivreAudio when deserialized.
	 * 
	 * @param duree the length of this serialized object
	 */
	public void setDuree(int duree) {
		this.duree = duree;
	}

	/** 
	 * Returns the location of this Chanson or LivreAudio.
	 * <p>
	 * The location of this element is displayed in the librairy.
	 * 
	 * @return the location of this element
	 * @see Chanson#toString()
	 * @see LivreAudio#toString()
	 * @see Album#getList()
	 * @see Playlist#getList()
	 */
	public String getContenu() {
		return contenu;
	}

	/** 
	 * Registers the location of this Chanson or LivreAudio when deserialized.
	 * 
	 * @param contenu the location of this serialized object
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
}
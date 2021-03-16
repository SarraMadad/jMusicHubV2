package musichub.util;

import musichub.business.*;

import java.beans.XMLDecoder;
import java.io.*;

/**
 * DeserializeFromXML allow the deserialization of all objects of the librairy.
 * <p>
 * The objects to deserialize are ElementList, AlbumList and PlaylistList.
 * 
 * @author FERNANDES Mickael and LECLERC Maxence
 * @version 1.0
 * @see ElementList
 * @see AlbumList
 * @see PlaylistList
 * @see SerializeToXML
 */
public class DeserializeFromXML {
	/** Name of the file to read from. */
	private String SERIALIZED_FILE_NAME;

	/**
	 * Constructor to deserialize an object. It has to be called before
	 * the others methods of this class.
	 * <p> 
	 * Depending of the name argument, the file name will change.
	 * 
	 * @param name in which file to read from
	 */
	public DeserializeFromXML(String name) {
		switch(name) {
			case "elements":
				SERIALIZED_FILE_NAME = "./files/elements.xml";
				break;
			case "albums":
				SERIALIZED_FILE_NAME = "./files/albums.xml";
				break;
			case "playlists":
				SERIALIZED_FILE_NAME = "./files/playlists.xml";
				break;
		}
	}

	/**
	 * Used to deserialize the AlbumList object.
	 * 
	 * @return an AlbumList object containning all albums informations
	 */
	public AlbumList decodeAlbums() {
		XMLDecoder decoder = null;
		AlbumList list = new AlbumList();
		try {

			/* Decodes the object. */
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME)));
			list = (AlbumList) decoder.readObject();		
			decoder.close();
		} catch(FileNotFoundException fileNotFound) {
			System.out.println("Erreur : fichier albums.xml introuvable. Aucune donnée n'a été importée.");
		}

		return list;
	}

	/**
	 * Used to deserialize the ElementList object.
	 * 
	 * @return an ElementList object containning all songs and audio books informations
	 */
	public ElementList decodeElements() {
		XMLDecoder decoder = null;
		ElementList list = new ElementList();
		try {

			/* Decodes the object. */
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME)));
			list = (ElementList) decoder.readObject();		
			decoder.close();
		} catch(FileNotFoundException fileNotFound) {
			System.out.println("Erreur : fichier elements.xml introuvable. Aucune donnée n'a été importée.");
		}

		return list;
	}

	/**
	 * Used to deserialize the PlaylistList object.
	 * 
	 * @return an PlaylistList object containning all playlists informations
	 */
	public PlaylistList decodePlaylists() {
		XMLDecoder decoder = null;
		PlaylistList list = new PlaylistList();
		try {

			/* Decodes the object. */
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME)));
			list = (PlaylistList) decoder.readObject();		
			decoder.close();
		} catch(FileNotFoundException fileNotFound) {
			System.out.println("Erreur : fichier playlists.xml introuvable. Aucune donnée n'a été importée.");
		}

		return list;
	}
}
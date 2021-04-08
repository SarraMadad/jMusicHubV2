package musichub.util;

import musichub.business.*;

import java.beans.XMLEncoder;
import java.io.*;

/**
 * SerializeToXML allow the serialization of all objects of the librairy.
 * <p>
 * The objects to serialize are ElementList, AlbumList and PlaylistList.
 *
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 * @see ElementList
 * @see AlbumList
 * @see PlaylistList
 * @see DeserializeFromXML
 */
public class SerializeToXML {
	/** Name of the file to write in. */
	private String SERIALIZED_FILE_NAME;

	/**
	 * Constructor to serialize an object.
	 * <p> 
	 * Depending of the name argument, the file name will change.
	 * 
	 * @param name in which file to serialize
	 * @param list the object to serialize
	 */
	public SerializeToXML(String name, UserList list) {
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

		XMLEncoder encoder = null;
		try {
			/* Writes the object in the file selected. */
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(SERIALIZED_FILE_NAME)));
			encoder.writeObject(list);
			encoder.close();
		} catch(FileNotFoundException fileNotFound) {
			IntLogger sfl = SingletonFileLogger.getInstance();
			sfl.write(Levels.WARNING, "SerializeToXML() : file " + SERIALIZED_FILE_NAME + " not found");

			IntLogger scl = SingletonConsoleLogger.getInstance();
			scl.write(Levels.INFO, "Fichier XML introuvable. Les modifications n'ont pas été enregistrées.");
		}
	}
}
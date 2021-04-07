package musichub.main;

import musichub.business.*;
import musichub.util.DeserializeFromXML;

/**
 * MainNetwork inherits MainClient and MainServer.
 * <p>
 * The objects to update are ElementList, AlbumList and PlaylistList.
 *
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 * @see ElementList
 * @see AlbumList
 * @see PlaylistList
 */

public abstract class MainNetwork {
    /** List of all the elements. */
    ElementList elements = null;
    /** List of all the albums. */
    AlbumList albums = null;
    /** List of all the playlists. */
    PlaylistList playlists = null;

    protected void actualisation() {
        elements = new DeserializeFromXML("elements").decodeElements();
        albums = new DeserializeFromXML("albums").decodeAlbums();
        playlists = new DeserializeFromXML("playlists").decodePlaylists();
    }
}

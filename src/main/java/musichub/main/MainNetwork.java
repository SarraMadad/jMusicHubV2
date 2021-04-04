package musichub.main;

import musichub.business.AlbumList;
import musichub.business.ElementList;
import musichub.business.PlaylistList;
import musichub.util.DeserializeFromXML;

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

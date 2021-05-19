package cz.fi.muni.pa165.yellow_yak;

/**
 * Represents the entry points for the API
 * this list can be increased so that it contains all the 
 * other URIs also for the sub-resources so that it can 
 * reused globally from all the controllers
 */
public abstract class ApiUris {
    public static final String ROOT_URI_COMPETITIONS   = "/competitions";
    public static final String ROOT_URI_PLAYERS      = "/players";
    public static final String ROOT_URI_GAMES     = "/games";
    public static final String ROOT_URI_TEAMS = "/teams";
    public static final String ROOT_URI_SCORES = "/scores";
}

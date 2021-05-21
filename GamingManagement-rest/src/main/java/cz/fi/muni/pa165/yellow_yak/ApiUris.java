package cz.fi.muni.pa165.yellow_yak;

/**
 * Represents the entry points for the API
 * this list can be increased so that it contains all the 
 * other URIs also for the sub-resources so that it can 
 * reused globally from all the controllers
 */
public abstract class ApiUris {
    public static final String ROOT_URI_COMPETITIONS   = "rest/competitions";
    public static final String ROOT_URI_PLAYERS      = "rest/players";
    public static final String ROOT_URI_GAMES     = "rest/games";
    public static final String ROOT_URI_TEAMS = "rest/teams";
    public static final String ROOT_URI_SCORES = "rest/scores";
}

package me.nettychannell.bhapi.user;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.UUID;

public interface BHUser {

    /**
     * It returns the UUID of the user.
     * @return The UUID of the user.
     * */
    UUID getUUID();

    /**
     * It returns the name of the user.
     * @return The name of the user.
     * */
    String getName();

    /**
     * It returns the homes of the user.
     * @return The homes of the user.
     * */
    HashMap<String, Location> getHomes();

    /**
     * It adds a home to the user.
     * @param name The name of the home.
     * @param location The location of the home.
     * */
    void addHome(String name, Location location);

    /**
     * It removes a home from the user.
     * @param name The name of the home.
     * */
    void removeHome(String name);

    /**
     * It returns the location of a home.
     * @param name The name of the home.
     * @return The location of the home.
     * */
    Location getHome(String name);

    /**
     * It returns if the user has a home.
     * @param name The name of the home.
     * @return If the user has a home.
     * */
    boolean hasHome(String name);

}

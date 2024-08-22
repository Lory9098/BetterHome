package me.nettychannell.bhbukkit.backend.profiler;

import it.ytnoos.loadit.api.UserData;
import lombok.Getter;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class HomeProfiler extends UserData {

    private final UUID uuid;
    private final HashMap<String, Location> homes;


    public HomeProfiler(UUID uuid, String name, HashMap<String, Location> homes) {
        super(uuid, name);
        this.uuid = uuid;
        this.homes = homes;
    }

    public HomeProfiler(UUID uuid, String name) {
        this(uuid, name, new HashMap<>());
    }


    public void addHome(String name, Location location) {
        homes.put(name, location);
    }

    public void removeHome(String name) {
        homes.remove(name);
    }

    public Location getHome(String name) {
        return homes.get(name);
    }

    public boolean hasHome(String name) {
        return homes.containsKey(name);
    }

}

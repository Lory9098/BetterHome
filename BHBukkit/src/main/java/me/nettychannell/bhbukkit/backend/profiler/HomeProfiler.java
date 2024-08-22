package me.nettychannell.bhbukkit.backend.profiler;

import it.ytnoos.loadit.api.UserData;
import lombok.Getter;
import me.nettychannell.bhapi.user.BHUser;
import me.nettychannell.bhbukkit.BHBukkit;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class HomeProfiler extends UserData implements BHUser {

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


    @Override
    public void addHome(String name, Location location) {
        homes.put(name, location);

        BHBukkit.getInstance().getBHDatabase().setHomeProfilerAsync(this);
    }

    @Override
    public void removeHome(String name) {
        homes.remove(name);

        BHBukkit.getInstance().getBHDatabase().setHomeProfilerAsync(this);
    }

    @Override
    public Location getHome(String name) {
        return homes.get(name);
    }

    @Override
    public boolean hasHome(String name) {
        return homes.containsKey(name);
    }

}

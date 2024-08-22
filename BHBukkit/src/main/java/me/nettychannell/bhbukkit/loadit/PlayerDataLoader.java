package me.nettychannell.bhbukkit.loadit;

import it.ytnoos.loadit.api.DataLoader;
import lombok.RequiredArgsConstructor;
import me.nettychannell.bhbukkit.backend.Database;
import me.nettychannell.bhbukkit.backend.profiler.HomeProfiler;
import org.bukkit.Bukkit;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class PlayerDataLoader implements DataLoader<HomeProfiler> {
    private final Database database;

    @Override
    public Optional<HomeProfiler> getOrCreate(UUID uuid, String s) {
        HomeProfiler player = database.getHomeProfiler(uuid);

        if (player == null) {
            player = new HomeProfiler(uuid, s);
            database.setHomeProfiler(player);
        }

        return Optional.of(player);
    }

    @Override
    public Optional<HomeProfiler> load(UUID uuid) {
        return Optional.ofNullable(database.getHomeProfiler(uuid));
    }

    @Override
    public Optional<HomeProfiler> load(String name) {
        return load(Bukkit.getOfflinePlayer(name).getUniqueId());
    }
}

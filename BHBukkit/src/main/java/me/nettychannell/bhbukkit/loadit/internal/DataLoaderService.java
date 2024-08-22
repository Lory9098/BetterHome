package me.nettychannell.bhbukkit.loadit.internal;

import lombok.RequiredArgsConstructor;
import me.nettychannell.bhapi.loader.BHDataLoader;
import me.nettychannell.bhapi.user.BHUser;
import me.nettychannell.bhbukkit.BHBukkit;
import me.nettychannell.bhbukkit.backend.profiler.HomeProfiler;
import org.bukkit.entity.Player;

import java.util.UUID;

@RequiredArgsConstructor
public class DataLoaderService implements BHDataLoader<BHUser> {
    private final BHBukkit instance;

    @Override
    public HomeProfiler getCached(Player player) {
        return instance.getLoadit().getContainer().getCached(player);
    }

    @Override
    public BHUser getCached(UUID uuid) {
        return instance.getLoadit().getContainer().getCached(uuid).orElse(null);
    }
}

package me.nettychannell.bhapi.loader;

import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public interface BHDataLoader<T> {

    T getCached(Player player);

    T getCached(UUID uuid);

}

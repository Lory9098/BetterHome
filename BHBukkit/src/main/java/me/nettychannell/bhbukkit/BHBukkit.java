package me.nettychannell.bhbukkit;

import co.aikar.commands.PaperCommandManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import it.ytnoos.loadit.Loadit;
import lombok.Getter;
import me.nettychannell.bhbukkit.backend.Database;
import me.nettychannell.bhbukkit.backend.profiler.HomeProfiler;
import me.nettychannell.bhbukkit.commands.HomeCMD;
import me.nettychannell.bhbukkit.loadit.PlayerDataLoader;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public final class BHBukkit extends JavaPlugin {

    @Getter
    private static BHBukkit instance;

    private Database BHDatabase;
    private Loadit<HomeProfiler> loadit;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        BHDatabase = new Database(
                getConfig().getString("database.host"),
                getConfig().getInt("database.port"),
                getConfig().getString("database.database"),
                getConfig().getString("database.username"),
                getConfig().getString("database.password")
        );

        loadit = Loadit.createInstance(this, new PlayerDataLoader(BHDatabase));
        loadit.init();

        PaperCommandManager manager = new PaperCommandManager(this);
        manager.registerCommand(new HomeCMD(this));

        manager.getCommandCompletions().registerAsyncCompletion("args", context -> Sets.newHashSet("set", "teleport", "remove"));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

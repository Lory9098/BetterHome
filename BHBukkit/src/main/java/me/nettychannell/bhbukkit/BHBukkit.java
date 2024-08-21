package me.nettychannell.bhbukkit;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import me.nettychannell.bhbukkit.backend.Database;
import me.nettychannell.bhbukkit.commands.HomeCMD;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class BHBukkit extends JavaPlugin {

    @Getter
    private static BHBukkit instance;

    private Database BHDatabase;

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

        PaperCommandManager manager = new PaperCommandManager(this);
        manager.registerCommand(new HomeCMD());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

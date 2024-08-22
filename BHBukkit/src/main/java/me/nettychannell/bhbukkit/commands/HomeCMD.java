package me.nettychannell.bhbukkit.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import lombok.RequiredArgsConstructor;
import me.nettychannell.bhbukkit.BHBukkit;
import me.nettychannell.bhbukkit.backend.profiler.HomeProfiler;
import me.nettychannell.bhbukkit.messages.Message;
import me.nettychannell.bhbukkit.utils.ChatUtil;
import org.bukkit.entity.Player;

@CommandAlias("home|h")
@RequiredArgsConstructor
public class HomeCMD extends BaseCommand {
    private final BHBukkit instance;

    @Default
    @HelpCommand
    public void onDefault(Player player) {
        Message.HOME_HELP.forEach(player::sendMessage);
    }

    @Subcommand("set")
    public void onSet(Player player, String name) {
        HomeProfiler homeProfiler = instance.getLoadit().getContainer().getCached(player);

        if (homeProfiler.hasHome(name)) {
            player.sendMessage(Message.HOME_ALREADY_EXISTS);
            return;
        }

        homeProfiler.addHome(name, player.getLocation());
        player.sendMessage(Message.HOME_SET.replace("%home%", name));
    }

    @Subcommand("remove")
    public void onRemove(Player player, String name) {
        HomeProfiler homeProfiler = instance.getLoadit().getContainer().getCached(player);

        if (!homeProfiler.hasHome(name)) {
            player.sendMessage(Message.HOME_DOES_NOT_EXIST);
            return;
        }

        homeProfiler.removeHome(name);
        player.sendMessage(Message.HOME_REMOVED.replace("%home%", name));
    }

    @Subcommand("teleport")
    public void onTeleport(Player player, String name) {
        HomeProfiler homeProfiler = instance.getLoadit().getContainer().getCached(player);

        if (!homeProfiler.hasHome(name)) {
            player.sendMessage(Message.HOME_DOES_NOT_EXIST);
            return;
        }

        player.teleport(homeProfiler.getHome(name));
        player.sendMessage(Message.HOME_TELEPORTED.replace("%home%", name));
    }

}

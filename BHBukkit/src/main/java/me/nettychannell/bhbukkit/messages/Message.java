package me.nettychannell.bhbukkit.messages;

import lombok.experimental.UtilityClass;
import me.nettychannell.bhbukkit.BHBukkit;
import me.nettychannell.bhbukkit.utils.ChatUtil;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

@UtilityClass
public class Message {

    public final List<String> HOME_HELP;
    public final String HOME_ALREADY_EXISTS, HOME_SET, HOME_DOES_NOT_EXIST, HOME_REMOVED, HOME_TELEPORTED;

    static {
        ConfigurationSection section = BHBukkit.getInstance().getConfig().getConfigurationSection("messages");

        HOME_HELP = ChatUtil.color(section.getStringList("home-help"));
        HOME_ALREADY_EXISTS = ChatUtil.color(section.getString("home-already-exists"));
        HOME_SET = ChatUtil.color(section.getString("home-set"));
        HOME_DOES_NOT_EXIST = ChatUtil.color(section.getString("home-does-not-exist"));
        HOME_REMOVED = ChatUtil.color(section.getString("home-removed"));
        HOME_TELEPORTED = ChatUtil.color(section.getString("home-teleported"));
    }

}

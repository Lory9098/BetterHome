package me.nettychannell.bhbukkit.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

import java.util.List;

@UtilityClass
public class ChatUtil {

    public String color(String old) {
        return ChatColor.translateAlternateColorCodes('&', old);
    }

    public static List<String> color(List<String> old) {
        old.replaceAll(ChatUtil::color);

        return old;
    }
}

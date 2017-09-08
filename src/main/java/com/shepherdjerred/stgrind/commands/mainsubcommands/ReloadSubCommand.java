package com.shepherdjerred.stgrind.commands.mainsubcommands;

import com.shepherdjerred.stgrind.Main;
import com.shepherdjerred.stgrind.files.ConfigHelper;
import com.shepherdjerred.stgrind.messages.commands.GenericMessages;
import com.shepherdjerred.stgrind.messages.commands.MainCommandMessages;
import org.bukkit.command.CommandSender;

public class ReloadSubCommand {

    public static void Executor(CommandSender sender, String[] args) {

        if (!sender.hasPermission("stGrind.reload")) {
            sender.sendMessage(GenericMessages.getNoPermsMessage());
            return;
        }

        Main.getInstance().reloadConfig();
        ConfigHelper.loadConfigs();

        sender.sendMessage(MainCommandMessages.getReloadMessage());

    }

}

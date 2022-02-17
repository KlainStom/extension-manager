package com.github.klainstom.extensionmanager.commands.manager;

import com.github.klainstom.extensionmanager.permissions.Permissions;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.ConsoleSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.extensions.Extension;

public class ManagerListCommand extends Command {
    public static final ManagerListCommand INSTANCE = new ManagerListCommand();

    private ManagerListCommand() {
        super("list");
        addConditionalSyntax(
                (sender, commandString) -> sender.hasPermission(Permissions.LIST) || (sender instanceof ConsoleSender),
                (sender, context) -> {
                    sender.sendMessage(Component.text("==== Extensions list ====", NamedTextColor.YELLOW));
                    for (Extension ext : MinecraftServer.getExtensionManager().getExtensions()) {
                        sender.sendMessage(Component.text(ext.getOrigin().getName(), NamedTextColor.GREEN)
                                .append(Component.text(": ", NamedTextColor.GOLD))
                                .append(Component.text(ext.getOrigin().getVersion(), NamedTextColor.AQUA))
                                .clickEvent(ClickEvent.runCommand("extensionmanager show "+ext.getOrigin().getName()))
                        );
                    }
                });
    }
}

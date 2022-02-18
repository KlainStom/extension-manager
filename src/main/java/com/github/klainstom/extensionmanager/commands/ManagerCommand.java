package com.github.klainstom.extensionmanager.commands;

import com.github.klainstom.extensionmanager.commands.manager.ManagerListCommand;
import com.github.klainstom.extensionmanager.commands.manager.ManagerReleasesCommand;
import net.kyori.adventure.text.Component;
import net.minestom.server.command.builder.Command;

public class ManagerCommand extends Command {
    protected ManagerCommand() {
        super("extensionmanager", "extmanager");
        setDefaultExecutor(((sender, context) -> sender.sendMessage(Component.text("This alone won't do much."))));
        addSubcommand(ManagerListCommand.INSTANCE);
        addSubcommand(ManagerReleasesCommand.INSTANCE);
        // TODO: 16.02.22 check for github repo releases
        // TODO: 16.02.22 add github release downloads for updating
    }
}

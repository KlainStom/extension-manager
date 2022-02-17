package com.github.klainstom.extensionmanager;

import com.github.klainstom.extensionmanager.commands.Commands;
import net.minestom.server.MinecraftServer;
import net.minestom.server.extensions.Extension;

public class ExtensionMain extends Extension {
    @Override
    public void initialize() {
        var commandManager = MinecraftServer.getCommandManager();
        commandManager.register(Commands.MANAGER_COMMAND);
    }

    @Override
    public void terminate() {

    }
}

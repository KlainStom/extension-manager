package com.github.klainstom.extensionmanager;

import com.github.klainstom.extensionmanager.commands.Commands;
import net.minestom.server.MinecraftServer;
import net.minestom.server.extensions.Extension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ExtensionMain extends Extension {
    public static Path DATA_DIRECTORY;

    @Override
    public void preInitialize() {
        DATA_DIRECTORY = Objects.requireNonNull(MinecraftServer.getExtensionManager()
                .getExtension("&Name"), "Extension installed but not found!").getDataDirectory();

        if (!DATA_DIRECTORY.toFile().exists()) {
            try {
                Files.createDirectory(DATA_DIRECTORY);
            } catch (IOException e) {
                MinecraftServer.LOGGER.error("Could not create &Name data directory", e);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize() {
        var commandManager = MinecraftServer.getCommandManager();
        commandManager.register(Commands.MANAGER_COMMAND);
    }

    @Override
    public void terminate() {

    }
}

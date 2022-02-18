package com.github.klainstom.extensionmanager.commands.manager;

import com.github.klainstom.extensionmanager.githubapi.GitHubApi;
import com.github.klainstom.extensionmanager.githubapi.jsonobjects.Release;
import com.github.klainstom.extensionmanager.githubapi.jsonobjects.ReleaseAsset;
import com.github.klainstom.extensionmanager.permissions.Permissions;
import com.google.gson.JsonObject;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.ConsoleSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.extensions.Extension;

public class ManagerReleasesCommand extends Command {
    public static final ManagerReleasesCommand INSTANCE = new ManagerReleasesCommand();

    private ManagerReleasesCommand() {
        super("releases");
        setCondition((sender, commandString) -> sender.hasPermission(Permissions.RELEASES) || (sender instanceof ConsoleSender));
        addSyntax((sender, context) -> {
            sender.sendMessage(Component.text("==== Extensions releases ====", NamedTextColor.YELLOW));
            for (Extension ext : MinecraftServer.getExtensionManager().getExtensions()) {
                sender.sendMessage(Component.text(ext.getOrigin().getName(), NamedTextColor.GREEN));
                JsonObject meta = ext.getOrigin().getMeta();
                if (!meta.has("github")) {
                    sender.sendMessage("No GitHub object");
                    continue;
                }
                JsonObject github = meta.getAsJsonObject("github");
                if (!github.has("release")) {
                    sender.sendMessage("No GitHub release object");
                    continue;
                } // TODO: 18.02.22 maybe show git commit as fallback
                JsonObject release = github.getAsJsonObject("release");

                String account = release.getAsJsonPrimitive("account").getAsString();
                String repo = release.getAsJsonPrimitive("repo").getAsString();
                String releaseTag = release.getAsJsonPrimitive("release").getAsString();
                // TODO: 18.02.22 show releases as up and downgrades based on releaseTag (semVer)

                for (Release r : GitHubApi.getReleases(account, repo)) {
                    sender.sendMessage(Component.text("===> %s: %s".formatted( r.getName(), r.getTagName())));
                    for (ReleaseAsset asset : r.getAssets()) {
                        sender.sendMessage(Component.text("-> %s: %s".formatted(asset.getName(), asset.getDownloadUrl())));
                    }
                }
            }
        });
    }
}

package com.github.klainstom.extensionmanager.githubapi;

import com.github.klainstom.extensionmanager.githubapi.jsonobjects.Release;
import com.google.gson.*;
import net.minestom.server.MinecraftServer;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GitHubApi {
    private static final Gson gson = new GsonBuilder().create();

    private static final String RELEASES_URL_TEMPLATE = "https://api.github.com/repos/{owner}/{repo}/releases";
    private static final String RATE_LIMIT_URL = "https://api.github.com/rate_limit";

    public static List<Release> getReleases(String owner, String repo) {
        try {
            URL releasesUrl = new URL(RELEASES_URL_TEMPLATE.replace("{owner}", owner).replace("{repo}", repo));
            JsonArray releasesArray = JsonParser.parseReader(new InputStreamReader(releasesUrl.openStream())).getAsJsonArray();
            List<Release> releaseList = new ArrayList<>();
            releasesArray.forEach(releaseJson -> releaseList.add(gson.fromJson(releaseJson, Release.class)));
            return List.copyOf(releaseList);
        } catch (MalformedURLException e) {
            MinecraftServer.LOGGER.error("RELEASES_URL_TEMPLATE is not right", e);
            e.printStackTrace();
        } catch (IOException e) {
            MinecraftServer.LOGGER.error("IOException on openStream", e);
            e.printStackTrace();
        }
        return List.of();
    }
}

package com.github.klainstom.extensionmanager.githubapi.jsonobjects;

public class ReleaseAsset {
    private String name;
    private String browser_download_url;

    public String getName() { return name; }
    public String getDownloadUrl() { return browser_download_url; }
}

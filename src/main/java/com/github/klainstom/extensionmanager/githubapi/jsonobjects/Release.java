package com.github.klainstom.extensionmanager.githubapi.jsonobjects;

import java.util.List;

public class Release {
    private String tag_name;
    private String name;
    private List<ReleaseAsset> assets;

    public String getTagName() {
        return tag_name;
    }

    public String getName() {
        return name;
    }

    public List<ReleaseAsset> getAssets() {
        return assets;
    }
}

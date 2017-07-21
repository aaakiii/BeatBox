package com.derrick.park.beatbox;

/**
 * Created by park on 2017-07-17.
 */

public class Sound {

    private String mAssetPath;
    private String mName;
    //soundがnullの場合を考慮してintではなくIntegerを使用
    private Integer mSoundId;

    public Sound(String assetPath) {
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        String filename = components[components.length - 1];
        mName = filename.replace(".wav", "");
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        this.mSoundId = soundId;
    }
}

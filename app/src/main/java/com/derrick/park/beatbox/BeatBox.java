package com.derrick.park.beatbox;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by park on 2017-07-17.
 */

public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SAMPLE_SOUNDS = "sample_sounds";
    private AssetManager mAssets;
    private ArrayList<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;
    private static final int MAX_SOUNDS = 5;//How many sounds play st once.


    public BeatBox(Context context) {
        mAssets = context.getAssets();
        //API19以上ならSoundPoolBuilderを使う
        //use "0" for default
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }

    public void play(Sound sound){
        Integer soundId = sound.getSoundId();
        if(sound == null){
            //this might happen if Sound failed to load.
            return;
        }
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void release(){
        mSoundPool.release();

    }

    private void loadSounds() {
        String[] soundsNames;
        try {
            soundsNames = mAssets.list(SAMPLE_SOUNDS);
            Log.i(TAG, "loadSounds: Found " + soundsNames.length + " sounds.");
        } catch (IOException ioe) {
            Log.e(TAG, "loadSounds: Could not list assets", ioe);
            return;
        }

        for(String filename: soundsNames) {
            try{
                String assetPath = SAMPLE_SOUNDS + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);

            }catch (IOException e){
                e.printStackTrace();
                Log.e(TAG, "loadSound: Could not load sound" + filename, e);
            }

        }
    }
    //To load sounds. loading some file and returns to Integer(Id). for keeping truck of sounds
    //IOException: Input and Output exception
    private void load(Sound sound) throws IOException{
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd, 1);
        sound.setSoundId(soundId);

    }

    public List<Sound> getSounds() {
        return mSounds;
    }

}

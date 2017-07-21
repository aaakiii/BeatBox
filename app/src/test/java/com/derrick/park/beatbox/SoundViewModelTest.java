package com.derrick.park.beatbox;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by AKI on 2017-07-21.
 */
public class SoundViewModelTest {
    private BeatBox mBeatBox;
    private Sound mSound;
    private SoundViewModel mSubject;

    @Before
    public void setUp() throws Exception {
        mBeatBox = mock(BeatBox.class);
        // Sound is a simple data object with no behavior to break.
        //so it's safe not to mock it.
        mSound = new Sound("assetPath");
        mSubject = new SoundViewModel(mBeatBox);
        mSubject.setSound(mSound);
    }

    @Test
    public void testSoundNameAsTitle(){
        assertThat(mSubject.getTitle(), is(mSound.getName()));
    }
    // integration between SoundViewModel and our new BeatBox.play(Sound) method

    @Test
    public void callsBeatBoxPlayOnButtonClicked(){
        mSubject.onButtonClicked();
        //Mockito has verify(object) method to check and see
        // whether a method was called the way you expected them to be called
        verify(mBeatBox).play(mSound);
        // verify that the play() method was called on mBeatBox with Sound

    }



}
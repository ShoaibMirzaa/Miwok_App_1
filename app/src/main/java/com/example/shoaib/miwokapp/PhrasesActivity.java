package com.example.shoaib.miwokapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onStop(){
        super.onStop();
        releaseMediaPlayer();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

      final ArrayList<Word> word = new ArrayList<Word>();

        word.add(new Word("Where are you going?", "Minto Wuksus",R.raw.phrase_where_are_you_going ));
        word.add(new Word("What is your name?", "tinnә oyaase'nә",R.raw.phrase_what_is_your_name  ));
        word.add(new Word("My name is...", "oyaaset...",R.raw.phrase_my_name_is ));
        word.add(new Word("How are you feeling?", "michәksәs?",R.raw.phrase_how_are_you_feeling  ));
        word.add(new Word("I’m feeling good.", "kuchi achit",R.raw.phrase_im_feeling_good ));
        word.add(new Word("Are you coming?", "әәnәs'aa?",R.raw.phrase_where_are_you_going  ));
        word.add(new Word("Yes, I’m coming.", "hәә’ әәnәm",R.raw.phrase_yes_im_coming  ));
        word.add(new Word("I’m coming.", "әәnәm",R.raw.phrase_im_coming  ));
        word.add(new Word("Let’s go.", "yoowutis",R.raw.phrase_lets_go ));
        word.add(new Word("Come here.", "әnni'nem",R.raw.phrase_come_here ));



        WordAdapter Adapter = new WordAdapter(this,word,R.color.category_phrases);

        ListView activityText = findViewById(R.id.commanIntent);

        activityText.setAdapter(Adapter);

        activityText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word words = word.get(position);
                releaseMediaPlayer();

                mediaPlayer = MediaPlayer.create(PhrasesActivity.this,words.getmAudioResource());

                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });


    }

    private void releaseMediaPlayer(){
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }

}


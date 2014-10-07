package com.example.lyapisradio;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener {
	
	private Button playButton;
	private MediaPlayer player;
    private ImageButton vkButton;
    private ImageButton youButton;
    private ImageButton twButton;
    private ImageButton fbButton;

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUIElements();

        initializeMediaPlayer();
    }

    private void initializeUIElements() {

       
    	playButton = (Button) findViewById(R.id.button_play);
    	playButton.setOnClickListener(this);

    	vkButton = (ImageButton) findViewById(R.id.imageVk);
    	vkButton.setOnClickListener(this);
        youButton = (ImageButton) findViewById(R.id.imageYoutube);
        youButton.setOnClickListener(this);
        twButton = (ImageButton) findViewById(R.id.imageTwitter);
        twButton.setOnClickListener(this);
        fbButton = (ImageButton) findViewById(R.id.imageFacebook);
        fbButton.setOnClickListener(this);

    }
    
    
    
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.button_play:
        	if ((v == playButton)&&!(player.isPlaying())) {
                startPlaying();
               
            } else if (player.isPlaying()){
                stopPlaying();
              
            }
          break;
        case R.id.imageTwitter:
        	buttonCheck("twitter://user?screen_name=lyapis", 
        			"https://twitter.com/#!/lyapis");
              
          break;
        case R.id.imageFacebook:
        	
        	buttonCheck("fb://profile/lyapis", 
        			"https://www.facebook.com/lyapis");
          break;
        case R.id.imageYoutube:
        	buttonCheck("vnd.youtube://user/lyapisvideo", 
        			"https://www.youtube.com/user/lyapisvideo");
        	stopPlaying();
          break;
        case R.id.imageVk:
        	buttonCheck("https://vk.com/lyapistrubetskoy", 
        			"https://vk.com/lyapistrubetskoy");
          break;
        }
      }
    

   
    private void startPlaying() {

        player.prepareAsync();

        player.setOnPreparedListener(new OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {
                player.start();
            }
        });
        playButton.setText(R.string.stop);

    }

    private void stopPlaying() {
        if (player.isPlaying()) {
            player.stop();
            player.release();
            initializeMediaPlayer();
        }

        playButton.setText(R.string.play);
        
     }

    private void initializeMediaPlayer() {
        player = new MediaPlayer();
        try {
            player.setDataSource("http://online.lyapis.com/RadioLyapisCrew");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onPause();
        if (player.isPlaying()) {
           stopPlaying();
        }
    }
    
    private void buttonCheck(String app, String http){
    	 try {
            	startActivity(new Intent(
            			Intent.ACTION_VIEW,
            			Uri.parse(app)));
            } catch (Exception e) {
            	startActivity(new Intent(Intent.ACTION_VIEW,
            			Uri.parse(http)));
            }
    }
     
    
}

package com.example.repaudiobasico;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaController.MediaPlayerControl {
    MediaPlayer mediaPlayer;
    MediaController mc;
    Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.duality);
        mc = new MediaController(this);
        mc.setMediaPlayer(this);
        mc.setAnchorView(findViewById(R.id.imageViewCaratula));
        h = new Handler();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        mc.show(0);
                    }
                });
            }
        });

   /*     mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                TextView estado = findViewById(R.id.textViewEstado);
                mediaPlayer.release();
                estado.setText("Released!");
            }
        });*/
    }

    public void Show(View v){
        mc.show();
    }

    public void Hide(View v){
        mc.hide();
    }
/*
    public void play(View view) {
        TextView estado = findViewById(R.id.textViewEstado);
        if (mediaPlayer.isPlaying()){
            estado.setText("Está sonando Duality, de Slipknot.");
        } else {
            estado.setText("El MP está parado, lo ponemos a reproducir.");
            mediaPlayer.start();
        }
    }

    public void pause(View view) {
        TextView estado = findViewById(R.id.textViewEstado);
        if (mediaPlayer.isPlaying()){
            estado.setText("Ponemos pausa");
            mediaPlayer.pause();
        } else {
            estado.setText("El reproductor está ya pausado.");
        }
    }

    public void stop(View view) throws IOException {
        TextView estado = findViewById(R.id.textViewEstado);
        if (mediaPlayer!=null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            try{
                mediaPlayer.prepare();
                estado.setText("Hacemos Stop a la música y un prepare().");

            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            estado.setText("La música ya estaba parada.");
        }
    }*/

    @Override
    public void start() {
    if (!mediaPlayer.isPlaying()){
        mediaPlayer.start();
    }
    }

    @Override
    public void pause() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    @Override
    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        mediaPlayer.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return mediaPlayer.getAudioSessionId();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN)
            if(!mc.isShowing())
                mc.show(0);
            else
                mc.hide();
        return false;
    }

}
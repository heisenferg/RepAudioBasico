package com.example.repaudiobasico;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageView caratula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.duality);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                TextView estado = findViewById(R.id.textViewEstado);
                mediaPlayer.release();
                estado.setText("Released!");
            }
        });
    }

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
    }
}
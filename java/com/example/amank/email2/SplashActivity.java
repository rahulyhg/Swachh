package com.example.amank.email2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.widget.ImageView;

public class SplashActivity extends Activity {

    TextToSpeech tts;
    private ImageView imageViewRound;
    private static int Splash_Time=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageViewRound=(ImageView)findViewById(R.id.imageView_round);
        Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.satyamevjayate);
        imageViewRound.setImageBitmap(icon);

//        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
//
//            @Override
//            public void onInit(int status) {
//                if(status!= TextToSpeech.ERROR){
//                    tts.setLanguage(Locale.UK);
//                }
//            }
//        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               // tts.speak("swachh", TextToSpeech.QUEUE_FLUSH, null);
                Intent i = new Intent(SplashActivity.this,LoginOption.class);
                startActivity(i);
                finish();
            }
        }, Splash_Time);
    }
}

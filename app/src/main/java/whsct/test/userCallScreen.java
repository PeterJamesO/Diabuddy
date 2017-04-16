package whsct.test;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;

public class userCallScreen extends AppCompatActivity {
    public void boolean loudspeaker = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_call_screen);

        // Start timer on screen open
        Chronometer simpleChronometer = (Chronometer) findViewById(R.id.callChronometer);

        simpleChronometer.start();


    }

    public void loudspeakerButton(View view) {
        //Loudspeaker
        if (loudspeaker = true) {
            AudioManager audioManager = (AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
            audioManager.setMode(AudioManager.MODE_IN_CALL);
            audioManager.setSpeakerphoneOn(true);
        }
        else (loudspeaker = false) {
            AudioManager audioManager = (AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
            audioManager.setSpeakerphoneOn(false);
        }
    }

    public void endCallButton(View view) {
        // Change to rating screen
        Intent intent = new Intent(this, userRating.class);

        //Reset & stop timer on screen close
        Chronometer simpleChronometer = (Chronometer) findViewById(R.id.callChronometer);
        simpleChronometer.setBase(SystemClock.elapsedRealtime());
        simpleChronometer.stop();
        startActivity(intent);

    }
}



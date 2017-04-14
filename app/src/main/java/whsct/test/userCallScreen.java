package whsct.test;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;

public class userCallScreen extends AppCompatActivity {
    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_call_screen);

        // Start timer on screen open
        Chronometer simpleChronometer = (Chronometer) findViewById(R.id.callChronometer);

        simpleChronometer.start();

        // Loudspeaker Button
        button= (ImageButton)findViewById(R.id.loudspeakerButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setBackgroundResource(R.drawable.speakeroff);
            }
        });
    }

    public void endCallButton(View view) {
        // Change to rating screen
        Intent intent = new Intent(this, userRating.class);

        //Reset & stop timer on screen close
        Chronometer simpleChronometer = (Chronometer) findViewById(R.id.callChronometer);
        simpleChronometer.setBase(SystemClock.elapsedRealtime());
        simpleChronometer.stop();

        // Loudspeaker Button
        startActivity(intent);
    }

}

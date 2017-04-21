package whsct.test;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainMenu extends AppCompatActivity {
    public static final String NAME = "com.whsct.MESSAGE";
    Context context = this;
    Intent intentUR;
    Intent intentCall;
    boolean phoneRinging = false;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Pull name from login
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.NAME);
        TextView name = (TextView) findViewById(R.id.welcomeText);
        name.setText(message);


        ImageView image = (ImageView) findViewById(R.id.imageView3);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(context, R.raw.babygiveitup);
                mp.start();
            }
        });
    }

    public void callButton(View view) {
        System.out.println("start");
        registerListener(context);
        intentCall = new Intent(Intent.ACTION_CALL);
        System.out.println("middle");
        intentCall.setData(Uri.parse("tel:07422661220"));
        if (ContextCompat.checkSelfPermission(MainMenu.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainMenu.this, new String[]{Manifest.permission.CALL_PHONE},101);
        }
        else
        {
            startActivity(intentCall);
            phoneRinging = true;

        }
        System.out.println("end");
    }

    public void notesButton(View view) {
        // Go to emergency screen
        Intent intent = new Intent(this, newNote.class);
        startActivity(intent);
    }

    public void emergencyButton(View view) {
        // Go to emergency screen
        Intent intent = new Intent(this, emergencyScreen.class);
        startActivity(intent);
    }

    public void settingButton(View view) {
        // Go to setting screen
        Intent intent = new Intent(this, settingMenu.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void registerListener(Context context) {
        ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).listen(new MainMenu.MyPhoneStateListener(),
                PhoneStateListener.LISTEN_CALL_STATE);
    }


    public class MyPhoneStateListener extends PhoneStateListener {


        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            try {
              Intent intentUR = Intent.parseUri(incomingNumber, Intent.URI_INTENT_SCHEME);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            switch (state) {
                //Outgoing
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    phoneRinging = false;
                    break;
                //Hangup
                case TelephonyManager.CALL_STATE_IDLE:
                    System.out.println("yay");
                    phoneRinging = false;
                    intentUR.setClass(context, userRating.class);
                    startActivity(intentUR);
                    break;
                //Incoming
                case TelephonyManager.CALL_STATE_RINGING:
                    phoneRinging = true;
                    break;

            }
        }
    }
}

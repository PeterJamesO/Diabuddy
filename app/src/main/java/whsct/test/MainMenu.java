package whsct.test;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.URISyntaxException;

public class MainMenu extends AppCompatActivity {
    public static final String NAME = "com.whsct.MESSAGE";
    Context context = this;

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
    }

    public void callButton(View view) {
        Log.d("DEBUGGING:", "start");

        Intent intent = new Intent(Intent.ACTION_CALL);
        Log.d("DEBUGGING:", "middle");
        intent.setData(Uri.parse("tel:07422661220"));
        if (ContextCompat.checkSelfPermission(MainMenu.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainMenu.this, new String[]{Manifest.permission.CALL_PHONE},101);
        }
        else
        {
            startActivity(intent);
        }
        Log.d("DEBUGGING:", "end");
        registerListener(context);
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
        Intent intent;

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            try {
                intent = Intent.parseUri(incomingNumber, Intent.URI_INTENT_SCHEME);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            switch (state) {
                //Hangup
                case TelephonyManager.CALL_STATE_IDLE:
                    Log.d("Apple fingers", "yay");
                    intent = new Intent(context, userRating.class);
                    startActivity(intent);
                    break;
                //Outgoing
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    break;
                //Incoming
                case TelephonyManager.CALL_STATE_RINGING:
                    break;
            }
        }
    }


}

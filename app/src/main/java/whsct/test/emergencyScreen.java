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
import android.widget.EditText;
import android.widget.TextView;

import java.net.URISyntaxException;
import java.util.Locale;

public class emergencyScreen extends AppCompatActivity {
    public static final String DOCNUM = "com.whsct.NUMBER";
    Doctor doc1;
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void returnButton(View view) {
        // Go to main menu
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    public void emergencyButton(View view) {
        // Calls doctor with listed number
        Log.d("DEBUGGING:", "start");

        Intent intent = new Intent(Intent.ACTION_CALL);
        Log.d("DEBUGGING:", "middle");
        intent.setData(Uri.parse("tel:07422661220"));
        if (ContextCompat.checkSelfPermission(emergencyScreen.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(emergencyScreen.this, new String[]{Manifest.permission.CALL_PHONE}, 101);
        } else {
            startActivity(intent);
        }
        Log.d("DEBUGGING:", "end");
        registerListener(context);
        Log.d("DEBUGGING:", "Boom!");
    }

    public void registerListener(Context context) {
        ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).listen(new MyPhoneStateListener(),
                PhoneStateListener.LISTEN_CALL_STATE);
    }

    public void emergencyNumber(View view) {
        //Displays doctor number;
        Intent intent = getIntent();
        String message = intent.getStringExtra(settingMenu.DOCNUM);
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



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
import java.util.List;
import java.util.Locale;

public class emergencyScreen extends AppCompatActivity {
    Context context = this;
    Button emergencyButton;
    boolean confirmed = false;

    private DataSource dataSource;
    private List<UserData> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        dataSource = new DataSource(this);
        dataSource.open();
        users = dataSource.getAllUsers();

        emergencyButton = (Button) findViewById(R.id.emergencyButton);
    }

    public void returnButton(View view) {
        // Go to main menu
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    public void emergencyButton(View view) {
        // Calls doctor with listed number
        emergencyButton.setText(users.get(0).getNumber());
        registerListener(context);
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:07422661220"));
        if (ContextCompat.checkSelfPermission(emergencyScreen.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(emergencyScreen.this, new String[]{Manifest.permission.CALL_PHONE}, 101);
        }
        // Start call on second click
        if (confirmed) startActivity(intent);
        confirmed = true;
    }

    public void registerListener(Context context) {
        ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).listen(new MyPhoneStateListener(),
                PhoneStateListener.LISTEN_CALL_STATE);
    }

    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
        emergencyButton.setText("EMERGENCY");
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
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



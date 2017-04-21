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
import java.util.List;

public class MainMenu extends AppCompatActivity {
    public static final String NAME = "com.whsct.MESSAGE";
    Context context = this;
    Intent intentCall;
    Intent intentUR;
    boolean phoneRinging = false;
    private DataSource dataSource;
    private List<UserData> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        dataSource = new DataSource(this);
        dataSource.open();
        users = dataSource.getAllUsers();

        // Pull name from database
        TextView name = (TextView) findViewById(R.id.welcomeText);
        Log.d("MainMenu", "Before active user");
        name.setText("Hello " + users.get(users.size() - 1).getUsername()); // TODO
        Log.d("MainMenu", "After active user");

        ImageView image = (ImageView) findViewById(R.id.imageView3);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(context, R.raw.babygiveitup);
                mp.start();
            }
        });
    }

    // Call button - calls number then will travel to user rating screen seen in function below.
    public void callButton(View view) {
        System.out.println("start");
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

    // Goes to notes screen
    public void notesButton(View view) {
        // Go to emergency screen
        Intent intent = new Intent(this, newNote.class);
        startActivity(intent);
    }

    // Goes to emergency screen
    public void emergencyButton(View view) {
        // Go to emergency screen
        Intent intent = new Intent(this, emergencyScreen.class);
        startActivity(intent);
    }

    // Goes to setting screen
    public void settingButton(View view) {
        // Go to setting screen
        Intent intent = new Intent(this, settingMenu.class);
        startActivity(intent);
    }

    // Check active status
    /*
        Loop through all users to find active user
        Count from 0
        return counter value once active user is found

     */
    public int activeUser() {
        int counter = 0;
        for (UserData user: users) {
            if (user.getActive() == "yes") {
                break;
            }
            else {
                ++counter;
            }
        }
        return counter;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Moves to this screen after call
    @Override
    public void onResume() {
        super.onResume();
        Intent intent = new Intent(this, userRating.class);
        if (phoneRinging) {
            startActivity(intent);
        }

    }

}

package whsct.test;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class settingMenu extends AppCompatActivity {
    //Doctor Number
    public static final String DOCNUM = "com.whsct.NUMBER";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    // Return button
    public void returnButton(View view) {
        // Go to main menu
        Intent intent = new Intent(this, MainMenu.class);


        startActivity(intent);
    }

    // Logs user out of the app
    public void logoutButton(View view) {
        // Log Out of app
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

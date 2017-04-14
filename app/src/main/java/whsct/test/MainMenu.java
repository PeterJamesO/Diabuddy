package whsct.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {
    public static final String NAME = "com.whsct.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        // Pull name from login
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.NAME);
        TextView name = (TextView) findViewById(R.id.welcomeText);
        name.setText(message);
    }

    public void callButton(View view) {
        // Go to emergency screen
        Intent intent = new Intent(this, userCallSetupScreen.class);
        startActivity(intent);
    }

    public void notesButton(View view) {
        // Go to emergency screen
        Intent intent = new Intent(this, Notes.class);
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
        startActivity(intent);    }
}

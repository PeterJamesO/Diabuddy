package whsct.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
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
}

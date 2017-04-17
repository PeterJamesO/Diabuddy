package whsct.test;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class volunteerMenu extends AppCompatActivity {
    public static final String NAME = "com.whsct.MESSAGE";
    public static final String COUNTER = "com.whsct.COUNT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Pull name from settings
        Intent intent = getIntent();
        String message = intent.getStringExtra(volunteerLogin.NAME);

        // Capture text from settings & set string as text
        TextView name = (TextView) findViewById(R.id.nameText);
        name.setText(message);

        //Pull count of button
        String ctr = intent.getStringExtra(volunteerLogin.COUNTER);
        TextView count = (TextView) findViewById(R.id.countText);
        count.setText(ctr);
    }

    public void settingButton(View view) {
        // Go to setting screen
        Intent intent = new Intent(this, volunteerSetting.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        // Log Out of app
        Intent intent = new Intent(this, volunteerLogin.class);
        startActivity(intent);
    }

}

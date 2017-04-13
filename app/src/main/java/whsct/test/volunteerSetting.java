package whsct.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class volunteerSetting extends AppCompatActivity {
    public static final String NAME = "com.whsct.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_setting);
    }

    public void returnButton(View view) {
        // Go to main menu
        Intent intent = new Intent(this, volunteerMenu.class);
        startActivity(intent);
    }


    public void logoutButton(View view) {
        // Log Out of app
        Intent intent = new Intent(this, volunteerLogin.class);
        startActivity(intent);
    }

    public void nameContent(View view) {
        // Pull name to main menu
        Intent intent = new Intent(this, volunteerMenu.class);
        EditText editText = (EditText) findViewById(R.id.passwordField);
        String message = editText.getText().toString();
        intent.putExtra(NAME, message);
        startActivity(intent);
    }
}

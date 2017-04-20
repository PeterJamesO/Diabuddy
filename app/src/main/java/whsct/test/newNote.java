package whsct.test;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class newNote extends AppCompatActivity {
    private EditText username;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_note);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        username = (EditText) findViewById(R.id.nameText);
        content = (EditText) findViewById(R.id.contentText);
        
    }

    public void returnButton(View view) {
        // Go to notes screen
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sendDoctorButton(View view) {
        // Send note to Doctor
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date = sdf.format(new Date());

        Intent email = new Intent(android.content.Intent.ACTION_SEND);
        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"peterjameso@outlook.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Note from " + username.getText().toString() + " - " + date);
        email.putExtra(android.content.Intent.EXTRA_TEXT, "Username:" + username.getText().toString() + "\nContent: " + content.getText().toString());
        startActivity(email);
    }

}

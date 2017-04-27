package whsct.test;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;

import java.text.SimpleDateFormat;
import java.util.Date;



public class userRating extends AppCompatActivity {
    Context context = this;
    private EditText username;
    private Switch switchB;
    private RatingBar ratingB;

    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    String date = sdf.format(new Date());



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_rating);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        username = (EditText) findViewById(R.id.usernameRating);
        switchB = (Switch) findViewById(R.id.switchRating);
        ratingB = (RatingBar) findViewById(R.id.starRating);
    }

    @Override // Back button reverts to main menu
    public void onBackPressed(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    // Submit button
    public void submitButton(View view){
        //sendEmail();
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    // Switch Position Boolean
    public String onCheckedChanged() {
        if (switchB.isChecked()) {
            return "yes";
        } else {
            //do something when unchecked
            return "no";
        }
    }

    // Sends feedback email to developer with feedback from user.
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sendEmailButton(View view) {
        // Send email feedback to developer
        Log.d("DEBUGGER", "Go");

        Intent email = new Intent(android.content.Intent.ACTION_SEND);
        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"peterjameso@outlook.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Feedback " + username.getText().toString() + " - " + date);
        email.putExtra(android.content.Intent.EXTRA_TEXT, "Username: " + username.getText().toString() +
                "\nThe volunteer provided sufficient help: " + onCheckedChanged() + "\nRating: " + ratingB.getRating());
        startActivity(email);
        Log.d("DEBUGGER", "We did it");
    }

    // On restart, app will move to another screen.
    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}

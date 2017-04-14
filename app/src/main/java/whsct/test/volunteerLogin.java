package whsct.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class volunteerLogin extends AppCompatActivity {
    public static final String NAME = "com.whsct.MESSAGE";
    public static final String COUNTER = "com.whsct.COUNT";
    public static int ctr = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_login);
    }

    public void loginButton(View view) {
        // Go to main menu
        Intent intent = new Intent(this, volunteerMenu.class);

        //Add username to main menu
        EditText username = (EditText) findViewById(R.id.usernameField);
        EditText password = (EditText) findViewById(R.id.passwordField);
        String s_username = username.getText().toString();
        String s_password = password.getText().toString();

        //Verify before continuing
        if (verify(s_username, s_password)) {
            intent.putExtra(NAME, "Hello" + s_username);

            //Add button count to main menu
            ctr++;
            String s_ctr = Integer.toString(ctr);
            intent.putExtra(COUNTER, s_ctr);
            startActivity(intent);
        }

        else {
            TextView textView = (TextView) findViewById(R.id.passwordIncorrect);
            textView.setText("Error: Incorrect username or password!");
        }

    }


    public void changeDiabeticButton(View view) {
        // Change to volunteer login
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        // Disables back button so app can not be entered via backdoor.
    }

    //Verfication method
    private boolean verify(String username, String password) {
        return true;
    }

}



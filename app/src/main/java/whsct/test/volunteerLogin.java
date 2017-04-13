package whsct.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

        //Add username to main menu
        Intent intent = new Intent(this, volunteerMenu.class);
        EditText editText = (EditText) findViewById(R.id.usernameField);
        String message = editText.getText().toString();
        intent.putExtra(NAME, "Hello " + message + "!");

        //Add button count to main menu
        ctr++;
        String s_ctr = Integer.toString(ctr);
        intent.putExtra(COUNTER, s_ctr);
        startActivity(intent);

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


}



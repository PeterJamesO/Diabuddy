package whsct.test;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String NAME = "com.whsct.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.validationMsg);
        textView.setText("");
    }

    public void loginButton(View view) {
        // Go to main menu
        Intent intent = new Intent(this, MainMenu.class);

        // Pull username to Main Menu
        EditText usernameInput = (EditText) findViewById(R.id.usernamefield);
        String username = usernameInput.getText().toString();
        EditText passwordInput = (EditText) findViewById(R.id.passwordfield);
        String password = passwordInput.getText().toString();
        // Validate before continuing
        if (validate(username, password)) {
            TextView textView = (TextView) findViewById(R.id.validationMsg);
            textView.setTextColor(Color.GREEN);
            textView.setText("Loading...");
            intent.putExtra(NAME, "Hello " + username + "!");
            startActivity(intent);
        }
        else {
            TextView textView = (TextView) findViewById(R.id.validationMsg);
            textView.setTextColor(Color.RED);
            textView.setText("Incorrect, try again");
        }
    }


    public void changeVolunteerButton(View view) {
        // Change to volunteer login
        Intent intent = new Intent(this, volunteerLogin.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        // Disables back button so app can not be entered via backdoor. Hello
    }

    private boolean validate(String username, String password) {
        if (username.equalsIgnoreCase("diabetic") && password.contentEquals("password")) {
            return true;
        }
        else {
            return false;
        }
    }
}

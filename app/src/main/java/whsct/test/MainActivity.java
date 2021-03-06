package whsct.test;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String NAME = "com.whsct.MESSAGE";
    private static final String LOG_TITLE = "MainActivity: ";
    private TextView easteregg;
    private Context context = this;

    private DataSource dataSource;
    private List<UserData> users;

    @Override // On Create
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSource = new DataSource(this);
        dataSource.open();
        users = dataSource.getAllUsers();

        TextView textView = (TextView) findViewById(R.id.validationMsg);
        textView.setText("");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Easter Egg function
        easteregg = (TextView) findViewById(R.id.subtitletext);
        easteregg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d(LOG_TITLE, "OnLongClick start");
                Intent intent = new Intent(context, TestDatabaseActivity.class);
                startActivity(intent);
                Log.d(LOG_TITLE, "OnLongClick end");
                return true;
            }
        });
    }

    // Login Button
    public void loginButton(View view) {
        // Deactivate any active user
        for (UserData user: users) {
            if (user.getActive() == "yes") {
                user.setActive("no");
            }
        }

        // Go to main menu
        Intent intent = new Intent(this, MainMenu.class);
        boolean invalid = true;

        EditText usernameInput = (EditText) findViewById(R.id.usernamefield);
        String username = usernameInput.getText().toString();
        EditText passwordInput = (EditText) findViewById(R.id.passwordfield);
        String password = passwordInput.getText().toString();

        // Find username and password in database
        int counter = 0;
        for (UserData user: users) {
            if (user.getUsername().equalsIgnoreCase(username) && users.get(counter).getPassword().contentEquals(password)) {
                // Valid user - set user as active
                user.setActive("yes");
                invalid = false;

                TextView textView = (TextView) findViewById(R.id.validationMsg);
                textView.setTextColor(Color.GREEN);
                textView.setText("Loading...");
                startActivity(intent);
                textView.setText("");

                break;
            }
            ++counter;
        }
        if (invalid) {
            TextView textView = (TextView) findViewById(R.id.validationMsg);
            textView.setTextColor(Color.RED);
            textView.setText("INCORRECT, TRY AGAIN");

        }
    }

    // Change to volunteer login
    public void changeVolunteerButton(View view) {
        // Change to volunteer login
        Intent intent = new Intent(this, volunteerLogin.class);
        startActivity(intent);
    }

    // Moves to new account screen
    public void createAccount(View view) {
        // Go to main menu
        Intent intent = new Intent(this, newAccount.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }
}

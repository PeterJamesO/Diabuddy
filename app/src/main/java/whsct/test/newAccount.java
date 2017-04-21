package whsct.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class newAccount extends AppCompatActivity {
    private DataSource dataSource;
    private EditText username;
    private EditText password;
    private EditText email;
    private EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_account);

        // Start database
        dataSource = new DataSource(this);
        dataSource.open();

        username = (EditText) findViewById(R.id.nameNew);
        password = (EditText) findViewById(R.id.passwordNew);
        email = (EditText) findViewById(R.id.emailNew);
        number = (EditText) findViewById(R.id.numberNew);
    }

    // Login button
    public void loginButton(View view) {
        // TODO: check for duplicate account
        dataSource.createUser(
                username.getText().toString(),
                password.getText().toString(),
                email.getText().toString(),
                number.getText().toString(),
                false
        );

        // Get user to log in with new account
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Account validation
    public void validAccount(View view) {
        if (true) {
            Toast.makeText(newAccount.this, "Account Created", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(newAccount.this, "Invalid - Check fields above.", Toast.LENGTH_SHORT).show();
        }
    }

    // Pattern validation
    public boolean isValidUsername(final String username) {

        Pattern pattern;
        Matcher matcher;

        final String USERNAME_PATTERN = "(/^(?=.*\\d)[A-Za-z]{4,}$/)";

        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);

        return matcher.matches();

    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "(/^(?=.*\\d)(?=.*[A-Z])[0-9a-zA-Z]{4,}$/)";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public boolean isValidEmail(final String email) {

        Pattern pattern;
        Matcher matcher;

        final String EMAIL_PATTERN = "(^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$)";

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        return matcher.matches();

    }

    public boolean isValidNumber(final String number) {

        Pattern pattern;
        Matcher matcher;

        final String NUMBER_PATTERN = "(/^[0-9]{11,}$/)";

        pattern = Pattern.compile(NUMBER_PATTERN);
        matcher = pattern.matcher(number);

        return matcher.matches();

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



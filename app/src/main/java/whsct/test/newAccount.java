package whsct.test;

import android.content.Context;
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

    // What needs to be valid to allow account to be created
    public boolean criteria(String username, String password, String email, String number) {
        if (isValidUsername(username) && isValidPassword(password) && isValidEmail(email) && isValidNumber(number)) {
            return true;
        }
        else {
            return false;
        }
    }

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
                "no"
        );

        // Get user to log in with new account
        Intent intent = new Intent(this, MainActivity.class);
        if(validAccount()) {
            startActivity(intent);
        }
    }

    // Account validation
    public boolean validAccount() {
        if (criteria(username.getText().toString(), password.getText().toString(), email.getText().toString(), number.getText().toString())) {
            Toast.makeText(newAccount.this, "Account Created", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(newAccount.this, "Invalid - Check fields above.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    // Pattern validation
    public boolean isValidUsername(final String username) {

        Pattern pattern;
        Matcher matcher;

        final String USERNAME_PATTERN = "^(?!.*\\.\\.)(?!.*\\.$)[^\\W][\\w.]{0,29}$";

        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);

        return matcher.matches();

    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public boolean isValidEmail(final String email) {

        Pattern pattern;
        Matcher matcher;

        final String EMAIL_PATTERN = "[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+";

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        return matcher.matches();

    }

    public boolean isValidNumber(final String number) {

        Pattern pattern;
        Matcher matcher;

        final String NUMBER_PATTERN = "^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$";

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



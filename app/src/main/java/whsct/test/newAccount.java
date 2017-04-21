package whsct.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

    public void loginButton(View view) {
        // TODO: check for duplicate account
        dataSource.createUser(
                username.getText().toString(),
                password.getText().toString(),
                email.getText().toString(),
                number.getText().toString()
        );

        // Get user to log in with new account
        Intent intent = new Intent(this, MainActivity.class);
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

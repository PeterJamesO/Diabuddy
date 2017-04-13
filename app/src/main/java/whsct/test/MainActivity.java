package whsct.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginButton(View view) {
        // Go to main menu
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }


    public void changeVolunteerButton(View view) {
        // Change to volunteer login
        Intent intent = new Intent(this, volunteerLogin.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        // Disables back button so app can not be entered via backdoor.
    }
}

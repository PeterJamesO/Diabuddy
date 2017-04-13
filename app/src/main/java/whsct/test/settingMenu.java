package whsct.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class settingMenu extends AppCompatActivity {
    //Doctor Number
    Doctor doc1 = new Doctor();
    public static final String DOCNUM = "com.whsct.NUMBER";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_menu);
    }


    public void returnButton(View view) {
        // Go to main menu
        Intent intent = new Intent(this, MainMenu.class);

        // Doc Num
        EditText editText = (EditText) findViewById(R.id.numberContent);
        String message = editText.getText().toString();
        doc1.setDoctorNumber(message);
        startActivity(intent);
    }

    public void logoutButton(View view) {
        // Log Out of app
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

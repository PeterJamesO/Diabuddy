package whsct.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class emergencyScreen extends AppCompatActivity {
    public static final String DOCNUM = "com.whsct.NUMBER";
    Doctor doc1;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_screen);
    }

    public void returnButton(View view) {
        // Go to main menu
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    public void emergencyButton(View view) {
        // Calls doctor with listed number
        Intent intent = getIntent();
        Button button = (Button) findViewById(R.id.emergencyButton);
        button.setText(String.valueOf(doc1.getDoctorNumber()));
    }

    public void emergencyNumber(View view) {
        //Displays doctor number;
        Intent intent = getIntent();
        String message = intent.getStringExtra(settingMenu.DOCNUM);
        TextView name = (TextView) findViewById(R.id.emergencyNumber);
        name.setText(message);
    }
}


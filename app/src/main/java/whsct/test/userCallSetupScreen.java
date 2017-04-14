package whsct.test;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class userCallSetupScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_call_setup_screen);
    }

    public void advanceButton(View view) {
        Intent intent = new Intent(this, userCallScreen.class);
        startActivity(intent);
    }

}

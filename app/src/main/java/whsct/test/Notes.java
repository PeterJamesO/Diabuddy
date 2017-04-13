package whsct.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Notes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);
    }

    public void returnButton(View view) {
        // Go to main menu
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    public void newNoteButton(View view) {
        // Go to new note screen
        Intent intent = new Intent(this, newNote.class);
        startActivity(intent);
    }
}

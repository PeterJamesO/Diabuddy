package whsct.test;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.Random;

public class TestDatabaseActivity extends ListActivity {
    private DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_database);

        dataSource = new DataSource(this);
        dataSource.open();

        List<UserData> values = dataSource.getAllUsers();

        // Using SimpleCursonAdapter to show elements in a list view
        ArrayAdapter<UserData> adapter = new ArrayAdapter<UserData>(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    public void onClick(View view) {
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) getListAdapter();
        UserData user;

        switch (view.getId()) {
            case R.id.addUser:
                // Save to database
                int random = new Random().nextInt(3);
                String[] usernames = new String[] { "Bob", "Jo", "Betty" };
                String email = usernames[random] + "@gmail.com";
                user = dataSource.createUser(usernames[random], "ZAQzaq123", email, "+447516253460", false);
                adapter.add("Username:\t" + user.getUsername() + "\nDr.'s email:\t" + user.getEmail());
                break;
            case R.id.deleteUser:
                if (getListAdapter().getCount() > 0) {
                    user = (UserData) getListAdapter().getItem(0);
                    dataSource.deleteUser(user);
                    adapter.remove(user.getUsername());
                }
            case R.id.deleteAll:
                if (getListAdapter().getCount() > 0) {

                    dataSource.deleteAllUsers();
                    adapter.clear();
                }
            break;
        }
        adapter.notifyDataSetChanged();
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

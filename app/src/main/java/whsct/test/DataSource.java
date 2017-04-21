package whsct.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamiegardner on 20/04/2017.
 */

public class DataSource {
    // Database Fields
    private SQLiteDatabase database;
    private MySQLiteHelper mySQLiteHelper;
    private String[] allColumns = { 
            MySQLiteHelper.COLUMN_ID, 
            MySQLiteHelper.COLUMN_USERNAME, 
            MySQLiteHelper.COLUMN_PASSWORD, 
            MySQLiteHelper.COLUMN_EMAIL, 
            MySQLiteHelper.COLUMN_NUM,
            MySQLiteHelper.COLUMN_ACTIVE
    };

    // Constructor
    public DataSource(Context context) {
        mySQLiteHelper = new MySQLiteHelper(context);
    }
    
    // Methods
    public void open() throws SQLException {
        database = mySQLiteHelper.getWritableDatabase();
    }
    
    public void close() {
        mySQLiteHelper.close();
    }
    
    public UserData createUser(String username, String password, String email, String number, String active) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_USERNAME, username);
        values.put(MySQLiteHelper.COLUMN_PASSWORD, password);
        values.put(MySQLiteHelper.COLUMN_EMAIL, email);
        values.put(MySQLiteHelper.COLUMN_NUM, number);
        values.put(MySQLiteHelper.COLUMN_ACTIVE, active);
        long insertID = database.insert(MySQLiteHelper.TABLE_NAME, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME, allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertID, null, null, null, null);
        cursor.moveToFirst();
        UserData newUser = cursorToUser(cursor);
        cursor.close();
        
        return newUser;
    }

    public void deleteUser(UserData user) {
        long id = user.getId();
        String name = user.getUsername();
        System.out.println("User " + name + " deleted");
        database.delete(MySQLiteHelper.TABLE_NAME, MySQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public List<UserData> getAllUsers() {
        List<UserData> users = new ArrayList<>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            UserData user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        return users;
    }

    public void deleteAllUsers() {
        for (UserData user: getAllUsers()) {
            deleteUser(user);
        }
    }

    private UserData cursorToUser(Cursor cursor) {
        UserData user = new UserData();
        user.setId(cursor.getLong(0));
        user.setUsername(cursor.getString(1));
        user.setPassword(cursor.getString(2));
        user.setEmail(cursor.getString(3));
        user.setNumber(cursor.getString(4));
        user.setActive(cursor.getString(5));
        
        return user;
    }


}

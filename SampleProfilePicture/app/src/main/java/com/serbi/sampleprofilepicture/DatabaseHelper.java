package com.serbi.sampleprofilepicture;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static String DATABASE_NAME = "users.db";
    private static int DATABASE_VERSION = 1;

    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] byteImage;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, firstname TEXT, lastname TEXT, profile BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public void storeData(UserModel userModel) {
        SQLiteDatabase database = this.getWritableDatabase();
        Bitmap bitmap = userModel.getProfile();

        byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byteImage = byteArrayOutputStream.toByteArray();

        ContentValues values = new ContentValues();
        values.put("firstname", userModel.getFirstName());
        values.put("lastname", userModel.getLastName());
        values.put("profile", byteImage);

        long result = database.insert("users", null, values);
        if (result != -1) {
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
            database.close();
        } else {
            Toast.makeText(context, "Fail to save", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getUser() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM users", null);
        return cursor;
    }
}
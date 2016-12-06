package com.khimich.testdbapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.khimich.testdbapp.utils.Constants.CREATE_CONTACTS_TABLE;
import static com.khimich.testdbapp.utils.Constants.DATABASE_NAME;
import static com.khimich.testdbapp.utils.Constants.DATABASE_VERSION;
import static com.khimich.testdbapp.utils.Constants.DROP_TABLE;
import static com.khimich.testdbapp.utils.Constants.KEY_LS_NAME;
import static com.khimich.testdbapp.utils.Constants.KEY_NAME;
import static com.khimich.testdbapp.utils.Constants.NAME;
import static com.khimich.testdbapp.utils.Constants.SQL_IS_EMPTY;
import static com.khimich.testdbapp.utils.Constants.SURNAME;
import static com.khimich.testdbapp.utils.Constants.TABLE_USERS;

public class DataBaseHelper extends SQLiteOpenHelper {

    private String firstName;
    private String lastName;
    private Cursor cursor;
    private SQLiteDatabase db;
    private ContentValues values;
    private int limit = 50;
    private int offset = 0;

    public DataBaseHelper(Context context, int limit) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.limit = limit;
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE + TABLE_USERS);
        onCreate(sqLiteDatabase);
    }

    public void addContact() {
        values = new ContentValues();
        for (int i = 0; i < 1000; i++) {
            firstName = NAME + i;
            lastName = SURNAME + i;
            values.put(KEY_NAME, firstName);
            values.put(KEY_LS_NAME, lastName);
            db.insert(TABLE_USERS, null, values);
        }
        db.close();
    }

    public Cursor getListContents() {
        SQLiteDatabase database = getWritableDatabase();
        cursor = database.rawQuery(("select * from " + TABLE_USERS + " limit "
                + limit + " offset " + offset), null);
        offset += limit;
        return cursor;
    }

    public boolean isEmptyDb() {
        Cursor cursor = db.rawQuery(SQL_IS_EMPTY, null);
        cursor.moveToFirst();
        int icount = cursor.getInt(0);
        cursor.close();
        return icount <= 0;
    }
}

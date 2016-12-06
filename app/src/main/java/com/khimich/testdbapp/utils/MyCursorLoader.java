package com.khimich.testdbapp.utils;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

import com.khimich.testdbapp.db.DataBaseHelper;


public class MyCursorLoader extends CursorLoader {

    private DataBaseHelper dataBaseHelper;
    private Cursor cursor;

    public MyCursorLoader(Context context, DataBaseHelper dataBaseHelper) {
        super(context);
        this.dataBaseHelper = dataBaseHelper;
    }

    @Override
    public Cursor loadInBackground() {
        cursor = dataBaseHelper.getListContents();
        return cursor;
    }
}

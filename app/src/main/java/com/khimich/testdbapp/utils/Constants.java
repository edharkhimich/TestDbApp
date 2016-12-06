package com.khimich.testdbapp.utils;

public class Constants {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "user_db";
    public static final String TABLE_USERS = "users";
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "first_name";
    public static final String KEY_LS_NAME = "last_name";
    public static final String NAME = "ANDROID";
    public static final String SURNAME = "DEV";
    public static final String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT,"
            + KEY_LS_NAME + " TEXT" + ")";
    public static final String SQL_IS_EMPTY = "SELECT count(*) FROM " + TABLE_USERS;
    public static final String KEY = "123";
    public static final String SETTINGS_ACTION = "SETTINGS";
    public static final int DEFAULT_VALUE = 50;
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
}
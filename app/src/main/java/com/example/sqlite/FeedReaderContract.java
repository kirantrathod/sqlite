package com.example.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;

public final class FeedReaderContract {
    private FeedReaderContract(){};

    public  static class FeedEntery implements BaseColumns {
        public static final String TABLE_NAME="student";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_AGE="age";
        public static final String COLUMN_CRUSHNAME="crushname";


        public static final String TABLE_NAME1="images";
        public  static final String COLUMN_ID="id";
        public static final String COLUMN_IMAGE="image";
        public static final String COLUMN_STU_ID="student_id";
    }
    public static final String SQL_CREATE_STUDENTS= "create table "
            + FeedEntery.TABLE_NAME + " ("
            + FeedEntery._ID + " INTEGER PRIMARY KEY,"
            + FeedEntery.COLUMN_NAME + " TEXT,"
            + FeedEntery.COLUMN_AGE + " INTEGER,"
            + FeedEntery.COLUMN_CRUSHNAME + " TEXT)";
    public static final String SQL_DELETE_STUDENTS =
            "DROP TABLE IF EXISTS " + FeedEntery.TABLE_NAME;

    public static final String SQL_CREATE_IMAGES="create table "
            + FeedEntery.TABLE_NAME1 + " ("
            + FeedEntery.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FeedEntery.COLUMN_IMAGE + " BLOB,"
            + FeedEntery.COLUMN_STU_ID + "INTEGER, "
            + "CONSTRAINT fk_images"
            + " FOREIGN KEY (" + FeedEntery._ID + " )"
            +
            "  REFERENCES " + FeedEntery.TABLE_NAME + " (" + FeedEntery._ID + ")"
            +");";
    public static final String SQL_DELETE_IMAGES =
            "DROP TABLE IF EXISTS " + FeedEntery.TABLE_NAME1;

}

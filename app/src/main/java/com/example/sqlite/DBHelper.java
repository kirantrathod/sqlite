package com.example.sqlite;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.sqlite.FeedReaderContract.SQL_CREATE_IMAGES;
import static com.example.sqlite.FeedReaderContract.SQL_CREATE_STUDENTS;
import static com.example.sqlite.FeedReaderContract.SQL_DELETE_STUDENTS;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION= 1;
    public static final String DATABASE_NAME= "student";
    private Context mcontext;
    private DBHelper dbHelper;
    private Cursor c3;
    private SQLiteDatabase database;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mcontext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_STUDENTS);
       // db.execSQL(SQL_CREATE_IMAGES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_STUDENTS);
       // db.execSQL(SQL_DELETE_STUDENTS);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db,int oldVersion, int newVersion){
        onUpgrade(db,oldVersion,newVersion);
    }
   // DBHelper dbhelper =new DBHelper(mcontext);

    public DBHelper open() throws SQLException {
         dbHelper = new DBHelper(mcontext);
         database= dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }
    /*public void insertImage(String ID,byte[] b){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new  ContentValues();
        cv.put(FeedReaderContract.FeedEntery.COLUMN_IMAGE,b);
        cv.put(FeedReaderContract.FeedEntery.COLUMN_STU_ID,ID);
        database.insert(FeedReaderContract.FeedEntery.TABLE_NAME1, null, cv );
    }*/
   /* public Cursor FetchImage(String Id){
        String[] columns = new String[]
                {FeedReaderContract.FeedEntery.COLUMN_IMAGE};
        String select= FeedReaderContract.FeedEntery.COLUMN_STU_ID + "=?";
        String[] where ={Id};
        Cursor cursor1 = database.query(FeedReaderContract.FeedEntery.TABLE_NAME1,
                columns, select, where, null, null, null);
       if (cursor1 != null) {
            cursor1.moveToFirst();
        }
       //byte[] image=.getBlob(1);
        return cursor1;
    }*/
    public void insert(String name,int age,String crushname){
        SQLiteDatabase db1=this.getWritableDatabase();
        ContentValues v=new ContentValues();
        v.put(FeedReaderContract.FeedEntery.COLUMN_NAME,name);
        v.put(FeedReaderContract.FeedEntery.COLUMN_AGE,age);
        v.put(FeedReaderContract.FeedEntery.COLUMN_CRUSHNAME,crushname);
        long newRowId=db1.insert(FeedReaderContract.FeedEntery.TABLE_NAME,null,v);
        Toast.makeText(mcontext,"row "+ newRowId,Toast.LENGTH_SHORT).show();
        /*if (db1.isOpen()){
            db1.close();
        }*/
    }
   /* public ArrayList<students> getStudents(){
        SQLiteDatabase db2 =this.getReadableDatabase();
        String query = "select * from student";
        ArrayList<students> listOfStudents = new ArrayList<>();
        Cursor c = db2.rawQuery(query, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                String Name = c.getString(1);
                int Age=c.getInt(2);
                String CrushName = c.getString(3);
                students s = new students();
                s.setName(Name);
                s.setAge(Age);
                s.setCrushname(CrushName);
                listOfStudents.add(s);
                c.moveToNext();
            }

        }

    return listOfStudents;
    }
*/
   public Cursor fetch() {
       //SQLiteDatabase database=this.getReadableDatabase();
       String[] columns = new String[]
               {FeedReaderContract.FeedEntery._ID,FeedReaderContract.FeedEntery.COLUMN_NAME,
                       FeedReaderContract.FeedEntery.COLUMN_AGE, FeedReaderContract.FeedEntery.COLUMN_CRUSHNAME};
       Cursor cursor = database.query(FeedReaderContract.FeedEntery.TABLE_NAME,
               columns, null, null, null, null, null);
       if (cursor != null) {
           cursor.moveToFirst();
       }
       return cursor;
   }



}


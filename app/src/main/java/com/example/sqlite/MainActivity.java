package com.example.sqlite;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText Name,age,Crushname;
    Button Submit,Fetch;
    DBHelper db;
    ListView listView;
    private SimpleCursorAdapter adapter;
    final String[] from = new String[] {FeedReaderContract.FeedEntery._ID,FeedReaderContract.FeedEntery.COLUMN_NAME,
            FeedReaderContract.FeedEntery.COLUMN_AGE, FeedReaderContract.FeedEntery.COLUMN_CRUSHNAME};
    final int[] to = new int[] {R.id.ItemId, R.id.ItemName, R.id.ItemAge, R.id.ItemCrushName };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DBHelper(getApplicationContext());
        Name=(EditText)findViewById(R.id.NameEdit);
        age=(EditText)findViewById(R.id.age);
        Crushname=(EditText)findViewById(R.id.crushname);
        Submit=(Button)findViewById(R.id.submit);
        Fetch=(Button)findViewById(R.id.fetch);
        listView=(ListView)findViewById(R.id.listview);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String NAME=Name.getText().toString();
            int AGE=Integer.parseInt(age.getText().toString());
            String CRUSHNAME=Crushname.getText().toString();
            db.insert(NAME,AGE,CRUSHNAME);
            }
        });
        Fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.open();
                Cursor c=db.fetch();
                adapter = new SimpleCursorAdapter(MainActivity.this, R.layout.singlelistitem, c, from, to, 0);
                adapter.notifyDataSetChanged();

                listView.setAdapter(adapter);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView ID = (TextView) view.findViewById(R.id.ItemId);
                TextView Name = (TextView) view.findViewById(R.id.ItemName);
                TextView Age = (TextView) view.findViewById(R.id.ItemAge);
                TextView CrushName = (TextView) view.findViewById(R.id.ItemCrushName);
                String Id=ID.getText().toString();
                String NAME=Name.getText().toString();
                int AGE=Integer.parseInt(Age.getText().toString());
                String CRUSHNAME=CrushName.getText().toString();

                Intent intent=new Intent(MainActivity.this,userProfile.class);
                intent.putExtra("ID",Id);
                intent.putExtra("NAME",NAME);
                intent.putExtra("AGE",AGE);
                intent.putExtra("CRUSHNAME",CRUSHNAME);
                startActivity(intent);

            }
        });
    }

}

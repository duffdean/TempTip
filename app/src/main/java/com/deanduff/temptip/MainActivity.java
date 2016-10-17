package com.deanduff.temptip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import Utils.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        TextView xBox = (TextView)findViewById(R.id.textView);
        TextView yBox = (TextView)findViewById(R.id.textView2);

        try {
            db.createDatabase();
            db.openDatabase();
        } catch (Exception ioe) {
            throw new Error("Unable to create database");
        }


        xBox.setText(String.valueOf(event.getX()));
        yBox.setText(String.valueOf(event.getY()));

        db.storeTempValues(event.getX(), event.getY());
        db.close();

        return super.onTouchEvent(event);
    }
}

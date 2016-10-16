package com.deanduff.temptip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        TextView xBox = (TextView)findViewById(R.id.textView);
        TextView yBox = (TextView)findViewById(R.id.textView2);


        xBox.setText(String.valueOf(event.getX()));
        yBox.setText(String.valueOf(event.getY()));
        return super.onTouchEvent(event);
    }
}

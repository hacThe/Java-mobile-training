package com.example.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE_KEY = "com.example.android.intentdemo.extra.MESSAGE";
    public static final int MY_CODE = 1;
    TextView xnxx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

   /* public void butClicked(View view)
    {
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        String message = "Hello activity!";
        intent.putExtra(EXTRA_MESSAGE_KEY, message);
       *//* intent.setData(Uri.parse("http://www.google.com"))*//*
        *//*startActivity(intent);*//*
        startActivityForResult(intent, MY_CODE);
    }

    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_CODE) { // Identify activity
            if (resultCode == RESULT_OK) { // Activity succeeded
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                // … do something with the data
                xnxx.setText(reply);
            }}}*/

}



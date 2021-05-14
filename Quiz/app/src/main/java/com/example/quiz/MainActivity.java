package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    question a = new question();
    Button btnA, btnB, btnC, btnD, Back, Next;
    TextView question, count;
    int index = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnA = (Button) findViewById(R.id.answerA);
        btnB = (Button) findViewById(R.id.answerB);
        btnC = (Button) findViewById(R.id.answerC);
        btnD = (Button) findViewById(R.id.answerD);
        Back = (Button) findViewById(R.id.BtnCheck);
        Next = (Button) findViewById(R.id.BtnNext);

        question = (TextView) findViewById(R.id.question);
        count = (TextView) findViewById(R.id.count);


    }

    public void LoadNextQuestion(int index)
    {


    }





}
package com.example.multiplechoice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int imageRes[] = {R.drawable.q1, R.drawable.q2, R.drawable.q3,R.drawable.q4,R.drawable.q5};
    int viewIndex = 0;
    int index = 0;
    int score = 0;
    questions MyQuestion = new questions();
    Button A,B,C,D,BACK,NEXT;
    ImageView imgView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        A = (Button) findViewById(R.id.a);
        B = (Button) findViewById(R.id.b);
        C = (Button) findViewById(R.id.c);
        D = (Button) findViewById(R.id.d);

        BACK = (Button) findViewById(R.id.back);
        NEXT = (Button) findViewById(R.id.next);
        NEXT.setEnabled(false);
        BACK.setEnabled(false);

        imgView = (ImageView) findViewById((R.id.imageView));
        textView = (TextView) findViewById(R.id.question);

        LoadQuestion();


    }
    void LoadQuestion()
    {
        int stt = index+1;
        textView.setText("Question "+  stt + " "+ MyQuestion.getQuestion(index));
        imgView.setImageResource(imageRes[index]);
        A.setText("A."+ MyQuestion.getChoice(index)[0]);
        A.setBackgroundColor(Color.parseColor("#3B6F98"));

        B.setText("B."+ MyQuestion.getChoice(index)[1]);
        B.setBackgroundColor(Color.parseColor("#3B6F98"));

        C.setText("C."+ MyQuestion.getChoice(index)[2]);
        C.setBackgroundColor(Color.parseColor("#3B6F98"));

        D.setText("D."+ MyQuestion.getChoice(index)[3]);
        D.setBackgroundColor(Color.parseColor("#3B6F98"));


    }

    public void onClick(View v) {
        Button AA = (Button)v;
        String str = AA.getText().toString();
        str = str.substring(2);

        if (str.equals(MyQuestion.getCorrectAnswer(index)))
        {
            RightChoice(AA);
        }
        else LeftChoice(AA);
        SetDisable();
        if (index == 4) {
            NEXT.setEnabled(false);
            textView.setText("Your score: " + score);
            imgView.setImageResource(R.drawable.end);
            A.setText("Ket");
            B.setText("Thuc");
            C.setText("Roi");
            D.setText("Do");
            A.setBackgroundColor(Color.parseColor("#3B6F98"));
            B.setBackgroundColor(Color.parseColor("#3B6F98"));
            C.setBackgroundColor(Color.parseColor("#3B6F98"));
            D.setBackgroundColor(Color.parseColor("#3B6F98"));

        }
    }


    void RightChoice(Button AA)
    {
        score+=20;
        AA.setBackgroundColor(Color.GREEN);
        UpdateScore();
    }

    void UpdateScore()
    {
        TextView scr = (TextView) findViewById(R.id.Score);
        scr.setText(""+ score);
    }


    void LeftChoice(Button AA)
    {
        AA.setBackgroundColor(Color.RED);
    }


    void NextQuestion()
    {
        if (index <5)
        {
            index++;
        }
        else Reset();


    }
    void Reset()
    {
        index = 0;
        score = 0;
    }


    void SetDisable()
    {
        A.setEnabled(false);
        B.setEnabled(false);
        C.setEnabled(false);
        D.setEnabled(false);
        NEXT.setEnabled(true);
    }

    void SetEnable()
    {
        A.setEnabled(true);
        B.setEnabled(true);
        C.setEnabled(true);
        D.setEnabled(true);
        NEXT.setEnabled(false);

    }

    public void next_click(View v)
    {
        viewIndex++;
        if (viewIndex<=index)
            ShowQuestion();
        else {
            NextQuestion();
            LoadQuestion();
            SetEnable();
        }
        BACK.setEnabled(true);
    }


    public void back_click(View v)
    {
        viewIndex--;
        SetDisable();
        ShowQuestion();
        if (viewIndex == 0) {
            BACK.setEnabled(false);
        }
    }

    private void ShowQuestion() {
        int stt = viewIndex+1;
        if (viewIndex == index)
        {
            SetEnable();
            LoadQuestion();
            return;
        }

        textView.setText("Question "+  stt + " "+ MyQuestion.getQuestion(index));
        imgView.setImageResource(imageRes[viewIndex]);

        A.setText("A."+ MyQuestion.getChoice(viewIndex)[0]);
        A.setBackgroundColor(Color.parseColor("#3B6F98"));
        if (A.getText().toString().substring(2).equals(MyQuestion.getCorrectAnswer(viewIndex)))
            A.setBackgroundColor(Color.GREEN);

        B.setText("B."+ MyQuestion.getChoice(viewIndex)[1]);
        B.setBackgroundColor(Color.parseColor("#3B6F98"));
        if (B.getText().toString().substring(2).equals(MyQuestion.getCorrectAnswer(viewIndex)))
            B.setBackgroundColor(Color.GREEN);

        C.setText("C."+ MyQuestion.getChoice(viewIndex)[2]);
        C.setBackgroundColor(Color.parseColor("#3B6F98"));
        if (C.getText().toString().substring(2).equals(MyQuestion.getCorrectAnswer(viewIndex)))
            C.setBackgroundColor(Color.GREEN);

        D.setText("D."+ MyQuestion.getChoice(viewIndex)[3]);
        D.setBackgroundColor(Color.parseColor("#3B6F98"));
        if (D.getText().toString().substring(2).equals(MyQuestion.getCorrectAnswer(viewIndex)))
            D.setBackgroundColor(Color.GREEN);

    }
}
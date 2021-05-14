package com.example.tip_caculator_19522252;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private double amount;
    private EditText display;
    private SeekBar seekBar;
    private TextView rateTV;
    private TextView totalTV;
    private TextView tipTV;

    private double total, tip, rate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        total = tip = rate = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (EditText) findViewById(R.id.dttAmount);
        seekBar = (SeekBar) findViewById(R.id.sbTipRate);
        rateTV = (TextView) findViewById(R.id.tvRate);
        totalTV = (TextView) findViewById(R.id.tvTotalAmount);
        tipTV = (TextView) findViewById(R.id.tvTipAmount);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                rateTV.setText("" + progress + "%");
                if (display.getText().toString().equals("")) amount = 0;
                else
                amount = Double.parseDouble(display.getText().toString());
                rate = progress;
                total = amount*(100 + progress)/100;
                tip = amount*progress/100;
                totalTV.setText("" + total + "$");
                tipTV.setText("" + tip + "$");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        display.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (display.getText().toString().equals("")) amount = 0;
                else
                    amount = Double.parseDouble(display.getText().toString());
                //rate = progress;
                total = amount*(100 + rate)/100;
                tip = amount*rate/100;
                totalTV.setText("" + total + "$");
                tipTV.setText("" + tip + "$");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public void SetRate(View view)
    {

    }
}


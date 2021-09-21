package com.assignment1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Calculator calc;
    private Boolean shouldShowHistory;

    private TextView displayDefault, displayWithHistory, switchBtn;
    private Button num0, num1, num2, num3, num4, num5, num6, num7, num8, num9;;
    private Button plusOp, minusOp, mulOp, divideOp, equalsOp;
    private Button clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize objects
        this.calc = new Calculator();

        displayDefault = (TextView) findViewById(R.id.displayDefault);
        displayWithHistory = (TextView)findViewById(R.id.displayWithHistory);

        switchBtn = (TextView)findViewById(R.id.switchBtn);
        switchBtn.setText("ADVANCE - WITH HISTORY");
        switchBtn.setBackgroundResource(R.color.blue);
        this.shouldShowHistory = false;

        num0 = (Button)findViewById(R.id.zero);
        num1 = (Button)findViewById(R.id.one);
        num2 = (Button)findViewById(R.id.two);
        num3 = (Button)findViewById(R.id.three);
        num4 = (Button)findViewById(R.id.four);
        num5 = (Button)findViewById(R.id.five);
        num6 = (Button)findViewById(R.id.six);
        num7 = (Button)findViewById(R.id.seven);
        num8 = (Button)findViewById(R.id.eight);
        num9 = (Button)findViewById(R.id.nine);

        plusOp = (Button)findViewById(R.id.plusOp);
        minusOp = (Button)findViewById(R.id.minusOp);
        mulOp = (Button)findViewById(R.id.mulOp);
        divideOp = (Button)findViewById(R.id.divide);
        equalsOp = (Button)findViewById(R.id.equal);

        clear = (Button)findViewById(R.id.clear);

        switchBtn = (TextView)findViewById(R.id.switchBtn);

        // button listens on click
        num0.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        plusOp.setOnClickListener(this);
        minusOp.setOnClickListener(this);
        mulOp.setOnClickListener(this);
        divideOp.setOnClickListener(this);
        equalsOp.setOnClickListener(this);
        clear.setOnClickListener(this);
        switchBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String idView = view.getResources().getResourceEntryName(view.getId());

        if(idView.equals("switchBtn")){
            this.shouldShowHistory = !this.shouldShowHistory;
            this.calc.refresh();
            displayWithHistory.setText(String.format("%s",""));
            ViewUtils.updateBtn(this, this.switchBtn, this.shouldShowHistory);
            return;
        }

        String strToAdd = ((Button)view).getText().toString();

        this.calc.push(strToAdd);

        String displayString = this.calc.getDefault(view);

        displayDefault.setText(String.format("%s",displayString));

        if(this.shouldShowHistory && ((Button) view).getText().equals("=")){
            displayWithHistory.setText(String.format("%s",this.calc.getHistory()));
        }

    }


}
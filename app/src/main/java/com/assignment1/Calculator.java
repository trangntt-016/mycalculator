package com.assignment1;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class Calculator {
    private String currentStr;

    private List<String>historyCalculations;

    public Calculator(){
        this.currentStr = "";
        this.historyCalculations = new ArrayList<>();
    }

    public void push(String value){
        if(value.equals("C")){
            this.currentStr = "";
            return;
        };

        this.currentStr+=value;

        if(value.equals("=") && this.calculate()!=Integer.MAX_VALUE){
            this.currentStr += this.calculate();

            this.historyCalculations.add(this.currentStr);
        }

    }

    public int calculate(){

        if(this.currentStr.isEmpty()) return Integer.MAX_VALUE;


        int result = calcUtil(String.valueOf(this.currentStr.charAt(0)),String.valueOf(this.currentStr.charAt(2)),String.valueOf(this.currentStr.charAt(1)));

        for(int i = 3; i < this.currentStr.length() && this.currentStr.charAt(i)!='='; i+=2){
            result = calcUtil(String.valueOf(result),String.valueOf(this.currentStr.charAt(i+1)),String.valueOf(this.currentStr.charAt(i)));

        }

        return result;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getCurrentCalculation(View view){
        if(((Button)view).getText().toString().equals("C")) return "";

        // convert calculation 2*9=18 to 2 * 9 = 18
        return Arrays.stream(this.currentStr.split("")).map(c->{
            if(c.equals("=") || c.equals("*") || c.equals("/") || c.equals("+") || c.equals("-")) return " " + c + " ";

            return c;
        }).collect(Collectors.joining());
    }

    public void clearHistory(){
        this.historyCalculations = new ArrayList<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getHistoryCalculations(){
        return toString(this.historyCalculations);
    }

    @TargetApi(Build.VERSION_CODES.N)
    public int calcUtil(String num1, String num2, String op) {
        if(!isNumberValid(num1) || !isNumberValid(num2) || !isOperationValid(op)) return Integer.MAX_VALUE;

        int result = 0;

        switch(op){
            case "+":
                result = Integer.parseInt(num1) + Integer.parseInt(num2);
                break;
            case "-":
                result = Integer.parseInt(num1) - Integer.parseInt(num2);
                break;
            case "/":
                result = Integer.parseInt(num1) / Integer.parseInt(num2);
                break;
            case "*":
                result = Integer.parseInt(num1) * Integer.parseInt(num2);
                break;
        }

        return result;
    }

    public boolean isOperationValid(String op){
        if( op.equals("*") || op.equals("/") || op.equals("-") || op.equals("+")) return true;

        return false;
    }

    public boolean isNumberValid(String numStr){
        int number = Integer.parseInt(numStr);

        return number <=Integer.MAX_VALUE && number>=Integer.MIN_VALUE;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String toString(List<String>stringList){
        // convert each calculation 2*9=18 to 2 * 9 = 18
        stringList = stringList.stream().map(s->{
            return Arrays.stream(s.split("")).map(c->{
                if(c.equals("=") || c.equals("*") || c.equals("/") || c.equals("+") || c.equals("-")) return " " + c + " ";

                return c;
            }).collect(Collectors.joining());
        }).collect(Collectors.toList());

        return stringList.toString()
                .replace(",","\n")
                .replace("[","")
                .replace("]","");
    }
}


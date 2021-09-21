package com.assignment1;


import android.widget.TextView;

public class ViewUtils {
    public static void updateBtn(MainActivity main, TextView switchBtn, Boolean shouldShowHistory){
        if(!shouldShowHistory){
            switchBtn.setText("ADVANCE - WITH HISTORY");
            switchBtn.setBackgroundResource(R.color.blue);
            switchBtn.setTextColor(main.getResources().getColor(R.color.black));
        }
        else{
            switchBtn.setText("STANDARD - NO HISTORY");
            switchBtn.setBackgroundResource(R.color.deepblue);
            switchBtn.setTextColor(main.getResources().getColor(R.color.white));
        }

    }
}

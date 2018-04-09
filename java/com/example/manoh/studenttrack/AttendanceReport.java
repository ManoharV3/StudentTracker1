package com.example.manoh.studenttrack;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by manoh on 3/30/2018.
 */

public class AttendanceReport  extends AppCompatActivity {



    private String TAG = AttendanceReport.class.getSimpleName();
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        show_report();
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void show_report()
    {
        Log.i(TAG,"calling");
        AttendanceDB at = new AttendanceDB(getApplicationContext());
        String result1 = at.getData();
        String[] array = result1.split(":::");
        Log.i(TAG, array[0]+"");
        LinearLayout ly = (LinearLayout)(findViewById(R.id.linear));
        String previous_date = "";
        String[] sessions = new String[7];
        int count = 0;
        for(int i=0;i<array.length;i++)
        {
            String[] dummy = array[i].split(",");
            Log.i(TAG, Arrays.toString(dummy)+"");
            Log.i(TAG,"date_prev "+dummy[3].split(" ")[0]);
            String current_date = dummy[3].split(" ")[0];

            if(!current_date.equals(previous_date))
            {
                count =0;
                previous_date = current_date;
            }
            else{
                Log.i(TAG,"date"+current_date);
                sessions[count++] = dummy[1];
//                LinearLayout l1 = new LinearLayout(getApplicationContext());
//                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1f);
//                lp.setMargins(18,18,18,18);
//                l1.setOrientation(LinearLayout.VERTICAL);
//                TextView t1 = new TextView(getApplicationContext());
//                t1.setWidth(200);
//                t1.setHeight(50);
//                t1.setText(current_date);
//                l1.setLayoutParams(lp);
//                l1.addView(t1);
//                ly.addView(l1);
//                TextView t2 = new TextView(getApplicationContext());
//                t2.setWidth(200);
//                t2.setHeight(50);
//                t2.setText("sessions");
//                l1.setLayoutParams(lp);
//                l1.addView(t2);
//                ly.addView(l1);

//                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(70dp,LinearLayout.LayoutParams.WRAP_CONTENT,1f);





            }
            Log.i(TAG,Arrays.toString(sessions)+" sessions");
            LinearLayout l1 = new LinearLayout(getApplicationContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1f);
            lp.setMargins(18,18,18,18);
//                l1.setOrientation(LinearLayout.VERTICAL);
            TextView t1 = new TextView(getApplicationContext());
            TextView t2 = new TextView(getApplicationContext());
            TextView t3 = new TextView(getApplicationContext());

            t1.setLayoutParams(lp);
            t2.setLayoutParams(lp);
            t3.setLayoutParams(lp);
            t1.setText(dummy[1]);
            t1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            if(dummy[2].equals("1")){
                t2.setText("absent");
                t2.setTextColor(getResources().getColor(R.color.colorRed));

            }
            else {
                t2.setText("present");
                t2.setTextColor(getResources().getColor(R.color.colorGreen));

            }
            t3.setText(dummy[3]);
            l1.setLayoutParams(lp);
            l1.addView(t1);
            l1.addView(t2);
            l1.addView(t3);
            ly.addView(l1);


        }

    }


}

package com.example.manoh.studenttrack;

import android.graphics.Color;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by manoh on 3/27/2018.
 */

public class Attendance extends AppCompatActivity {
    private String TAG = Attendance.class.getSimpleName();
    protected LocationManager locationManager;
    int session_id = 0;
    Calendar cal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        cal = Calendar.getInstance();
        Log.i(TAG, "Current time :" + cal.getTime());
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int mins = cal.get(Calendar.MINUTE);
        Log.i(TAG, hour + " hour " + mins);
        LinearLayout ly = (LinearLayout) (findViewById(R.id.checkin));
        LinearLayout l1 = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        lp.setMargins(18, 18, 18, 18);
        l1.setOrientation(LinearLayout.VERTICAL);
        TextView t1 = new TextView(getApplicationContext());
        t1.setLayoutParams(lp);
        if (hour >= 18 || (hour <= 8 && mins < 50)) {
            findViewById(R.id.checkOutID).setEnabled(false);
            findViewById(R.id.checkinID).setEnabled(false);
            t1.setText("There is no marking of attendance at this time");
            t1.setTextSize(30);
            t1.setBackgroundColor(Color.parseColor("#8BC34A"));
            l1.addView(t1);
            ly.addView(l1);

        } else if ((hour <= 18 && hour != 13) || (hour >= 8 && mins >= 50)) {
            findViewById(R.id.checkOutID).setEnabled(false);
            findViewById(R.id.checkinID).setEnabled(true);
        } else if ((hour <= 11 && hour != 13) || (hour >= 8 && mins >= 50)) {
            findViewById(R.id.checkOutID).setEnabled(false);
            findViewById(R.id.checkinID).setEnabled(true);
        } else if (hour == 13 && (mins <= 50) || mins >= 00) {

            findViewById(R.id.checkOutID).setEnabled(false);
            findViewById(R.id.checkinID).setEnabled(false);
            t1.setText("There is no marking of attendance at this time");
            t1.setTextSize(30);
            t1.setBackgroundColor(Color.parseColor("#8BC34A"));
            l1.addView(t1);
            ly.addView(l1);
        } else if (hour < 13 || (hour >= 10 && mins >= 50)) {
            findViewById(R.id.checkOutID).setEnabled(false);
            findViewById(R.id.checkinID).setEnabled(true);
        } else if (hour <= 18 || (hour >= 13 && mins >= 50)) {
            findViewById(R.id.checkOutID).setEnabled(false);
            findViewById(R.id.checkinID).setEnabled(true);
        } else {
            findViewById(R.id.checkOutID).setEnabled(true);
            findViewById(R.id.checkinID).setEnabled(false);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void checkIn(View v) {

        findViewById(R.id.checkOutID).setEnabled(true);
        findViewById(R.id.checkinID).setEnabled(false);

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
//        String format = simpleDateFormat.format(new Date());
//        Log.i(TAG,"Current time :"+format);
        cal = Calendar.getInstance();
//        Log.i(TAG,"Current time :"+cal.getTime());
        display(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
//        Toast.makeText(Attendance.this,"check in time :"+cal.getTime(),Toast.LENGTH_LONG).show();


    }

    public void display(int hour, int mins) {
        if (hour >= 8 && hour <= 10) {
            findViewById(R.id.checkOutID).setEnabled(true);
            findViewById(R.id.checkinID).setEnabled(false);
            session_id = 1;
            LinearLayout ly = (LinearLayout) (findViewById(R.id.checkin));
            LinearLayout l1 = new LinearLayout(getApplicationContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            lp.setMargins(18, 18, 18, 18);
            l1.setOrientation(LinearLayout.VERTICAL);
            TextView t1 = new TextView(getApplicationContext());
            t1.setLayoutParams(lp);
            if ((hour == 8 && mins >= 50) || (hour == 9 && mins <= 10)) {
                t1.setText("you have been marked for session 1 attendance");
                t1.setTextSize(30);
                t1.setBackgroundColor(Color.parseColor("#8BC34A"));
                l1.addView(t1);
                ly.addView(l1);
                Date now = new Date();
                DateFormat formatter = DateFormat.getInstance(); // Date and time
                String dateStr = formatter.format(now);
                AttendanceDB at = new AttendanceDB(getApplicationContext());
                boolean result = at.insertValues(session_id, 0, dateStr.toString());

            } else if ((hour == 9 && mins > 10) || (hour == 10 && mins < 50)) {
                int hr = (hour - 9);
                int ms = (mins - 10);
                int late = (hr * 60) + ms;
                t1.setText("you are late for session 1  by " + late + " minutes");
                t1.setTextSize(30);
                t1.setBackgroundColor(Color.parseColor("#F44336"));
                l1.addView(t1);
                ly.addView(l1);
//                AttendanceDB at = new AttendanceDB(getApplicationContext());
//                boolean result = at.insertValues(session_id,1,cal.getTime().toString());
//                Log.i(TAG,result+" result");
                Date now = new Date();
                DateFormat formatter = DateFormat.getInstance(); // Date and time
                String dateStr = formatter.format(now);
                AttendanceDB at = new AttendanceDB(getApplicationContext());
                boolean result = at.insertValues(session_id, 1, dateStr.toString());


            }
        } else {
            if (hour >= 10 && hour <= 12) {
                findViewById(R.id.checkOutID).setEnabled(true);
                findViewById(R.id.checkinID).setEnabled(false);
                session_id = 2;
                LinearLayout ly = (LinearLayout) (findViewById(R.id.checkin));
                LinearLayout l1 = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
                lp.setMargins(18, 18, 18, 18);
                l1.setOrientation(LinearLayout.VERTICAL);
                TextView t1 = new TextView(getApplicationContext());
                t1.setLayoutParams(lp);


                if ((hour == 10 && mins >= 50) || (hour == 11 && mins <= 10)) {

                    t1.setText("you have been marked for session 2 attendance");
                    t1.setTextSize(30);
                    t1.setBackgroundColor(Color.parseColor("#8BC34A"));
                    l1.addView(t1);
                    ly.addView(l1);
//                AttendanceDB at = new AttendanceDB(getApplicationContext());
//                boolean result = at.insertValues(session_id,0,cal.getTime().toString());
//                Log.i(TAG,result+" result");
                    Date now = new Date();
                    DateFormat formatter = DateFormat.getInstance(); // Date and time
                    String dateStr = formatter.format(now);
                    AttendanceDB at = new AttendanceDB(getApplicationContext());
                    boolean result = at.insertValues(session_id, 0, dateStr.toString());

                } else if ((hour == 11 && mins > 10) || (hour == 12 && mins < 50)) {
                    int hr = (hour - 11);
                    int ms = (mins - 10);
                    int late = (hr * 60) + ms;
                    t1.setText("you are late for session 2  by " + late + " minutes");
                    t1.setTextSize(30);
                    t1.setBackgroundColor(Color.parseColor("#F44336"));
                    l1.addView(t1);
                    ly.addView(l1);
//                AttendanceDB at = new AttendanceDB(getApplicationContext());
//                boolean result = at.insertValues(session_id,1,cal.getTime().toString());
//                Log.i(TAG,result+" result");
                    Date now = new Date();
                    DateFormat formatter = DateFormat.getInstance(); // Date and time
                    String dateStr = formatter.format(now);
                    AttendanceDB at = new AttendanceDB(getApplicationContext());
                    boolean result = at.insertValues(session_id, 1, dateStr.toString());


                }
            }
        }
        if(hour >=13 && hour <=17)
        {
            findViewById(R.id.checkOutID).setEnabled(true);
            findViewById(R.id.checkinID).setEnabled(false);
            session_id = 3;
            LinearLayout ly = (LinearLayout)(findViewById(R.id.checkin));
            LinearLayout l1 = new LinearLayout(getApplicationContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1f);
            lp.setMargins(18,18,18,18);
            l1.setOrientation(LinearLayout.VERTICAL);
            TextView t1 = new TextView(getApplicationContext());
            t1.setLayoutParams(lp);
            if((hour ==13 && mins >= 50) || (hour ==14 && mins <= 10))
            {
                t1.setText("you have been marked for session 3 attendance");
                t1.setTextSize(30);
                t1.setBackgroundColor(Color.parseColor("#8BC34A"));
                l1.addView(t1);
                ly.addView(l1);
//                AttendanceDB at = new AttendanceDB(getApplicationContext());
//                boolean result = at.insertValues(session_id,0,cal.getTime().toString());
//                Log.i(TAG,result+" result");
                Date now = new Date();
                DateFormat formatter = DateFormat.getInstance(); // Date and time
                String dateStr = formatter.format(now);
                AttendanceDB at = new AttendanceDB(getApplicationContext());
                boolean result = at.insertValues(session_id,0,dateStr.toString());

            }
            else if((hour >=14 && mins > 10) || (hour <=17 && mins < 50)){
                int hr = (hour - 14);
                int ms = (mins - 10);
                int late = (hr*60)+ms;
                t1.setText("you are late for session 3  by " + late + " minutes");
                t1.setTextSize(30);
                t1.setBackgroundColor(Color.parseColor("#F44336"));
                l1.addView(t1);
                ly.addView(l1);
//                AttendanceDB at = new AttendanceDB(getApplicationContext());
//                boolean result = at.insertValues(session_id,1,cal.getTime().toString());
//                Log.i(TAG,result+" result");
                Date now = new Date();
                DateFormat formatter = DateFormat.getInstance(); // Date and time
                String dateStr = formatter.format(now);
                AttendanceDB at = new AttendanceDB(getApplicationContext());
                boolean result = at.insertValues(session_id,1,dateStr.toString());


            }
        }
        else if(hour>=18 || (hour<=8 && mins <50))
        {
            findViewById(R.id.checkOutID).setEnabled(false);
            findViewById(R.id.checkinID).setEnabled(false);
        }

    }
    public void checkOut(View V)
    {
        findViewById(R.id.checkOutID).setEnabled(false);
        findViewById(R.id.checkinID).setEnabled(true);

        Calendar cal = Calendar.getInstance();
        Log.i(TAG,"Current time :"+cal.getTime());
        Toast.makeText(Attendance.this,"Checkout  time :"+cal.getTime(),Toast.LENGTH_LONG).show();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int mins = cal.get(Calendar.MINUTE);
        LinearLayout ly = (LinearLayout)(findViewById(R.id.checkout));
        LinearLayout lyy = (LinearLayout)(findViewById(R.id.checkin));
        ly.setVisibility(View.VISIBLE);
        lyy.setVisibility(View.GONE);
        LinearLayout l1 = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1f);
        lp.setMargins(18,18,18,18);
        l1.setOrientation(LinearLayout.VERTICAL);
        TextView t1 = new TextView(getApplicationContext());
        t1.setLayoutParams(lp);
        if(hour<11 || hour < 13 || hour <18 )
        {

            findViewById(R.id.checkOutID).setEnabled(false);
            findViewById(R.id.checkinID).setEnabled(false);
            t1.setText("You are checking out before time. You may loose attendance");
            t1.setTextSize(30);
            t1.setBackgroundColor(Color.parseColor("#F44336"));
            l1.addView(t1);
            ly.addView(l1);

        }

    }

}

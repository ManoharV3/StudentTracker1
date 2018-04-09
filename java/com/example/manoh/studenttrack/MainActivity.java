package com.example.manoh.studenttrack;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

    }
//    public void login(View v){
//        Intent i = new Intent(MainActivity.this, com.example.manoh.studenttrack.Profile.class);
//        startActivity(i);
//    }

    public void getContacts(View v)
    {
        Intent i = new Intent(MainActivity.this, com.example.manoh.studenttrack.Profile.class);
        startActivity(i);
    }
    public void display(View V)
    {
        Intent i = new Intent(MainActivity.this, com.example.manoh.studenttrack.Attendance.class);
        startActivity(i);
    }
    public void show_report(View v)
    {
        Intent i = new Intent(MainActivity.this, com.example.manoh.studenttrack.AttendanceReport.class);
        startActivity(i);
    }





}

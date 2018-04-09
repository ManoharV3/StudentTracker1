package com.example.manoh.studenttrack;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Profile extends AppCompatActivity {

    private String TAG = Profile.class.getSimpleName();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        new GetContacts().execute();



    }


    private class GetContacts extends AsyncTask<Void, Void, Void> {
        ArrayList<LinkedHashMap<String, String>> student_list = null;
        LinkedHashMap<String, String> student_data = null;
        private ListView lv;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Profile.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting responsehttp
            String url = "http://msitis-iiith.appspot.com/api/profile/ag5ifm1zaXRpcy1paWl0aHIUCxIHU3R1ZGVudBiAgIDA48GYCgw";
            String jsonStr = sh.makeServiceCall(url);
            Log.i(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    student_list = new ArrayList<>();
                    student_data = new LinkedHashMap<>();
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    String status = jsonObj.getString("status");
                    // Getting JSON Array node
                    JSONArray data = jsonObj.getJSONArray("data");

                    // looping through the data
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject st_data = data.getJSONObject(i);
                        String app_number = st_data.getString("application_number");
                        String blood_group = st_data.getString("blood_group");
                        String middlename = st_data.getString("student_middlename");
                        String pincode = st_data.getString("pincode");
                        String st_rank = st_data.getString("rank");
                        String st_mobile_primary = st_data.getString("student_mobile1");
                        String st_father = st_data.getString("father_name");
                        String st_ug_grade = st_data.getString("ug_grade");
                        String st_father_mail = st_data.getString("father_email");
                        String st_lastupdate = st_data.getString("last_updated");
                        String st_tenth_grade = st_data.getString("tenth_grade");
                        String st_firstname = st_data.getString("student_firstname");
                        String st_mothername = st_data.getString("mother_name");
                        String st_hostelname = st_data.getString("hostel");
                        String st_intermediategrade = st_data.getString("intermediate_grade");
                        String st_qualifier_exam = st_data.getString("qualifier_exam");
                        String st_state = st_data.getString("state");
                        String st_date_of_birth = st_data.getString("date_of_birth");
                        String st_student_lastname = st_data.getString("student_lastname");
                        String st_roll_number = st_data.getString("roll_number");
                        String st_gat_critical_reading = st_data.getString("gat_critical_reading");
                        String st_ug_branch = st_data.getString("ug_branch");
                        String st_center = st_data.getString("center");
                        String st_city = st_data.getString("city");
                        String st_student_fullname = st_data.getString("student_fullname");
                        String st_student_email = st_data.getString("student_email");

                        // tmp hash map for single contact


                        // adding each child node to HashMap key => value
                        student_data.put("application_number", app_number);
                        student_data.put("blood_group", blood_group);
                        student_data.put("student_middlename", middlename);
                        student_data.put("pincode", pincode);
                        student_data.put("rank", st_rank);
                        student_data.put("student_mobile1", st_mobile_primary);
                        student_data.put("father_name", st_father);
                        student_data.put("ug_grade", st_ug_grade);
                        student_data.put("father_email", st_father_mail);
                        student_data.put("last_updated", st_lastupdate);
                        student_data.put("tenth_grade", st_tenth_grade);
                        student_data.put("student_firstname", st_firstname);
                        student_data.put("mother_name", st_mothername);
                        student_data.put("hostel", st_hostelname);

                        Log.i(TAG, student_data.toString());


                    }
                    Log.i(TAG, student_data.toString());
                    LinearLayout ly = (LinearLayout) findViewById(R.id.linear);
//                    for(String k : student_data.keySet())
//                    {
//
//                    }







                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
////            ListAdapter adapter = new SimpleAdapter(login.this,student_list,R.layout.list_item,new String[]{"application_number","blood_group"},new int[]{R.id.app_number,R.id.blood_group});
////
////            lv.setAdapter(adapter);
//            Log.i(TAG, "kjdjknjk");
//            Log.i(TAG, student_data.toString());
            LinearLayout ly = (LinearLayout)(findViewById(R.id.linear));
//            first iterate LInkedH        keys value pair

            for(String k:student_data.keySet()){
                LinearLayout l1 = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1f);
                lp.setMargins(18,18,18,18);
//                l1.setOrientation(LinearLayout.VERTICAL);
                TextView t1 = new TextView(getApplicationContext());
                TextView t2 = new TextView(getApplicationContext());
                t1.setLayoutParams(lp);
                t2.setLayoutParams(lp);

                t1.setText(k);
                t1.setTextColor(getResources().getColor(R.color.colorAccent));
                t2.setText(student_data.get(k));
                t2.setTextColor(getResources().getColor(R.color.colorAccent));
                l1.setLayoutParams(lp);
                l1.addView(t1);
                l1.addView(t2);
                ly.addView(l1);



//                TextView tv = new TextView(getBaseContext());
//                tv.setText(k+"   :::   "+student_data.get(k));
//                tv.setTextSize(15);
//                ly.addView(tv);

            }
//            Log.i(TAG,student_data.toString());

        }
    }
}
package com.sits.school360.ui.examSchedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sits.school360.GlobalVariables;
import com.sits.school360.R;
import com.sits.school360.ui.examSchedule.ExamScheduleDataObject;
import com.sits.school360.ui.examSchedule.ExamScheduleRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExamScheduleActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int x;
    String URL = "http://apischools360.sitslive.com/Api/examSchedule?stuCode=";
    String Key = "&key=";
    ArrayList<String> Date;
    ArrayList<String> FeeFor;
    ArrayList<String> TotalAmount;
    //ArrayList<String> TotalDue;
    ArrayList<Integer> TotalReceive;
    ArrayList<Integer> Balance;

    private static String LOG_TAG = "CardViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_schedule);
        Date = new ArrayList<>();
        FeeFor = new ArrayList<>();
        TotalAmount = new ArrayList<>();
        TotalReceive = new ArrayList<>();
        //TotalDue = new ArrayList<>();
        Balance = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        loadCardsData(URL);
    }

    private ArrayList<ExamScheduleDataObject> getDataSet() {
        ArrayList results = new ArrayList<ExamScheduleDataObject>();
        for (int index = 0; index < x; index++) {
            ExamScheduleDataObject obj = new ExamScheduleDataObject("Date: "+Date.get(index),
                    "Subject: "+FeeFor.get(index),"Exam Type: "+TotalAmount.get(index),
                    "Maximum Marks: "+TotalReceive.get(index),"Minimum Marks: "+Balance.get(index));
            results.add(index, obj);
        }
        return results;
    }

    private void loadCardsData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String str = GlobalVariables.id+"&key="+GlobalVariables.schoolID;
        String test=url+str;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, test, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] res= new String[]{"a",response};
                res[1]="{\"name\":"+res[1]+"}";
                try {
                    JSONObject jsonObject = new JSONObject(res[1]);
                    JSONArray jsonArray = jsonObject.getJSONArray("name");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String date = jsonObject1.getString("date");
                        String feeFor = jsonObject1.getString("subject_name");
                        String totalAmount = jsonObject1.getString("exam_type");
                        //String totalDue = jsonObject1.getString("time");
                        Integer totalReceive = jsonObject1.getInt("maximum_marks");
                        Integer balance = jsonObject1.getInt("minimum_marks");
                        Date.add(date);
                        FeeFor.add(feeFor);
                        TotalAmount.add(totalAmount);
                        //TotalDue.add(totalDue);
                        TotalReceive.add(totalReceive);
                        Balance.add(balance);
                        x = x + 1;
                    }

                    mAdapter = new ExamScheduleRecyclerViewAdapter(getDataSet());
                    mRecyclerView.setAdapter(mAdapter);
                    //spinner.setAdapter(new ArrayAdapter<String>(FeeSummaryActivity.this, android.R.layout.simple_spinner_dropdown_item, SchoolNames));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }
}

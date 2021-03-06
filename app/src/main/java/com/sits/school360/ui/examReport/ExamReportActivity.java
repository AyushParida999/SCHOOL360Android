package com.sits.school360.ui.examReport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
import com.sits.school360.ui.examReport.ExamReportDataObject;
import com.sits.school360.ui.examReport.ExamReportRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExamReportActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int x;
    String URL = "http://apischools360.sitslive.com/Api/Fee?stuCode=931&key=@@schools@@@@@@@@@3@@@@&schoolCodeKey=3";
    ArrayList<String> Date;
    ArrayList<String> FeeFor;
    ArrayList<String> TotalAmount;
    ArrayList<String> TotalDue;
    ArrayList<String> TotalReceive;
    ArrayList<String> Balance;
    ArrayList<String> Spin;
    Spinner spinner;
    int Hold;
    private static String LOG_TAG = "CardViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_report);
        Date = new ArrayList<>();
        FeeFor = new ArrayList<>();
        TotalAmount = new ArrayList<>();
        TotalReceive = new ArrayList<>();
        TotalDue = new ArrayList<>();
        Balance = new ArrayList<>();
        Spin=new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        spinner=(Spinner)findViewById(R.id.examType);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub

                spinner.getSelectedItemPosition();
                Hold = spinner.getSelectedItemPosition();
                loadCardsData(URL,Hold+1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
    }

    private ArrayList<ExamReportDataObject> getDataSet() {
        ArrayList results = new ArrayList<ExamReportDataObject>();
        for (int index = 0; index < x; index++) {
            ExamReportDataObject obj = new ExamReportDataObject("Subject Name: "+Date.get(index),"Maximum Marks: "+FeeFor.get(index),
                    "Minimum Marks: "+TotalAmount.get(index),"Obtained Marks: "+TotalDue.get(index),"","");
            results.add(index, obj);
        }
        return results;
    }

    private void loadCardsData(String url,int h) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        Integer str = GlobalVariables.id;
        String test=url+str+"&examType="+h;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, test, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] arr=response.split("<string xmlns=\"http://tempuri.org/\">");
                String[] res2=arr[1].split("</");
                String[] res = new String[]{"a", res2[0]};
                res[1] = "{\"name\":" + res[1] + "}";
                try {
                    JSONObject jsonObject = new JSONObject(res[1]);
                    JSONArray jsonArray = jsonObject.getJSONArray("name");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String date = jsonObject1.getString("subject_name");
                        String feeFor = jsonObject1.getString("maximum_marks");
                        String totalAmount = jsonObject1.getString("minimum_marks");
                        String totalDue = jsonObject1.getString("obtained_marks");
                        //String totalReceive = jsonObject1.getString("TotalReceive");
                        //String balance = jsonObject1.getString("Balance");
                        Date.add(date);
                        FeeFor.add(feeFor);
                        TotalAmount.add(totalAmount);
                        TotalDue.add(totalDue);
                        //TotalReceive.add(totalReceive);
                        //Balance.add(balance);
                        x = x + 1;
                    }

                    mAdapter = new ExamReportRecyclerViewAdapter(getDataSet());
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
    private void loadExamTypes(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] res = new String[]{"a", response};
                res[1] = "{\"name\":" + res[1] + "}";
                try {
                    JSONObject jsonObject = new JSONObject(res[1]);
                    JSONArray jsonArray = jsonObject.getJSONArray("name");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String date = jsonObject1.getString("subject_name");
                        String feeFor = jsonObject1.getString("maximum_marks");
                        String totalAmount = jsonObject1.getString("minimum_marks");
                        String totalDue = jsonObject1.getString("obtained_marks");
                        //String totalReceive = jsonObject1.getString("TotalReceive");
                        //String balance = jsonObject1.getString("Balance");
                        Date.add(date);
                        FeeFor.add(feeFor);
                        TotalAmount.add(totalAmount);
                        TotalDue.add(totalDue);
                        //TotalReceive.add(totalReceive);
                        //Balance.add(balance);
                        x = x + 1;
                    }

                    spinner.setAdapter(new ArrayAdapter<String>(ExamReportActivity.this, android.R.layout.simple_spinner_dropdown_item, Spin));
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

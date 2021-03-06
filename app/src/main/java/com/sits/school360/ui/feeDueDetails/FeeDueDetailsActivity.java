package com.sits.school360.ui.feeDueDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.sits.school360.ui.feeDueDetails.FeeDueDetailsDataObject;
import com.sits.school360.ui.feeDueDetails.FeeDueDetailsRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FeeDueDetailsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int x;
    String URL = "http://apischools360.sitslive.com/Api/FeeDueDetails?stuCode=";
    String Key="&key="+GlobalVariables.schoolID;
    ArrayList<String> Date;
    ArrayList<String> FeeFor;
    ArrayList<String> TotalAmount;
    ArrayList<String> TotalDue;
    ArrayList<String> TotalReceive;
    //ArrayList<Integer> Balance;

    private static String LOG_TAG = "CardViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_due_details);
        Date = new ArrayList<>();
        FeeFor = new ArrayList<>();
        TotalAmount = new ArrayList<>();
        TotalReceive = new ArrayList<>();
        TotalDue = new ArrayList<>();
        //Balance = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view2);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        loadCardsData(URL);
    }

    private ArrayList<FeeDueDetailsDataObject> getDataSet() {
        ArrayList results = new ArrayList<FeeDueDetailsDataObject>();
        for (int index = 0; index < x; index++) {
            FeeDueDetailsDataObject obj = new FeeDueDetailsDataObject("Fee Due No: "+Date.get(index),"Issue Date: "+FeeFor.get(index),
                    "Due Date: "+TotalAmount.get(index),"Amount: "+TotalDue.get(index),"Remarks: "+TotalReceive.get(index));
            results.add(index, obj);
        }
        return results;
    }

    private void loadCardsData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        Integer str = GlobalVariables.id;
        String test=url+str+Key;
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
                        String date = jsonObject1.getString("fee_due_number");
                        String feeFor = jsonObject1.getString("issue_date");
                        String totalAmount = jsonObject1.getString("due_date");
                        String totalDue = jsonObject1.getString("amount");
                        String totalReceive = jsonObject1.getString("remarks");
                        //Integer balance = jsonObject1.getInt("Balance");
                        Date.add(date);
                        FeeFor.add(feeFor);
                        TotalAmount.add(totalAmount);
                        TotalDue.add(totalDue);
                        TotalReceive.add(totalReceive);
                        //Balance.add(balance);
                        x = x + 1;
                    }

                    mAdapter = new FeeDueDetailsRecyclerViewAdapter(getDataSet());
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

package com.sits.school360.ui.feeSummary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sits.school360.GlobalVariables;
import com.sits.school360.LoginActivity;
import com.sits.school360.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FeeSummaryActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int x;
    String URL="http://apischools360.sitslive.com/Api/FeeSummary?stuCode=";
    String Key = "&key="+GlobalVariables.schoolID;
//    ArrayList<String> Date;
//    ArrayList<String> FeeFor;
    ArrayList<Integer> TotalAmount;
    ArrayList<Integer> TotalDue;
    ArrayList<Integer> TotalReceive;
    ArrayList<Integer> Balance;

    private static String LOG_TAG = "CardViewActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_summary);
//        Date=new ArrayList<>();
//        FeeFor=new ArrayList<>();
        TotalAmount=new ArrayList<>();
        TotalReceive=new ArrayList<>();
        TotalDue=new ArrayList<>();
        Balance=new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        loadCardsData(URL);
    }

    private ArrayList<FeeSummaryDataObject> getDataSet() {
        ArrayList results = new ArrayList<FeeSummaryDataObject>();
        for (int index = 0; index < x; index++) {
            FeeSummaryDataObject obj = new FeeSummaryDataObject("Opening Balance : "+TotalAmount.get(index),"TCurrent Dues: "+
                    TotalReceive.get(index),"Current Received: "+TotalDue.get(index),"Remaining Balance: "+Balance.get(index));
            results.add(index, obj);
        }
        return results;
    }
    private void loadCardsData(String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        Integer str = GlobalVariables.id;
        String test=url+str+Key;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, test, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] arr=response.split("<string xmlns=\"http://tempuri.org/\">");
                String[] res2=arr[1].split("</string");
                String[] res = new String[]{"a", res2[0]};
                res[1] = "{\"name\":" + res[1] + "}";
                try{
                    JSONObject jsonObject=new JSONObject(res[1]);
                    JSONArray jsonArray=jsonObject.getJSONArray("name");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        //String date=jsonObject1.getString("Date");
                        //String feeFor=jsonObject1.getString("FeeFor");
                        Integer totalAmount=jsonObject1.getInt("OpeningBalance");
                        Integer totalDue=jsonObject1.getInt("CurrentDues");
                        Integer totalReceive=jsonObject1.getInt("CurrentReceived");
                        Integer balance=jsonObject1.getInt("RemainingBalance");
                        //Date.add(date);
                        //FeeFor.add(feeFor);
                        TotalAmount.add(totalAmount);
                        TotalDue.add(totalDue);
                        TotalReceive.add(totalReceive);
                        Balance.add(balance);
                        x=x+1;
                    }

                    mAdapter = new FeeSummaryRecyclerViewAdapter(getDataSet());
                    mRecyclerView.setAdapter(mAdapter);
                    //spinner.setAdapter(new ArrayAdapter<String>(FeeSummaryActivity.this, android.R.layout.simple_spinner_dropdown_item, SchoolNames));
                }catch (JSONException e){e.printStackTrace();}
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
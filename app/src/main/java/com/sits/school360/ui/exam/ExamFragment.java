package com.sits.school360.ui.exam;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sits.school360.R;
import com.sits.school360.ui.examSchedule.ExamScheduleRecyclerViewAdapter;
import com.sits.school360.ui.feeSummary.FeeSummaryRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExamFragment extends Fragment {

    public static final String TAG = "MyTag";
    RequestQueue requestQueue;
    ArrayList<String> FeeFor;
    public static String test="";
    public ExamFragment() {
        // Required empty public constructor
    }
    public static ExamFragment newInstance() {
        ExamFragment fragment = new ExamFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_exam, container, false);

        standardQueueStringRequest(v);

        // Inflate the layout for this fragment
        return v;
    }

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private void standardQueueStringRequest(View v) {
        final TextView mTextView = (TextView) v.findViewById(R.id.text_gallery);
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        //mLayoutManager = new LinearLayoutManager(this);
        //mRecyclerView.setLayoutManager(mLayoutManager);
        // StringRequest with VOLLEY with Standard RequestQueue
        // Instantiate the RequestQueue.
        requestQueue = Volley.newRequestQueue(v.getContext());
        String url = "http://45.115.62.5:89/AndroidAPI.asmx/GetExamScheduleDetails?classCode=";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String[] arr=response.split("<string xmlns=\"http://tempuri.org/\">");
                        String[] res2=arr[1].split("</");
                        String[] res = new String[]{"a", res2[0]};
                        res[1] = "{\"name\":" + res[1] + "}";
                        try{
                            JSONObject jsonObject=new JSONObject(res[1]);
                            JSONArray jsonArray=jsonObject.getJSONArray("name");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                String date=jsonObject1.getString("Date");
                                String feeFor=jsonObject1.getString("FeeFor");
                                Integer totalAmount=jsonObject1.getInt("TotalAmount");
                                Integer totalDue=jsonObject1.getInt("TotalDue");
                                Integer totalReceive=jsonObject1.getInt("TotalReceive");
                                Integer balance=jsonObject1.getInt("Balance");
                                test=feeFor;
                            }
//                            mAdapter = new ExamRecyclerViewAdapter(getDataSet());
//                            mRecyclerView.setAdapter(mAdapter);
                            //spinner.setAdapter(new ArrayAdapter<String>(FeeSummaryActivity.this, android.R.layout.simple_spinner_dropdown_item, SchoolNames));
                        }catch (JSONException e){e.printStackTrace();}
                        // Display the first 500 characters of the response string.
                        mTextView.setText("Response is: " + test);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });

        stringRequest.setTag(TAG);
        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll(TAG);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
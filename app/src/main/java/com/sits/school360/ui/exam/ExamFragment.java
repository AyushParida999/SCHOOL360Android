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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sits.school360.R;
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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Volley.
     */
    // TODO: Rename and change types and number of parameters
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

    private void standardQueueStringRequest(View v) {
        final TextView mTextView = (TextView) v.findViewById(R.id.text_gallery);

        // StringRequest with VOLLEY with Standard RequestQueue
        // Instantiate the RequestQueue.
        requestQueue = Volley.newRequestQueue(v.getContext());
        String url = "http://apischools360.sitslive.com/Api/Fee?stuCode=931&key=@@schools@@@@@@@@@3@@@@&schoolCodeKey=3";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String[] res= new String[]{"a",response};
                        res[1]="{\"name\":"+res[1]+"}";
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
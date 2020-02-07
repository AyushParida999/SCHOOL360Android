package com.sits.school360.ui.exam;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sits.school360.GlobalVariables;
import com.sits.school360.ui.feeDueDetails.FeeDueDetailsRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExamViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private String URL = "http://45.115.62.5:89/AndroidAPI.asmx/GetFeeDueDetails?StudentCode=3";

    public ExamViewModel() {
        loadCardsData(URL);
        mText = new MutableLiveData<>();
        mText.setValue("This is exam fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
    private void loadCardsData(String url) {
        Integer str = GlobalVariables.id;
        String test=url+str;
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
                        String date = jsonObject1.getString("fee_due_number");
                        String feeFor = jsonObject1.getString("issue_date");
                        String totalAmount = jsonObject1.getString("due_date");
                        Integer totalDue = jsonObject1.getInt("amount");
                        String totalReceive = jsonObject1.getString("remarks");
                        //Integer balance = jsonObject1.getInt("Balance");

                    }


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

    }
}
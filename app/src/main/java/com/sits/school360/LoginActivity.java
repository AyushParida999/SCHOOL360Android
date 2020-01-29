package com.sits.school360;


import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    Spinner spinner;
    EditText editTextUname;
    EditText editTextPws;
    Button login;
    String URL="http://apischools360.sitslive.com/Api/GetSchools?key=@@schools@@@@@@@@@3@@@@";
    String URLLogin="http://apischools360.sitslive.com/Api/Login?id=1&uname=";
    String SchoolCodeKey="&schoolCodeKey=";
    String Password="&pws=";
    String Key="&key=@@schools@@@@@@@@@3@@@@";
    ArrayList<String> SchoolNames;
    ArrayList<Integer> ID;
    ArrayList<String>temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SchoolNames=new ArrayList<>();
        ID=new ArrayList<>();
        spinner=(Spinner)findViewById(R.id.spinner);
        loadSpinnerData(URL);
        temp=new ArrayList<>();
        editTextUname=(EditText)findViewById(R.id.editText);
        editTextPws=(EditText)findViewById(R.id.editText2);
        login=(Button)findViewById((R.id.button));
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newURL=URLLogin+editTextUname.getText().toString()+SchoolCodeKey+ ID.get(spinner.getSelectedItemPosition())+Password+editTextPws.getText().toString()+Key;
                loginEvent(newURL,v);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String country= spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
                Toast.makeText(getApplicationContext(),country,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
    }
    private void loginEvent(final String url, final View view) {
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] res= new String[]{"a",response};
                res[1]="{\"name\":"+res[1]+"}";
                try{
                    JSONObject jsonObject=new JSONObject(res[1]);

                    JSONArray jsonArray=jsonObject.getJSONArray("name");
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String tempp = jsonObject1.getString("reg_no");
                        if (tempp.equals(editTextUname.getText().toString())) {

                            Intent intent = new Intent(view.getContext(), HomeActivity.class);
                            intent.putExtra("url",url);
                            view.getContext().startActivity(intent);
                        }
                        else{

                        }
                    }
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
    private void loadSpinnerData(String url) {
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] res= new String[]{"a",response};
                res[1]="{\"name\":"+res[1]+"}";
                try{
                    JSONObject jsonObject=new JSONObject(res[1]);

                        JSONArray jsonArray=jsonObject.getJSONArray("name");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String school=jsonObject1.getString("school_name");
                            Integer id=jsonObject1.getInt("school_code");
                            SchoolNames.add(school);
                            ID.add(id);
                        }
                    spinner.setAdapter(new ArrayAdapter<String>(LoginActivity.this, android.R.layout.simple_spinner_dropdown_item, SchoolNames));
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
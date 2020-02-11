package com.sits.school360.ui.homeDetails;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.util.Base64;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.sits.school360.GlobalVariables;
import com.sits.school360.LoginActivity;
import com.sits.school360.R;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    String URL="http://45.115.62.5:89/AndroidAPI.asmx/GetProfileDetails?studentCode=";
    private AppBarConfiguration mAppBarConfiguration;
    private int x;
    ArrayList<String> Date;
    ArrayList<String> FeeFor;
    ArrayList<String> TotalAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Date = new ArrayList<>();
        FeeFor = new ArrayList<>();
        TotalAmount = new ArrayList<>();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        loadCardsData(URL);
        NavigationView navigationView = findViewById(R.id.nav_view);


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_fee, R.id.nav_exam, R.id.nav_transport,
                R.id.nav_academics, R.id.nav_profile)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                GlobalVariables.id=null;
                new AlertDialog.Builder(this)
                        .setTitle("Logout")
                        .setMessage("Do you really want to Logout?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void loadCardsData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
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
                        String date = jsonObject1.getString("name");
                        String feeFor = jsonObject1. getString("phone");
                        String ImageInBit = jsonObject1.getString("image");
                        Date.add(date);
                        FeeFor.add(feeFor);
                        TotalAmount.add(ImageInBit);
//                        GlobalVariables.uname=date;
//                        GlobalVariables.uphone=feeFor;
                        x = x + 1;
                    }

                    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                    View hView =  navigationView.getHeaderView(0);
                    TextView nav_user = (TextView)hView.findViewById(R.id.name);
                    nav_user.setText(Date.get(0));
                    TextView nav_contact=(TextView)hView.findViewById(R.id.name2);
                    nav_contact.setText(FeeFor.get(0));

                    ImageView imageView=(ImageView) hView.findViewById(R.id.imageView);
                    byte[] decodedString = Base64.decode(TotalAmount.get(0), Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    //GlobalVariables.uimage=decodedByte;
                    imageView.setImageBitmap(decodedByte);

                    ConstraintLayout constraintLayout=(ConstraintLayout)findViewById(R.id.homeLayout);
                    View v=constraintLayout.getViewById(R.id.homeLayout);
                    TextView nav_user2 = (TextView)hView.findViewById(R.id.user_name);
                    nav_user.setText(Date.get(0));
                    TextView nav_contact2=(TextView)hView.findViewById(R.id.user_id);
                    nav_contact.setText(FeeFor.get(0));
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

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}

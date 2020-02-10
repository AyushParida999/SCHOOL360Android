package com.sits.school360.ui.exam;

import android.content.Context;
import android.content.Intent;
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
import androidx.cardview.widget.CardView;
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
import com.sits.school360.ui.examReport.ExamReportActivity;
import com.sits.school360.ui.examSchedule.ExamScheduleActivity;
import com.sits.school360.ui.examSchedule.ExamScheduleRecyclerViewAdapter;
import com.sits.school360.ui.feeDueDetails.FeeDueDetailsActivity;
import com.sits.school360.ui.feePaidDetails.FeePaidDetailsActivity;
import com.sits.school360.ui.feeSummary.FeeSummaryActivity;
import com.sits.school360.ui.feeSummary.FeeSummaryRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExamFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_exam, container, false);
        CardView personalDetails=v.findViewById(R.id.FeeSummary);
        personalDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ExamScheduleActivity.class);
                getActivity().startActivity(intent);
            }
        });
        CardView feeDueDetails=v.findViewById(R.id.FeeDueDetails);
        feeDueDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ExamReportActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return v;
    }}
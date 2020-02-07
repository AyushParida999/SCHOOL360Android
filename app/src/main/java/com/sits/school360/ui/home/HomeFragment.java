package com.sits.school360.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sits.school360.AttendanceActivity;
import com.sits.school360.FeePaymentActivity;
import com.sits.school360.MainActivity;
import com.sits.school360.R;
import com.sits.school360.ui.examSchedule.ExamScheduleActivity;
import com.sits.school360.ui.feeDueDetails.FeeDueDetailsActivity;
import com.sits.school360.ui.feePaidDetails.FeePaidDetailsActivity;
import com.sits.school360.ui.feeSummary.FeeSummaryActivity;
import com.sits.school360.ui.transportDetails.TransportActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        CardView personalDetails=v.findViewById(R.id.PersonalDetails);
        personalDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeeSummaryActivity.class);
                getActivity().startActivity(intent);
            }
        });
        CardView feeDueDetails=v.findViewById(R.id.feeDueDetails);
        feeDueDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeeDueDetailsActivity.class);
                getActivity().startActivity(intent);
            }
        });
        CardView examSchedule=v.findViewById(R.id.examSchedule);
        examSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ExamScheduleActivity.class);
                getActivity().startActivity(intent);
            }
        });
        CardView transport=v.findViewById(R.id.transport);
        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TransportActivity.class);
                getActivity().startActivity(intent);
            }
        });
       /* Button feeSummary = v.findViewById(R.id.feeSummary);
        feeSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeeSummaryActivity.class);
                getActivity().startActivity(intent);
            }
        });
        Button feePayment = v.findViewById(R.id.feePayment);
        feePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeePaymentActivity.class);
                getActivity().startActivity(intent);
            }
        });
        Button feePaidDetails = v.findViewById(R.id.feePaidDetails);
        feePaidDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeePaidDetailsActivity.class);
                getActivity().startActivity(intent);
            }
        });
        Button feeDueDetails = v.findViewById(R.id.feeDueDetails);
        feeDueDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeeDueDetailsActivity.class);
                getActivity().startActivity(intent);
            }
        });
        Button attendance = v.findViewById(R.id.attendance);
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AttendanceActivity.class);
                getActivity().startActivity(intent);
            }
        });
        Button transport = v.findViewById(R.id.transport);
        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TransportActivity.class);
                getActivity().startActivity(intent);
            }
        });*/
        return v;
    }
}
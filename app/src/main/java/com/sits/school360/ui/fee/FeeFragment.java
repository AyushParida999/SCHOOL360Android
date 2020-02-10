package com.sits.school360.ui.fee;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sits.school360.R;
import com.sits.school360.ui.examSchedule.ExamScheduleActivity;
import com.sits.school360.ui.feeDueDetails.FeeDueDetailsActivity;
import com.sits.school360.ui.feePaidDetails.FeePaidDetailsActivity;
import com.sits.school360.ui.feeSummary.FeeSummaryActivity;

public class FeeFragment extends Fragment {

    private FeeViewModel feeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fee, container, false);
        CardView feeSummary=v.findViewById(R.id.FeeSummary);
        feeSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeeSummaryActivity.class);
                getActivity().startActivity(intent);
            }
        });
        CardView feeDueDetails=v.findViewById(R.id.FeeDueDetails);
        feeDueDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeeDueDetailsActivity.class);
                getActivity().startActivity(intent);
            }
        });
        CardView feeReceiveDetails=v.findViewById(R.id.FeeReceiveDetails);
        feeReceiveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeePaidDetailsActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return v;
    }
}
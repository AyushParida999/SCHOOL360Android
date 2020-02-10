package com.sits.school360.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sits.school360.AttendanceActivity;
import com.sits.school360.FeePaymentActivity;
import com.sits.school360.GlobalVariables;
import com.sits.school360.MainActivity;
import com.sits.school360.R;
import com.sits.school360.ui.academics.AcademicsFragment;
import com.sits.school360.ui.exam.ExamFragment;
import com.sits.school360.ui.examSchedule.ExamScheduleActivity;
import com.sits.school360.ui.feeDueDetails.FeeDueDetailsActivity;
import com.sits.school360.ui.feePaidDetails.FeePaidDetailsActivity;
import com.sits.school360.ui.feeSummary.FeeSummaryActivity;
import com.sits.school360.ui.profile.ProfileFragment;
import com.sits.school360.ui.transportDetails.TransportActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

//        TextView uname=(TextView)v.findViewById(R.id.user_name);
//        uname.setText(GlobalVariables.uname);
//        TextView uphone=(TextView)v.findViewById(R.id.user_id);
//        uphone.setText(GlobalVariables.uphone);
//
//
//        ImageView uimagee=(ImageView)v.findViewById(R.id.user_photo);
//        uimagee.setImageBitmap(GlobalVariables.uimage);


        CardView personalDetails=v.findViewById(R.id.PersonalDetails);
        personalDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileFragment.class);
                getActivity().startActivity(intent);
            }
        });
        CardView fee=v.findViewById(R.id.fee);
        fee.setOnClickListener(new View.OnClickListener() {
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
                Intent intent = new Intent(getActivity(), ExamFragment.class);
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
        CardView academics=v.findViewById(R.id.academics);
        academics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AcademicsFragment.class);
                getActivity().startActivity(intent);
            }
        });
        CardView homework=v.findViewById(R.id.homework);
        homework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TransportActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return v;
    }
}
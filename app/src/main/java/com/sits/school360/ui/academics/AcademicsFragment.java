package com.sits.school360.ui.academics;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.sits.school360.ui.myAttendance.AttendanceActivity;
import com.sits.school360.EventsActivity;
import com.sits.school360.MyNoticesActivity;
import com.sits.school360.R;
import com.sits.school360.ui.homeworkDetails.HomeworkActivity;
import com.sits.school360.ui.subjectDetails.SubjectDetailsActivity;
import com.sits.school360.ui.teachersDetails.MyTeachersActivity;
import com.sits.school360.ui.timetableDetails.TimeTableActivity;

public class AcademicsFragment extends Fragment {

    private AcademicsViewModel academicsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_academics, container, false);
        CardView attendance=v.findViewById(R.id.Attendance);
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AttendanceActivity.class);
                getActivity().startActivity(intent);
            }
        });
        CardView timeTable=v.findViewById(R.id.TimeTable);
        timeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TimeTableActivity.class);
                getActivity().startActivity(intent);
            }
        });
        CardView notices=v.findViewById(R.id.Notices);
        notices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyNoticesActivity.class);
                getActivity().startActivity(intent);
            }
        });
        CardView myTeachers=v.findViewById(R.id.MyTeachers);
        myTeachers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyTeachersActivity.class);
                getActivity().startActivity(intent);
            }
        });
        CardView homework=v.findViewById(R.id.Homework);
        homework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeworkActivity.class);
                getActivity().startActivity(intent);
            }
        });
        CardView events=v.findViewById(R.id.Events);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SubjectDetailsActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return v;
    }
}
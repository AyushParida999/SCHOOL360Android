package com.sits.school360.ui.teachers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sits.school360.R;

public class TeachersFragment extends Fragment {

    private TeachersViewModel teachersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        teachersViewModel =
                ViewModelProviders.of(this).get(TeachersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_teachers, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        teachersViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
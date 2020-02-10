package com.sits.school360.ui.academics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.sits.school360.R;

public class AcademicsFragment extends Fragment {

    private AcademicsViewModel academicsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        academicsViewModel =
                ViewModelProviders.of(this).get(AcademicsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_academics, container, false);
 /*       final TextView textView = root.findViewById(R.id.text_share);
        academicsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}
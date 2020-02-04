package com.sits.school360.ui.fee;

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

public class FeeFragment extends Fragment {

    private FeeViewModel feeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        feeViewModel =
                ViewModelProviders.of(this).get(FeeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_attendance, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        feeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
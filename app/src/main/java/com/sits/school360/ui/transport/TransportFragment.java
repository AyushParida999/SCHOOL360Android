package com.sits.school360.ui.transport;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sits.school360.R;

public class TransportFragment extends Fragment {

    private TransportViewModel transportViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        transportViewModel =
                ViewModelProviders.of(this).get(TransportViewModel.class);
        View root = inflater.inflate(R.layout.fragment_transport, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        transportViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
package com.taskapp.onboard;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.taskapp.MainActivity;
import com.taskapp.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoardFragment extends Fragment {
    Button startButton, skipButton;

    public BoardFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_board, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textTitle = view.findViewById(R.id.textTitle);
        startButton = view.findViewById(R.id.start_button);
        skipButton = view.findViewById(R.id.skip_button);

        int pos = getArguments().getInt("pos");
        switch (pos) {
            case 0:
                view.setBackgroundColor(Color.GRAY);
                imageView.setImageResource(R.drawable.back1);
                imageView.setImageResource(R.drawable.apple1);
                textTitle.setText("Это яблоко целое");
                break;
            case 1:
                view.setBackgroundColor(Color.RED);
                imageView.setImageResource(R.drawable.apple2);
                textTitle.setText("Это яблоко покусанное");
                break;
            case 2:
                view.setBackgroundColor(Color.GREEN);
                imageView.setImageResource(R.drawable.apple3);
                textTitle.setText("Это яблоко сьеденоое");
                break;
        }
        startButton.setVisibility(pos == 2 ? View.VISIBLE : View.GONE);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getActivity().getSharedPreferences("settings", MODE_PRIVATE);
                preferences.edit().putBoolean("isShown", true).apply();
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
                Toast.makeText(getContext(), "Ты кликнул на меня", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}

package com.example.happy_farm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.happy_farm.R;

public class user_fragment extends Fragment {
    private TextView userNameView;
    private ImageView userImageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userNameView = view.findViewById(R.id.Name_user);
        userImageView = view.findViewById(R.id.Image_user);
        userNameView.setText("lyx ");
        userImageView.setImageResource(R.drawable.ico_bar1);
    }
}

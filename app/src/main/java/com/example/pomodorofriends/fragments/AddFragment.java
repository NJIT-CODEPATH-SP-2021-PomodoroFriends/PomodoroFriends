package com.example.pomodorofriends.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.pomodorofriends.R;
import com.example.pomodorofriends.Timer;
import com.ikovac.timepickerwithseconds.MyTimePickerDialog;
import com.ikovac.timepickerwithseconds.TimePicker;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {

    private MyTimePickerDialog activityTimer;
    private MyTimePickerDialog breakTimer;

    private Button btnActivityTime;
    private Button btnBreakTime;


    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activityTimer = new MyTimePickerDialog(getContext(), new MyTimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute, int seconds) {
				/*time.setText(getString(R.string.time) + String.format("%02d", hourOfDay)+
						":" + String.format("%02d", minute) +
						":" + String.format("%02d", seconds));	*/
            }
        }, 0, 0, 0, true);
        breakTimer = new MyTimePickerDialog(getContext(), new MyTimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute, int seconds) {
				/*time.setText(getString(R.string.time) + String.format("%02d", hourOfDay)+
						":" + String.format("%02d", minute) +
						":" + String.format("%02d", seconds));	*/
            }
        }, 0, 0, 0, true);
        //mTimePicker.show();
        btnActivityTime = view.findViewById(R.id.btnActivityTime);
        btnActivityTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityTimer.show();
            }
        });

        btnBreakTime = view.findViewById(R.id.btnBreakTime);
        btnBreakTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                breakTimer.show();
            }
        });

    }


    private void saveTimer(String description, ParseUser currentUser, File photoFile) {
        Timer timer = new Timer();
        //timer.setUser(currentUser);

    }
}
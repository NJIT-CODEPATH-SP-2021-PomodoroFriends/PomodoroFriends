package com.example.pomodorofriends.fragments;

import android.app.MediaRouteButton;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

    private static final String TAG = "AddFragment";

    private MyTimePickerDialog activityTimer;
    private MyTimePickerDialog breakTimer;

    private ProgressBar loadingProgressBar;
    private EditText etCaption;
    private EditText etPeriod;
    private Button btnActivityTime;
    private Button btnBreakTime;
    private Button btnSaveTimer;

    private int activityTime = 0;
    private int breakTime = 0;

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
        etCaption = view.findViewById(R.id.etCaption);
        etPeriod = view.findViewById(R.id.etPeriod);
        btnActivityTime = view.findViewById(R.id.btnActivityTime);
        btnBreakTime = view.findViewById(R.id.btnBreakTime);
        btnSaveTimer = view.findViewById(R.id.btnSaveTimer);
        loadingProgressBar = view.findViewById(R.id.loading);

        activityTimer = new MyTimePickerDialog(getContext(), new MyTimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hour, int minute, int seconds) {
				/*time.setText(getString(R.string.time) + String.format("%02d", hourOfDay)+
						":" + String.format("%02d", minute) +
						":" + String.format("%02d", seconds));	*/
                activityTime = (hour*60*60)+(minute*60)+seconds;
            }
        }, 0, 0, 0, true);
        breakTimer = new MyTimePickerDialog(getContext(), new MyTimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hour, int minute, int seconds) {
                breakTime = (hour*60*60)+(minute*60)+seconds;
            }
        }, 0, 0, 0, true);


        btnActivityTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityTimer.show();
            }
        });


        btnBreakTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                breakTimer.show();
            }
        });
        btnSaveTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String caption = etCaption.getText().toString();
                if(caption.isEmpty()){
                    Toast.makeText(getContext(), "Caption cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                int period = Integer.parseInt(etPeriod.getText().toString());
                if(period > 20){
                    Toast.makeText(getContext(), "No more than 20 repetitions", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(period == 0){
                    Toast.makeText(getContext(), "At least one repetition needed", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(activityTime < 5){
                    Toast.makeText(getContext(), "Activity time has to be more than 5 seconds", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(breakTime < 5){
                    Toast.makeText(getContext(), "Break time has to be more than 5 seconds", Toast.LENGTH_SHORT).show();
                    return;
                }
                ParseUser currentUser = ParseUser.getCurrentUser();
                loadingProgressBar.setVisibility(View.VISIBLE);
                saveTimer(caption, period, activityTime, breakTime, currentUser);
            }
        });

    }

    private void saveTimer(String caption, int period, int breakTimer, int activityTimer, ParseUser currentUser) {
        Timer timer = new Timer();
        timer.setCaption(caption);
        timer.setPeriod(period);
        timer.setBreakTimer(breakTimer);
        timer.setActivityTimer(activityTimer);
        timer.setUser(currentUser);
        timer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                loadingProgressBar.setVisibility(View.INVISIBLE);
                if (e != null) {
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(getContext(), "Error while saving", Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "Post saved successfuly");
                Toast.makeText(getContext(), "Timer saved!", Toast.LENGTH_SHORT).show();

                etCaption.setText("");
                etPeriod.setText("");
            }
        });

        //Return to basket
        //this.getActivity().onBackPressed();
    }
}
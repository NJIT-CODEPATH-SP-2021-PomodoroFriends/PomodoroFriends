package com.example.pomodorofriends.fragments;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.pomodorofriends.R;
import com.example.pomodorofriends.Timer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimerActionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimerActionFragment extends Fragment {

    private static final String TAG = "TimerActionFragment";
    private TextView tvTimer;
    private TextView tvTimerCaption;
    private TextView tvTimerPeriod;
    private FrameLayout flTimerAction;
    private Button btnTimerStart;

    private int activityTime;
    private int breakTime;
    private int period;
    private int current;
    private String caption;
    private CountDownTimer countDownActivity;
    private CountDownTimer countDownBreak;

    private boolean started = false;
    public TimerActionFragment() {
        // Required empty public constructor
    }


    public static TimerActionFragment newInstance(String param1, String param2) {
        TimerActionFragment fragment = new TimerActionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        activityTime = 60*1000;
        breakTime = 10*1000;
        caption = "Basic Activity";
        period = 4;

        if( getArguments() != null){
            activityTime =  getArguments().getInt("activityTime")*1000;
            breakTime =  getArguments().getInt("breakTime")*1000;
            caption =  getArguments().getString("caption");
            period =  getArguments().getInt("period");
        }
        current = period;
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_timer_action, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //String msg = getArguments().getString("msg",null);
        //Log.i(TAG, msg);

        tvTimer = view.findViewById(R.id.tvTimer);
        tvTimerCaption = view.findViewById(R.id.tvTimerCaption);
        tvTimerPeriod = view.findViewById(R.id.tvTimerPeriod);
        flTimerAction = view.findViewById(R.id.flTimerAction);
        btnTimerStart = view.findViewById(R.id.btnTimerStart);
        tvTimerCaption.setText(caption);
        tvTimerPeriod.setText(current+"/"+period);
        String activityTimeFormat = Timer.format(activityTime/1000);
        //Log.i(TAG, activityTimeFormat);
        tvTimer.setText(activityTimeFormat);
        if(started){
            btnTimerStart.setVisibility(View.INVISIBLE);
        }
        btnTimerStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity();
                started = true;
                btnTimerStart.setVisibility(View.INVISIBLE);
            }
        });


    }

    private void startActivity(){
        Log.i(TAG, String.valueOf(activityTime));

        tvTimerCaption.setText(caption);
        tvTimerPeriod.setText(current+"/"+period);
        Drawable flDrawable = flTimerAction.getBackground();
        flDrawable = DrawableCompat.wrap(flDrawable);
        //the color is a direct color int and not a color resource
        DrawableCompat.setTint(flDrawable, getResources().getColor(R.color.red_500));
        flTimerAction.setBackground(flDrawable);
        countDownActivity = new CountDownTimer(activityTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String activityTimeFormat = Timer.format(millisUntilFinished/1000);
                //Log.i(TAG, activityTimeFormat);
                tvTimer.setText(activityTimeFormat);
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "Finished timer");
                startBreak();
            }
        };
        countDownActivity.start();
        //countDownActivity.cancel();
    }


    private void startBreak(){
        tvTimerCaption.setText("Take a Break!");
        Drawable flDrawable = flTimerAction.getBackground();
        flDrawable = DrawableCompat.wrap(flDrawable);
        //the color is a direct color int and not a color resource
        DrawableCompat.setTint(flDrawable, getResources().getColor(R.color.green_400));
        flTimerAction.setBackground(flDrawable);

        countDownBreak = new CountDownTimer(breakTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String activityTimeFormat = Timer.format(millisUntilFinished/1000);
                //Log.i(TAG, activityTimeFormat);
                tvTimer.setText(activityTimeFormat);
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "Finished timer");
                current--;
                if(current == 0) {
                    tvTimerPeriod.setText(current+"/"+period);
                    tvTimerCaption.setText("You're finished!!");
                    Drawable flDrawable = flTimerAction.getBackground();
                    flDrawable = DrawableCompat.wrap(flDrawable);
                    //the color is a direct color int and not a color resource
                    DrawableCompat.setTint(flDrawable, getResources().getColor(R.color.red_500));
                    flTimerAction.setBackground(flDrawable);
                    btnTimerStart.setText("Reset");
                    btnTimerStart.setVisibility(View.VISIBLE);
                    btnTimerStart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            reset();
                            btnTimerStart.setVisibility(View.INVISIBLE);
                        }
                    });
                    return;
                }
                startActivity();
            }
        };
        countDownBreak.start();
        //countDownActivity.cancel();
    }

    private void reset(){
        current = period;
        tvTimerCaption.setText(caption);
        tvTimerPeriod.setText(current+"/"+period);
        String activityTimeFormat = Timer.format(activityTime/1000);
        //Log.i(TAG, activityTimeFormat);
        tvTimer.setText(activityTimeFormat);
        startActivity();
    }


}
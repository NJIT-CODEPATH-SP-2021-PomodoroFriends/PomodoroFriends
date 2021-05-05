package com.example.pomodorofriends.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pomodorofriends.R;
import com.example.pomodorofriends.Timer;
import com.example.pomodorofriends.TimerAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

//TODO: REPLACE username
    //GIVE THANKYOu TO USER CREDITS AND SPECIAL THANKS TO CODEPATH
    //AVATAR FROM BOLD LETTER OF USERNAME



public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";
    private RecyclerView rvMyPosts;
    protected List<Timer> allTimers;
    protected TimerAdapter adapter;

    private TextView userText;
    private TextView thankYouText;
    private TextView userFirstChar;

    private static final String thankYouMessage = "Thank you to Codepath for making this possible, and thank you to YOU for using our app.";

    public ProfileFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userText = view.findViewById(R.id.userField);
        userFirstChar = view.findViewById(R.id.tvUserFirstChar);
        //rvMyPosts = view.findViewById(R.id.rvTimers);

        //allTimers = new ArrayList<Timer>();
        //adapter = new TimerAdapter(getContext(), allTimers);

        populatePage();


    }

    private void populatePage() {
        userText.setText(ParseUser.getCurrentUser().getUsername());
        //thankYouText.setText(thankYouMessage);
       // rvMyPosts.setAdapter(adapter);
        //rvMyPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        //queryMyPosts();
        userFirstChar.setText("" + ParseUser.getCurrentUser().getUsername().charAt(0)); // It did not like trying to stick a char in there, so had to convert it to a string
    }

    protected void queryMyPosts(){
        ParseQuery<Timer> query = ParseQuery.getQuery(Timer.class);
        query.include(Timer.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Timer.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Timer>() {
            @Override
            public void done(List<Timer> timers, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Couldn't fetch posts", e);
                }

                for(Timer post: timers){
                    Log.i(TAG, "Post "+ post.getCaption());
                }
                // Remember to CLEAR OUT old items before appending in the new ones
                adapter.clear();
                // ...the data has come back, add new items to your adapter...
                adapter.addAll(timers);

            }
        });
    }

}
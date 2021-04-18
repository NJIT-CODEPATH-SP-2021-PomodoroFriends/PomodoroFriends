package com.example.pomodorofriends.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pomodorofriends.R;
import com.example.pomodorofriends.Timer;
import com.example.pomodorofriends.TimerAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class BasketFragment extends Fragment {

    private static final String TAG = "BasketFragment";
    private RecyclerView rvPosts;
    protected List<Timer> allTimers;
    protected TimerAdapter adapter;

    public BasketFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPosts = view.findViewById(R.id.rvTimers);
        allTimers = new ArrayList<Timer>();
        adapter = new TimerAdapter(getContext(), allTimers);

        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));

        queryPosts();

    }

    protected void queryPosts(){
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
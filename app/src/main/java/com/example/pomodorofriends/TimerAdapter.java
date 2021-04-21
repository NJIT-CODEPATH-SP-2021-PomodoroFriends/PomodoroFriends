package com.example.pomodorofriends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.util.List;

public class TimerAdapter extends RecyclerView.Adapter<TimerAdapter.ViewHolder> {

    private Context context;
    private List<Timer> timers;

    public TimerAdapter(Context context, List<Timer> timers){
        this.context = context;
        this.timers = timers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_timer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Timer timer = timers.get(position);
        holder.bind(timer);
    }

    @Override
    public int getItemCount() {
        return timers.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        this.timers.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Timer> posts) {
        this.timers.addAll(posts);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvCaption;
        TextView tvUsername;
        TextView tvPeriod;
        Chip chActivity;
        Chip chBreak;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCaption = itemView.findViewById(R.id.tvCaption);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvPeriod = itemView.findViewById(R.id.tvPeriod);
            chActivity = itemView.findViewById(R.id.chActivity);
            chBreak = itemView.findViewById(R.id.chBreak);
        }

        public void bind(Timer timer) {
            tvCaption.setText(timer.getCaption());
            tvUsername.setText(timer.getUser().getUsername());
            tvPeriod.setText(timer.getPeriod()+" reps");
            chActivity.setText(Timer.format(timer.getActivityTimer()));
            chBreak.setText(Timer.format(timer.getBreakTimer()));
        }
    }
}

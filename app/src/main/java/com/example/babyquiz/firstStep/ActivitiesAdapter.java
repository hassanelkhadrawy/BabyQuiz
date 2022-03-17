package com.example.babyquiz.firstStep;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babyquiz.R;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.AdapterViewHolder> {
    OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activities, parent, false);
        return new AdapterViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.activityTitle.setText("نشاط " + (position + 1));
    }

    @Override
    public int getItemCount() {
        return 14;
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView activityTitle;

        public AdapterViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            activityTitle = itemView.findViewById(R.id.activity_num);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                            listener.onItemClick(position);
                    }
                }
            });
        }
    }
}

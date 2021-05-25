
package com.example.taskmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    public List<Task> tasks=new ArrayList<Task>();
    private OnTaskListner onTaskListner;
    public TaskAdapter(List<Task> tasks , OnTaskListner onTaskListner){
        this.tasks=tasks;
        this.onTaskListner=onTaskListner;

    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Task task;
        private TextView title;
        private TextView body;
        private OnTaskListner onTaskListner;
        public ViewHolder(View view,OnTaskListner onTaskListner) {
            super(view);
            this.title = view.findViewById(R.id.taskTitle);
            this.body = view.findViewById(R.id.taskBody);
            this.onTaskListner=onTaskListner;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onTaskListner.onTaskListner(getAdapterPosition());
        }
    }
    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.fragment_task, parent, false);

        ViewHolder viewHolder = new ViewHolder(listItem,onTaskListner);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        Task task= tasks.get(position);
        holder.title.setText(task.getTitle());
        holder.body.setText(task.getBody());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public interface OnTaskListner{
        void onTaskListner(int position);
    }
}

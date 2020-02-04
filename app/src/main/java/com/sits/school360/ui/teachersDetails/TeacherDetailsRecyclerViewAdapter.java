package com.sits.school360.ui.teachersDetails;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sits.school360.R;
import com.sits.school360.ui.examSchedule.ExamScheduleDataObject;

import java.util.ArrayList;

public class TeacherDetailsRecyclerViewAdapter extends RecyclerView.Adapter<TeacherDetailsRecyclerViewAdapter.DataObjectHolder> {
    private static String LOG_TAG = "FeeSummaryRecyclerViewAdapter";
    private ArrayList<TeacherDetailsDataObject> mDataset;
    private static MyClickListener myClickListener;
    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView date;
        TextView feeFor;
        TextView totalAmount;
        TextView totalReceived;
        TextView totalDue;
        TextView balance;
        public DataObjectHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
            feeFor = (TextView) itemView.findViewById(R.id.feeFor);
            totalAmount = (TextView) itemView.findViewById(R.id.totalAmount);
            totalReceived = (TextView) itemView.findViewById(R.id.totalReceive);
            totalDue = (TextView) itemView.findViewById(R.id.totalDue);
            balance = (TextView) itemView.findViewById(R.id.balance);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
    public TeacherDetailsRecyclerViewAdapter(ArrayList<TeacherDetailsDataObject> myDataset) {
        mDataset = myDataset;
    }
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fee_summary_cards, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }
    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.date.setText(mDataset.get(position).getmDate());
        holder.feeFor.setText(mDataset.get(position).getmFeeFor());
        holder.totalAmount.setText(mDataset.get(position).getmTotalAmount());
        holder.totalReceived.setText(mDataset.get(position).getmTotalReceive());
        holder.totalDue.setText(mDataset.get(position).getmTotalDue());
        holder.balance.setText(mDataset.get(position).getmBalance());
    }
    public void addItem(TeacherDetailsDataObject dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }
    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
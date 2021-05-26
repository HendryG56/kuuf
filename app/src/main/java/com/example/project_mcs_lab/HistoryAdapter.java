package com.example.project_mcs_lab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private Context cntx;
    private ArrayList<History> history;
    private OnItemClickListener translistener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        translistener = listener;
    }

    public HistoryAdapter(Context cntx) {
        this.cntx = cntx;
    }

    public void setHistory(ArrayList<History> history) {
        this.history = history;
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cntx).inflate(R.layout.transaction_history, parent, false);
        return new HistoryViewHolder(view, translistener);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, int position) {
        holder.productname.setText(history.get(position).getProduct_name());
        holder.transactiondate.setText(history.get(position).getTr_date());
        holder.productprice.setText("Rp. " + String.valueOf(history.get(position).getProduct_price()) + ",00");
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{
        TextView productname;
        TextView transactiondate;
        TextView productprice;
        Button deletetransaction;
        public HistoryViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            productname = itemView.findViewById(R.id.productname);
            transactiondate = itemView.findViewById(R.id.transactiondate);
            productprice = itemView.findViewById(R.id.productprice);
            deletetransaction = itemView.findViewById(R.id.btndelete);

            deletetransaction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}


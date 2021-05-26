package com.example.project_mcs_lab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private Context cntx;
    private ArrayList<Game> games;
    Integer product_id;
    Integer userid;

    public GameAdapter(Context cntx) {
        this.cntx = cntx;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @NonNull
    @Override
    public GameAdapter.GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cntx).inflate(R.layout.game_product, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameAdapter.GameViewHolder holder, final int position) {
        holder.GameName.setText(games.get(position).getGamename());
        holder.MinPlayer.setText("Min Player: " + String.valueOf(games.get(position).getMinplayer()));
        holder.MaxPlayer.setText("Max Player: " + String.valueOf(games.get(position).getMaxplayer()));
        holder.GamePrice.setText("Price: Rp." +String.valueOf(games.get(position).getGameprice()) + ",00");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product_id = games.get(position).getProduct_id();
                Intent intent = new Intent(cntx, ProductDetailPage.class);
                intent.putExtra("idproduct", product_id);
                intent.putExtra("iduser", userid);
                cntx.startActivity(intent);
                ((Activity)cntx).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public class GameViewHolder extends RecyclerView.ViewHolder{
        TextView GameName;
        TextView MinPlayer;
        TextView MaxPlayer;
        TextView GamePrice;
        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            GameName = itemView.findViewById(R.id.gameproductname);
            MinPlayer = itemView.findViewById(R.id.minplayer);
            MaxPlayer = itemView.findViewById(R.id.maxplayer);
            GamePrice = itemView.findViewById(R.id.gameproductprice);
        }
    }
}

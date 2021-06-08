package com.example.project_mcs_lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StorePage extends AppCompatActivity {

    RecyclerView gamesview;
    ArrayList<Game> games =new ArrayList<>();
    int userid;
    GameDB gameDB;
    int count;
    int check = 0;
    ArrayList<Account> accounts = new ArrayList<>();
    ArrayList<Transaction> transactions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_page);

        gameDB = new GameDB(this);

        gamesview = findViewById(R.id.store_view);

        Intent intent = getIntent();
        userid = intent.getIntExtra("USER_ID_STORE", 0);

        final GameAdapter gameadp =new GameAdapter(this);
        gameadp.setGames(games);
        gameadp.setUserid(userid);

        gamesview.setAdapter(gameadp);
        gamesview.setLayoutManager(new LinearLayoutManager(this));

        count = gameDB.countTableSize();
        if(count == 0) {
            /*Game game1 = new Game();
            game1.setGamename("Exploding Kitten");
            game1.setMinplayer(2);
            game1.setMaxplayer(5);
            game1.setGameprice(250000);
            game1.setLongitude(106.265139);
            game1.setLatitude(-6.912035);
            gameDB.insertgame(game1);*/

            /*{
                "name": "Card Against Humanity",
                "min_player": 2,
                "max_player": 4,
                "price": 182500,
                "created_at": "9/8/2014",
                "latitude": "-7.586037",
                "longitude": "108.126810"
            }*/

            RequestQueue requestQueue = Volley.newRequestQueue(StorePage.this);
            String url = "https://api.jsonbin.io/b/5eb51c6947a2266b1474d701/7";

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray items = response.getJSONArray("items");
                        int len = items.length();

                        for(int i = 0; i < len; i++){
                            JSONObject item = items.getJSONObject(i);

                            Game game = new Game();
                            game.setGamename(item.getString("name"));
                            game.setMinplayer(item.getInt("min_player"));
                            game.setMaxplayer(item.getInt("max_player"));
                            game.setGameprice(item.getInt("price"));
                            game.setLatitude(item.getDouble("latitude"));
                            game.setLongitude(item.getDouble("longitude"));

                            gameDB.insertgame(game);
                            games.add(game);
                            gameadp.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("apiData", "Error calling api");
                }
            });

            requestQueue.add(jsonObjectRequest);

            check = 1;
        } else{
            for(int i = 1; i <= count; i++){
                Game game = gameDB.getGame(i);
                games.add(game);
                gameadp.notifyDataSetChanged();
            }
        }
        count = gameDB.countTableSize();
        for(int i = 1; i <= count; i++){
            Game game = gameDB.getGame(i);
            games.add(game);
            gameadp.notifyDataSetChanged();
        }
    }
}
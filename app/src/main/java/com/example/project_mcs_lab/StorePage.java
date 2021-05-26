package com.example.project_mcs_lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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

        count = gameDB.countTableSize();
        if(count == 0) {
            Game game1 = new Game();
            game1.setGamename("Exploding Kitten");
            game1.setMinplayer(2);
            game1.setMaxplayer(5);
            game1.setGameprice(250000);
            game1.setLongitude(106.265139);
            game1.setLatitude(-6.912035);
            gameDB.insertgame(game1);
            games.add(game1);

            Game game2 = new Game();
            game2.setGamename("Card Against Humanity");
            game2.setMinplayer(2);
            game2.setMaxplayer(4);
            game2.setGameprice(182500);
            game2.setLongitude(108.126810);
            game2.setLatitude(-7.586037);
            gameDB.insertgame(game2);
            games.add(game2);

            Game game3 = new Game();
            game3.setGamename("Bang Dice Game");
            game3.setMinplayer(3);
            game3.setMaxplayer(8);
            game3.setGameprice(355000);
            game3.setLongitude(103.806584);
            game3.setLatitude(-5.345676);
            gameDB.insertgame(game3);
            games.add(game3);

            Game game4 = new Game();
            game4.setGamename("Arkham Horror");
            game4.setMinplayer(3);
            game4.setMaxplayer(8);
            game4.setGameprice(250000);
            game4.setLongitude(101.789556);
            game4.setLatitude(3.743289);
            gameDB.insertgame(game4);
            games.add(game4);

            Game game5 = new Game();
            game5.setGamename("The Dark Moon");
            game5.setMinplayer(2);
            game5.setMaxplayer(7);
            game5.setGameprice(560000);
            game5.setLongitude(108.890254);
            game5.setLatitude(-6.782312);
            gameDB.insertgame(game5);
            games.add(game5);

            Game game6 = new Game();
            game6.setGamename("Pandemic");
            game6.setMinplayer(2);
            game6.setMaxplayer(5);
            game6.setGameprice(1250000);
            game6.setLongitude(104.804334);
            game6.setLatitude(1.816432);
            gameDB.insertgame(game6);
            games.add(game6);

            Game game7 = new Game();
            game7.setGamename("The Werewolf Ultimate");
            game7.setMinplayer(5);
            game7.setMaxplayer(12);
            game7.setGameprice(325000);
            game7.setLongitude(106.632134);
            game7.setLatitude(-6.890323);
            gameDB.insertgame(game7);
            games.add(game7);
            check = 1;
        }
        else{
            for(int i = 1; i <= count; i++){
                Game game = gameDB.getGame(i);
                games.add(game);
            }
        }

        gamesview = findViewById(R.id.store_view);

        Intent intent = getIntent();
        userid = intent.getIntExtra("USER_ID_STORE", 0);

        GameAdapter gameadp =new GameAdapter(this);
        gameadp.setGames(games);
        gameadp.setUserid(userid);

        gamesview.setAdapter(gameadp);
        gamesview.setLayoutManager(new LinearLayoutManager(this));
    }
}
package com.example.project_mcs_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ProductDetailPage extends AppCompatActivity {

    TextView productname;
    TextView productmin;
    TextView productmax;
    TextView productprice;
    Button buyproduct;
    int cek = 0;
    int userid;
    int productid;
    GameDB gameDB;
    AccountDB accountDB;
    TransactionDB transactionDB;
    Account account = new Account();
    Game game = new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_page);

        productname = findViewById(R.id.gameproductname);
        productmin = findViewById(R.id.minplayer);
        productmax = findViewById(R.id.maxplayer);
        productprice = findViewById(R.id.gameproductprice);

        gameDB = new GameDB(this);
        accountDB = new AccountDB(this);
        transactionDB = new TransactionDB(this);

        Intent detail = getIntent();
        productid = detail.getIntExtra("idproduct", 0);
        userid = detail.getIntExtra("iduser", 0);

        game = gameDB.getGame(productid);
        account = accountDB.getAccount(userid);

        productname.setText(game.getGamename());
        productmin.setText("Min Player: " + String.valueOf(game.getMinplayer()));
        productmax.setText("Max Player: " + String.valueOf(game.getMaxplayer()));
        productprice.setText("Price: Rp." + String.valueOf(game.getGameprice()) + ",00");

    }
    public void BuyProduct(View view){

        if(account.getNominal() < game.getGameprice()){
            Toast.makeText(this, "You dont have enough Kuuf Cash!", Toast.LENGTH_SHORT).show();
        }
        else{
            cek++;
            accountDB.minusNominal(account, userid, game.getGameprice());
        }
        if(cek == 1){
            String transactiondate = new SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault()).format(new Date());
            Transaction transaction = new Transaction();
            transaction.setUser_id(userid);
            transaction.setProduct_id(productid);
            transaction.setTr_date(transactiondate);
            transactionDB.insertTransaction(transaction);
            finish();
        }
    }
}
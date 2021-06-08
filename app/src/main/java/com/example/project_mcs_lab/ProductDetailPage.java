package com.example.project_mcs_lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
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

    SmsManager smsManager;
    int smsPermission;

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

        smsManager = SmsManager.getDefault();
        smsPermission = ContextCompat.checkSelfPermission(ProductDetailPage.this, Manifest.permission.SEND_SMS);

        if(smsPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ProductDetailPage.this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }

        Intent detail = getIntent();
        productid = detail.getIntExtra("idproduct", 0);
        userid = detail.getIntExtra("iduser", 0);

        Toast.makeText(this, "" + productid, Toast.LENGTH_SHORT).show();

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

            Account account = accountDB.getAccount(userid);
            Game game = gameDB.getGame(productid);

            //SEND SMS
            String message = "Pembelian " + game.getGamename() + " seharga " + game.getGameprice() + " Berhasil!";
            smsManager.sendTextMessage(account.getPhonenumber(), null, message, null, null);

            finish();
        }
    }
    public void showLocation(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("USER_ID", userid);
        bundle.putInt("PRODUCT_ID", productid);
        bundle.putString("NAME", game.getGamename());
        bundle.putDouble("LONGITUDE", game.getLongitude());
        bundle.putDouble("LATITUDE", game.getLatitude());
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
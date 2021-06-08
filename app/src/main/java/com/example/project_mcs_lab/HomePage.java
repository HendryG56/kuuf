package com.example.project_mcs_lab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    TextView usergreeting;
    TextView homenominal;
    TextView notransactions;
    RecyclerView transactionsview;
    AccountDB accountDB;
    TransactionDB transactionDB;
    int user_id;
    ArrayList<Account> homeaccount = new ArrayList<>();
    ArrayList<History> history = new ArrayList<>();
    ArrayList<Account> tampung = new ArrayList<>();
    HistoryAdapter historyadp = new HistoryAdapter(this);
    public static final int STORE_DATA = 2;
    public static final int WALLET_NOMINAL = 1;
    public final static int default_key = 0;
    String tampungwallet;
    int nambah = 0;
    int plus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        usergreeting = findViewById(R.id.usergreeting);
        homenominal = findViewById(R.id.usernominal);
        notransactions = findViewById(R.id.nohistory);
        transactionsview = findViewById(R.id.th_view);
        accountDB = new AccountDB(this);
        transactionDB = new TransactionDB(this);

        Intent intenthome = getIntent();
        user_id = intenthome.getIntExtra("USER_ID", 0);

        Account account = accountDB.getAccount(user_id);
        usergreeting.setText("Welcome Back, " + (account.getUsername()));
        homenominal.setText("Rp." + account.getNominal() + ",00");


        history = transactionDB.getTransaction(user_id);

        historyadp.setHistory(history);
        transactionsview.setAdapter(historyadp);
        transactionsview.setLayoutManager(new LinearLayoutManager(this));

        historyadp.setOnItemClickListener(new HistoryAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                deletedata(position);
            }
        });

        if (history != null) {
            if (history.isEmpty()) {
                transactionsview.setVisibility(View.GONE);
                notransactions.setVisibility(View.VISIBLE);
            } else {
                transactionsview.setVisibility(View.VISIBLE);
                notransactions.setVisibility(View.GONE);
            }
        }
        else if(history == null){
            transactionsview.setVisibility(View.GONE);
            notransactions.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Account account = accountDB.getAccount(user_id);
        usergreeting.setText("Welcome Back, " + (account.getUsername()));
        homenominal.setText("Rp." + account.getNominal() + ",00");

        history = transactionDB.getTransaction(user_id);
        historyadp.setHistory(history);
        transactionsview.setAdapter(historyadp);
        transactionsview.setLayoutManager(new LinearLayoutManager(this));

        if (history != null) {
            if (history.isEmpty()) {
                transactionsview.setVisibility(View.GONE);
                notransactions.setVisibility(View.VISIBLE);
            } else {
                transactionsview.setVisibility(View.VISIBLE);
                notransactions.setVisibility(View.GONE);
            }
        }
        else if(history == null){
            transactionsview.setVisibility(View.GONE);
            notransactions.setVisibility(View.VISIBLE);
        }
    }

        public void deletedata(int position){
        int tr_tampung = history.get(position).getId_tr();
        transactionDB.deleteTransaction(tr_tampung);
        history.remove(position);
        historyadp.notifyItemRemoved(position);
        if(history.isEmpty()){
            transactionsview.setVisibility(View.GONE);
            notransactions.setVisibility(View.VISIBLE);
        }
        else{
            transactionsview.setVisibility(View.VISIBLE);
            notransactions.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.HomeButton:
                Toast.makeText(this,"Home Page", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.StoreButton:
                Intent storeintent = new Intent(this, StorePage.class);
                storeintent.putExtra("USER_ID_STORE", user_id);
                startActivity(storeintent);
                return true;
            case R.id.ProfileButton:
                nambah++;
                Intent profileintent = new Intent(this, ProfilePage.class);
                profileintent.putExtra("USER_ID_PROFILE", user_id);
                startActivity(profileintent);
                return true;
            case R.id.LogOutButton:
                Intent logoutintent = new Intent(this, MainActivity.class);
                startActivity(logoutintent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
package com.example.project_mcs_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfilePage extends AppCompatActivity {

    TextView usernameprofile;
    TextView genderprofile;
    TextView phonenumberprofile;
    TextView walletprofile;
    TextView dateofbirthprofile;
    RadioGroup nominalgroup;
    RadioButton nominal;
    Button confirmtopup;
    EditText passwordprofile;
    AccountDB accountDB;
    Account account = new Account();
    int profile_user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        usernameprofile =  findViewById(R.id.Usernameprofile);
        genderprofile =  findViewById(R.id.Genderprofile);
        phonenumberprofile =  findViewById(R.id.PhoneNumberprofile);
        walletprofile =  findViewById(R.id.Walletprofile);
        dateofbirthprofile =  findViewById(R.id.DateofBirthprofile);
        nominalgroup = findViewById(R.id.topupradiogroup);
        confirmtopup = findViewById(R.id.ConfirmTopUp);
        passwordprofile = findViewById(R.id.PasswordProfile);
        accountDB = new AccountDB(this);

        Intent profileintent = getIntent();
        profile_user_id = profileintent.getIntExtra("USER_ID_PROFILE", 0);

        account = accountDB.getAccount(profile_user_id);

        usernameprofile.setText(account.getUsername());
        genderprofile.setText(account.getGender());
        phonenumberprofile.setText(account.getPhonenumber());
        walletprofile.setText(String.valueOf(account.getNominal()));
        dateofbirthprofile.setText(account.getDateofbirth());
    }

    public void topupbutton(View view) {
        int nominalradiogroup = nominalgroup.getCheckedRadioButtonId();
        nominal = findViewById(nominalradiogroup);
    }

    public void ConfirmTopUp(View view) {
        int cek = 0;
        String Passwordprofile = passwordprofile.getText().toString();
        if(nominal == null || nominal.equals("")){
            Toast.makeText(this,"Top Up Nominal must be selected!", Toast.LENGTH_SHORT).show();
        }
        else{
            cek++;
        }
        if(!Passwordprofile.equals(account.getPassword())){
            Toast.makeText(this,"Input the right password!",Toast.LENGTH_SHORT).show();
        }
        else{
            cek++;
        }
        if(cek == 2) {
            Toast.makeText(this, "Top Up Successfully", Toast.LENGTH_SHORT).show();
            if (nominal.getText().toString().equals("Rp.250.000")) {
                accountDB.updateNominal(account, profile_user_id, 250000);
            } else if (nominal.getText().toString().equals("Rp.500.000")) {
                accountDB.updateNominal(account, profile_user_id, 500000);
            } else {
                accountDB.updateNominal(account, profile_user_id, 1000000);
            }
            finish();
        }
    }
}
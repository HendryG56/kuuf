package com.example.project_mcs_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RegisterPage extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText phonenumber;
    EditText confirmpassword;
    EditText dateofbirth;
    RadioGroup gendergroup;
    RadioButton gender;
    CheckBox agreeterm;
    Button registerout;
    DatePickerDialog.OnDateSetListener datepicker;
    String date;
    AccountDB accountDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);
        phonenumber = findViewById(R.id.PhoneNumber);
        confirmpassword = findViewById(R.id.ConfirmPassword);
        dateofbirth = findViewById(R.id.Date);
        gendergroup = findViewById(R.id.genderradiobuttongrup);
        agreeterm = findViewById(R.id.Agreeterm);
        registerout = findViewById(R.id.RegisterOut);

        accountDB = new AccountDB(this);

        Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);


        dateofbirth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog datepickerdialog = new DatePickerDialog(RegisterPage.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        date = day+"/"+month+"/"+year;
                        dateofbirth.setText(date);
                    }
                },year,month,day);
                datepickerdialog.show();
            }
        });
    }

    public void genderbutton(View view) {
        int genderradiogroup = gendergroup.getCheckedRadioButtonId();
        gender = findViewById(genderradiogroup);
    }

    public void RegisterAccount(View view) {
        String Username = username.getText().toString();
        String Password = password.getText().toString();
        String Confirmpassword = confirmpassword.getText().toString();
        String Phonenumber = phonenumber.getText().toString();
        String Dateofbirth = dateofbirth.getText().toString();
        int count = 0;
        int cek = 0;
        int cek2 = 0;

        if(Username.length() < 6 || Username.length() > 12){
            Toast.makeText(this,"Username must be between 6 and 12 characters!", Toast.LENGTH_SHORT).show();
        }
        else{
            count++;
        }
        if(Password.length() <= 8){
            Toast.makeText(this,"Password must be more than 8 characters!", Toast.LENGTH_SHORT).show();
        }
        else{
            count++;
        }
        for(int a = 0; a < Password.length(); a++) {
            if (!(Password.charAt(a) >= 'A' && Password.charAt(a) <= 'Z') && !(Password.charAt(a) >= 'a' && Password.charAt(a) <= 'z') && !(Password.charAt(a) >= '0' && Password.charAt(a) <= '9')){
                cek = 1;
            }
        }
        if(cek == 1){
            Toast.makeText(this, "Password must contains only alphanumeric!", Toast.LENGTH_SHORT).show();
        }
        else{
            count++;
        }
        if(Phonenumber.length() < 10 || Phonenumber.length() > 12){
            Toast.makeText(this,"Phone number must be between 10 and 12 digits!", Toast.LENGTH_SHORT).show();
        }
        else{
            count++;
        }
        for(int a = 0; a < Phonenumber.length(); a++) {
            if (!(Phonenumber.charAt(a) >= '0' && Phonenumber.charAt(a) <= '9')){
                cek2 = 1;
            }
        }
        if(cek2 == 1){
            Toast.makeText(this,"Phone number must contains only numbers!", Toast.LENGTH_SHORT).show();
        }
        else{
            count++;
        }
        if(!Confirmpassword.equals(Password)){
            Toast.makeText(this,"Confirm password must be the same with password!", Toast.LENGTH_SHORT).show();
        }
        else{
            count++;
        }
        if(Dateofbirth == null || Dateofbirth.equals("")){
            Toast.makeText(this,"Date of Birth must be filled!", Toast.LENGTH_SHORT).show();
        }
        else{
            count++;
        }
        if(gender == null || gender.equals("")){
            Toast.makeText(this,"Gender must be selected!", Toast.LENGTH_SHORT).show();
        }
        else{
            count++;
        }
        if(agreeterm.isChecked()){
            count++;
        }
        else{
            Toast.makeText(this,"Must agree for terms and conditions!", Toast.LENGTH_SHORT).show();
        }
        if(count == 9){
            Account account = new Account();
            account.setUsername(Username);
            account.setPassword(Password);
            account.setPhonenumber(Phonenumber);
            account.setDateofbirth(Dateofbirth);
            account.setGender(gender.getText().toString());
            account.setNominal(0);

            accountDB.insertAccount(account);

            Toast.makeText(this,"Register Successfull!", Toast.LENGTH_SHORT).show();

            //Kembali ke Login Screen
            finish();
        }
    }

    public void LoginAccount(View view) {
        Intent intentregister = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intentregister);
        finish();
    }
}
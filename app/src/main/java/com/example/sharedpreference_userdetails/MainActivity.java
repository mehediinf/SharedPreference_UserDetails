package com.example.sharedpreference_userdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView detailsTxetView;
    private EditText usernameEditText,passwordEditText;
    private Button saveButton,loadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detailsTxetView = findViewById(R.id.detailsTextView_Id);
        usernameEditText = findViewById(R.id.userNameEditText_Id);
        passwordEditText = findViewById(R.id.passwordEditText_Id);
        saveButton = findViewById(R.id.saveButton_Id);
        loadButton = findViewById(R.id.loadButton_Id);


        saveButton.setOnClickListener(this);
        loadButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.saveButton_Id){

            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            //Writing data

            if (username.equals("") || password.equals("")){

                Toast.makeText(getApplicationContext(),"Please enter some data",Toast.LENGTH_SHORT).show();
            }
            else {
                SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usernameKey",username);
                editor.putString("passwordKey",password);
                editor.commit();


                usernameEditText.setText("");
                passwordEditText.setText("");


                Toast.makeText(getApplicationContext(),"Data is stored successfully",Toast.LENGTH_SHORT).show();

            }


        }
        else if (v.getId()==R.id.loadButton_Id){

            //to read data
            SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);

            if (sharedPreferences.contains("usernameKey") && sharedPreferences.contains("passwordKey")){

                String username = sharedPreferences.getString("usernameKey","Data Not Found");
                String password = sharedPreferences.getString("passwordKey","Data Not Found");

                detailsTxetView.setText(username+"\n"+password);

            }
            
        }




    }
}
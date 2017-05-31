package com.b_healty.john.prototype1;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.b_healty.john.prototype1.models.Users;


public class LoginActivity extends AppCompatActivity {

    EditText name;
    Button login;

   // DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);

    DBHandler dbHandler;
    Users users = new Users();
    MainActivity mainActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //mainActivity.finish();

        login = (Button) findViewById(R.id.button);
        name = (EditText) findViewById(R.id.editText2);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = name.getText().toString();
                users.setName(message);
                dbHandler.addUser(users);



                mainActivity.printName();

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }





}

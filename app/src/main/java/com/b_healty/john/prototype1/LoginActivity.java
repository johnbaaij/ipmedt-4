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
    MainActivity mainActivity;


    public LoginActivity() {
        this.dbHandler = new DBHandler(this, null, null, 1);
    }

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

                Users users = new Users();
                String message = name.getText().toString();
                users.setName(message);
                dbHandler.addUser(users);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                Bundle b = new Bundle();
                b.putString("name", message); 	// Your id
                intent.putExtras(b); 	// Put your id to your next Intent
                startActivity(intent);	// start

                //mainActivity.printName();

                //startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }





}

package com.b_healty.john.prototype1;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.b_healty.john.prototype1.models.Users;


public class LoginActivity extends AppCompatActivity {

    EditText name;
    Button login;

   // DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);

    DBHandler dbHandler;
    MainActivity mainActivity;
    RadioButton radioButton;
    RadioGroup radioGroup;


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

        name.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String message = name.getText().toString();


                if (message.isEmpty()){

                    Toast.makeText(getApplicationContext(), "Voer je naam in", Toast.LENGTH_LONG).show();


                }

                else {

                    //int selectedId = radioGroup.getCheckedRadioButtonId();

                    radioButton = (RadioButton) findViewById(R.id.radioButtonM);
                    String gender;


                    if(radioButton.isChecked())
                    {

                        gender = "man";
                    }
                    else
                    {
                        gender = "vrouw";
                    }

                    //Toast.makeText(getApplicationContext(), gender, Toast.LENGTH_LONG).show();


                    Users users = new Users();
                    users.setName(message);
                    users.setGender(gender);
                    dbHandler.addUser(users);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Bundle b = new Bundle();
                    b.putString("name", message); 	// Your id
                    intent.putExtras(b); 	// Put your id to your next Intent
                    startActivity(intent);	// start
                }




                //mainActivity.printName();

                //startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }

}

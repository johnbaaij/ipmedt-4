package com.b_healty.john.prototype1;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.b_healty.john.prototype1.generalClasses.DatabaseHelper;
import com.b_healty.john.prototype1.generalClasses.DatabaseInfo;


public class LoginActivity extends AppCompatActivity {

    EditText name;
    Button login;

    DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.button);
        name = (EditText) findViewById(R.id.editText2);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = name.getText().toString();



                ContentValues values = new ContentValues();
                values.put(DatabaseInfo.UserColumn.NAME, message);


                // INSERT dit values object in DE (ZELFGEMAAKTE) RIJ COURSE,
                dbHelper.insert(DatabaseInfo.UserTable.USER, null, values);



            }
        });
    }





}

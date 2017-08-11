package com.remake.creview;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.UriMatcher;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateUserActivity extends AppCompatActivity implements View.OnClickListener{

    EditText name;
    EditText email;
    EditText phone;
    EditText address;
    Button button;

    Users user;
    UserAdapter adapter;
    ArrayList<Users> userList;
    boolean updateMode;
    ContentResolver resolver;

    public void initViews(){
        name = (EditText) findViewById(R.id.editTextName);
        email = (EditText) findViewById(R.id.editTextEmail);
        phone = (EditText) findViewById(R.id.editTextPhone);
        address = (EditText) findViewById(R.id.editTextAddress);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        resolver = getContentResolver();
//        adapter = new UserAdapter(this,R.layout.card_view,userList);
        Intent rcv = getIntent();
        updateMode = rcv.hasExtra(Utils.KEY_CUSTOMER);
        if(updateMode){
            user = (Users)rcv.getSerializableExtra(Utils.KEY_CUSTOMER);

            name.setText(user.getName());
            email.setText(user.getEmail());
            phone.setText(user.getPhone());
            address.setText(user.getAddress());

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        initViews();


    }

    @Override
    public void onClick(View v) {
        user.setName(name.getText().toString().trim());
        user.setEmail(email.getText().toString().trim());
        user.setPhone(phone.getText().toString().trim());
        user.setAddress(address.getText().toString().trim());

        updateUser();

       /* Intent intent = new Intent(UpdateUserActivity.this,AllUsersActivity.class);
        startActivity(intent);*/
    }
    public void updateUser(){
        ContentValues values = new ContentValues();
        values.put(Utils.COL_NAME,user.getName());
        values.put(Utils.COL_EMAIL,user.getEmail());
        values.put(Utils.COL_PHONE,user.getPhone());
        values.put(Utils.COL_ADDRESS,user.getAddress());

        String where = Utils.COL_ID+" = "+user.getId();
        int i = resolver.update(Utils.USER_URI,values,where,null);
        if(i>0){
            //adapter.notifyDataSetChanged();
            Intent data = new Intent();
            data.putExtra("keyUpdate",user);

            setResult(102,data);
            finish();
        }
    }
}

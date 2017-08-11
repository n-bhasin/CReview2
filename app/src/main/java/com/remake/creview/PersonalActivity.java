package com.remake.creview;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

public class PersonalActivity extends AppCompatActivity implements View.OnClickListener,RatingBar.OnRatingBarChangeListener{

    EditText name;
    EditText email;
    EditText phone;
    EditText address;
    Button button;
    RatingBar ratingBar;

    Users users;
    Intent intent;
    ContentResolver resolver;
    String quality;
    String service;
    String cleaniness;
    public void initViews(){
        name = (EditText) findViewById(R.id.editTextName);
        email = (EditText) findViewById(R.id.editTextEmail);
        phone = (EditText) findViewById(R.id.editTextPhone);
        address = (EditText) findViewById(R.id.editTextAddress);
        button = (Button) findViewById(R.id.button);
        ratingBar = (RatingBar)findViewById(R.id.rating);

        users = new Users();
        intent = getIntent();
        resolver = getContentResolver();
        quality = intent.getStringExtra("keyQuality");
        service = intent.getStringExtra("keyService");
        cleaniness = intent.getStringExtra("keyClean");
        button.setOnClickListener(this);
        ratingBar.setOnRatingBarChangeListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        initViews();
    }

    @Override
    public void onClick(View v) {

        if (name.getText().toString().trim().equals("")){
            name.setError("Enter Valid Name!");
        }

        else if(phone.getText().toString().trim().equals("")) {
            phone.setError("Enter Valid Phone Number!");

        }
        else if(phone.length()<10  || phone.length()>10){
            phone.setError("Please Fill the Correct Number!");
        }
        else if (address.getText().toString().trim().equals("")){
            address.setError("Enter valid Address!");
        }
        else{
            users.setName(name.getText().toString().trim());
            users.setEmail( email.getText().toString().trim());
            users.setPhone(phone.getText().toString().trim());
            users.setAddress(address.getText().toString().trim());
            users.setQuality(quality);
            users.setService(service);
            users.setCleaniness(cleaniness);
           /* intent.putExtra("keyName",users.getName());
            intent.putExtra("keyEmail",users.getEmail());
            intent.putExtra("keyPhone",users.getPhone());
            intent.putExtra("keyAddress",users.getAddress());
            intent.putExtra("keyDob",users.getDob());
            intent.putExtra("keyAniv",users.getAnivdate());
            intent.putExtra("keyCity",users.getCity());
            startActivity(intent);*/
            insert();
            showDialog();
            clearField();
        }
    }

    void clearField(){
        name.setText("");
        email.setText("");
        phone.setText("");
        address.setText("");
        ratingBar.setRating(0F);
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        users.setRatings(rating);
    }

    public void insert(){
        ContentValues values = new ContentValues();
        values.put(Utils.COL_NAME,users.getName());
        values.put(Utils.COL_EMAIL,users.getEmail());
        values.put(Utils.COL_PHONE,users.getPhone());
        values.put(Utils.COL_ADDRESS,users.getAddress());
        values.put(Utils.COL_QUALITY,users.getQuality());
        values.put(Utils.COL_SERVICE,users.getService());
        values.put(Utils.COL_CLEANINESS,users.getCleaniness());
        values.put(Utils.COL_RATING,users.getRatings());

        Uri uri = resolver.insert(Utils.USER_URI,values);
    }
    void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thank You");
        builder.setMessage("Thank Your for your valuable Feedback and gracious time to review our service.We are sincerely concerned and apologize if we fell short on your expectations in some areas.");
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(PersonalActivity.this,ServiceMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.create().show();
    }
}

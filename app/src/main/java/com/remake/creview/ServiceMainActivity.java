package com.remake.creview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class ServiceMainActivity extends AppCompatActivity implements View.OnClickListener{

    RadioButton qualityExcellent;
    RadioButton qualityGood;
    RadioButton qualityAverage;
    RadioButton serviceExcellent;
    RadioButton serviceGood;
    RadioButton serviceAverage;
    RadioButton cleanExcellent;
    RadioButton cleanGood;
    RadioButton cleanAverage;
    Button button;

    Users users;
    Intent intentSend,Custintent;
    public void initViews(){

        qualityExcellent = (RadioButton)findViewById(R.id.radioButtonQE);
        qualityGood = (RadioButton)findViewById(R.id.radioButtonQG);
        qualityAverage = (RadioButton)findViewById(R.id.radioButtonQA);
        serviceExcellent = (RadioButton)findViewById(R.id.radioButtonSE);
        serviceGood = (RadioButton)findViewById(R.id.radioButtonSG);
        serviceAverage = (RadioButton)findViewById(R.id.radioButtonSA);
        cleanExcellent = (RadioButton)findViewById(R.id.radioButtonCE);
        cleanGood = (RadioButton)findViewById(R.id.radioButtonCG);
        cleanAverage = (RadioButton)findViewById(R.id.radioButtonCA);
        button = (Button)findViewById(R.id.buttonNext);

        users = new Users();

        qualityExcellent.setOnClickListener(this);
        qualityGood.setOnClickListener(this);
        qualityAverage.setOnClickListener(this);
        serviceExcellent.setOnClickListener(this);
        serviceGood.setOnClickListener(this);
        serviceAverage.setOnClickListener(this);
        cleanExcellent.setOnClickListener(this);
        cleanGood.setOnClickListener(this);
        cleanAverage.setOnClickListener(this);
        button.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main);

        initViews();
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            case R.id.buttonNext:

                intentSend = new Intent(ServiceMainActivity.this,PersonalActivity.class);
                intentSend.putExtra("keyQuality",users.getQuality());
                intentSend.putExtra("keyService",users.getService());
                intentSend.putExtra("keyClean",users.getCleaniness());
                startActivity(intentSend);

                clearFlieds();

                break;
            case R.id.radioButtonQE:
                users.setQuality(qualityExcellent.getText().toString().trim());
                break;
            case R.id.radioButtonQG:
                users.setQuality(qualityGood.getText().toString().trim());
                break;
            case R.id.radioButtonQA:
                users.setQuality(qualityAverage.getText().toString().trim());
                break;
            case R.id.radioButtonSE:
                users.setService(serviceExcellent.getText().toString().trim());
                break;
            case R.id.radioButtonSG:
                users.setService(serviceGood.getText().toString().trim());
                break;
            case R.id.radioButtonSA:
                users.setService(serviceAverage.getText().toString().trim());
                break;
            case R.id.radioButtonCE:
                users.setCleaniness(cleanExcellent.getText().toString().trim());
                break;
            case R.id.radioButtonCG:
                users.setCleaniness(cleanGood.getText().toString().trim());
                break;
            case R.id.radioButtonCA:
                users.setCleaniness(cleanAverage.getText().toString().trim());
                break;
        }

    }

    void clearFlieds(){
        qualityExcellent.setChecked(false);
        qualityGood.setChecked(false);
        qualityAverage.setChecked(false);
        serviceExcellent.setChecked(false);
        serviceGood.setChecked(false);
        serviceAverage.setChecked(false);
        cleanExcellent.setChecked(false);
        cleanGood.setChecked(false);
        cleanAverage.setChecked(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.customers){
            Custintent = new Intent(ServiceMainActivity.this,AllUsersActivity.class);
            startActivity(Custintent);
        }
        return super.onOptionsItemSelected(item);
    }


    /*public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this,"Press Twice To destroy The Activity..",Toast.LENGTH_LONG).show();
    }*/
}

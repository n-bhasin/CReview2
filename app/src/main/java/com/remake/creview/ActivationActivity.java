package com.remake.creview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivationActivity extends AppCompatActivity {

    EditText proKey;
    Button btnSubmit;
    String ProductKey = "bhasin_neeraj_28_creviews";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activation);

        proKey = (EditText)findViewById(R.id.editTextProKey);
        btnSubmit = (Button)findViewById(R.id.buttonSubmit);

        sharedPreferences = getSharedPreferences("activate",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = proKey.getText().toString().trim();
                if(key.equals(ProductKey)){
                    Intent intent = new Intent(ActivationActivity.this,ServiceMainActivity.class);
                    startActivity(intent);
                    editor.putString("keyId",ProductKey);
                    editor.commit();
                    finish();
                }
                else{
                    Snackbar.make(v,"   Please Enter The Valid Product Key!",Snackbar.LENGTH_LONG).show();
                }

            }
        });
    }

}
